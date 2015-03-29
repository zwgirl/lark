(function(){

	var	
		isEmpty = function(it){
			for(var p in it){
				return 0;
			}
			return 1;
		},

		toString = {}.toString,

		isFunction = function(it){
			return toString.call(it) == "[object Function]";
		},

		isString = function(it){
			return toString.call(it) == "[object String]";
		},

		forEach = function(vector, callback){
			if(vector){
				for(var i = 0; i < vector.length;){
					callback(vector[i++]);
				}
			}
		},

		mix = function(dest, src){
			for(var p in src){
				dest[p] = src[p];
			}
			return dest;
		},

		makeError = function(error, info){
			return mix(new Error(error), {src:"dojoLoader", info:info});
		},

		uidSeed = 1,

		uid = function(){
			// Returns a unique identifier (within the lifetime of the document) of the form /_d+/.
			return "_" + uidSeed++;
		},

		// FIXME: how to doc window.require() api

		// this will be the global require function; define it immediately so we can start hanging things off of it
		req = function(
			config,		  //(object, optional) hash of configuration properties
			dependencies, //(array of commonjs.moduleId, optional) list of modules to be loaded before applying callback
			callback	  //(function, optional) lambda expression to apply to module values implied by dependencies
		){
			return contextRequire(config, dependencies, callback, req);
		},

		// the loader uses the has.js API to control feature inclusion/exclusion; define then use throughout
		global = this,

		doc = global.document;

//		element = doc && doc.createElement("DiV");
//
/*		has = req.has = function(name){
			return isFunction(hasCache[name]) ? (hasCache[name] = hasCache[name](global, doc, element)) : hasCache[name];
		};*/

	//
	// define the loader data
	//

	// the loader will use these like symbols if the loader has the traceApi; otherwise
	// define magic numbers so that modules can be provided as part of defaultConfig
	var	requested = 1,
		arrived = 2,
		executing = 4,
		executed = 5;

	// configuration machinery; with an optimized/built defaultConfig, all configuration machinery can be discarded
	// lexical variables hold key loader data structures to help with minification; these may be completely,
	// one-time initialized by defaultConfig for optimized/built versions
	var
		modules = {},

		insertPointSibling
			// the nodes used to locate where scripts are injected into the document
			= 0;


	// build the loader machinery iaw configuration, including has feature tests
	var	injectDependencies = function(module){
			// checkComplete!=0 holds the idle signal; we're not idle if we're injecting dependencies
			guardCheckComplete(function(){
				forEach(module.deps, injectModule);
			});
		},

		contextRequire = function(a1, a2, a3, contextRequire){
			var module, syntheticMid;
//			if(arguments.length == 4){
//				module = getModule(a1);
//				if(module && module.executed){
//					return module.result;
//				}
//			}

			syntheticMid = "require*" + uid();

			// resolve the request list with respect to the reference module
			for(var mid, deps = [], i = 0; i < a2.length;){
				mid = a2[i++];
				deps.push(getModule(mid));
			}

			// construct a synthetic module to control execution of the requestList, and, optionally, callback
			module = mix(makeModuleInfo(syntheticMid), {
				injected: arrived,
				deps: deps,
				def: a3
			});
			modules[module.mid] = module;

			// checkComplete!=0 holds the idle signal; we're not idle if we're injecting dependencies
			injectDependencies(module);

			// try to immediately execute
			// if already traversing a factory tree, then strict causes circular dependency to abort the execution; maybe
			// it's possible to execute this require later after the current traversal completes and avoid the circular dependency.
			// ...but *always* insist on immediate in synch mode
//			var strict = checkCompleteGuard && legacyMode!=sync;
			guardCheckComplete(function(){
				execModule(module/*, strict*/);
			});
			if(!module.executed){
				// some deps weren't on board or circular dependency detected and strict; therefore, push into the execQ
				execQ.push(module);
			}
			checkComplete();
			return contextRequire;
		},

		execQ =
			// The list of modules that need to be evaluated.
			[],

		defQ =
			// The queue of define arguments sent to loader.
			[],

		waiting =
			// The set of modules upon which the loader is waiting for definition to arrive
			{},

		setRequested = function(module){
			module.injected = requested;
			waiting[module.mid] = 1;
			startTimer();
		},

		setArrived = function(module){
			module.injected = arrived;
			delete waiting[module.mid];
			if(isEmpty(waiting)){
				clearTimer();
			}
		},

		execComplete = req.idle =
			function(){
				return !defQ.length && isEmpty(waiting) && !execQ.length && !checkCompleteGuard;
			},


		makeModuleInfo = function(mid){
			return {mid:mid, executed:0, def:0};
		},

		getModule = function(mid){
			// compute and optionally construct (if necessary) the module implied by the mid with respect to referenceModule
			return modules[mid] || (modules[mid] = makeModuleInfo(mid));
		},

		runFactory = function(module, args){
			req.trace("loader-run-factory", [module.mid]);
			var factory = module.def, result;
			result= isFunction(factory) ? factory.apply(null, args) : factory;
			module.result = result===undefined && module.cjs ? module.cjs.exports : result;
		},

		abortExec = {},

		defOrder = 0,

		finishExec = function(module){
			req.trace("loader-finish-exec", [module.mid]);
			module.executed = executed;
			module.defOrder = defOrder++;
			// remove all occurrences of this module from the execQ
			for(var i = 0; i < execQ.length;){
				if(execQ[i] === module){
					execQ.splice(i, 1);
				}else{
					i++;
				}
			}
			// delete references to synthetic modules
			if (/^require\*/.test(module.mid)) {
				delete modules[module.mid];
			}
		},

		execModule = function(module){
			// run the dependency vector, then run the factory for module
			if(module.executed === executing){
				return abortExec;
			}
			// at this point the module is either not executed or fully executed


			if(!module.executed){
				if(!module.def){
					return abortExec;
				}
				var deps = module.deps || [],
					arg, argResult,
					args = [],
					i = 0;

				// for circular dependencies, assume the first module encountered was executed OK
				// modules that circularly depend on a module that has not run its factory will get
				// the pre-made cjs.exports===module.result. They can take a reference to this object and/or
				// add properties to it. When the module finally runs its factory, the factory can
				// read/write/replace this object. Notice that so long as the object isn't replaced, any
				// reference taken earlier while walking the deps list is still valid.
				module.executed = executing;
				while((arg = deps[i++])){
					argResult = execModule(arg);
					if(argResult === abortExec){
						module.executed = 0;
//						req.trace("loader-exec-module", ["abort", mid]);
//						has("dojo-trace-api") && circleTrace.pop();
						return abortExec;
					}
					args.push(argResult);
				}
				runFactory(module, args);
				finishExec(module);
			}
			// at this point the module is guaranteed fully executed

			return module.result;
		},


		checkCompleteGuard = 0,

		guardCheckComplete = function(proc){
			try{
				checkCompleteGuard++;
				proc();
			}finally{
				checkCompleteGuard--;
			}
			if(execComplete()){
//				signal("idle", []);
			}
		},

		checkComplete = function(){
			// keep going through the execQ as long as at least one factory is executed
			// plugins, recursion, cached modules all make for many execution path possibilities
			if(checkCompleteGuard){
				return;
			}
			guardCheckComplete(function(){
				for(var currentDefOrder, module, i = 0; i < execQ.length;){
					currentDefOrder = defOrder;
					module = execQ[i];
					execModule(module);
					if(currentDefOrder!=defOrder){
						// defOrder was bumped one or more times indicating something was executed (note, this indicates
						// the execQ was modified, maybe a lot (for example a later module causes an earlier module to execute)
						i = 0;
					}else{
						// nothing happened; check the next module in the exec queue
						i++;
					}
				}
			});
		};

		var injectingModule = 0,

			injectModule = function(module){
				// Inject the module. In the browser environment, this means appending a script element into
				// the document; in other environments, it means loading a file.
				//
				// If in synchronous mode, then get the module synchronously if it's not xdomainLoading.

				var mid = module.mid;
				if(module.executed || module.injected || waiting[mid]){
					return;
				}
				setRequested(module);

				var onLoadCallback = function(){
					runDefQ(module);
					checkComplete();
				};
				injectingModule = module;
				console.log("injectModule injectUrl 1");
				req.injectUrl(mid, onLoadCallback, module);
				console.log("after injectModule injectUrl");
				injectingModule = 0;
			},

			defineModule = function(module, deps, def){
				mix(module, {
					deps: deps,
					def: def
				});

				// resolve deps with respect to this module
				for(var i = 0; deps[i]; i++){
					deps[i] = getModule(deps[i]);
				}
				setArrived(module);

				if(!isFunction(def) && !deps.length){
					module.result = def;
					finishExec(module);
				}

				return module;
			},

			runDefQ = function(referenceModule){
				// defQ is an array of [id, dependencies, factory]
				var definedModules = [],
					module, args;
				while(defQ.length){
					args = defQ.shift();
					module = (args[0] && getModule(args[0]));
					definedModules.push([module, args[1], args[2]]);
				}
				forEach(definedModules, function(args){
					injectDependencies(defineModule.apply(null, args));
				});
			};

	var timerId = 0,
		clearTimer = function(){
			timerId && clearTimeout(timerId);
			timerId = 0;
		},

		startTimer = function(){
			clearTimer();
			if(req.waitms){
				timerId = global.setTimeout(function(){
					clearTimer();
					signal(error, makeError("timeout", waiting));
				}, req.waitms);
			}
		};
		var domOn = function(node, eventName, ieEventName, handler){
				node.addEventListener(eventName, handler, false);
				return function(){
					node.removeEventListener(eventName, handler, false);
				};
			},
			windowOnLoadListener = domOn(window, "load", "onload", function(){
				req.pageLoaded = 1;
				doc.readyState!="complete" && (doc.readyState = "complete");
				windowOnLoadListener();
			});

			var scripts = doc.getElementsByTagName("script"),
				i = 0,
				script;
			while(!insertPointSibling){
				if(!/^dojo/.test((script = scripts[i++]) && script.type)){
					insertPointSibling= script;
				}
			}

			req.injectUrl = function(url, callback, owner){
				// insert a script element to the insert-point element with src=url;
				// apply callback upon detecting the script has loaded.

				var node = owner.node = doc.createElement("script"),
					onLoad = function(e){
						e = e || window.event;
						var node = e.target || e.srcElement;
						if(e.type === "load" || /complete|loaded/.test(node.readyState)){
							loadDisconnector();
							errorDisconnector();
							console.log("injectUrl callback! 4");
							callback && callback();
						}
					},
					loadDisconnector = domOn(node, "load", "onreadystatechange", onLoad),
					errorDisconnector = domOn(node, "error", "onerror", function(e){
						loadDisconnector();
						errorDisconnector();
//						signal(error, makeError("scriptError", [url, e]));
					});

				node.type = "text/javascript";
				node.charset = "utf-8";
				node.src = url;
				console.log("before parentNode.insertBefore! 2");
				insertPointSibling.parentNode.insertBefore(node, insertPointSibling);
				console.log("after parentNode.insertBefore! 3");
				return node;
			};

		req.log = function(){
			try{
				for(var i = 0; i < arguments.length; i++){
					console.log(arguments[i]);
				}
			}catch(e){}
		};

		var trace = req.trace = function(
			group,	// the trace group to which this application belongs
			args	// the contents of the trace
		){
//			///
//			// Tracing interface by group.
//			//
//			// Sends the contents of args to the console iff (req.trace.on && req.trace[group])
//
//			if(trace.on && trace.group[group]){
//				signal("trace", [group, args]);
//				for(var arg, dump = [], text= "trace:" + group + (args.length ? (":" + args[0]) : ""), i= 1; i<args.length;){
//					arg = args[i++];
//					if(isString(arg)){
//						text += ", " + arg;
//					}else{
//						dump.push(arg);
//					}
//				}
//				req.log(text);
//				dump.length && dump.push(".");
//				req.log.apply(req, dump);
//			}
		};
//		mix(trace, {
//			on:1,
//			group:{},
//			set:function(group, value){
//				if(isString(group)){
//					trace.group[group]= value;
//				}else{
//					mix(trace.group, group);
//				}
//			}
//		});
//		trace.set(mix(mix(mix({}, defaultConfig.trace), userConfig.trace), dojoSniffConfig.trace));
//		on("config", function(config){
//			config.trace && trace.set(config.trace);
//		});

	var def = function(
		mid,		  //(commonjs.moduleId, optional)
		dependencies, //(array of commonjs.moduleId, optional) list of modules to be loaded before running factory
		factory		  //(any)
	){
		args = [mid, dependencies, factory];
		defQ.push(args);
	};

//	if(has("dojo-requirejs-api")){
		req.def = def;
//	}

//	// now that req is fully initialized and won't change, we can hook it up to the error signal
//	on(error, function(arg){
//		try{
//			console.error(arg);
//			if(arg instanceof Error){
//				for(var p in arg){
//					console.log(p + ":", arg[p]);
//				}
//				console.log(".");
//			}
//		}catch(e){}
//	});

	global.define = def;
	global.require = req;
})
();

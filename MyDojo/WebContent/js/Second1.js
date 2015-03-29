/**
 * Type
 */
(function(){
	if(!window.__cache){
		window.__cache = {};
	}
	
	window.__cache["1"] = "121212121212121";
	
	console.log("execute Second.js!");
	return function(){
		console.log("this is my Second!");
	};  
})();
package org.summer.sdt.internal.compiler.javascript;

import java.util.HashSet;
import java.util.Set;

import org.summer.sdt.core.compiler.CharOperation;
/**
 * 
 * @author cym
 * @since 2014-12-19
 */
public class JsScope {
	private static final int THREASHOLD = 1000;
	private final JsScope parent;
	private Set<char[]> variables = new HashSet<char[]>();
	public JsScope(JsScope parent){
		this.parent = parent;
	}
	
	public JsScope getParent() {
		return parent;
	}
	
	public boolean isExists(char[] variable){
		if(variables.contains(variable)){
			return true;
		}
		
		if(parent == null){
			return false;
		}
		return parent.isExists(variable);
	}
	
	public char[] createVariable(char[] feed){
		if(!isExists(feed)){
			return feed;
		}
		
		for(int index = 0; index < THREASHOLD; index ++){
			char[] candidate = CharOperation.concat(feed, String.valueOf(index).toCharArray());
			if(!isExists(candidate)){
				return candidate;
			}
		}
		
		throw new RuntimeException("createVariable");
	}
	
}

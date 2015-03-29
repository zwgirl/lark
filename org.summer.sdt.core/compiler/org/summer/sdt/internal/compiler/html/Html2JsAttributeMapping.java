package org.summer.sdt.internal.compiler.html;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cym
 */
public final class Html2JsAttributeMapping {
	final static Map<String, String> html2js = new HashMap<String, String>();
	final static Map<String, String> js2html = new HashMap<String, String>();
	static{
		html2js.put("http-equiv", "httpEquiv");
		html2js.put("for", "htmlFor");
		html2js.put("class", "className");
		
		js2html.put("httpEquiv", "http-equiv");
		js2html.put("htmlFor", "for");
		js2html.put("className", "class");
	}
	
	public static String getJsAttributeName(String htmlAttr){
		String result = html2js.get(htmlAttr);
		return result == null ? htmlAttr : result;
	}
	
	public static String getHtmlAttributeName(String jsAttr){
		String result = js2html.get(jsAttr);
		return result == null ? jsAttr : result;
	}
}

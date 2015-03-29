package org.summer.sdt.internal.compiler.javascript;


import java.util.LinkedHashMap;

import org.summer.sdt.internal.compiler.ast.AllocationExpression;
import org.summer.sdt.internal.compiler.ast.Argument;
import org.summer.sdt.internal.compiler.ast.FieldReference;
import org.summer.sdt.internal.compiler.ast.LocalDeclaration;
import org.summer.sdt.internal.compiler.ast.MessageSend;
import org.summer.sdt.internal.compiler.ast.MethodDeclaration;
import org.summer.sdt.internal.compiler.ast.NameReference;

public class JsGenerator {
	private StringBuffer contents = new StringBuffer();
	
	private Line current;
	
	private int column = 0;
	
	  /**
	   * A map of source names to source name index
	   */
	  private LinkedHashMap<String, Integer> originalNameMap = new LinkedHashMap<String, Integer>();

	public JsGenerator append(NameReference name, boolean requireMap) {
		current.add(new Mapping());
		column += name.sourceEnd - name.sourceEnd;
		return this;
	}

	public JsGenerator append(char[] token, FilePosition pos,
			boolean requireMap) {
		// TODO Auto-generated method stub
		return this;
	}

	public JsGenerator append(MessageSend invocation, boolean requireMap) {
		// TODO Auto-generated method stub
		return this;
	}

	public JsGenerator append(Argument argument, boolean requireMap) {
		// TODO Auto-generated method stub
		return this;
	}

	public JsGenerator append(AllocationExpression allocation,
			boolean requireMap) {
		// TODO Auto-generated method stub
		return this;
	}

	public JsGenerator append(LocalDeclaration local, boolean requireMap) {
		// TODO Auto-generated method stub
		return this;
	}

	public JsGenerator append(MethodDeclaration method, boolean requireMap) {
		// TODO Auto-generated method stub
		return this;
	}

	public JsGenerator append(FieldReference field, boolean requireMap) {
		column += name.sourceEnd - name.sourceEnd;
		return this;
	}

	public JsGenerator newLine() {
		current = new Line();
		column = 0;
		return this;
	}

	public JsGenerator indent(int indent) {
		for(int i = 0; i < indent; i++){
			contents.append('\t');
			column++;
		}
		return this;
	}

	public JsGenerator whitespace(int count) {
		for(int i = 0; i < count; i++){
			contents.append('\t');
			column++;
		}
		return this;
	}

	public JsGenerator append(char[] token) {
		column += token.length;
		return this;
	}

}

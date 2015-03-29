package org.summer.sdt.internal.compiler.ast;

import org.summer.sdt.internal.compiler.lookup.Scope;

public class XAMLComment extends XAMLElement{
	public char[] source;

	public XAMLComment(char[] source, long pos) {
		super();
		this.source = source;
		//by default the position are the one of the field (not true for super access)
		this.sourceStart = (int) (pos >>> 32);
		this.sourceEnd = (int) (pos & 0x00000000FFFFFFFFL);
	}
	@Override
	protected void printTagName(int indent, StringBuffer output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected StringBuffer doGenerateExpression(Scope scope, int indent, StringBuffer output) {
		return output;
	}

}

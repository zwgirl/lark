package org.summer.sdt.internal.compiler.ast;

import org.summer.sdt.internal.compiler.impl.Constant;
import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.ElementScope;
import org.summer.sdt.internal.compiler.lookup.MethodScope;
import org.summer.sdt.internal.compiler.lookup.Scope;

public class Script extends XAMLElement{
	public Statement[] statements;
//	public int lastVisibleFieldID;
//	public int bodyStart;
//	public int bodyEnd;
	
	public int modifier;
	public Script(Statement[] statements, int modifiers) {
		this.statements = statements;
		this.modifier = modifiers;

		if (statements != null && statements.length>0) {
			this.declarationSourceStart = this.sourceStart = statements[0].sourceStart;
		}
	}

	@Override
	protected void printTagName(int indent, StringBuffer output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected StringBuffer doGenerateExpression(Scope scope, int indent, StringBuffer output) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void resolve(ElementScope scope) {
		this.constant = Constant.NotAConstant;
	}
	
	@Override
	public void resolve(BlockScope scope) {
		MethodScope classScope = scope.classScope().referenceContext.staticInitializerScope;
		if(this.statements != null){
			for(Statement statement : this.statements){
				statement.resolve(classScope);
			}
		}
	}

}

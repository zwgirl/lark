package org.summer.sdt.internal.compiler.ast;

import org.summer.sdt.internal.compiler.codegen.CodeStream;
import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.Scope;

public class AttributeElement extends XAMLElement{
	public AttributeElement() {
		super();
	}

	public PropertyReference property;

	@Override
	public void generateCode(BlockScope currentScope, CodeStream codeStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resolve(BlockScope scope) {
		type.resolve(scope);
		property.resolve(scope);
		for(XAMLElement child : children){
			child.resolve(scope);
		}
	}


	@Override
	protected void printTagName(int indent, StringBuffer output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StringBuffer doGenerateExpression(Scope scope, int indent, StringBuffer output) {
		printIndent(indent, output);
		output.append("this.").append(property.token).append(" = ");
		
//		if(type.){
//			
//		}
		return output;
	}

}

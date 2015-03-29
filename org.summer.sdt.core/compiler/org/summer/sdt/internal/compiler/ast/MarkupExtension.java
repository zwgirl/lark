package org.summer.sdt.internal.compiler.ast;

import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.ReferenceBinding;
import org.summer.sdt.internal.compiler.lookup.Scope;
import org.summer.sdt.internal.compiler.lookup.TypeConstants;

/**
 * 
 * @author cym
 * 
 *         using by XAML
 */
public class MarkupExtension extends XAMLElement {
	
	public MarkupExtension() {
		super();
	}

	@Override
	public StringBuffer print(int indent, StringBuffer output) {
		return printExpression(indent, output);
	}
	
	protected void printProperties(int indent, StringBuffer output){
		for(Attribute attribute : this.attributes){
			output.append(" ");
			attribute.print(indent, output);
		}
	}

	@Override
	public StringBuffer printExpression(int indent, StringBuffer output) {
		output.append("{").append(type.getLastToken());
		printProperties(indent, output);
		return output.append(" }");
	}

	@Override
	public StringBuffer doGenerateExpression(Scope scope, int indent, StringBuffer output) {
		if(this.resolvedType == null){
			return output;
		}
		ReferenceBinding rb = (ReferenceBinding) this.resolvedType;
		output.append("new (");
		rb.generate(output, null);
		output.append(")(");
		
		output.append("{");
		boolean comma = false;
		for(Attribute attribute : this.attributes){
			if(comma){
				output.append(", ");
			}
			
			attribute.buildOptionPart(scope, indent, output);
			comma = true;
		}
		output.append("}");
		output.append(")");

		return output;
	}

	@Override
	protected void printTagName(int indent, StringBuffer output) {
		// TODO Auto-generated method stub
		
	}

}

package org.summer.sdt.internal.compiler.ast;

import org.summer.sdt.internal.compiler.html.Html2JsAttributeMapping;
import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.ReferenceBinding;
import org.summer.sdt.internal.compiler.lookup.Scope;

/**
 * 
 * @authorcym
 * 
 * XAML
 */
public class CommonAttribute extends Attribute{

	public CommonAttribute(PropertyReference property) {
		super(property);
	}

	@Override
	protected void printPropertyName(int indent, StringBuffer output) {
		output.append(property.token);
	}

	@Override
	public StringBuffer printExpression(int indent, StringBuffer output) {
		return output;
	}
	
	@Override
	public void resolve(BlockScope scope) {
		this.property.resolve(scope);
		this.value.resolve(scope);
	}

	@Override
	public StringBuffer doGenerateExpression(Scope scope, int indent, StringBuffer output) {
		output.append(Html2JsAttributeMapping.getHtmlAttributeName(new String(property.token))).append(" = ");
		
		if((this.bits & ASTNode.IsEventCallback) != 0){
			if(this.method != null){
				if(this.method.isStatic()){
					output.append(this.method.declaringClass.sourceName).append('.');
					if(value instanceof StringLiteral){
						output.append(((StringLiteral)value).source).append("(event); \"");
					}
				} else {
					output.append("\"__this.");
					if(value instanceof StringLiteral){
						output.append(((StringLiteral)value).source).append("(event); \"");
					}
				}
			} else if(this.field != null){
				if(this.field.isStatic()){
					output.append(this.field.declaringClass.sourceName).append('.');
					if(value instanceof StringLiteral){
						output.append(((StringLiteral)value).source).append("(event); \"");
					}
				} else {
					output.append("\"__this.");
					if(value instanceof StringLiteral){
						output.append(((StringLiteral)value).source).append("(event); \"");
					}
				}
			}

		} else if(this.property.binding.type.isEnum()){
			((ReferenceBinding)this.property.binding.type).generate(output, null);
			output.append('.').append(((StringLiteral)value).source);
		} else {
			value.doGenerateExpression(scope, indent, output);
		}
		return output;
	}
	
	public StringBuffer generateStaticHTML(Scope initializerScope, int indent, StringBuffer output) {
		return output;
	}
}

package org.summer.sdt.internal.compiler.ast;

import org.summer.sdt.internal.compiler.ASTVisitor;
import org.summer.sdt.internal.compiler.codegen.CodeStream;
import org.summer.sdt.internal.compiler.impl.Constant;
import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.ClassScope;
import org.summer.sdt.internal.compiler.lookup.ElementScope;
import org.summer.sdt.internal.compiler.lookup.Scope;
import org.summer.sdt.internal.compiler.lookup.TypeBinding;

/**
 * 
 * @author cym
 * 
 *         using by XAML
 */
public abstract class XAMLElement extends XAMLNode {
	public static final Attribute[] NO_ATTRIBUTES = new Attribute[0];
	public static final XAMLElement[] NO_ELEMENTS= new XAMLElement[0];
	public static final char[][] noName = new char[0][0];


	public TypeReference type;
	public TypeReference closeType;
	public Attribute[] attributes = NO_ATTRIBUTES;

	public XAMLElement[] children = NO_ELEMENTS;
	public int declarationSourceStart;
	public int declarationSourceEnd;
	public int bodyStart;
	public int bodyEnd; // doesn't include the trailing comment if any.
	public ElementScope scope;
	
	
	protected XAMLElement(){
		super();
	}
	

	public StringBuffer print(int indent, StringBuffer output) {
		return printStatement(indent, output);
	}
	
	@Override
	public StringBuffer printStatement(int indent, StringBuffer output) {
		printIndent(indent, output);
		
		output.append("<");
		printTagName(indent, output);
//		printProperties(indent, output);
		
		if(this.kind == EMPTY_ELEMENT){
			return output.append("/>");
		} else {
			output.append(">");
		}
		
//		printChildElement(indent, output);
		
		output.append("\n");
		printIndent(indent, output).append("</");
		
		printTagName(indent, output);
		
		return output.append(">");
	}
	
	public void traverse(ASTVisitor visitor, ClassScope scope) {
		if (visitor.visit(this, scope)) {
			if (this.children != null) {
				for (int i = 0, length = this.children.length; i < length; i++)
					children[i].traverse(visitor, scope);
			}
		}
		visitor.endVisit(this, scope);
	}
	
	protected abstract void printTagName(int indent, StringBuffer output);
	
//	protected void printProperties(int indent, StringBuffer output){
//		for(Attribute attribute : attributes){
//			output.append(" ");
//			attribute.print(indent, output);
//		}
//	}
//	
//	public StringBuffer printChildElement(int indent, StringBuffer output) {
//		for(Element element : children){
//			output.append('\n');
//			element.print(indent + 1, output);
//		}
//		return output;
//	}
	
	
	/**
	 * Resolve the type of this expression in the context of a blockScope
	 *
	 * @param scope
	 * @return
	 * 	Return the actual type of this expression after resolution
	 */
//	public TypeBinding resolveType(ClassScope scope) {
//		if (this.resolvedType != null)
//			return this.resolvedType;
//
//		this.resolvedType = scope.getType(this.type.getLastToken());
//		
//		if (this.resolvedType instanceof TypeVariableBinding) {
//			TypeVariableBinding typeVariable = (TypeVariableBinding) this.resolvedType;
//			if (typeVariable.declaringElement instanceof SourceTypeBinding) {
//				scope.tagAsAccessingEnclosingInstanceStateOf((ReferenceBinding) typeVariable.declaringElement, true /* type variable access */);
//			}
//		}
//
//		if (scope.kind == Scope.CLASS_SCOPE && this.resolvedType.isValidBinding())
//			if (((ClassScope) scope).detectHierarchyCycle(this.resolvedType, this.type))
//				return null;
//		
//		boolean hasError = false;
//		if (resolvedType == null) {
//			return null; // detected cycle while resolving hierarchy
//		} else if ((hasError = !resolvedType.isValidBinding()) == true) {
//			reportInvalidType(scope);
//			switch (resolvedType.problemId()) {
//				case ProblemReasons.NotFound :
//				case ProblemReasons.NotVisible :
//				case ProblemReasons.InheritedNameHidesEnclosingName :
//					resolvedType = resolvedType.closestMatch();
//					if (type == null) return null;
//					break;
//				default :
//					return null;
//			}
//		}
//		if (resolvedType.isArrayType() && ((ArrayBinding) resolvedType).leafComponentType == TypeBinding.VOID) {
//			scope.problemReporter().cannotAllocateVoidArray(this);
//			return null;
//		}
////		if (!(this instanceof QualifiedTypeReference)   // QualifiedTypeReference#getTypeBinding called above will have already checked deprecation
////				&& isTypeUseDeprecated(type, scope)) {
////			reportDeprecatedType(type, scope);
////		}
//		resolvedType = scope.environment().convertToRawType(resolvedType, false /*do not force conversion of enclosing types*/);
//		if (resolvedType.leafComponentType().isRawType()
//				&& (this.bits & ASTNode.IgnoreRawTypeCheck) == 0
//				&& scope.compilerOptions().getSeverity(CompilerOptions.RawTypeReference) != ProblemSeverities.Ignore) {
//			scope.problemReporter().rawTypeReference(this, resolvedType);
//		}
//		return resolvedType;
//	}
	
	protected void reportDeprecatedType(TypeBinding type, Scope scope, int index) {
		scope.problemReporter().deprecatedType(type, this, index);
	}
	
	protected void reportDeprecatedType(TypeBinding type, Scope scope) {
		scope.problemReporter().deprecatedType(type, this, Integer.MAX_VALUE);
	}
	
	protected void reportInvalidType(Scope scope) {
		scope.problemReporter().invalidType(this, this.resolvedType);
	}
	
	/**
	 * Resolve the type of this expression in the context of a blockScope
	 *
	 * @param scope
	 * @return
	 * 	Return the actual type of this expression after resolution
	 */
	public TypeBinding resolveType(BlockScope scope) {
		// by default... subclasses should implement a better TB if required.
		return null;
	}
	
	/**
	 * Resolve the type of this expression in the context of a classScope
	 *
	 * @param scope
	 * @return
	 * 	Return the actual type of this expression after resolution
	 */
	public TypeBinding resolveType(ClassScope scope) {
		// by default... subclasses should implement a better TB if required.
		return null;
	}
	
	public void resolve(BlockScope scope) {
		this.constant = Constant.NotAConstant;
		if(this.type != null){
			this.resolvedType = type.resolveType(scope);
		}
		
		if(this.closeType != null){
			closeType.resolveType(scope);
		}
		
		ElementScope eleScope = new ElementScope(this, scope);
		resolveAttribute(eleScope);
		resolveChild(eleScope);
	}
	
	protected void resolveChild(BlockScope scope){
		for(XAMLElement child: children){
			if(child instanceof PCDATA || child instanceof XAMLComment){
				continue;
			}
			child.resolve(scope);
		}
	}
	
	protected void resolveAttribute(BlockScope scope){
		for(Attribute attr : attributes){
			attr.resolve(scope);
		}
	}

	@Override
	public StringBuffer printExpression(int indent, StringBuffer output) {
		// TODO Auto-generated method stub
		return output;
	}
	
	protected StringBuffer buildDOMScript(Scope scope, int indent, StringBuffer output, String parent, String context){
		return output;
		
	}
	
	/**
	 * Every expression is responsible for generating its implicit conversion when necessary.
	 *
	 * @param currentScope org.summer.sdt.internal.compiler.lookup.BlockScope
	 * @param codeStream org.summer.sdt.internal.compiler.codegen.CodeStream
	 * @param valueRequired boolean
	 */
	public void generateCode(BlockScope currentScope, CodeStream codeStream, boolean valueRequired) {
//		if (this.constant != Constant.NotAConstant) {
//			// generate a constant expression
//			int pc = codeStream.position;
//			codeStream.generateConstant(this.constant, this.implicitConversion);
//			codeStream.recordPositionsFrom(pc, this.sourceStart);
//		} else {
//			// actual non-constant code generation
//			throw new ShouldNotImplement(Messages.ast_missingCode);
//		}
	}
}

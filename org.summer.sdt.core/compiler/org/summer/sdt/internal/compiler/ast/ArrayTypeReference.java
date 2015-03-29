/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Stephan Herrmann - Contribution for
 *								Bug 429958 - [1.8][null] evaluate new DefaultLocation attribute of @NonNullByDefault
 *								Bug 435570 - [1.8][null] @NonNullByDefault illegally tries to affect "throws E"
 *******************************************************************************/
package org.summer.sdt.internal.compiler.ast;

import org.summer.sdt.core.compiler.CharOperation;
import org.summer.sdt.internal.compiler.ASTVisitor;
import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.ClassScope;
import org.summer.sdt.internal.compiler.lookup.ReferenceBinding;
import org.summer.sdt.internal.compiler.lookup.Scope;
import org.summer.sdt.internal.compiler.lookup.TypeBinding;

public class ArrayTypeReference extends SingleTypeReference {
	public int dimensions;
	private Annotation[][] annotationsOnDimensions; // jsr308 style type annotations on dimensions.
	public int originalSourceEnd;
	public int extendedDimensions;

	/**
	 * ArrayTypeReference constructor comment.
	 * @param source char[]
	 * @param dimensions int
	 * @param pos int
	 */
	public ArrayTypeReference(char[] source, int dimensions, long pos) {

		super(source, pos);
		this.originalSourceEnd = this.sourceEnd;
		this.dimensions = dimensions ;
		this.annotationsOnDimensions = null;
	}

	public ArrayTypeReference(char[] source, int dimensions, Annotation[][] annotationsOnDimensions, long pos) {
		this(source, dimensions, pos);
		if (annotationsOnDimensions != null) {
			this.bits |= ASTNode.HasTypeAnnotations;
		}
		this.annotationsOnDimensions = annotationsOnDimensions;
	}

	public int dimensions() {

		return this.dimensions;
	}
	
	public int extraDimensions() {
		return this.extendedDimensions;
	}

	/**
	 @see org.summer.sdt.internal.compiler.ast.TypeReference#getAnnotationsOnDimensions(boolean)
	*/
	public Annotation[][] getAnnotationsOnDimensions(boolean useSourceOrder) {
		if (useSourceOrder || this.annotationsOnDimensions == null || this.annotationsOnDimensions.length == 0 || this.extendedDimensions == 0 || this.extendedDimensions == this.dimensions)
			return this.annotationsOnDimensions;
		Annotation [][] externalAnnotations = new Annotation[this.dimensions][];
		final int baseDimensions = this.dimensions - this.extendedDimensions;
		System.arraycopy(this.annotationsOnDimensions, baseDimensions, externalAnnotations, 0, this.extendedDimensions);
		System.arraycopy(this.annotationsOnDimensions, 0, externalAnnotations, this.extendedDimensions, baseDimensions);
		return externalAnnotations;
	}
	
	public void setAnnotationsOnDimensions(Annotation [][] annotationsOnDimensions) {
		this.annotationsOnDimensions = annotationsOnDimensions;
	}
	/**
	 * @return char[][]
	 */
	public char [][] getParameterizedTypeName(){
		int dim = this.dimensions;
		char[] dimChars = new char[dim*2];
		for (int i = 0; i < dim; i++) {
			int index = i*2;
			dimChars[index] = '[';
			dimChars[index+1] = ']';
		}
		return new char[][]{ CharOperation.concat(this.token, dimChars) };
	}
//	protected TypeBinding getTypeBinding(Scope scope) {
//
//		if (this.resolvedType != null) {
//			return this.resolvedType;
//		}
//		if (this.dimensions > 255) {
//			scope.problemReporter().tooManyDimensions(this);
//		}
//		TypeBinding leafComponentType = scope.getType(this.token);
//		return scope.createArrayType(leafComponentType, this.dimensions);
//
//	}

	public StringBuffer printExpression(int indent, StringBuffer output){

		super.printExpression(indent, output);
		if ((this.bits & IsVarArgs) != 0) {
			for (int i= 0 ; i < this.dimensions - 1; i++) {
				if (this.annotationsOnDimensions != null && this.annotationsOnDimensions[i] != null) {
					output.append(' ');
					printAnnotations(this.annotationsOnDimensions[i], output);
					output.append(' ');
				}
				output.append("[]"); //$NON-NLS-1$
			}
			if (this.annotationsOnDimensions != null && this.annotationsOnDimensions[this.dimensions - 1] != null) {
				output.append(' ');
				printAnnotations(this.annotationsOnDimensions[this.dimensions - 1], output);
				output.append(' ');
			}
			output.append("..."); //$NON-NLS-1$
		} else {
			for (int i= 0 ; i < this.dimensions; i++) {
				if (this.annotationsOnDimensions != null && this.annotationsOnDimensions[i] != null) {
					output.append(" "); //$NON-NLS-1$
					printAnnotations(this.annotationsOnDimensions[i], output);
					output.append(" "); //$NON-NLS-1$
				}
				output.append("[]"); //$NON-NLS-1$
			}
		}
		return output;
	}

	public void traverse(ASTVisitor visitor, BlockScope scope) {
		if (visitor.visit(this, scope)) {
			if (this.annotations != null) {
				Annotation [] typeAnnotations = this.annotations[0];
				for (int i = 0, length = typeAnnotations == null ? 0 : typeAnnotations.length; i < length; i++) {
					typeAnnotations[i].traverse(visitor, scope);
				}
			}
			if (this.annotationsOnDimensions != null) {
				for (int i = 0, max = this.annotationsOnDimensions.length; i < max; i++) {
					Annotation[] annotations2 = this.annotationsOnDimensions[i];
					if (annotations2 != null) {
						for (int j = 0, max2 = annotations2.length; j < max2; j++) {
							Annotation annotation = annotations2[j];
							annotation.traverse(visitor, scope);
						}
					}
				}
			}
		}
		visitor.endVisit(this, scope);
	}

	public void traverse(ASTVisitor visitor, ClassScope scope) {
		if (visitor.visit(this, scope)) {
			if (this.annotations != null) {
				Annotation [] typeAnnotations = this.annotations[0];
				for (int i = 0, length = typeAnnotations == null ? 0 : typeAnnotations.length; i < length; i++) {
					typeAnnotations[i].traverse(visitor, scope);
				}
			}
			if (this.annotationsOnDimensions != null) {
				for (int i = 0, max = this.annotationsOnDimensions.length; i < max; i++) {
					Annotation[] annotations2 = this.annotationsOnDimensions[i];
					if (annotations2 != null) {
						for (int j = 0, max2 = annotations2.length; j < max2; j++) {
							Annotation annotation = annotations2[j];
							annotation.traverse(visitor, scope);
						}
					}
				}
			}
		}
		visitor.endVisit(this, scope);
	}

	protected TypeBinding internalResolveType(Scope scope, int location) {
		TypeBinding internalResolveType = super.internalResolveType(scope, location);
		return internalResolveType;
	}
	
	@Override
	public boolean hasNullTypeAnnotation() {
    	if (super.hasNullTypeAnnotation())
    		return true;
    	if (this.resolvedType != null && !this.resolvedType.hasNullTypeAnnotations())
    		return false; // shortcut
    	if (this.annotationsOnDimensions != null) {
    		for (int i = 0; i < this.annotationsOnDimensions.length; i++) {
				Annotation[] innerAnnotations = this.annotationsOnDimensions[i];
				if (containsNullAnnotation(innerAnnotations))
					return true;
			}
    	}
    	return false;
	}
	
	/**
     * @see org.summer.sdt.internal.compiler.ast.ArrayQualifiedTypeReference#getTypeBinding(org.summer.sdt.internal.compiler.lookup.Scope)
     */
    protected TypeBinding getTypeBinding(Scope scope) {
        return null; // not supported here - combined with resolveType(...)
    }
    
    public boolean isParameterizedTypeReference() {
    	return true;
    }
    
	public TypeBinding resolveType(BlockScope scope, boolean checkBounds, int location) {
	    return internalResolveType(scope, null, checkBounds, location);
	}

	public TypeBinding resolveType(ClassScope scope, int location) {
	    return internalResolveType(scope, null, false /*no bounds check in classScope*/, location);
	}

	public TypeBinding resolveTypeEnclosing(BlockScope scope, ReferenceBinding enclosingType) {
	    return internalResolveType(scope, enclosingType, true/*check bounds*/, 0);
	}
	
	   /*
     * No need to check for reference to raw type per construction
     */
	private TypeBinding internalResolveType(Scope scope, ReferenceBinding enclosingType, boolean checkBounds, int location) {
//		// handle the error here
//		this.constant = Constant.NotAConstant;
//		if ((this.bits & ASTNode.DidResolve) != 0) { // is a shared type reference which was already resolved
//			if (this.resolvedType != null) { // is a shared type reference which was already resolved
//				if (this.resolvedType.isValidBinding()) {
//					return this.resolvedType;
//				} else {
//					switch (this.resolvedType.problemId()) {
//						case ProblemReasons.NotFound :
//						case ProblemReasons.NotVisible :
//						case ProblemReasons.InheritedNameHidesEnclosingName :
//							TypeBinding type = this.resolvedType.closestMatch();
//							return type;
//						default :
//							return null;
//					}
//				}
//			}
//		}
//		this.bits |= ASTNode.DidResolve;
//		TypeBinding type = internalResolveLeafType(scope, enclosingType, checkBounds);
//
//		// handle three different outcomes:
//		if (type == null) {
//			this.resolvedType = createArrayType(scope, this.resolvedType);
//			resolveAnnotations(scope, 0); // no defaultNullness for buggy type
//			if (checkBounds)
//				checkNullConstraints(scope, this.typeArguments);
//			return null;							// (1) no useful type, but still captured dimensions into this.resolvedType
//		} else {
//			type = createArrayType(scope, type);
//			if (!this.resolvedType.isValidBinding() && this.resolvedType.dimensions() == type.dimensions()) {
//				resolveAnnotations(scope, 0); // no defaultNullness for buggy type
//				if (checkBounds)
//					checkNullConstraints(scope, this.typeArguments);
//				return type;						// (2) found some error, but could recover useful type (like closestMatch)
//			} else {
//				this.resolvedType = type; 			// (3) no complaint, keep fully resolved type (incl. dimensions)
//				resolveAnnotations(scope, location);
//				if (checkBounds)
//					checkNullConstraints(scope, this.typeArguments);
//				return this.resolvedType; // pick up any annotated type.
//			}
//		}
		
		if (this.resolvedType != null) {
			return this.resolvedType;
		}
		if (this.dimensions > 255) {
			scope.problemReporter().tooManyDimensions(this);
		}
		TypeBinding leafComponentType = scope.getType(this.token);
		return scope.createArrayType(leafComponentType, this.dimensions);
		
	}
//	private TypeBinding internalResolveLeafType(Scope scope, ReferenceBinding enclosingType, boolean checkBounds) {
//		ReferenceBinding currentType;
//		if (enclosingType == null) {
//			this.resolvedType = scope.getType(this.token);
//			if (this.resolvedType.isValidBinding()) {
//				currentType = (ReferenceBinding) this.resolvedType;
//			} else {
//				reportInvalidType(scope);
//				switch (this.resolvedType.problemId()) {
//					case ProblemReasons.NotFound :
//					case ProblemReasons.NotVisible :
//					case ProblemReasons.InheritedNameHidesEnclosingName :
//						TypeBinding type = this.resolvedType.closestMatch();
//						if (type instanceof ReferenceBinding) {
//							currentType = (ReferenceBinding) type;
//							break;
//						}
//						//$FALL-THROUGH$ - unable to complete type binding, but still resolve type arguments
//					default :
//						boolean isClassScope = scope.kind == Scope.CLASS_SCOPE;
//					int argLength = this.typeArguments.length;
//					for (int i = 0; i < argLength; i++) {
//						TypeReference typeArgument = this.typeArguments[i];
//						if (isClassScope) {
//							typeArgument.resolveType((ClassScope) scope);
//						} else {
//							typeArgument.resolveType((BlockScope) scope, checkBounds);
//						}
//					}
//					return null;
//				}
//				// be resilient, still attempt resolving arguments
//			}
//			enclosingType = currentType.enclosingType(); // if member type
//			if (enclosingType != null) {
//				enclosingType = currentType.isStatic()
//					? (ReferenceBinding) scope.environment().convertToRawType(enclosingType, false /*do not force conversion of enclosing types*/)
//					: scope.environment().convertToParameterizedType(enclosingType);
//				currentType = scope.environment().createParameterizedType((ReferenceBinding) currentType.erasure(), null /* no arg */, enclosingType);
//			}
//		} else { // resolving member type (relatively to enclosingType)
//			this.resolvedType = currentType = scope.getMemberType(this.token, enclosingType);
//			if (!this.resolvedType.isValidBinding()) {
//				scope.problemReporter().invalidEnclosingType(this, currentType, enclosingType);
//				return null;
//			}
//			if (isTypeUseDeprecated(currentType, scope))
//				scope.problemReporter().deprecatedType(currentType, this);
//			ReferenceBinding currentEnclosing = currentType.enclosingType();
//			if (currentEnclosing != null && TypeBinding.notEquals(currentEnclosing.erasure(), enclosingType.erasure())) {
//				enclosingType = currentEnclosing; // inherited member type, leave it associated with its enclosing rather than subtype
//			}
//		}
//
//		// check generic and arity
//	    boolean isClassScope = scope.kind == Scope.CLASS_SCOPE;
//	    TypeReference keep = null;
//	    if (isClassScope) {
//	    	keep = ((ClassScope) scope).superTypeReference;
//	    	((ClassScope) scope).superTypeReference = null;
//	    }
//	    final boolean isDiamond = (this.bits & ASTNode.IsDiamond) != 0;
//		int argLength = this.typeArguments.length;
//		TypeBinding[] argTypes = new TypeBinding[argLength];
//		boolean argHasError = false;
//		ReferenceBinding currentOriginal = (ReferenceBinding)currentType.original();
//		for (int i = 0; i < argLength; i++) {
//		    TypeReference typeArgument = this.typeArguments[i];
//		    TypeBinding argType = isClassScope
//				? typeArgument.resolveTypeArgument((ClassScope) scope, currentOriginal, i)
//				: typeArgument.resolveTypeArgument((BlockScope) scope, currentOriginal, i);
//			this.bits |= (typeArgument.bits & ASTNode.HasTypeAnnotations);
//		     if (argType == null) {
//		         argHasError = true;
//		     } else {
//		    	 argTypes[i] = argType;
//		     }
//		}
//		if (argHasError) {
//			return null;
//		}
//		if (isClassScope) {
//	    	((ClassScope) scope).superTypeReference = keep;
//			if (((ClassScope) scope).detectHierarchyCycle(currentOriginal, this))
//				return null;
//		}
//
//		TypeVariableBinding[] typeVariables = currentOriginal.typeVariables();
//		if (typeVariables == Binding.NO_TYPE_VARIABLES) { // non generic invoked with arguments
//			boolean isCompliant15 = scope.compilerOptions().originalSourceLevel >= ClassFileConstants.JDK1_5;
//			if ((currentOriginal.tagBits & TagBits.HasMissingType) == 0) {
//				if (isCompliant15) { // below 1.5, already reported as syntax error
//					this.resolvedType = currentType;
//					scope.problemReporter().nonGenericTypeCannotBeParameterized(0, this, currentType, argTypes);
//					return null;
//				}
//			}
//			// resilience do not rebuild a parameterized type unless compliance is allowing it
//			if (!isCompliant15) {
//				if (!this.resolvedType.isValidBinding())
//					return currentType;
//				return this.resolvedType = currentType;
//			}
//			// if missing generic type, and compliance >= 1.5, then will rebuild a parameterized binding
//		} else if (argLength != typeVariables.length) {
//			if (!isDiamond) { // check arity, IsDiamond never set for 1.6-
//				scope.problemReporter().incorrectArityForParameterizedType(this, currentType, argTypes);
//				return null;
//			} 
//		} else if (!currentType.isStatic()) {
//			ReferenceBinding actualEnclosing = currentType.enclosingType();
//			if (actualEnclosing != null && actualEnclosing.isRawType()){
//				scope.problemReporter().rawMemberTypeCannotBeParameterized(
//						this, scope.environment().createRawType(currentOriginal, actualEnclosing), argTypes);
//				return null;
//			}
//		}
//
//    	ParameterizedTypeBinding parameterizedType = scope.environment().createParameterizedType(currentOriginal, argTypes, enclosingType);
//		// check argument type compatibility for non <> cases - <> case needs no bounds check, we will scream foul if needed during inference.
//    	if (!isDiamond) {
//    		if (checkBounds) // otherwise will do it in Scope.connectTypeVariables() or generic method resolution
//    			parameterizedType.boundCheck(scope, this.typeArguments);
//    		else
//    			scope.deferBoundCheck(this);
//    	}
//		if (isTypeUseDeprecated(parameterizedType, scope))
//			reportDeprecatedType(parameterizedType, scope);
//
//		if (!this.resolvedType.isValidBinding()) {
//			return parameterizedType;
//		}
//		return this.resolvedType = parameterizedType;
//	}
	private TypeBinding createArrayType(Scope scope, TypeBinding type) {
		if (this.dimensions > 0) {
			if (this.dimensions > 255)
				scope.problemReporter().tooManyDimensions(this);
			return scope.createArrayType(type, this.dimensions);
		}
		return type;
	}
}

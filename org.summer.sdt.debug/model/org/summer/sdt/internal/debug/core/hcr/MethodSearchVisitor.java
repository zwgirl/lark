/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Yevgen Kogan - Bug 403475 - Hot Code Replace drops too much frames in some cases
 *     Jacob Saoumi - Bug 434695 - Hot Code Replace drops some frames in case of anonymous classes
 *******************************************************************************/
package org.summer.sdt.internal.debug.core.hcr;

import org.summer.sdt.core.Signature;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.ASTVisitor;
import org.summer.sdt.core.dom.AbstractTypeDeclaration;
import org.summer.sdt.core.dom.AnnotationTypeDeclaration;
import org.summer.sdt.core.dom.AnnotationTypeMemberDeclaration;
import org.summer.sdt.core.dom.AnonymousClassDeclaration;
import org.summer.sdt.core.dom.ArrayAccess;
import org.summer.sdt.core.dom.ArrayCreation;
import org.summer.sdt.core.dom.ArrayInitializer;
import org.summer.sdt.core.dom.ArrayType;
import org.summer.sdt.core.dom.AssertStatement;
import org.summer.sdt.core.dom.Assignment;
import org.summer.sdt.core.dom.Block;
import org.summer.sdt.core.dom.BlockComment;
import org.summer.sdt.core.dom.BooleanLiteral;
import org.summer.sdt.core.dom.BreakStatement;
import org.summer.sdt.core.dom.CastExpression;
import org.summer.sdt.core.dom.CatchClause;
import org.summer.sdt.core.dom.CharacterLiteral;
import org.summer.sdt.core.dom.ClassInstanceCreation;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.dom.ConditionalExpression;
import org.summer.sdt.core.dom.ConstructorInvocation;
import org.summer.sdt.core.dom.ContinueStatement;
import org.summer.sdt.core.dom.DoStatement;
import org.summer.sdt.core.dom.EmptyStatement;
import org.summer.sdt.core.dom.EnhancedForStatement;
import org.summer.sdt.core.dom.EnumConstantDeclaration;
import org.summer.sdt.core.dom.EnumDeclaration;
import org.summer.sdt.core.dom.ExpressionStatement;
import org.summer.sdt.core.dom.FieldAccess;
import org.summer.sdt.core.dom.FieldDeclaration;
import org.summer.sdt.core.dom.ForStatement;
import org.summer.sdt.core.dom.IMethodBinding;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.IfStatement;
import org.summer.sdt.core.dom.ImportDeclaration;
import org.summer.sdt.core.dom.InfixExpression;
import org.summer.sdt.core.dom.Initializer;
import org.summer.sdt.core.dom.InstanceofExpression;
import org.summer.sdt.core.dom.Javadoc;
import org.summer.sdt.core.dom.LabeledStatement;
import org.summer.sdt.core.dom.LineComment;
import org.summer.sdt.core.dom.MarkerAnnotation;
import org.summer.sdt.core.dom.MemberRef;
import org.summer.sdt.core.dom.MemberValuePair;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.core.dom.MethodInvocation;
import org.summer.sdt.core.dom.MethodRef;
import org.summer.sdt.core.dom.MethodRefParameter;
import org.summer.sdt.core.dom.Modifier;
import org.summer.sdt.core.dom.NormalAnnotation;
import org.summer.sdt.core.dom.NullLiteral;
import org.summer.sdt.core.dom.NumberLiteral;
import org.summer.sdt.core.dom.PackageDeclaration;
import org.summer.sdt.core.dom.ParameterizedType;
import org.summer.sdt.core.dom.ParenthesizedExpression;
import org.summer.sdt.core.dom.PostfixExpression;
import org.summer.sdt.core.dom.PrefixExpression;
import org.summer.sdt.core.dom.PrimitiveType;
import org.summer.sdt.core.dom.QualifiedName;
import org.summer.sdt.core.dom.QualifiedType;
import org.summer.sdt.core.dom.ReturnStatement;
import org.summer.sdt.core.dom.SimpleName;
import org.summer.sdt.core.dom.SimpleType;
import org.summer.sdt.core.dom.SingleMemberAnnotation;
import org.summer.sdt.core.dom.SingleVariableDeclaration;
import org.summer.sdt.core.dom.StringLiteral;
import org.summer.sdt.core.dom.SuperConstructorInvocation;
import org.summer.sdt.core.dom.SuperFieldAccess;
import org.summer.sdt.core.dom.SuperMethodInvocation;
import org.summer.sdt.core.dom.SwitchCase;
import org.summer.sdt.core.dom.SwitchStatement;
import org.summer.sdt.core.dom.SynchronizedStatement;
import org.summer.sdt.core.dom.TagElement;
import org.summer.sdt.core.dom.TextElement;
import org.summer.sdt.core.dom.ThisExpression;
import org.summer.sdt.core.dom.ThrowStatement;
import org.summer.sdt.core.dom.TryStatement;
import org.summer.sdt.core.dom.TypeDeclaration;
import org.summer.sdt.core.dom.TypeDeclarationStatement;
import org.summer.sdt.core.dom.TypeLiteral;
import org.summer.sdt.core.dom.TypeParameter;
import org.summer.sdt.core.dom.UnionType;
import org.summer.sdt.core.dom.VariableDeclarationExpression;
import org.summer.sdt.core.dom.VariableDeclarationFragment;
import org.summer.sdt.core.dom.VariableDeclarationStatement;
import org.summer.sdt.core.dom.WhileStatement;
import org.summer.sdt.core.dom.WildcardType;

/**
 * Visits an AST to find a method declaration
 */
public class MethodSearchVisitor extends ASTVisitor {

	/**
	 * Class the method belongs to
	 */
	private String fClassName;
	/**
	 * Method to search for
	 */
	private String fName;
	private String[] fParameterTypes;

	/**
	 * The search result, or <code>null</code> if none
	 */
	private MethodDeclaration fMatch;

	/**
	 * Sets the signature of the method to search for
	 * 
	 * @param methodName
	 *            name of method to search for
	 * @param methodSignature
	 *            signature of the method to search for
	 */
	public void setTargetMethod(String className, String methodName, String methodSignature) {
		fClassName = className;
		fName = methodName;
		fParameterTypes = Signature.getParameterTypes(methodSignature);
		// convert parameter types same format that we get from the AST type
		// bindings
		for (int i = 0; i < fParameterTypes.length; i++) {
			String type = fParameterTypes[i];
			type = type.replace('/', '.');
			fParameterTypes[i] = type;
		}
		fMatch = null;
	}

	/**
	 * Returns the search result, or <code>null</code> if none
	 * 
	 * @return matching method declartion or <code>null</code>
	 */
	public MethodDeclaration getMatch() {
		return fMatch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.
	 * MethodDeclaration)
	 */
	@Override
	public boolean visit(MethodDeclaration node) {
		ITypeBinding binding = null;
		IMethodBinding mbinding = node.resolveBinding();
		if(mbinding != null) {
			binding = mbinding.getDeclaringClass();
		}
		else {
			ASTNode parent = node.getParent();
			if(parent instanceof AbstractTypeDeclaration) {
				binding = ((AbstractTypeDeclaration) parent).resolveBinding();
			}
			else if(parent instanceof AnonymousClassDeclaration) {
				binding = ((AnonymousClassDeclaration) parent).resolveBinding();
			}
		}
		String typeName = null;
		if (binding != null) {
			typeName = binding.getQualifiedName();
			if ((typeName == null || "".equals(typeName)) && binding.getBinaryName() != null) { //$NON-NLS-1$
				typeName = binding.getBinaryName().replace('$', '.');
			}
		}
		// if no binding exists, the behaviour should be the same as without checking for type name
		if (node.getName().getIdentifier().equals(fName) && (typeName == null || typeName.equals(fClassName))) {
			IMethodBinding methodBinding = node.resolveBinding();
			if (methodBinding != null) {
				ITypeBinding[] typeBindings = methodBinding.getParameterTypes();
				if (typeBindings.length == fParameterTypes.length) {
					for (int i = 0; i < typeBindings.length; i++) {
						ITypeBinding typeBinding = typeBindings[i];
						String typeSignature = Signature.createTypeSignature(
								typeBinding.getQualifiedName(), true);
						if (!fParameterTypes[i].equals(typeSignature)) {
							return true;
						}
					}
					fMatch = node;
				}
			}
		}
		return isSearching();
	}

	/**
	 * Returns whether this visitor is still searching for a match.
	 * 
	 * @return whether this visitor is still searching for a match
	 */
	private boolean isSearching() {
		return fMatch == null;
	}

	@Override
	public boolean visit(AnnotationTypeDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(ArrayAccess node) {
		return isSearching();
	}

	@Override
	public boolean visit(ArrayCreation node) {
		return isSearching();
	}

	@Override
	public boolean visit(ArrayInitializer node) {
		return isSearching();
	}

	@Override
	public boolean visit(ArrayType node) {
		return isSearching();
	}

	@Override
	public boolean visit(AssertStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(Assignment node) {
		return isSearching();
	}

	@Override
	public boolean visit(Block node) {
		return isSearching();
	}

	@Override
	public boolean visit(BlockComment node) {
		return isSearching();
	}

	@Override
	public boolean visit(BooleanLiteral node) {
		return isSearching();
	}

	@Override
	public boolean visit(BreakStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(CastExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(CatchClause node) {
		return isSearching();
	}

	@Override
	public boolean visit(CharacterLiteral node) {
		return isSearching();
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		return isSearching();
	}

	@Override
	public boolean visit(CompilationUnit node) {
		return isSearching();
	}

	@Override
	public boolean visit(ConditionalExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		return isSearching();
	}

	@Override
	public boolean visit(ContinueStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(DoStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(EmptyStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(EnhancedForStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(ExpressionStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(FieldAccess node) {
		return isSearching();
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(ForStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(IfStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(ImportDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(InfixExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(Initializer node) {
		return isSearching();
	}

	@Override
	public boolean visit(InstanceofExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(Javadoc node) {
		return isSearching();
	}

	@Override
	public boolean visit(LabeledStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(LineComment node) {
		return isSearching();
	}

	@Override
	public boolean visit(MarkerAnnotation node) {
		return isSearching();
	}

	@Override
	public boolean visit(MemberRef node) {
		return isSearching();
	}

	@Override
	public boolean visit(MemberValuePair node) {
		return isSearching();
	}

	@Override
	public boolean visit(MethodInvocation node) {
		return isSearching();
	}

	@Override
	public boolean visit(MethodRef node) {
		return isSearching();
	}

	@Override
	public boolean visit(MethodRefParameter node) {
		return isSearching();
	}

	@Override
	public boolean visit(Modifier node) {
		return isSearching();
	}

	@Override
	public boolean visit(NormalAnnotation node) {
		return isSearching();
	}

	@Override
	public boolean visit(NullLiteral node) {
		return isSearching();
	}

	@Override
	public boolean visit(NumberLiteral node) {
		return isSearching();
	}

	@Override
	public boolean visit(PackageDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(ParameterizedType node) {
		return isSearching();
	}

	@Override
	public boolean visit(ParenthesizedExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(PostfixExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(PrefixExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(PrimitiveType node) {
		return isSearching();
	}

	@Override
	public boolean visit(QualifiedName node) {
		return isSearching();
	}

	@Override
	public boolean visit(QualifiedType node) {
		return isSearching();
	}

	@Override
	public boolean visit(ReturnStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(SimpleName node) {
		return isSearching();
	}

	@Override
	public boolean visit(SimpleType node) {
		return isSearching();
	}

	@Override
	public boolean visit(SingleMemberAnnotation node) {
		return isSearching();
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(StringLiteral node) {
		return isSearching();
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		return isSearching();
	}

	@Override
	public boolean visit(SuperFieldAccess node) {
		return isSearching();
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		return isSearching();
	}

	@Override
	public boolean visit(SwitchCase node) {
		return isSearching();
	}

	@Override
	public boolean visit(SwitchStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(SynchronizedStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(TagElement node) {
		return isSearching();
	}

	@Override
	public boolean visit(TextElement node) {
		return isSearching();
	}

	@Override
	public boolean visit(ThisExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(ThrowStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(TryStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		return isSearching();
	}

	@Override
	public boolean visit(TypeDeclarationStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(TypeLiteral node) {
		return isSearching();
	}

	@Override
	public boolean visit(TypeParameter node) {
		return isSearching();
	}

	@Override
	public boolean visit(UnionType node) {
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationExpression node) {
		return isSearching();
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		return isSearching();
	}

	@Override
	public boolean visit(VariableDeclarationStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(WhileStatement node) {
		return isSearching();
	}

	@Override
	public boolean visit(WildcardType node) {
		return isSearching();
	}
}

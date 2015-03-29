/*******************************************************************************
 * Copyright (c) 2013, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.text.correction.proposals;

import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.dom.AST;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.dom.Expression;
import org.summer.sdt.core.dom.IBinding;
import org.summer.sdt.core.dom.IMethodBinding;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.IVariableBinding;
import org.summer.sdt.core.dom.LambdaExpression;
import org.summer.sdt.core.dom.ReturnStatement;
import org.summer.sdt.core.dom.VariableDeclarationFragment;
import org.summer.sdt.internal.corext.dom.ASTNodeFactory;
import org.summer.sdt.internal.corext.dom.Bindings;
import org.summer.sdt.internal.corext.dom.ScopeAnalyzer;
import org.summer.sdt.internal.ui.text.correction.ASTResolving;

public class MissingReturnTypeInLambdaCorrectionProposal extends MissingReturnTypeCorrectionProposal {

	private final LambdaExpression lambdaExpression;

	public MissingReturnTypeInLambdaCorrectionProposal(ICompilationUnit cu, LambdaExpression lambda, ReturnStatement existingReturn, int relevance) {
		super(cu, null, existingReturn, relevance);
		lambdaExpression= lambda;
		fExistingReturn= existingReturn;
	}

	@Override
	protected AST getAST() {
		return lambdaExpression.getAST();
	}
	
	@Override
	public ITypeBinding getReturnTypeBinding() {
		IMethodBinding methodBinding= lambdaExpression.resolveMethodBinding();
		if (methodBinding != null && methodBinding.getReturnType() != null) {
			return methodBinding.getReturnType();
		}
		return null;
	}

	
	@Override
	protected CompilationUnit getCU() {
		return (CompilationUnit) lambdaExpression.getRoot();
	}
	
	@Override
	protected Expression createDefaultExpression(AST ast) {
		return ASTNodeFactory.newDefaultExpression(ast, getReturnTypeBinding());
	}
	
	@Override
	protected ASTNode getBody() {
		return lambdaExpression.getBody();
	}
	
	@Override
	protected int getModifiers() {
		return 0;
	}

	@Override
	protected Expression computeProposals(AST ast, ITypeBinding returnBinding, int returnOffset, CompilationUnit root, Expression result) {
		ScopeAnalyzer analyzer= new ScopeAnalyzer(root);
		IBinding[] bindings= analyzer.getDeclarationsInScope(returnOffset, ScopeAnalyzer.VARIABLES | ScopeAnalyzer.CHECK_VISIBILITY);

		org.summer.sdt.core.dom.NodeFinder finder= new org.summer.sdt.core.dom.NodeFinder(root, returnOffset, 0);
		ASTNode varDeclFrag= ASTResolving.findAncestor(finder.getCoveringNode(), ASTNode.VARIABLE_DECLARATION_FRAGMENT);
		IVariableBinding varDeclFragBinding= null;
		if (varDeclFrag != null)
			varDeclFragBinding= ((VariableDeclarationFragment) varDeclFrag).resolveBinding();
		for (int i= 0; i < bindings.length; i++) {
			IVariableBinding curr= (IVariableBinding) bindings[i];
			ITypeBinding type= curr.getType();
			// Bindings are compared to make sure that a lambda does not return a variable which is yet to be initialised.
			if (type != null && type.isAssignmentCompatible(returnBinding) && testModifier(curr) && !Bindings.equals(curr, varDeclFragBinding)) {
				if (result == null) {
					result= ast.newSimpleName(curr.getName());
				}
				addLinkedPositionProposal(RETURN_EXPRESSION_KEY, curr.getName(), null);
			}
		}
		return result;
	}
}

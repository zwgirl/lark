/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.corext.refactoring.typeconstraints;

import org.summer.sdt.core.dom.AnonymousClassDeclaration;
import org.summer.sdt.core.dom.ArrayAccess;
import org.summer.sdt.core.dom.ArrayCreation;
import org.summer.sdt.core.dom.ArrayInitializer;
import org.summer.sdt.core.dom.ArrayType;
import org.summer.sdt.core.dom.AssertStatement;
import org.summer.sdt.core.dom.Assignment;
import org.summer.sdt.core.dom.Block;
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
import org.summer.sdt.core.dom.ExpressionStatement;
import org.summer.sdt.core.dom.FieldAccess;
import org.summer.sdt.core.dom.FieldDeclaration;
import org.summer.sdt.core.dom.ForStatement;
import org.summer.sdt.core.dom.IfStatement;
import org.summer.sdt.core.dom.ImportDeclaration;
import org.summer.sdt.core.dom.InfixExpression;
import org.summer.sdt.core.dom.Initializer;
import org.summer.sdt.core.dom.InstanceofExpression;
import org.summer.sdt.core.dom.Javadoc;
import org.summer.sdt.core.dom.LabeledStatement;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.core.dom.MethodInvocation;
import org.summer.sdt.core.dom.NullLiteral;
import org.summer.sdt.core.dom.NumberLiteral;
import org.summer.sdt.core.dom.PackageDeclaration;
import org.summer.sdt.core.dom.ParenthesizedExpression;
import org.summer.sdt.core.dom.PostfixExpression;
import org.summer.sdt.core.dom.PrefixExpression;
import org.summer.sdt.core.dom.PrimitiveType;
import org.summer.sdt.core.dom.QualifiedName;
import org.summer.sdt.core.dom.ReturnStatement;
import org.summer.sdt.core.dom.SimpleName;
import org.summer.sdt.core.dom.SimpleType;
import org.summer.sdt.core.dom.SingleVariableDeclaration;
import org.summer.sdt.core.dom.StringLiteral;
import org.summer.sdt.core.dom.SuperConstructorInvocation;
import org.summer.sdt.core.dom.SuperFieldAccess;
import org.summer.sdt.core.dom.SuperMethodInvocation;
import org.summer.sdt.core.dom.SwitchCase;
import org.summer.sdt.core.dom.SwitchStatement;
import org.summer.sdt.core.dom.SynchronizedStatement;
import org.summer.sdt.core.dom.ThisExpression;
import org.summer.sdt.core.dom.ThrowStatement;
import org.summer.sdt.core.dom.TryStatement;
import org.summer.sdt.core.dom.TypeDeclaration;
import org.summer.sdt.core.dom.TypeDeclarationStatement;
import org.summer.sdt.core.dom.TypeLiteral;
import org.summer.sdt.core.dom.VariableDeclarationExpression;
import org.summer.sdt.core.dom.VariableDeclarationFragment;
import org.summer.sdt.core.dom.VariableDeclarationStatement;
import org.summer.sdt.core.dom.WhileStatement;

/**
 * Empty implementation of a creator - provided to allow subclasses to override only a subset of methods.
 * Subclass to provide constraint creation functionality.
 */
public class ConstraintCreator {

	public static final ITypeConstraint[] EMPTY_ARRAY= new ITypeConstraint[0];

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.AnonymousClassDeclaration)
	 */
	public ITypeConstraint[] create(AnonymousClassDeclaration node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayAccess)
	 */
	public ITypeConstraint[] create(ArrayAccess node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayCreation)
	 */
	public ITypeConstraint[] create(ArrayCreation node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayInitializer)
	 */
	public ITypeConstraint[] create(ArrayInitializer node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayType)
	 */
	public ITypeConstraint[] create(ArrayType node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.AssertStatement)
	 */
	public ITypeConstraint[] create(AssertStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Assignment)
	 */
	public ITypeConstraint[] create(Assignment node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Block)
	 */
	public ITypeConstraint[] create(Block node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.BooleanLiteral)
	 */
	public ITypeConstraint[] create(BooleanLiteral node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.BreakStatement)
	 */
	public ITypeConstraint[] create(BreakStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CastExpression)
	 */
	public ITypeConstraint[] create(CastExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CatchClause)
	 */
	public ITypeConstraint[] create(CatchClause node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CharacterLiteral)
	 */
	public ITypeConstraint[] create(CharacterLiteral node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ClassInstanceCreation)
	 */
	public ITypeConstraint[] create(ClassInstanceCreation node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CompilationUnit)
	 */
	public ITypeConstraint[] create(CompilationUnit node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ConditionalExpression)
	 */
	public ITypeConstraint[] create(ConditionalExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ConstructorInvocation)
	 */
	public ITypeConstraint[] create(ConstructorInvocation node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ContinueStatement)
	 */
	public ITypeConstraint[] create(ContinueStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.DoStatement)
	 */
	public ITypeConstraint[] create(DoStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.EmptyStatement)
	 */
	public ITypeConstraint[] create(EmptyStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ExpressionStatement)
	 */
	public ITypeConstraint[] create(ExpressionStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.FieldAccess)
	 */
	public ITypeConstraint[] create(FieldAccess node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.FieldDeclaration)
	 */
	public ITypeConstraint[] create(FieldDeclaration node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ForStatement)
	 */
	public ITypeConstraint[] create(ForStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.IfStatement)
	 */
	public ITypeConstraint[] create(IfStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ImportDeclaration)
	 */
	public ITypeConstraint[] create(ImportDeclaration node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.InfixExpression)
	 */
	public ITypeConstraint[] create(InfixExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Initializer)
	 */
	public ITypeConstraint[] create(Initializer node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.InstanceofExpression)
	 */
	public ITypeConstraint[] create(InstanceofExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Javadoc)
	 */
	public ITypeConstraint[] create(Javadoc node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.LabeledStatement)
	 */
	public ITypeConstraint[] create(LabeledStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.MethodDeclaration)
	 */
	public ITypeConstraint[] create(MethodDeclaration node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.MethodInvocation)
	 */
	public ITypeConstraint[] create(MethodInvocation node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.NullLiteral)
	 */
	public ITypeConstraint[] create(NullLiteral node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.NumberLiteral)
	 */
	public ITypeConstraint[] create(NumberLiteral node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PackageDeclaration)
	 */
	public ITypeConstraint[] create(PackageDeclaration node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ParenthesizedExpression)
	 */
	public ITypeConstraint[] create(ParenthesizedExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PostfixExpression)
	 */
	public ITypeConstraint[] create(PostfixExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PrefixExpression)
	 */
	public ITypeConstraint[] create(PrefixExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PrimitiveType)
	 */
	public ITypeConstraint[] create(PrimitiveType node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.QualifiedName)
	 */
	public ITypeConstraint[] create(QualifiedName node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ReturnStatement)
	 */
	public ITypeConstraint[] create(ReturnStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SimpleName)
	 */
	public ITypeConstraint[] create(SimpleName node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SimpleType)
	 */
	public ITypeConstraint[] create(SimpleType node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SingleVariableDeclaration)
	 */
	public ITypeConstraint[] create(SingleVariableDeclaration node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.StringLiteral)
	 */
	public ITypeConstraint[] create(StringLiteral node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SuperConstructorInvocation)
	 */
	public ITypeConstraint[] create(SuperConstructorInvocation node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SuperFieldAccess)
	 */
	public ITypeConstraint[] create(SuperFieldAccess node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SuperMethodInvocation)
	 */
	public ITypeConstraint[] create(SuperMethodInvocation node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SwitchCase)
	 */
	public ITypeConstraint[] create(SwitchCase node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SwitchStatement)
	 */
	public ITypeConstraint[] create(SwitchStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SynchronizedStatement)
	 */
	public ITypeConstraint[] create(SynchronizedStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ThisExpression)
	 */
	public ITypeConstraint[] create(ThisExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ThrowStatement)
	 */
	public ITypeConstraint[] create(ThrowStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TryStatement)
	 */
	public ITypeConstraint[] create(TryStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TypeDeclaration)
	 */
	public ITypeConstraint[] create(TypeDeclaration node) {
		return EMPTY_ARRAY;

		// TODO account for enums and annotations
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TypeDeclarationStatement)
	 */
	public ITypeConstraint[] create(TypeDeclarationStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TypeLiteral)
	 */
	public ITypeConstraint[] create(TypeLiteral node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.VariableDeclarationExpression)
	 */
	public ITypeConstraint[] create(VariableDeclarationExpression node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.VariableDeclarationFragment)
	 */
	public ITypeConstraint[] create(VariableDeclarationFragment node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.VariableDeclarationStatement)
	 */
	public ITypeConstraint[] create(VariableDeclarationStatement node) {
		return EMPTY_ARRAY;
	}

	/**
	 * @param node the AST node
	 * @return array of type constraints, may be empty
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.WhileStatement)
	 */
	public ITypeConstraint[] create(WhileStatement node) {
		return EMPTY_ARRAY;
	}

}

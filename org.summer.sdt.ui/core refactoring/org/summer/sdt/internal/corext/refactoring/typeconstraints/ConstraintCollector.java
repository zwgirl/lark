/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.corext.refactoring.typeconstraints;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.summer.sdt.core.dom.ASTVisitor;
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
import org.summer.sdt.core.dom.MarkerAnnotation;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.core.dom.MethodInvocation;
import org.summer.sdt.core.dom.NormalAnnotation;
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
import org.summer.sdt.core.dom.SingleMemberAnnotation;
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


public final class ConstraintCollector extends ASTVisitor {

	private final ConstraintCreator fCreator;
	private final Set<ITypeConstraint> fConstraints;

	public ConstraintCollector() {
		this(new FullConstraintCreator());
	}

	public ConstraintCollector(ConstraintCreator creator) {
		Assert.isNotNull(creator);
		fCreator= creator;
		fConstraints= new LinkedHashSet<ITypeConstraint>();
	}

	private void add(ITypeConstraint[] constraints){
		fConstraints.addAll(Arrays.asList(constraints));
	}

	public void clear(){
		fConstraints.clear();
	}

	public ITypeConstraint[] getConstraints(){
		return fConstraints.toArray(new ITypeConstraint[fConstraints.size()]);
	}

	//------------------------- visit methods -------------------------//

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.AnonymousClassDeclaration)
	 */
	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayAccess)
	 */
	@Override
	public boolean visit(ArrayAccess node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayCreation)
	 */
	@Override
	public boolean visit(ArrayCreation node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayInitializer)
	 */
	@Override
	public boolean visit(ArrayInitializer node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ArrayType)
	 */
	@Override
	public boolean visit(ArrayType node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.AssertStatement)
	 */
	@Override
	public boolean visit(AssertStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Assignment)
	 */
	@Override
	public boolean visit(Assignment node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Block)
	 */
	@Override
	public boolean visit(Block node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.BooleanLiteral)
	 */
	@Override
	public boolean visit(BooleanLiteral node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.BreakStatement)
	 */
	@Override
	public boolean visit(BreakStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CastExpression)
	 */
	@Override
	public boolean visit(CastExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CatchClause)
	 */
	@Override
	public boolean visit(CatchClause node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CharacterLiteral)
	 */
	@Override
	public boolean visit(CharacterLiteral node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ClassInstanceCreation)
	 */
	@Override
	public boolean visit(ClassInstanceCreation node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.CompilationUnit)
	 */
	@Override
	public boolean visit(CompilationUnit node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ConditionalExpression)
	 */
	@Override
	public boolean visit(ConditionalExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ConstructorInvocation)
	 */
	@Override
	public boolean visit(ConstructorInvocation node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ContinueStatement)
	 */
	@Override
	public boolean visit(ContinueStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.DoStatement)
	 */
	@Override
	public boolean visit(DoStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.EmptyStatement)
	 */
	@Override
	public boolean visit(EmptyStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ExpressionStatement)
	 */
	@Override
	public boolean visit(ExpressionStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.FieldAccess)
	 */
	@Override
	public boolean visit(FieldAccess node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.FieldDeclaration)
	 */
	@Override
	public boolean visit(FieldDeclaration node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ForStatement)
	 */
	@Override
	public boolean visit(ForStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.IfStatement)
	 */
	@Override
	public boolean visit(IfStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ImportDeclaration)
	 */
	@Override
	public boolean visit(ImportDeclaration node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.InfixExpression)
	 */
	@Override
	public boolean visit(InfixExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Initializer)
	 */
	@Override
	public boolean visit(Initializer node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.InstanceofExpression)
	 */
	@Override
	public boolean visit(InstanceofExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.Javadoc)
	 */
	@Override
	public boolean visit(Javadoc node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.LabeledStatement)
	 */
	@Override
	public boolean visit(LabeledStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.MarkerAnnotation)
	 */
	@Override
	public boolean visit(MarkerAnnotation node) {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.MethodDeclaration)
	 */
	@Override
	public boolean visit(MethodDeclaration node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.MethodInvocation)
	 */
	@Override
	public boolean visit(MethodInvocation node) {
		add(fCreator.create(node));
		return true;
	}
	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.NormalAnnotation)
	 */
	@Override
	public boolean visit(NormalAnnotation node) {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.NullLiteral)
	 */
	@Override
	public boolean visit(NullLiteral node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.NumberLiteral)
	 */
	@Override
	public boolean visit(NumberLiteral node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PackageDeclaration)
	 */
	@Override
	public boolean visit(PackageDeclaration node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ParenthesizedExpression)
	 */
	@Override
	public boolean visit(ParenthesizedExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PostfixExpression)
	 */
	@Override
	public boolean visit(PostfixExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PrefixExpression)
	 */
	@Override
	public boolean visit(PrefixExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.PrimitiveType)
	 */
	@Override
	public boolean visit(PrimitiveType node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.QualifiedName)
	 */
	@Override
	public boolean visit(QualifiedName node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ReturnStatement)
	 */
	@Override
	public boolean visit(ReturnStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SimpleName)
	 */
	@Override
	public boolean visit(SimpleName node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SimpleType)
	 */
	@Override
	public boolean visit(SimpleType node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SingleMemberAnnotation)
	 */
	@Override
	public boolean visit(SingleMemberAnnotation node) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SingleVariableDeclaration)
	 */
	@Override
	public boolean visit(SingleVariableDeclaration node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.StringLiteral)
	 */
	@Override
	public boolean visit(StringLiteral node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SuperConstructorInvocation)
	 */
	@Override
	public boolean visit(SuperConstructorInvocation node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SuperFieldAccess)
	 */
	@Override
	public boolean visit(SuperFieldAccess node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SuperMethodInvocation)
	 */
	@Override
	public boolean visit(SuperMethodInvocation node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SwitchCase)
	 */
	@Override
	public boolean visit(SwitchCase node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SwitchStatement)
	 */
	@Override
	public boolean visit(SwitchStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.SynchronizedStatement)
	 */
	@Override
	public boolean visit(SynchronizedStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ThisExpression)
	 */
	@Override
	public boolean visit(ThisExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ThrowStatement)
	 */
	@Override
	public boolean visit(ThrowStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TryStatement)
	 */
	@Override
	public boolean visit(TryStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TypeDeclaration)
	 */
	@Override
	public boolean visit(TypeDeclaration node) {
		add(fCreator.create(node));
		return true;

		// TODO account for enums and annotations
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TypeDeclarationStatement)
	 */
	@Override
	public boolean visit(TypeDeclarationStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.TypeLiteral)
	 */
	@Override
	public boolean visit(TypeLiteral node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.VariableDeclarationExpression)
	 */
	@Override
	public boolean visit(VariableDeclarationExpression node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.VariableDeclarationFragment)
	 */
	@Override
	public boolean visit(VariableDeclarationFragment node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.VariableDeclarationStatement)
	 */
	@Override
	public boolean visit(VariableDeclarationStatement node) {
		add(fCreator.create(node));
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.WhileStatement)
	 */
	@Override
	public boolean visit(WhileStatement node) {
		add(fCreator.create(node));
		return true;
	}
}

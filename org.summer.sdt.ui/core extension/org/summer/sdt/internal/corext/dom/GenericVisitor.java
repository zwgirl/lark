/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.corext.dom;

import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.ASTVisitor;
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
import org.summer.sdt.core.dom.CreationReference;
import org.summer.sdt.core.dom.Dimension;
import org.summer.sdt.core.dom.DoStatement;
import org.summer.sdt.core.dom.EmptyStatement;
import org.summer.sdt.core.dom.EnhancedForStatement;
import org.summer.sdt.core.dom.EnumConstantDeclaration;
import org.summer.sdt.core.dom.EnumDeclaration;
import org.summer.sdt.core.dom.ExpressionMethodReference;
import org.summer.sdt.core.dom.ExpressionStatement;
import org.summer.sdt.core.dom.FieldAccess;
import org.summer.sdt.core.dom.FieldDeclaration;
import org.summer.sdt.core.dom.ForStatement;
import org.summer.sdt.core.dom.IfStatement;
import org.summer.sdt.core.dom.ImportDeclaration;
import org.summer.sdt.core.dom.InfixExpression;
import org.summer.sdt.core.dom.Initializer;
import org.summer.sdt.core.dom.InstanceofExpression;
import org.summer.sdt.core.dom.IntersectionType;
import org.summer.sdt.core.dom.Javadoc;
import org.summer.sdt.core.dom.LabeledStatement;
import org.summer.sdt.core.dom.LambdaExpression;
import org.summer.sdt.core.dom.LineComment;
import org.summer.sdt.core.dom.MarkerAnnotation;
import org.summer.sdt.core.dom.MemberRef;
import org.summer.sdt.core.dom.MemberValuePair;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.core.dom.MethodInvocation;
import org.summer.sdt.core.dom.MethodRef;
import org.summer.sdt.core.dom.MethodRefParameter;
import org.summer.sdt.core.dom.Modifier;
import org.summer.sdt.core.dom.NameQualifiedType;
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
import org.summer.sdt.core.dom.SuperMethodReference;
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
import org.summer.sdt.core.dom.TypeMethodReference;
import org.summer.sdt.core.dom.TypeParameter;
import org.summer.sdt.core.dom.UnionType;
import org.summer.sdt.core.dom.VariableDeclarationExpression;
import org.summer.sdt.core.dom.VariableDeclarationFragment;
import org.summer.sdt.core.dom.VariableDeclarationStatement;
import org.summer.sdt.core.dom.WhileStatement;
import org.summer.sdt.core.dom.WildcardType;
import org.summer.sdt.internal.corext.util.JDTUIHelperClasses;

/**
 * ASTVisitor that forwards all <code>visit(*)</code> calls to {@link #visitNode(ASTNode)}.
 * <p>
 * Note: New code should better use {@link ASTVisitor#preVisit2(ASTNode)}.
 * </p>
 * 
 * @see JDTUIHelperClasses
 */
public class GenericVisitor extends ASTVisitor {

	public GenericVisitor() {
		super();
	}

	/**
	 * @param visitJavadocTags <code>true</code> if doc comment tags are
	 * to be visited by default, and <code>false</code> otherwise
	 * @see Javadoc#tags()
	 * @see #visit(Javadoc)
	 * @since 3.0
	 */
	public GenericVisitor(boolean visitJavadocTags) {
		super(visitJavadocTags);
	}

	//---- Hooks for subclasses -------------------------------------------------

	/**
	 * Visits the given type-specific AST node.
	 * 
	 * @param node the AST note to visit
	 * @return <code>true</code> if the children of this node should be visited, and
	 *         <code>false</code> if the children of this node should be skipped
	 */
	protected boolean visitNode(ASTNode node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * 
	 * @param node the AST note to visit
	 */
	protected void endVisitNode(ASTNode node) {
		// do nothing
	}

	@Override
	public void endVisit(AnnotationTypeDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(AnnotationTypeMemberDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(AnonymousClassDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ArrayAccess node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ArrayCreation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ArrayInitializer node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ArrayType node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(AssertStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(Assignment node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(Block node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(BlockComment node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(BooleanLiteral node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(BreakStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(CastExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(CatchClause node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(CharacterLiteral node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ClassInstanceCreation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(CompilationUnit node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ConditionalExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ConstructorInvocation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ContinueStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(CreationReference node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(Dimension node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(DoStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(EmptyStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(EnhancedForStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(EnumConstantDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(EnumDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ExpressionMethodReference node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ExpressionStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(FieldAccess node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(FieldDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ForStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(IfStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ImportDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(InfixExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(Initializer node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(InstanceofExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(IntersectionType node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(Javadoc node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(LabeledStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(LambdaExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(LineComment node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(MarkerAnnotation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(MemberRef node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(MemberValuePair node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(MethodDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(MethodInvocation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(MethodRef node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(MethodRefParameter node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(Modifier node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(NameQualifiedType node) {
		endVisitNode(node);
	}

	@Override
	public void endVisit(NormalAnnotation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(NullLiteral node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(NumberLiteral node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(PackageDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ParameterizedType node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ParenthesizedExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(PostfixExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(PrefixExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(PrimitiveType node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(QualifiedName node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(QualifiedType node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ReturnStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SimpleName node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SimpleType node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SingleMemberAnnotation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SingleVariableDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(StringLiteral node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SuperConstructorInvocation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SuperFieldAccess node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SuperMethodInvocation node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SuperMethodReference node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SwitchCase node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SwitchStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(SynchronizedStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TagElement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TextElement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ThisExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(ThrowStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TryStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TypeDeclaration node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TypeDeclarationStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TypeLiteral node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TypeMethodReference node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(TypeParameter node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(UnionType node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(VariableDeclarationExpression node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(VariableDeclarationFragment node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(VariableDeclarationStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(WhileStatement node) {
		endVisitNode(node);
	}
	@Override
	public void endVisit(WildcardType node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(AnnotationTypeDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ArrayAccess node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ArrayCreation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ArrayInitializer node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ArrayType node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(AssertStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(Assignment node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(Block node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(BlockComment node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(BooleanLiteral node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(BreakStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(CastExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(CatchClause node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(CharacterLiteral node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ClassInstanceCreation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(CompilationUnit node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ConditionalExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ConstructorInvocation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ContinueStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(CreationReference node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(Dimension node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(DoStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(EmptyStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(EnhancedForStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(EnumConstantDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(EnumDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ExpressionMethodReference node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ExpressionStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(FieldAccess node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(FieldDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ForStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(IfStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ImportDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(InfixExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(Initializer node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(InstanceofExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(IntersectionType node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(Javadoc node) {
		if (super.visit(node))
			return visitNode(node);
		else
			return false;
	}
	@Override
	public boolean visit(LabeledStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(LambdaExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(LineComment node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(MarkerAnnotation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(MemberRef node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(MemberValuePair node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(MethodDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(MethodInvocation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(MethodRef node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(MethodRefParameter node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(Modifier node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(NameQualifiedType node) {
		return visitNode(node);
	}

	@Override
	public boolean visit(NormalAnnotation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(NullLiteral node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(NumberLiteral node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(PackageDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ParameterizedType node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ParenthesizedExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(PostfixExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(PrefixExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(PrimitiveType node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(QualifiedName node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(QualifiedType node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ReturnStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SimpleName node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SimpleType node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SingleMemberAnnotation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SingleVariableDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(StringLiteral node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SuperConstructorInvocation node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SuperFieldAccess node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SuperMethodInvocation node) {
		return visitNode(node);
	}


	@Override
	public boolean visit(SuperMethodReference node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SwitchCase node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SwitchStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(SynchronizedStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TagElement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TextElement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ThisExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(ThrowStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TryStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TypeDeclaration node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TypeDeclarationStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TypeLiteral node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TypeMethodReference node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(TypeParameter node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(UnionType node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(VariableDeclarationExpression node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(VariableDeclarationFragment node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(VariableDeclarationStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(WhileStatement node) {
		return visitNode(node);
	}
	@Override
	public boolean visit(WildcardType node) {
		return visitNode(node);
	}
}

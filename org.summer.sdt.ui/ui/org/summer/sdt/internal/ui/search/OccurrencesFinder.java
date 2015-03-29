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
package org.summer.sdt.internal.ui.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.ASTVisitor;
import org.summer.sdt.core.dom.Assignment;
import org.summer.sdt.core.dom.ClassInstanceCreation;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.dom.Expression;
import org.summer.sdt.core.dom.FieldAccess;
import org.summer.sdt.core.dom.FieldDeclaration;
import org.summer.sdt.core.dom.IBinding;
import org.summer.sdt.core.dom.IMethodBinding;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.IVariableBinding;
import org.summer.sdt.core.dom.ImportDeclaration;
import org.summer.sdt.core.dom.MethodInvocation;
import org.summer.sdt.core.dom.Modifier;
import org.summer.sdt.core.dom.Name;
import org.summer.sdt.core.dom.NameQualifiedType;
import org.summer.sdt.core.dom.NodeFinder;
import org.summer.sdt.core.dom.ParameterizedType;
import org.summer.sdt.core.dom.PostfixExpression;
import org.summer.sdt.core.dom.PrefixExpression;
import org.summer.sdt.core.dom.QualifiedName;
import org.summer.sdt.core.dom.SimpleName;
import org.summer.sdt.core.dom.SimpleType;
import org.summer.sdt.core.dom.SingleVariableDeclaration;
import org.summer.sdt.core.dom.Type;
import org.summer.sdt.core.dom.VariableDeclarationFragment;
import org.summer.sdt.core.dom.PrefixExpression.Operator;
import org.summer.sdt.internal.corext.dom.ASTNodes;
import org.summer.sdt.internal.corext.dom.Bindings;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.ui.viewsupport.BasicElementLabels;

public class OccurrencesFinder extends ASTVisitor implements IOccurrencesFinder {

	public static final String ID= "OccurrencesFinder"; //$NON-NLS-1$

	public static final String IS_WRITEACCESS= "writeAccess"; //$NON-NLS-1$
	public static final String IS_VARIABLE= "variable"; //$NON-NLS-1$

	private CompilationUnit fRoot;
	private Name fSelectedNode;
	private IBinding fTarget;

	private List<OccurrenceLocation> fResult;
	private Set<Name> fWriteUsages;

	private boolean fTargetIsStaticMethodImport;

	private String fReadDescription;
	private String fWriteDescription;

	public OccurrencesFinder() {
		super(true);
	}

	public String initialize(CompilationUnit root, int offset, int length) {
		return initialize(root, NodeFinder.perform(root, offset, length));
	}

	public String initialize(CompilationUnit root, ASTNode node) {
		if (!(node instanceof Name))
			return SearchMessages.OccurrencesFinder_no_element;
		fRoot= root;
		fSelectedNode= (Name)node;
		fTarget= fSelectedNode.resolveBinding();
		if (fTarget == null)
			return SearchMessages.OccurrencesFinder_no_binding;
		fTarget= getBindingDeclaration(fTarget);

		fTargetIsStaticMethodImport= isStaticImport(fSelectedNode.getParent());
		fReadDescription= Messages.format(SearchMessages.OccurrencesFinder_occurrence_description, BasicElementLabels.getJavaElementName(fTarget.getName()));
		fWriteDescription= Messages.format(SearchMessages.OccurrencesFinder_occurrence_write_description, BasicElementLabels.getJavaElementName(fTarget.getName()));
		return null;
	}

	private void performSearch() {
		if (fResult == null) {
			fResult= new ArrayList<OccurrenceLocation>();
			fWriteUsages= new HashSet<Name>();
			fRoot.accept(this);
		}
	}

	public OccurrenceLocation[] getOccurrences() {
		performSearch();
		if (fResult.isEmpty())
			return null;
		return fResult.toArray(new OccurrenceLocation[fResult.size()]);
	}

	public CompilationUnit getASTRoot() {
		return fRoot;
	}

	/*
	 * @see org.summer.sdt.internal.ui.search.IOccurrencesFinder#getJobLabel()
	 */
	public String getJobLabel() {
		return SearchMessages.OccurrencesFinder_searchfor ;
	}

	public String getElementName() {
		if (fSelectedNode != null) {
			return ASTNodes.asString(fSelectedNode);
		}
		return null;
	}

	public String getUnformattedPluralLabel() {
		return SearchMessages.OccurrencesFinder_label_plural;
	}

	public String getUnformattedSingularLabel() {
		return SearchMessages.OccurrencesFinder_label_singular;
	}

	@Override
	public boolean visit(QualifiedName node) {
		final IBinding binding= node.resolveBinding();
		if (binding instanceof IVariableBinding && ((IVariableBinding)binding).isField()) {
			SimpleName name= node.getName();
			return !addUsage(name, name.resolveBinding());
		}
		if (binding instanceof IMethodBinding) {
			if (isStaticImport(node)) {
				SimpleName name= node.getName();
				return !addPossibleStaticImport(name, (IMethodBinding) binding);
			}
		}
		return !addUsage(node, binding);
	}

	private static boolean isStaticImport(ASTNode node) {
		if (!(node instanceof QualifiedName))
			return false;

		ASTNode parent= ((QualifiedName)node).getParent();
		return parent instanceof ImportDeclaration && ((ImportDeclaration) parent).isStatic();
	}

	@Override
	public boolean visit(MethodInvocation node) {
		if (fTargetIsStaticMethodImport) {
			return !addPossibleStaticImport(node.getName(), node.resolveMethodBinding());
		}
		return true;
	}

	@Override
	public boolean visit(SimpleName node) {
		addUsage(node, node.resolveBinding());
		return true;
	}

	/*
	 * @see org.summer.sdt.core.dom.ASTVisitor#visit(org.summer.sdt.core.dom.ConstructorInvocation)
	 */
	@Override
	public boolean visit(ClassInstanceCreation node) {
		// match with the constructor and the type.

		Type type= node.getType();
		if (type instanceof ParameterizedType) {
			type= ((ParameterizedType) type).getType();
		}
		if (type instanceof SimpleType) {
			Name name= ((SimpleType) type).getName();
			if (name instanceof QualifiedName)
				name= ((QualifiedName)name).getName();
			addUsage(name, node.resolveConstructorBinding());
		} else if (type instanceof NameQualifiedType) {
			Name name= ((NameQualifiedType) type).getName();
			addUsage(name, node.resolveConstructorBinding());
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(Assignment node) {
		SimpleName name= getSimpleName(node.getLeftHandSide());
		if (name != null)
			addWrite(name, name.resolveBinding());
		return true;
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		addWrite(node.getName(), node.resolveBinding());
		return true;
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		if (node.getParent() instanceof FieldDeclaration || node.getInitializer() != null)
			addWrite(node.getName(), node.resolveBinding());
		return true;
	}

	@Override
	public boolean visit(PrefixExpression node) {
		PrefixExpression.Operator operator= node.getOperator();
		if (operator == Operator.INCREMENT || operator == Operator.DECREMENT) {
			SimpleName name= getSimpleName(node.getOperand());
			if (name != null)
				addWrite(name, name.resolveBinding());
		}
		return true;
	}

	@Override
	public boolean visit(PostfixExpression node) {
		SimpleName name= getSimpleName(node.getOperand());
		if (name != null)
			addWrite(name, name.resolveBinding());
		return true;
	}

	private boolean addWrite(Name node, IBinding binding) {
		if (binding != null && Bindings.equals(getBindingDeclaration(binding), fTarget)) {
			fWriteUsages.add(node);
			return true;
		}
		return false;
	}

	private boolean addUsage(Name node, IBinding binding) {
		if (binding != null && Bindings.equals(getBindingDeclaration(binding), fTarget)) {
			int flag= 0;
			String description= fReadDescription;
			if (fTarget instanceof IVariableBinding) {
				boolean isWrite= fWriteUsages.remove(node);
				flag= isWrite ? F_WRITE_OCCURRENCE : F_READ_OCCURRENCE;
				if (isWrite)
					description= fWriteDescription;
			}
			fResult.add(new OccurrenceLocation(node.getStartPosition(), node.getLength(), flag, description));
			return true;
		}
		return false;
	}

	public int getSearchKind() {
		return K_OCCURRENCE;
	}



	private boolean addPossibleStaticImport(Name node, IMethodBinding binding) {
		if (binding == null || node == null || !(fTarget instanceof IMethodBinding) || !Modifier.isStatic(binding.getModifiers()))
			return false;

		IMethodBinding targetMethodBinding= (IMethodBinding)fTarget;
		if ((fTargetIsStaticMethodImport || Modifier.isStatic(targetMethodBinding.getModifiers())) && (targetMethodBinding.getDeclaringClass().getTypeDeclaration() == binding.getDeclaringClass().getTypeDeclaration())) {
			if (node.getFullyQualifiedName().equals(targetMethodBinding.getName())) {
				fResult.add(new OccurrenceLocation(node.getStartPosition(), node.getLength(), 0, fReadDescription));
				return true;
			}
		}
		return false;
	}

	private SimpleName getSimpleName(Expression expression) {
		if (expression instanceof SimpleName)
			return ((SimpleName)expression);
		else if (expression instanceof QualifiedName)
			return (((QualifiedName) expression).getName());
		else if (expression instanceof FieldAccess)
			return ((FieldAccess)expression).getName();
		return null;
	}

	private IBinding getBindingDeclaration(IBinding binding) {
		switch (binding.getKind()) {
			case IBinding.TYPE :
				return ((ITypeBinding)binding).getTypeDeclaration();
			case IBinding.METHOD :
				return ((IMethodBinding)binding).getMethodDeclaration();
			case IBinding.VARIABLE :
				return ((IVariableBinding)binding).getVariableDeclaration();
			default:
				return binding;
		}
	}

	public String getID() {
		return ID;
	}
}

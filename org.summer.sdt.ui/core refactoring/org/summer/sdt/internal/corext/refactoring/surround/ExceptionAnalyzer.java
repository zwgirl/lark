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
package org.summer.sdt.internal.corext.refactoring.surround;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.BodyDeclaration;
import org.summer.sdt.core.dom.ClassInstanceCreation;
import org.summer.sdt.core.dom.ConstructorInvocation;
import org.summer.sdt.core.dom.IMethodBinding;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.LambdaExpression;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.core.dom.MethodInvocation;
import org.summer.sdt.core.dom.SuperConstructorInvocation;
import org.summer.sdt.core.dom.SuperMethodInvocation;
import org.summer.sdt.core.dom.ThrowStatement;
import org.summer.sdt.core.dom.Type;
import org.summer.sdt.core.dom.VariableDeclarationExpression;
import org.summer.sdt.internal.corext.dom.Bindings;
import org.summer.sdt.internal.corext.dom.Selection;
import org.summer.sdt.internal.corext.refactoring.util.AbstractExceptionAnalyzer;

public class ExceptionAnalyzer extends AbstractExceptionAnalyzer {

	private Selection fSelection;

	private static class ExceptionComparator implements Comparator<ITypeBinding> {
		public int compare(ITypeBinding o1, ITypeBinding o2) {
			int d1= getDepth(o1);
			int d2= getDepth(o2);
			if (d1 < d2)
				return 1;
			if (d1 > d2)
				return -1;
			return 0;
		}
		private int getDepth(ITypeBinding binding) {
			int result= 0;
			while (binding != null) {
				binding= binding.getSuperclass();
				result++;
			}
			return result;
		}
	}

	private ExceptionAnalyzer(Selection selection) {
		Assert.isNotNull(selection);
		fSelection= selection;
	}

	public static ITypeBinding[] perform(BodyDeclaration enclosingNode, Selection selection) {
		ExceptionAnalyzer analyzer= new ExceptionAnalyzer(selection);
		enclosingNode.accept(analyzer);
		List<ITypeBinding> exceptions= analyzer.getCurrentExceptions();
		if (enclosingNode.getNodeType() == ASTNode.METHOD_DECLARATION) {
			List<Type> thrownExceptions= ((MethodDeclaration) enclosingNode).thrownExceptionTypes();
			for (Iterator<Type> thrown= thrownExceptions.iterator(); thrown.hasNext();) {
				ITypeBinding thrownException= thrown.next().resolveBinding();
				if (thrownException != null) {
					for (Iterator<ITypeBinding> excep= exceptions.iterator(); excep.hasNext();) {
						ITypeBinding exception= excep.next();
						if (exception.isAssignmentCompatible(thrownException))
							excep.remove();
					}
				}
			}
		}
		Collections.sort(exceptions, new ExceptionComparator());
		return exceptions.toArray(new ITypeBinding[exceptions.size()]);
	}
	
	@Override
	public boolean visit(LambdaExpression node) {
		/*
		 * FIXME: Remove this method. It's just a workaround for bug 433426.
		 * ExceptionAnalyzer forces clients to on the wrong enclosing node (BodyDeclaration instead of LambdaExpression's body).
		 */
		return true;
	}

	@Override
	public boolean visit(ThrowStatement node) {
		ITypeBinding exception= node.getExpression().resolveTypeBinding();
		if (!isSelected(node) || exception == null || Bindings.isRuntimeException(exception)) // Safety net for null bindings when compiling fails.
			return true;

		addException(exception, node.getAST());
		return true;
	}

	@Override
	public boolean visit(MethodInvocation node) {
		if (!isSelected(node))
			return false;
		return handleExceptions(node.resolveMethodBinding(), node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		if (!isSelected(node))
			return false;
		return handleExceptions(node.resolveMethodBinding(), node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		if (!isSelected(node))
			return false;
		return handleExceptions(node.resolveConstructorBinding(), node);
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		if (!isSelected(node))
			return false;
		return handleExceptions(node.resolveConstructorBinding(), node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		if (!isSelected(node))
			return false;
		return handleExceptions(node.resolveConstructorBinding(), node);
	}

	@Override
	public boolean visit(VariableDeclarationExpression node) {
		if (!isSelected(node))
			return false;
		return super.visit(node);
	}

	private boolean handleExceptions(IMethodBinding binding, ASTNode node) {
		if (binding == null)
			return true;
		ITypeBinding[] exceptions= binding.getExceptionTypes();
		for (int i= 0; i < exceptions.length; i++) {
			addException(exceptions[i], node.getAST());
		}
		return true;
	}

	private boolean isSelected(ASTNode node) {
		return fSelection.getVisitSelectionMode(node) == Selection.SELECTED;
	}
}

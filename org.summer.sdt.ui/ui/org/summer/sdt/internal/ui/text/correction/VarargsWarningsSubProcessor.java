/*******************************************************************************
 * Copyright (c) 2011, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.summer.sdt.internal.ui.text.correction;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.ISharedImages;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.core.dom.AST;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.ClassInstanceCreation;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.dom.IMethodBinding;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.MarkerAnnotation;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.core.dom.MethodInvocation;
import org.summer.sdt.core.dom.Modifier;
import org.summer.sdt.core.dom.rewrite.ASTRewrite;
import org.summer.sdt.core.dom.rewrite.ListRewrite;
import org.summer.sdt.internal.corext.util.JavaModelUtil;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.internal.ui.text.correction.proposals.LinkedCorrectionProposal;
import org.summer.sdt.ui.text.java.IInvocationContext;
import org.summer.sdt.ui.text.java.IProblemLocation;
import org.summer.sdt.ui.text.java.correction.ASTRewriteCorrectionProposal;
import org.summer.sdt.ui.text.java.correction.ICommandAccess;

public class VarargsWarningsSubProcessor {

	private static class AddSafeVarargsProposal extends LinkedCorrectionProposal {

		private IMethodBinding fMethodBinding;

		private MethodDeclaration fMethodDeclaration;

		public AddSafeVarargsProposal(String label, ICompilationUnit cu, MethodDeclaration methodDeclaration, IMethodBinding methodBinding, int relevance) {
			super(label, cu, null, relevance, JavaPluginImages.get(JavaPluginImages.IMG_OBJS_JAVADOCTAG));
			fMethodDeclaration= methodDeclaration;
			fMethodBinding= methodBinding;
		}

		/* (non-Javadoc)
		 * @see org.summer.sdt.internal.ui.text.correction.ASTRewriteCorrectionProposal#getRewrite()
		 */
		@Override
		protected ASTRewrite getRewrite() throws CoreException {
			if (fMethodDeclaration == null) {
				CompilationUnit astRoot= ASTResolving.createQuickFixAST(getCompilationUnit(), null);
				fMethodDeclaration= (MethodDeclaration) astRoot.findDeclaringNode(fMethodBinding.getKey());
			}
			AST ast= fMethodDeclaration.getAST();
			ASTRewrite rewrite= ASTRewrite.create(ast);
			ListRewrite listRewrite= rewrite.getListRewrite(fMethodDeclaration, MethodDeclaration.MODIFIERS2_PROPERTY);

			MarkerAnnotation annotation= ast.newMarkerAnnotation();
			String importString= createImportRewrite((CompilationUnit) fMethodDeclaration.getRoot()).addImport("java.lang.SafeVarargs"); //$NON-NLS-1$
			annotation.setTypeName(ast.newName(importString));
			listRewrite.insertFirst(annotation, null);

			// set up linked mode
			addLinkedPosition(rewrite.track(annotation), true, "annotation"); //$NON-NLS-1$

			return rewrite;
		}

	}

	public static void addAddSafeVarargsProposals(IInvocationContext context, IProblemLocation problem, Collection<ICommandAccess> proposals) {
		ASTNode coveringNode= problem.getCoveringNode(context.getASTRoot());

		MethodDeclaration methodDeclaration= ASTResolving.findParentMethodDeclaration(coveringNode);
		if (methodDeclaration == null)
			return;
		
		IMethodBinding methodBinding= methodDeclaration.resolveBinding();
		if (methodBinding == null)
			return;
		
		int modifiers= methodBinding.getModifiers();
		if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers) && ! methodBinding.isConstructor())
			return; 
		
		String label= CorrectionMessages.VarargsWarningsSubProcessor_add_safevarargs_label;
		AddSafeVarargsProposal proposal= new AddSafeVarargsProposal(label, context.getCompilationUnit(), methodDeclaration, null, IProposalRelevance.ADD_SAFEVARARGS);
		proposals.add(proposal);
	}

	public static void addAddSafeVarargsToDeclarationProposals(IInvocationContext context, IProblemLocation problem, Collection<ICommandAccess> proposals) {
		if (!JavaModelUtil.is17OrHigher(context.getCompilationUnit().getJavaProject()))
			return;

		ASTNode coveringNode= problem.getCoveringNode(context.getASTRoot());
		IMethodBinding methodBinding;
		if (coveringNode instanceof MethodInvocation) {
			methodBinding= ((MethodInvocation) coveringNode).resolveMethodBinding();
		} else if (coveringNode instanceof ClassInstanceCreation) {
			methodBinding= ((ClassInstanceCreation) coveringNode).resolveConstructorBinding();
		} else {
			return;
		}
		if (methodBinding == null)
			return;
		
		String label= Messages.format(CorrectionMessages.VarargsWarningsSubProcessor_add_safevarargs_to_method_label, methodBinding.getName());

		ITypeBinding declaringType= methodBinding.getDeclaringClass();
		CompilationUnit astRoot= (CompilationUnit) coveringNode.getRoot();
		if (declaringType != null && declaringType.isFromSource()) {
			try {
				ICompilationUnit targetCu= ASTResolving.findCompilationUnitForBinding(context.getCompilationUnit(), astRoot, declaringType);
				if (targetCu != null) {
					AddSafeVarargsProposal proposal= new AddSafeVarargsProposal(label, targetCu, null, methodBinding.getMethodDeclaration(), IProposalRelevance.ADD_SAFEVARARGS);
					proposals.add(proposal);
				}
			} catch (JavaModelException e) {
				return;
			}
		}
	}

	public static void addRemoveSafeVarargsProposals(IInvocationContext context, IProblemLocation problem, Collection<ICommandAccess> proposals) {
		ASTNode coveringNode= problem.getCoveringNode(context.getASTRoot());
		if (!(coveringNode instanceof MethodDeclaration))
			return;

		MethodDeclaration methodDeclaration= (MethodDeclaration) coveringNode;
		MarkerAnnotation annotation= null;

		List<? extends ASTNode> modifiers= methodDeclaration.modifiers();
		for (Iterator<? extends ASTNode> iterator= modifiers.iterator(); iterator.hasNext();) {
			ASTNode node= iterator.next();
			if (node instanceof MarkerAnnotation) {
				annotation= (MarkerAnnotation) node;
				if ("SafeVarargs".equals(annotation.resolveAnnotationBinding().getName())) { //$NON-NLS-1$
					break;
				}
			}
		}

		if (annotation == null)
			return;

		ASTRewrite rewrite= ASTRewrite.create(coveringNode.getAST());
		rewrite.remove(annotation, null);

		String label= CorrectionMessages.VarargsWarningsSubProcessor_remove_safevarargs_label;
		Image image= JavaPlugin.getDefault().getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE);
		ASTRewriteCorrectionProposal proposal= new ASTRewriteCorrectionProposal(label, context.getCompilationUnit(), rewrite, IProposalRelevance.REMOVE_SAFEVARARGS, image);
		proposals.add(proposal);
	}

}

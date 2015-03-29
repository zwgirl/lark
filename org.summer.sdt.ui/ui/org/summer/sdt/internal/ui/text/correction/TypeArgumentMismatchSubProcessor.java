/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
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

import org.eclipse.swt.graphics.Image;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.ParameterizedType;
import org.summer.sdt.core.dom.SimpleName;
import org.summer.sdt.core.dom.rewrite.ASTRewrite;
import org.summer.sdt.internal.corext.dom.ASTNodes;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.ui.text.java.IInvocationContext;
import org.summer.sdt.ui.text.java.IProblemLocation;
import org.summer.sdt.ui.text.java.correction.ASTRewriteCorrectionProposal;
import org.summer.sdt.ui.text.java.correction.ICommandAccess;


public class TypeArgumentMismatchSubProcessor {

//	public static void getTypeParameterMismatchProposals(IInvocationContext context, IProblemLocation problem, Collection proposals) {
//	CompilationUnit astRoot= context.getASTRoot();
//	ASTNode selectedNode= problem.getCoveredNode(astRoot);
//	if (!(selectedNode instanceof SimpleName)) {
//	return;
//	}

//	ASTNode normalizedNode= ASTNodes.getNormalizedNode(selectedNode);
//	if (!(normalizedNode instanceof ParameterizedType)) {
//	return;
//	}
//	// waiting for result of https://bugs.eclipse.org/bugs/show_bug.cgi?id=81544


//	}

	public static void removeMismatchedArguments(IInvocationContext context, IProblemLocation problem, Collection<ICommandAccess> proposals){
		ICompilationUnit cu= context.getCompilationUnit();
		ASTNode selectedNode= problem.getCoveredNode(context.getASTRoot());
		if (!(selectedNode instanceof SimpleName)) {
			return;
		}

		ASTNode normalizedNode=ASTNodes.getNormalizedNode(selectedNode);
		if (normalizedNode instanceof ParameterizedType) {
			ASTRewrite rewrite = ASTRewrite.create(normalizedNode.getAST());
			ParameterizedType pt = (ParameterizedType) normalizedNode;
			ASTNode mt = rewrite.createMoveTarget(pt.getType());
			rewrite.replace(pt, mt, null);
			String label= CorrectionMessages.TypeArgumentMismatchSubProcessor_removeTypeArguments;
			Image image= JavaPluginImages.get(JavaPluginImages.IMG_CORRECTION_CHANGE);
			ASTRewriteCorrectionProposal proposal= new ASTRewriteCorrectionProposal(label, cu, rewrite, IProposalRelevance.REMOVE_TYPE_ARGUMENTS, image);
			proposals.add(proposal);
		}
	}

	public static void getInferDiamondArgumentsProposal(IInvocationContext context, IProblemLocation problem, Collection<ICommandAccess> proposals) {
		ASTNode selectedNode= problem.getCoveredNode(context.getASTRoot());
		if (!(selectedNode instanceof SimpleName)) {
			return;
		}
		
		QuickAssistProcessor.getInferDiamondArgumentsProposal(context, selectedNode, null, proposals);
	}

}

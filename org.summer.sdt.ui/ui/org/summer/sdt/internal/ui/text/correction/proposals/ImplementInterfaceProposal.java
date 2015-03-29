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
package org.summer.sdt.internal.ui.text.correction.proposals;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.dom.AST;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.dom.IBinding;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.Type;
import org.summer.sdt.core.dom.TypeDeclaration;
import org.summer.sdt.core.dom.rewrite.ASTRewrite;
import org.summer.sdt.core.dom.rewrite.ImportRewrite;
import org.summer.sdt.core.dom.rewrite.ListRewrite;
import org.summer.sdt.core.dom.rewrite.ImportRewrite.ImportRewriteContext;
import org.summer.sdt.internal.corext.codemanipulation.ContextSensitiveImportRewriteContext;
import org.summer.sdt.internal.corext.dom.Bindings;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.internal.ui.text.correction.ASTResolving;
import org.summer.sdt.internal.ui.text.correction.CorrectionMessages;
import org.summer.sdt.internal.ui.viewsupport.BasicElementLabels;

public class ImplementInterfaceProposal extends LinkedCorrectionProposal {

	private IBinding fBinding;
	private CompilationUnit fAstRoot;
	private ITypeBinding fNewInterface;

	public ImplementInterfaceProposal(ICompilationUnit targetCU, ITypeBinding binding, CompilationUnit astRoot, ITypeBinding newInterface, int relevance) {
		super("", targetCU, null, relevance, JavaPluginImages.get(JavaPluginImages.IMG_CORRECTION_CHANGE)); //$NON-NLS-1$

		Assert.isTrue(binding != null && Bindings.isDeclarationBinding(binding));

		fBinding= binding;
		fAstRoot= astRoot;
		fNewInterface= newInterface;

		String[] args= { BasicElementLabels.getJavaElementName(binding.getName()), BasicElementLabels.getJavaElementName(Bindings.getRawName(newInterface)) };
		setDisplayName(Messages.format(CorrectionMessages.ImplementInterfaceProposal_name, args));
	}

	@Override
	protected ASTRewrite getRewrite() throws CoreException {
		ASTNode boundNode= fAstRoot.findDeclaringNode(fBinding);
		ASTNode declNode= null;
		CompilationUnit newRoot= fAstRoot;
		if (boundNode != null) {
			declNode= boundNode; // is same CU
		} else {
			newRoot= ASTResolving.createQuickFixAST(getCompilationUnit(), null);
			declNode= newRoot.findDeclaringNode(fBinding.getKey());
		}
		ImportRewrite imports= createImportRewrite(newRoot);

		if (declNode instanceof TypeDeclaration) {
			AST ast= declNode.getAST();
			ASTRewrite rewrite= ASTRewrite.create(ast);

			ImportRewriteContext importRewriteContext= new ContextSensitiveImportRewriteContext(declNode, imports);
			Type newInterface= imports.addImport(fNewInterface, ast, importRewriteContext);
			ListRewrite listRewrite= rewrite.getListRewrite(declNode, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY);
			listRewrite.insertLast(newInterface, null);

			// set up linked mode
			final String KEY_TYPE= "type"; //$NON-NLS-1$
			addLinkedPosition(rewrite.track(newInterface), true, KEY_TYPE);
			return rewrite;
		}
		return null;
	}


}

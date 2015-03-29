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

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.dom.AST;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.dom.IBinding;
import org.summer.sdt.core.dom.IMethodBinding;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.Javadoc;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.core.dom.TagElement;
import org.summer.sdt.core.dom.TextElement;
import org.summer.sdt.core.dom.Type;
import org.summer.sdt.core.dom.TypeDeclaration;
import org.summer.sdt.core.dom.TypeParameter;
import org.summer.sdt.core.dom.rewrite.ASTRewrite;
import org.summer.sdt.core.dom.rewrite.ListRewrite;
import org.summer.sdt.core.dom.rewrite.ImportRewrite.ImportRewriteContext;
import org.summer.sdt.internal.corext.codemanipulation.ContextSensitiveImportRewriteContext;
import org.summer.sdt.internal.corext.dom.Bindings;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.internal.ui.text.correction.ASTResolving;
import org.summer.sdt.internal.ui.text.correction.CorrectionMessages;
import org.summer.sdt.internal.ui.text.correction.JavadocTagsSubProcessor;
import org.summer.sdt.internal.ui.viewsupport.BasicElementLabels;

public class AddTypeParameterProposal extends LinkedCorrectionProposal {

	private IBinding fBinding;
	private CompilationUnit fAstRoot;

	private final String fTypeParamName;
	private final ITypeBinding[] fBounds;

	public AddTypeParameterProposal(ICompilationUnit targetCU, IBinding binding, CompilationUnit astRoot, String name, ITypeBinding[] bounds, int relevance) {
		super("", targetCU, null, relevance, JavaPluginImages.get(JavaPluginImages.IMG_FIELD_PUBLIC)); //$NON-NLS-1$

		Assert.isTrue(binding != null && Bindings.isDeclarationBinding(binding));
		Assert.isTrue(binding instanceof IMethodBinding || binding instanceof ITypeBinding);

		fBinding= binding;
		fAstRoot= astRoot;
		fTypeParamName= name;
		fBounds= bounds;

		if (binding instanceof IMethodBinding) {
			String[] args= { BasicElementLabels.getJavaElementName(fTypeParamName), ASTResolving.getMethodSignature((IMethodBinding) binding) };
			setDisplayName(Messages.format(CorrectionMessages.AddTypeParameterProposal_method_label, args));
		} else {
			String[] args= { BasicElementLabels.getJavaElementName(fTypeParamName), ASTResolving.getTypeSignature((ITypeBinding) binding) };
			setDisplayName(Messages.format(CorrectionMessages.AddTypeParameterProposal_type_label, args));
		}
	}

	@Override
	protected ASTRewrite getRewrite() throws CoreException {
		ASTNode boundNode= fAstRoot.findDeclaringNode(fBinding);
		ASTNode declNode= null;

		if (boundNode != null) {
			declNode= boundNode; // is same CU
			createImportRewrite(fAstRoot);
		} else {
			CompilationUnit newRoot= ASTResolving.createQuickFixAST(getCompilationUnit(), null);
			declNode= newRoot.findDeclaringNode(fBinding.getKey());
			createImportRewrite(newRoot);
		}
		AST ast= declNode.getAST();
		TypeParameter newTypeParam= ast.newTypeParameter();
		newTypeParam.setName(ast.newSimpleName(fTypeParamName));
		if (fBounds != null && fBounds.length > 0) {
			List<Type> typeBounds= newTypeParam.typeBounds();
			ImportRewriteContext importRewriteContext= new ContextSensitiveImportRewriteContext(declNode, getImportRewrite());
			for (int i= 0; i < fBounds.length; i++) {
				Type newBound= getImportRewrite().addImport(fBounds[i], ast, importRewriteContext);
				typeBounds.add(newBound);
			}
		}
		ASTRewrite rewrite= ASTRewrite.create(ast);
		ListRewrite listRewrite;
		Javadoc javadoc;
		List<TypeParameter> otherTypeParams;
		if (declNode instanceof TypeDeclaration) {
			TypeDeclaration declaration= (TypeDeclaration) declNode;
			listRewrite= rewrite.getListRewrite(declaration, TypeDeclaration.TYPE_PARAMETERS_PROPERTY);
			otherTypeParams= declaration.typeParameters();
			javadoc= declaration.getJavadoc();
		} else {
			MethodDeclaration declaration= (MethodDeclaration) declNode;
			listRewrite= rewrite.getListRewrite(declNode, MethodDeclaration.TYPE_PARAMETERS_PROPERTY);
			otherTypeParams= declaration.typeParameters();
			javadoc= declaration.getJavadoc();
		}
		listRewrite.insertLast(newTypeParam, null);

		if (javadoc != null && otherTypeParams != null) {
			ListRewrite tagsRewriter= rewrite.getListRewrite(javadoc, Javadoc.TAGS_PROPERTY);
			Set<String> previousNames= JavadocTagsSubProcessor.getPreviousTypeParamNames(otherTypeParams, null);

			String name= '<' + fTypeParamName + '>';
			TagElement newTag= ast.newTagElement();
			newTag.setTagName(TagElement.TAG_PARAM);
			TextElement text= ast.newTextElement();
			text.setText(name);
			newTag.fragments().add(text);

			JavadocTagsSubProcessor.insertTag(tagsRewriter, newTag, previousNames);
		}
		return rewrite;
	}


}

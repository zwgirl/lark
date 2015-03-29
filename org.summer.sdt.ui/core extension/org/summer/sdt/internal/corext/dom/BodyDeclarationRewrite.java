/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.corext.dom;

import java.util.List;

import org.eclipse.text.edits.TextEditGroup;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.BodyDeclaration;
import org.summer.sdt.core.dom.ChildListPropertyDescriptor;
import org.summer.sdt.core.dom.rewrite.ASTRewrite;
import org.summer.sdt.core.dom.rewrite.ListRewrite;
import org.summer.sdt.internal.corext.util.JDTUIHelperClasses;


/**
 * Rewrite helper for body declarations.
 * 
 * @see ASTNodes#getBodyDeclarationsProperty(ASTNode)
 * @see JDTUIHelperClasses
 */
public class BodyDeclarationRewrite {

	private ASTNode fTypeNode;
	private ListRewrite fListRewrite;

	public static BodyDeclarationRewrite create(ASTRewrite rewrite, ASTNode typeNode) {
		return new BodyDeclarationRewrite(rewrite, typeNode);
	}

	private BodyDeclarationRewrite(ASTRewrite rewrite, ASTNode typeNode) {
		ChildListPropertyDescriptor property= ASTNodes.getBodyDeclarationsProperty(typeNode);
		fTypeNode= typeNode;
		fListRewrite= rewrite.getListRewrite(typeNode, property);
	}

	public void insert(BodyDeclaration decl, TextEditGroup description) {
		List<BodyDeclaration> container= ASTNodes.getBodyDeclarations(fTypeNode);
		int index= ASTNodes.getInsertionIndex(decl, container);
		fListRewrite.insertAt(decl, index, description);
	}
}

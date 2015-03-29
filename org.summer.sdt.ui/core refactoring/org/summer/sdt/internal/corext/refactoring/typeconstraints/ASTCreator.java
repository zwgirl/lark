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

import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.WorkingCopyOwner;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.ASTParser;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.internal.corext.refactoring.util.RefactoringASTParser;
import org.summer.sdt.internal.ui.javaeditor.ASTProvider;


public class ASTCreator {

	public static final String CU_PROPERTY= "org.summer.sdt.ui.refactoring.cu"; //$NON-NLS-1$

	private ASTCreator() {
		//private
	}

	public static CompilationUnit createAST(ICompilationUnit cu, WorkingCopyOwner workingCopyOwner) {
		CompilationUnit cuNode= getCuNode(workingCopyOwner, cu);
		cuNode.setProperty(CU_PROPERTY, cu);
		return cuNode;
	}

	private static CompilationUnit getCuNode(WorkingCopyOwner workingCopyOwner, ICompilationUnit cu) {
		ASTParser p = ASTParser.newParser(ASTProvider.SHARED_AST_LEVEL);
		p.setSource(cu);
		p.setResolveBindings(true);
		p.setWorkingCopyOwner(workingCopyOwner);
		p.setCompilerOptions(RefactoringASTParser.getCompilerOptions(cu));
		return (CompilationUnit) p.createAST(null);
	}

	public static ICompilationUnit getCu(ASTNode node) {
		Object property= node.getRoot().getProperty(CU_PROPERTY);
		if (property instanceof ICompilationUnit)
			return (ICompilationUnit)property;
		return null;
	}
}

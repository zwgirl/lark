/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.corext.refactoring.code;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.dom.ASTNode;
import org.summer.sdt.core.dom.Block;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.dom.MethodDeclaration;
import org.summer.sdt.internal.corext.dom.ASTNodes;
import org.summer.sdt.internal.corext.dom.Selection;
import org.summer.sdt.internal.corext.dom.SelectionAnalyzer;
import org.summer.sdt.internal.corext.refactoring.RefactoringCoreMessages;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.corext.util.Strings;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.viewsupport.BasicElementLabels;

public class CodeRefactoringUtil {

    public static RefactoringStatus checkMethodSyntaxErrors(int selectionStart, int selectionLength, CompilationUnit cuNode, String invalidSelectionMessage){
		SelectionAnalyzer analyzer= new SelectionAnalyzer(Selection.createFromStartLength(selectionStart, selectionLength), true);
		cuNode.accept(analyzer);
		ASTNode coveringNode= analyzer.getLastCoveringNode();
		if (! (coveringNode instanceof Block) || ! (coveringNode.getParent() instanceof MethodDeclaration))
			return RefactoringStatus.createFatalErrorStatus(invalidSelectionMessage);
		if (ASTNodes.getMessages(coveringNode, ASTNodes.NODE_ONLY).length == 0)
			return RefactoringStatus.createFatalErrorStatus(invalidSelectionMessage);

		MethodDeclaration methodDecl= (MethodDeclaration)coveringNode.getParent();
		String message= Messages.format(RefactoringCoreMessages.CodeRefactoringUtil_error_message, BasicElementLabels.getJavaElementName(methodDecl.getName().getIdentifier()));
		return RefactoringStatus.createFatalErrorStatus(message);
	}

	public static int getIndentationLevel(ASTNode node, ICompilationUnit unit) throws CoreException {
		IPath fullPath= unit.getCorrespondingResource().getFullPath();
		try{
			FileBuffers.getTextFileBufferManager().connect(fullPath, LocationKind.IFILE, new NullProgressMonitor());
			ITextFileBuffer buffer= FileBuffers.getTextFileBufferManager().getTextFileBuffer(fullPath, LocationKind.IFILE);
			try {
				IRegion region= buffer.getDocument().getLineInformationOfOffset(node.getStartPosition());
				return Strings.computeIndentUnits(buffer.getDocument().get(region.getOffset(), region.getLength()), unit.getJavaProject());
			} catch (BadLocationException exception) {
				JavaPlugin.log(exception);
			}
			return 0;
		} finally {
			FileBuffers.getTextFileBufferManager().disconnect(fullPath, LocationKind.IFILE, new NullProgressMonitor());
		}
	}

    private CodeRefactoringUtil() {}
}

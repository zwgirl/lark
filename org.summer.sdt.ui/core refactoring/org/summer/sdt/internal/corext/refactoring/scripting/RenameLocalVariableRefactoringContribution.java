/*******************************************************************************
 * Copyright (c) 2005, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.corext.refactoring.scripting;

import java.util.Map;

import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.summer.sdt.core.refactoring.IJavaRefactorings;
import org.summer.sdt.core.refactoring.descriptors.JavaRefactoringDescriptor;
import org.summer.sdt.internal.core.refactoring.descriptors.RefactoringSignatureDescriptorFactory;
import org.summer.sdt.internal.corext.refactoring.JavaRefactoringArguments;
import org.summer.sdt.internal.corext.refactoring.rename.RenameLocalVariableProcessor;

/**
 * Refactoring contribution for the rename local variable refactoring.
 *
 * @since 3.2
 */
public final class RenameLocalVariableRefactoringContribution extends JavaUIRefactoringContribution {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Refactoring createRefactoring(JavaRefactoringDescriptor descriptor, RefactoringStatus status) {
		JavaRefactoringArguments arguments= new JavaRefactoringArguments(descriptor.getProject(), retrieveArgumentMap(descriptor));
		RenameLocalVariableProcessor processor= new RenameLocalVariableProcessor(arguments, status);
		return new RenameRefactoring(processor);
	}

	@Override
	public RefactoringDescriptor createDescriptor() {
		return RefactoringSignatureDescriptorFactory.createRenameJavaElementDescriptor(IJavaRefactorings.RENAME_LOCAL_VARIABLE);
	}

	@Override
	public RefactoringDescriptor createDescriptor(String id, String project, String description, String comment, Map arguments, int flags) {
		return RefactoringSignatureDescriptorFactory.createRenameJavaElementDescriptor(id, project, description, comment, arguments, flags);
	}
}

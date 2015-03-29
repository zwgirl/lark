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
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IMethod;
import org.summer.sdt.core.ISourceRange;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.core.dom.ASTParser;
import org.summer.sdt.core.dom.CompilationUnit;
import org.summer.sdt.core.refactoring.IJavaRefactorings;
import org.summer.sdt.core.refactoring.descriptors.InlineMethodDescriptor;
import org.summer.sdt.core.refactoring.descriptors.JavaRefactoringDescriptor;
import org.summer.sdt.internal.core.refactoring.descriptors.RefactoringSignatureDescriptorFactory;
import org.summer.sdt.internal.corext.refactoring.JavaRefactoringDescriptorUtil;
import org.summer.sdt.internal.corext.refactoring.RefactoringCoreMessages;
import org.summer.sdt.internal.corext.refactoring.code.InlineMethodRefactoring;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.javaeditor.ASTProvider;

/**
 * Refactoring contribution for the inline method refactoring.
 *
 * @since 3.2
 */
public final class InlineMethodRefactoringContribution extends JavaUIRefactoringContribution {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Refactoring createRefactoring(JavaRefactoringDescriptor descriptor, RefactoringStatus status) throws CoreException {
		int selectionStart= -1;
		int selectionLength= -1;
		ICompilationUnit unit= null;
		CompilationUnit node= null;
		if (descriptor instanceof InlineMethodDescriptor) {
			InlineMethodDescriptor extended= (InlineMethodDescriptor) descriptor;
			Map<String, String> arguments= retrieveArgumentMap(extended);
			final String selection= arguments.get(JavaRefactoringDescriptorUtil.ATTRIBUTE_SELECTION);
			if (selection != null) {
				int offset= -1;
				int length= -1;
				final StringTokenizer tokenizer= new StringTokenizer(selection);
				if (tokenizer.hasMoreTokens())
					offset= Integer.valueOf(tokenizer.nextToken()).intValue();
				if (tokenizer.hasMoreTokens())
					length= Integer.valueOf(tokenizer.nextToken()).intValue();
				if (offset >= 0 && length >= 0) {
					selectionStart= offset;
					selectionLength= length;
				} else
					throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, Messages.format(RefactoringCoreMessages.InitializableRefactoring_illegal_argument, new Object[] { selection, JavaRefactoringDescriptorUtil.ATTRIBUTE_SELECTION}), null));
			}
			final String handle= arguments.get(JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT);
			if (handle != null) {
				final IJavaElement element= JavaRefactoringDescriptorUtil.handleToElement(descriptor.getProject(), handle, false);
				if (element == null || !element.exists())
					throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, Messages.format(RefactoringCoreMessages.InitializableRefactoring_inputs_do_not_exist, new String[] { RefactoringCoreMessages.InlineMethodRefactoring_name, IJavaRefactorings.INLINE_METHOD}), null));
				else {
					if (element instanceof ICompilationUnit) {
						unit= (ICompilationUnit) element;
						if (selection == null)
							throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, JavaRefactoringDescriptorUtil.ATTRIBUTE_SELECTION), null));
					} else if (element instanceof IMethod) {
						final IMethod method= (IMethod) element;
						try {
							final ISourceRange range= method.getNameRange();
							if (range != null) {
								selectionStart= range.getOffset();
								selectionLength= range.getLength();
							} else
								throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, Messages.format(RefactoringCoreMessages.InitializableRefactoring_illegal_argument, new Object[] { handle, JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT}), null));
						} catch (JavaModelException exception) {
							throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, Messages.format(RefactoringCoreMessages.InitializableRefactoring_inputs_do_not_exist, new String[] { RefactoringCoreMessages.InlineMethodRefactoring_name, IJavaRefactorings.INLINE_METHOD}), exception));
						}
						unit= method.getCompilationUnit();
					} else
						throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, Messages.format(RefactoringCoreMessages.InitializableRefactoring_illegal_argument, new Object[] { handle, JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT}), null));
					final ASTParser parser= ASTParser.newParser(ASTProvider.SHARED_AST_LEVEL);
					parser.setResolveBindings(true);
					parser.setSource(unit);
					node= (CompilationUnit) parser.createAST(null);
				}
			} else
				throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT), null));
		} else
			throw new CoreException(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 0, RefactoringCoreMessages.InitializableRefactoring_inacceptable_arguments, null));
		return InlineMethodRefactoring.create(unit, node, selectionStart, selectionLength);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RefactoringDescriptor createDescriptor() {
		return RefactoringSignatureDescriptorFactory.createInlineMethodDescriptor();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final RefactoringDescriptor createDescriptor(final String id, final String project, final String description, final String comment, final Map arguments, final int flags) {
		return RefactoringSignatureDescriptorFactory.createInlineMethodDescriptor(project, description, comment, arguments, flags);
	}
}

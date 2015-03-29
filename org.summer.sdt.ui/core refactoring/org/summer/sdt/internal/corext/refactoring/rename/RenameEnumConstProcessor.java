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
package org.summer.sdt.internal.corext.refactoring.rename;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.summer.sdt.core.Flags;
import org.summer.sdt.core.IField;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IJavaProject;
import org.summer.sdt.core.IType;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.core.refactoring.IJavaRefactorings;
import org.summer.sdt.core.refactoring.descriptors.JavaRefactoringDescriptor;
import org.summer.sdt.core.refactoring.descriptors.RenameJavaElementDescriptor;
import org.summer.sdt.internal.core.refactoring.descriptors.RefactoringSignatureDescriptorFactory;
import org.summer.sdt.internal.corext.refactoring.Checks;
import org.summer.sdt.internal.corext.refactoring.JDTRefactoringDescriptorComment;
import org.summer.sdt.internal.corext.refactoring.JavaRefactoringArguments;
import org.summer.sdt.internal.corext.refactoring.JavaRefactoringDescriptorUtil;
import org.summer.sdt.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.summer.sdt.internal.corext.refactoring.RefactoringCoreMessages;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.viewsupport.BasicElementLabels;
import org.summer.sdt.ui.JavaElementLabels;
import org.summer.sdt.ui.refactoring.IRefactoringProcessorIds;

public final class RenameEnumConstProcessor extends RenameFieldProcessor {

	/**
	 * Creates a new rename enum const processor.
	 *
	 * @param field
	 *            the enum constant
	 */
	public RenameEnumConstProcessor(IField field) {
		super(field);
	}

	/**
	 * Creates a new rename enum const processor.
	 *
	 * @param arguments
	 *            the arguments
	 *
	 * @param status
	 *            the status
	 */
	public RenameEnumConstProcessor(JavaRefactoringArguments arguments, RefactoringStatus status) {
		super(null);
		RefactoringStatus initializeStatus= initialize(arguments);
		status.merge(initializeStatus);
	}


	/*
	 * @see org.summer.sdt.internal.corext.refactoring.rename.RenameFieldProcessor#canEnableGetterRenaming()
	 */
	@Override
	public String canEnableGetterRenaming() throws CoreException {
		return ""; //$NON-NLS-1$
	}

	/*
	 * @see org.summer.sdt.internal.corext.refactoring.rename.RenameFieldProcessor#canEnableSetterRenaming()
	 */
	@Override
	public String canEnableSetterRenaming() throws CoreException {
		return ""; //$NON-NLS-1$
	}

	/*
	 * @see org.summer.sdt.internal.corext.refactoring.tagging.INameUpdating#checkNewElementName(java.lang.String)
	 */
	@Override
	public RefactoringStatus checkNewElementName(String newName) throws CoreException {
		RefactoringStatus result= Checks.checkEnumConstantName(newName, getField());
		if (Checks.isAlreadyNamed(getField(), newName))
			result.addFatalError(RefactoringCoreMessages.RenameEnumConstRefactoring_another_name);
		if (getField().getDeclaringType().getField(newName).exists())
			result.addFatalError(RefactoringCoreMessages.RenameEnumConstRefactoring_const_already_defined);
		return result;
	}

	@Override
	protected RenameJavaElementDescriptor createRefactoringDescriptor() {
		final IField field= getField();
		String project= null;
		IJavaProject javaProject= field.getJavaProject();
		if (javaProject != null)
			project= javaProject.getElementName();
		int flags= JavaRefactoringDescriptor.JAR_MIGRATION | JavaRefactoringDescriptor.JAR_REFACTORING | RefactoringDescriptor.STRUCTURAL_CHANGE;
		final IType declaring= field.getDeclaringType();
		try {
			if (!Flags.isPrivate(declaring.getFlags()))
				flags|= RefactoringDescriptor.MULTI_CHANGE;
			if (declaring.isAnonymous() || declaring.isLocal())
				flags|= JavaRefactoringDescriptor.JAR_SOURCE_ATTACHMENT;
		} catch (JavaModelException exception) {
			JavaPlugin.log(exception);
		}
		final String description= Messages.format(RefactoringCoreMessages.RenameEnumConstProcessor_descriptor_description_short, BasicElementLabels.getJavaElementName(fField.getElementName()));
		final String header= Messages.format(RefactoringCoreMessages.RenameEnumConstProcessor_descriptor_description, new String[] { BasicElementLabels.getJavaElementName(field.getElementName()), JavaElementLabels.getElementLabel(field.getParent(), JavaElementLabels.ALL_FULLY_QUALIFIED), BasicElementLabels.getJavaElementName(getNewElementName())});
		final String comment= new JDTRefactoringDescriptorComment(project, this, header).asString();
		final RenameJavaElementDescriptor descriptor= RefactoringSignatureDescriptorFactory.createRenameJavaElementDescriptor(IJavaRefactorings.RENAME_ENUM_CONSTANT);
		descriptor.setProject(project);
		descriptor.setDescription(description);
		descriptor.setComment(comment);
		descriptor.setFlags(flags);
		descriptor.setJavaElement(field);
		descriptor.setNewName(getNewElementName());
		descriptor.setUpdateReferences(fUpdateReferences);
		descriptor.setUpdateTextualOccurrences(fUpdateTextualMatches);
		return descriptor;
	}

	/*
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return IRefactoringProcessorIds.RENAME_ENUM_CONSTANT_PROCESSOR;
	}

	/*
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return RefactoringCoreMessages.RenameEnumConstRefactoring_name;
	}

	private RefactoringStatus initialize(JavaRefactoringArguments extended) {
		final String handle= extended.getAttribute(JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT);
		if (handle != null) {
			final IJavaElement element= JavaRefactoringDescriptorUtil.handleToElement(extended.getProject(), handle, false);
			if (element == null || !element.exists() || element.getElementType() != IJavaElement.FIELD)
				return JavaRefactoringDescriptorUtil.createInputFatalStatus(element, getProcessorName(), IJavaRefactorings.RENAME_ENUM_CONSTANT);
			else
				fField= (IField) element;
		} else
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT));
		final String name= extended.getAttribute(JavaRefactoringDescriptorUtil.ATTRIBUTE_NAME);
		if (name != null && !"".equals(name)) //$NON-NLS-1$
			setNewElementName(name);
		else
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, JavaRefactoringDescriptorUtil.ATTRIBUTE_NAME));
		final String references= extended.getAttribute(JavaRefactoringDescriptorUtil.ATTRIBUTE_REFERENCES);
		if (references != null) {
			setUpdateReferences(Boolean.valueOf(references).booleanValue());
		} else
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, JavaRefactoringDescriptorUtil.ATTRIBUTE_REFERENCES));
		final String matches= extended.getAttribute(ATTRIBUTE_TEXTUAL_MATCHES);
		if (matches != null) {
			setUpdateTextualMatches(Boolean.valueOf(matches).booleanValue());
		} else
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ATTRIBUTE_TEXTUAL_MATCHES));
		return new RefactoringStatus();
	}

	@Override
	public boolean isApplicable() throws CoreException {
		return RefactoringAvailabilityTester.isRenameEnumConstAvailable(getField());
	}
}

/*******************************************************************************
 * Copyright (c) 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.core.refactoring;

import org.summer.sdt.core.refactoring.descriptors.ChangeMethodSignatureDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ConvertAnonymousDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ConvertLocalVariableDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ConvertMemberTypeDescriptor;
import org.summer.sdt.core.refactoring.descriptors.CopyDescriptor;
import org.summer.sdt.core.refactoring.descriptors.DeleteDescriptor;
import org.summer.sdt.core.refactoring.descriptors.EncapsulateFieldDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ExtractClassDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ExtractConstantDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ExtractInterfaceDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ExtractLocalDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ExtractMethodDescriptor;
import org.summer.sdt.core.refactoring.descriptors.ExtractSuperclassDescriptor;
import org.summer.sdt.core.refactoring.descriptors.GeneralizeTypeDescriptor;
import org.summer.sdt.core.refactoring.descriptors.InferTypeArgumentsDescriptor;
import org.summer.sdt.core.refactoring.descriptors.InlineConstantDescriptor;
import org.summer.sdt.core.refactoring.descriptors.InlineLocalVariableDescriptor;
import org.summer.sdt.core.refactoring.descriptors.InlineMethodDescriptor;
import org.summer.sdt.core.refactoring.descriptors.IntroduceFactoryDescriptor;
import org.summer.sdt.core.refactoring.descriptors.IntroduceIndirectionDescriptor;
import org.summer.sdt.core.refactoring.descriptors.IntroduceParameterDescriptor;
import org.summer.sdt.core.refactoring.descriptors.IntroduceParameterObjectDescriptor;
import org.summer.sdt.core.refactoring.descriptors.MoveDescriptor;
import org.summer.sdt.core.refactoring.descriptors.MoveMethodDescriptor;
import org.summer.sdt.core.refactoring.descriptors.MoveStaticMembersDescriptor;
import org.summer.sdt.core.refactoring.descriptors.PullUpDescriptor;
import org.summer.sdt.core.refactoring.descriptors.PushDownDescriptor;
import org.summer.sdt.core.refactoring.descriptors.RenameJavaElementDescriptor;
import org.summer.sdt.core.refactoring.descriptors.UseSupertypeDescriptor;

import org.eclipse.ltk.core.refactoring.PerformRefactoringOperation;
import org.eclipse.ltk.core.refactoring.RefactoringContribution;
import org.eclipse.ltk.core.refactoring.RefactoringCore;

/**
 * Interface for refactoring ids offered by the JDT tooling.
 * <p>
 * This interface provides refactoring ids for refactorings offered by the JDT
 * tooling. Refactoring instances corresponding to such an id may be
 * instantiated by the refactoring framework using
 * {@link RefactoringCore#getRefactoringContribution(String)}. The resulting
 * refactoring instance may be executed on the workspace with a
 * {@link PerformRefactoringOperation}.
 * <p>
 * Clients may obtain customizable refactoring descriptors for a certain
 * refactoring by calling
 * {@link RefactoringCore#getRefactoringContribution(String)} with the
 * appropriate refactoring id and then calling
 * {@link RefactoringContribution#createDescriptor()} to obtain a customizable
 * refactoring descriptor. The concrete subtype of refactoring descriptors is
 * dependent from the <code>id</code> argument.
 * </p>
 * <p>
 * Note: this interface is not intended to be implemented by clients.
 * </p>
 *
 * @since 1.1
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IJavaRefactorings {

	/**
	 * Refactoring id of the 'Change Method Signature' refactoring (value:
	 * <code>org.summer.sdt.ui.change.method.signature</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ChangeMethodSignatureDescriptor}.
	 * </p>
	 */
	public static final String CHANGE_METHOD_SIGNATURE= "org.summer.sdt.ui.change.method.signature"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Convert Anonymous To Nested' refactoring (value:
	 * <code>org.summer.sdt.ui.convert.anonymous</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ConvertAnonymousDescriptor}.
	 * </p>
	 */
	public static final String CONVERT_ANONYMOUS= "org.summer.sdt.ui.convert.anonymous"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Convert Local Variable to Field' refactoring
	 * (value: <code>org.summer.sdt.ui.promote.temp</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ConvertLocalVariableDescriptor}.
	 * </p>
	 */
	public static final String CONVERT_LOCAL_VARIABLE= "org.summer.sdt.ui.promote.temp"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Convert Member Type to Top Level' refactoring
	 * (value: <code>org.summer.sdt.ui.move.inner</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ConvertMemberTypeDescriptor}.
	 * </p>
	 */
	public static final String CONVERT_MEMBER_TYPE= "org.summer.sdt.ui.move.inner"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Copy' refactoring (value:
	 * <code>org.summer.sdt.ui.copy</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link CopyDescriptor}.
	 * </p>
	 */
	public static final String COPY= "org.summer.sdt.ui.copy"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Delete' refactoring (value:
	 * <code>org.summer.sdt.ui.delete</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link DeleteDescriptor}.
	 * </p>
	 */
	public static final String DELETE= "org.summer.sdt.ui.delete"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Encapsulate Field' refactoring (value:
	 * <code>org.summer.sdt.ui.self.encapsulate</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link EncapsulateFieldDescriptor}.
	 * </p>
	 */
	public static final String ENCAPSULATE_FIELD= "org.summer.sdt.ui.self.encapsulate"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Extract Class' refactoring (value:
	 * <code>"org.summer.sdt.ui.extract.class</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ExtractClassDescriptor}.
	 * </p>
	 *
	 * @since 1.2
	 */
	public static final String EXTRACT_CLASS= "org.summer.sdt.ui.extract.class"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Extract Constant' refactoring (value:
	 * <code>org.summer.sdt.ui.extract.constant</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ExtractConstantDescriptor}.
	 * </p>
	 */
	public static final String EXTRACT_CONSTANT= "org.summer.sdt.ui.extract.constant"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Extract Interface' refactoring (value:
	 * <code>org.summer.sdt.ui.extract.interface</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ExtractInterfaceDescriptor}.
	 * </p>
	 */
	public static final String EXTRACT_INTERFACE= "org.summer.sdt.ui.extract.interface"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Extract Local Variable' refactoring (value:
	 * <code>org.summer.sdt.ui.extract.temp</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ExtractLocalDescriptor}.
	 * </p>
	 */
	public static final String EXTRACT_LOCAL_VARIABLE= "org.summer.sdt.ui.extract.temp"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Extract Method' refactoring (value:
	 * <code>org.summer.sdt.ui.extract.method</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ExtractMethodDescriptor}.
	 * </p>
	 */
	public static final String EXTRACT_METHOD= "org.summer.sdt.ui.extract.method"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Extract Superclass' refactoring (value:
	 * <code>org.summer.sdt.ui.extract.superclass</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link ExtractSuperclassDescriptor}.
	 * </p>
	 */
	public static final String EXTRACT_SUPERCLASS= "org.summer.sdt.ui.extract.superclass"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Generalize Declared Type' refactoring (value:
	 * <code>org.summer.sdt.ui.change.type</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link GeneralizeTypeDescriptor}.
	 * </p>
	 */
	public static final String GENERALIZE_TYPE= "org.summer.sdt.ui.change.type"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Infer Type Arguments' refactoring (value:
	 * <code>org.summer.sdt.ui.infer.typearguments</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link InferTypeArgumentsDescriptor}.
	 * </p>
	 */
	public static final String INFER_TYPE_ARGUMENTS= "org.summer.sdt.ui.infer.typearguments"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Inline Constant' refactoring (value:
	 * <code>org.summer.sdt.ui.inline.constant</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link InlineConstantDescriptor}.
	 * </p>
	 */
	public static final String INLINE_CONSTANT= "org.summer.sdt.ui.inline.constant"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Inline Local Variable' refactoring (value:
	 * <code>org.summer.sdt.ui.inline.temp</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link InlineLocalVariableDescriptor}.
	 * </p>
	 */
	public static final String INLINE_LOCAL_VARIABLE= "org.summer.sdt.ui.inline.temp"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Inline Method' refactoring (value:
	 * <code>org.summer.sdt.ui.inline.method</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link InlineMethodDescriptor}.
	 * </p>
	 */
	public static final String INLINE_METHOD= "org.summer.sdt.ui.inline.method"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Introduce Factory' refactoring (value:
	 * <code>org.summer.sdt.ui.introduce.factory</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link IntroduceFactoryDescriptor}.
	 * </p>
	 */
	public static final String INTRODUCE_FACTORY= "org.summer.sdt.ui.introduce.factory"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Introduce Indirection' refactoring (value:
	 * <code>org.summer.sdt.ui.introduce.indirection</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link IntroduceIndirectionDescriptor}.
	 * </p>
	 */
	public static final String INTRODUCE_INDIRECTION= "org.summer.sdt.ui.introduce.indirection"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Introduce Parameter' refactoring (value:
	 * <code>org.summer.sdt.ui.introduce.parameter</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link IntroduceParameterDescriptor}.
	 * </p>
	 */
	public static final String INTRODUCE_PARAMETER= "org.summer.sdt.ui.introduce.parameter"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Introduce Parameter Object' refactoring (value:
	 * <code>org.summer.sdt.ui.introduce.parameter.object</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link IntroduceParameterObjectDescriptor}.
	 * </p>
	 * @since 1.2
	 */
	public static final String INTRODUCE_PARAMETER_OBJECT= "org.summer.sdt.ui.introduce.parameter.object"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Move' refactoring (value:
	 * <code>org.summer.sdt.ui.move</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link MoveDescriptor}.
	 * </p>
	 */
	public static final String MOVE= "org.summer.sdt.ui.move"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Move Method' refactoring (value:
	 * <code>org.summer.sdt.ui.move.method</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link MoveMethodDescriptor}.
	 * </p>
	 */
	public static final String MOVE_METHOD= "org.summer.sdt.ui.move.method"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Move Static Members' refactoring (value:
	 * <code>org.summer.sdt.ui.move.static</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link MoveStaticMembersDescriptor}.
	 * </p>
	 */
	public static final String MOVE_STATIC_MEMBERS= "org.summer.sdt.ui.move.static"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Pull Up' refactoring (value:
	 * <code>org.summer.sdt.ui.pull.up</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link PullUpDescriptor}.
	 * </p>
	 */
	public static final String PULL_UP= "org.summer.sdt.ui.pull.up"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Push Down' refactoring (value:
	 * <code>org.summer.sdt.ui.push.down</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link PushDownDescriptor}.
	 * </p>
	 */
	public static final String PUSH_DOWN= "org.summer.sdt.ui.push.down"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Compilation Unit' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.compilationunit</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_COMPILATION_UNIT= "org.summer.sdt.ui.rename.compilationunit"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Enum Constant' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.enum.constant</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_ENUM_CONSTANT= "org.summer.sdt.ui.rename.enum.constant"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Field' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.field</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_FIELD= "org.summer.sdt.ui.rename.field"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Java Project' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.java.project</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_JAVA_PROJECT= "org.summer.sdt.ui.rename.java.project"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Local Variable' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.local.variable</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_LOCAL_VARIABLE= "org.summer.sdt.ui.rename.local.variable"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Method' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.method</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_METHOD= "org.summer.sdt.ui.rename.method"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Package' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.package</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_PACKAGE= "org.summer.sdt.ui.rename.package"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Resource' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.resource</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link org.summer.sdt.core.refactoring.descriptors.RenameResourceDescriptor}.
	 * </p>
	 * @deprecated Since 1.2. Use {@link org.eclipse.ltk.core.refactoring.resource.RenameResourceDescriptor#ID} instead.
	 */
	public static final String RENAME_RESOURCE= "org.summer.sdt.ui.rename.resource"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Source Folder' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.source.folder</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_SOURCE_FOLDER= "org.summer.sdt.ui.rename.source.folder"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Type' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.type</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_TYPE= "org.summer.sdt.ui.rename.type"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Rename Type Parameter' refactoring (value:
	 * <code>org.summer.sdt.ui.rename.type.parameter</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link RenameJavaElementDescriptor}.
	 * </p>
	 */
	public static final String RENAME_TYPE_PARAMETER= "org.summer.sdt.ui.rename.type.parameter"; //$NON-NLS-1$

	/**
	 * Refactoring id of the 'Use Supertype Where Possible' refactoring (value:
	 * <code>org.summer.sdt.ui.use.supertype</code>).
	 * <p>
	 * Clients may safely cast the obtained refactoring descriptor to
	 * {@link UseSupertypeDescriptor}.
	 * </p>
	 */
	public static final String USE_SUPER_TYPE= "org.summer.sdt.ui.use.supertype"; //$NON-NLS-1$
}
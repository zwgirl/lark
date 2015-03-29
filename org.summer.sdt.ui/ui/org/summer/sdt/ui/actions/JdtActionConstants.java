/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Mateusz Matela <mateusz.matela@gmail.com> - [code manipulation] [dcr] toString() builder wizard - https://bugs.eclipse.org/bugs/show_bug.cgi?id=26070
 *******************************************************************************/
package org.summer.sdt.ui.actions;

import org.eclipse.ui.texteditor.ITextEditorActionConstants;

/**
 * Action ids for standard actions, for groups in the menu bar, and
 * for actions in context menus of JDT views.
 *
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 *
 * @since 2.0
 *
 * @noextend This class is not intended to be subclassed by clients.
 */
public class JdtActionConstants {

	// Navigate menu

	/**
	 * Navigate menu: name of standard Goto Type global action
	 * (value <code>"org.summer.sdt.ui.actions.GoToType"</code>).
	 */
	public static final String GOTO_TYPE= "org.summer.sdt.ui.actions.GoToType"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Goto Package global action
	 * (value <code>"org.summer.sdt.ui.actions.GoToPackage"</code>).
	 */
	public static final String GOTO_PACKAGE= "org.summer.sdt.ui.actions.GoToPackage"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Open global action
	 * (value <code>"org.summer.sdt.ui.actions.Open"</code>).
	 */
	public static final String OPEN= "org.summer.sdt.ui.actions.Open"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Open Implementation global action
	 * (value <code>"org.summer.sdt.ui.actions.OpenImplementation"</code>).
	 * @since 3.6
	 */
	public static final String OPEN_IMPLEMENTATION= "org.summer.sdt.ui.actions.OpenImplementation"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Open Super Implementation global action
	 * (value <code>"org.summer.sdt.ui.actions.OpenSuperImplementation"</code>).
	 */
	public static final String OPEN_SUPER_IMPLEMENTATION= "org.summer.sdt.ui.actions.OpenSuperImplementation"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Open Type Hierarchy global action
	 * (value <code>"org.summer.sdt.ui.actions.OpenTypeHierarchy"</code>).
	 */
	public static final String OPEN_TYPE_HIERARCHY= "org.summer.sdt.ui.actions.OpenTypeHierarchy"; //$NON-NLS-1$

    /**
     * Navigate menu: name of standard Open Call Hierarchy global action
     * (value <code>"org.summer.sdt.ui.actions.OpenCallHierarchy"</code>).
     * @since 3.0
     */
    public static final String OPEN_CALL_HIERARCHY= "org.summer.sdt.ui.actions.OpenCallHierarchy"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Open External Javadoc global action (value
	 * <code>"org.summer.sdt.ui.actions.OpenExternalJavaDoc"</code>).
	 * @deprecated As of 3.6, replaced by {@link #OPEN_ATTACHED_JAVA_DOC}
	 */
	public static final String OPEN_EXTERNAL_JAVA_DOC= "org.summer.sdt.ui.actions.OpenExternalJavaDoc"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Open Attached Javadoc global action (value
	 * <code>"org.summer.sdt.ui.actions.OpenExternalJavaDoc"</code>).
	 * @since 3.6
	 */
	public static final String OPEN_ATTACHED_JAVA_DOC= "org.summer.sdt.ui.actions.OpenExternalJavaDoc"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Show in Packages View global action
	 * (value <code>"org.summer.sdt.ui.actions.ShowInPackagesView"</code>).
	 */
	public static final String SHOW_IN_PACKAGE_VIEW= "org.summer.sdt.ui.actions.ShowInPackagesView"; //$NON-NLS-1$

	/**
	 * Navigate menu: name of standard Show in Navigator View global action
	 * (value <code>"org.summer.sdt.ui.actions.ShowInNaviagtorView"</code>).
	 */
	public static final String SHOW_IN_NAVIGATOR_VIEW= "org.summer.sdt.ui.actions.ShowInNaviagtorView"; //$NON-NLS-1$

	// Edit menu

	/**
	 * Edit menu: name of standard Show Javadoc global action
	 * (value <code>"org.summer.sdt.ui.actions.ShowJavaDoc"</code>).
	 * @deprecated As of 3.3, replaced by {@link ITextEditorActionConstants#SHOW_INFORMATION}
	 */
	public static final String SHOW_JAVA_DOC= "org.summer.sdt.ui.actions.ShowJavaDoc"; //$NON-NLS-1$

	/**
	 * Edit menu: name of standard Code Assist global action
	 * (value <code>"org.summer.sdt.ui.actions.ContentAssist"</code>).
	 */
	public static final String CONTENT_ASSIST= "org.summer.sdt.ui.actions.ContentAssist"; //$NON-NLS-1$

	// Source menu

	/**
	 * Source menu: name of standard Comment global action
	 * (value <code>"org.summer.sdt.ui.actions.Comment"</code>).
	 */
	public static final String COMMENT= "org.summer.sdt.ui.actions.Comment"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Uncomment global action
	 * (value <code>"org.summer.sdt.ui.actions.Uncomment"</code>).
	 */
	public static final String UNCOMMENT= "org.summer.sdt.ui.actions.Uncomment"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard ToggleComment global action
	 * (value <code>"org.summer.sdt.ui.actions.ToggleComment"</code>).
	 * @since 3.0
	 */
	public static final String TOGGLE_COMMENT= "org.summer.sdt.ui.actions.ToggleComment"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Block Comment global action
	 * (value <code>"org.summer.sdt.ui.actions.AddBlockComment"</code>).
	 *
	 * @since 3.0
	 */
	public static final String ADD_BLOCK_COMMENT= "org.summer.sdt.ui.actions.AddBlockComment"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Block Uncomment global action
	 * (value <code>"org.summer.sdt.ui.actions.RemoveBlockComment"</code>).
	 *
	 * @since 3.0
	 */
	public static final String REMOVE_BLOCK_COMMENT= "org.summer.sdt.ui.actions.RemoveBlockComment"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Indent global action
	 * (value <code>"org.summer.sdt.ui.actions.Indent"</code>).
	 *
	 * @since 3.0
	 */
	public static final String INDENT= "org.summer.sdt.ui.actions.Indent"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Shift Right action
	 * (value <code>"org.summer.sdt.ui.actions.ShiftRight"</code>).
	 */
	public static final String SHIFT_RIGHT= "org.summer.sdt.ui.actions.ShiftRight"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Shift Left global action
	 * (value <code>"org.summer.sdt.ui.actions.ShiftLeft"</code>).
	 */
	public static final String SHIFT_LEFT= "org.summer.sdt.ui.actions.ShiftLeft"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Format global action
	 * (value <code>"org.summer.sdt.ui.actions.Format"</code>).
	 */
	public static final String FORMAT= "org.summer.sdt.ui.actions.Format"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Format Element global action
	 * (value <code>"org.summer.sdt.ui.actions.FormatElement"</code>).
	 * @since 3.0
	 */
	public static final String FORMAT_ELEMENT= "org.summer.sdt.ui.actions.FormatElement"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Add Import global action
	 * (value <code>"org.summer.sdt.ui.actions.AddImport"</code>).
	 */
	public static final String ADD_IMPORT= "org.summer.sdt.ui.actions.AddImport"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Organize Imports global action
	 * (value <code>"org.summer.sdt.ui.actions.OrganizeImports"</code>).
	 */
	public static final String ORGANIZE_IMPORTS= "org.summer.sdt.ui.actions.OrganizeImports"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Sort Members global action (value
	 * <code>"org.summer.sdt.ui.actions.SortMembers"</code>).
	 * @since 2.1
	 */
	public static final String SORT_MEMBERS= "org.summer.sdt.ui.actions.SortMembers"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Surround with try/catch block global action
	 * (value <code>"org.summer.sdt.ui.actions.SurroundWithTryCatch"</code>).
	 */
	public static final String SURROUND_WITH_TRY_CATCH= "org.summer.sdt.ui.actions.SurroundWithTryCatch"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Surround with try/multi-catch block global action (value
	 * <code>"org.summer.sdt.ui.actions.SurroundWithTryMultiCatch"</code>).
	 * 
	 * @since 3.7.1
	 */
	public static final String SURROUND_WITH_TRY_MULTI_CATCH= "org.summer.sdt.ui.actions.SurroundWithTryMultiCatch"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Override Methods global action
	 * (value <code>"org.summer.sdt.ui.actions.OverrideMethods"</code>).
	 */
	public static final String OVERRIDE_METHODS= "org.summer.sdt.ui.actions.OverrideMethods"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Generate Getter and Setter global action
	 * (value <code>"org.summer.sdt.ui.actions.GenerateGetterSetter"</code>).
	 */
	public static final String GENERATE_GETTER_SETTER= "org.summer.sdt.ui.actions.GenerateGetterSetter"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard delegate methods global action (value
	 * <code>"org.summer.sdt.ui.actions.GenerateDelegateMethods"</code>).
	 * @since 2.1
	 */
	public static final String GENERATE_DELEGATE_METHODS= "org.summer.sdt.ui.actions.GenerateDelegateMethods"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Add Constructor From Superclass global action
	 * (value <code>"org.summer.sdt.ui.actions.AddConstructorFromSuperclass"</code>).
	 */
	public static final String ADD_CONSTRUCTOR_FROM_SUPERCLASS= "org.summer.sdt.ui.actions.AddConstructorFromSuperclass"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Generate Constructor using Fields global action
	 * (value <code>"org.summer.sdt.ui.actions.GenerateConstructorUsingFields"</code>).
	 */
	public static final String GENERATE_CONSTRUCTOR_USING_FIELDS= "org.summer.sdt.ui.actions.GenerateConstructorUsingFields"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Generate hashCode() and equals() global action
	 * (value <code>"org.summer.sdt.ui.actions.GenerateHashCodeEquals"</code>).
	 * @since 3.2
	 */
	public static final String GENERATE_HASHCODE_EQUALS= "org.summer.sdt.ui.actions.GenerateHashCodeEquals"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Generate toString() global action
	 * (value <code>"org.summer.sdt.ui.actions.GenerateToString"</code>).
	 * @since 3.5
	 */
	public static final String GENERATE_TOSTRING= "org.summer.sdt.ui.actions.GenerateToString"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Add Javadoc Comment global action
	 * (value <code>"org.summer.sdt.ui.actions.AddJavaDocComment"</code>).
	 */
	public static final String ADD_JAVA_DOC_COMMENT= "org.summer.sdt.ui.actions.AddJavaDocComment"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Find Strings to Externalize global action
	 * (value <code>"org.summer.sdt.ui.actions.FindStringsToExternalize"</code>).
	 *
	 * @deprecated Use {@link JdtActionConstants#EXTERNALIZE_STRINGS} instead
	 */
	public static final String FIND_STRINGS_TO_EXTERNALIZE= "org.summer.sdt.ui.actions.FindStringsToExternalize"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Externalize Strings global action
	 * (value <code>"org.summer.sdt.ui.actions.ExternalizeStrings"</code>).
	 */
	public static final String EXTERNALIZE_STRINGS= "org.summer.sdt.ui.actions.ExternalizeStrings"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Convert Line Delimiters To Windows global action
	 * (value <code>"org.summer.sdt.ui.actions.ConvertLineDelimitersToWindows"</code>).
	 */
	public static final String CONVERT_LINE_DELIMITERS_TO_WINDOWS= "org.summer.sdt.ui.actions.ConvertLineDelimitersToWindows"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Convert Line Delimiters To UNIX global action
	 * (value <code>"org.summer.sdt.ui.actions.ConvertLineDelimitersToUNIX"</code>).
	 */
	public static final String CONVERT_LINE_DELIMITERS_TO_UNIX= "org.summer.sdt.ui.actions.ConvertLineDelimitersToUNIX"; //$NON-NLS-1$

	/**
	 * Source menu: name of standardConvert Line Delimiters To Mac global action
	 * (value <code>"org.summer.sdt.ui.actions.ConvertLineDelimitersToMac"</code>).
	 */
	public static final String CONVERT_LINE_DELIMITERS_TO_MAC= "org.summer.sdt.ui.actions.ConvertLineDelimitersToMac"; //$NON-NLS-1$

	/**
	 * Source menu: name of standard Clean up global action
	 * (value <code>"org.summer.sdt.ui.actions.CleanUp"</code>).
	 *
	 * @since 3.2
	 */
	public static final String CLEAN_UP= "org.summer.sdt.ui.actions.CleanUp"; //$NON-NLS-1$

	// Refactor menu

	/**
	 * Refactor menu: name of standard Self Encapsulate Field global action
	 * (value <code>"org.summer.sdt.ui.actions.SelfEncapsulateField"</code>).
	 */
	public static final String SELF_ENCAPSULATE_FIELD= "org.summer.sdt.ui.actions.SelfEncapsulateField"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Modify Parameters global action
	 * (value <code>"org.summer.sdt.ui.actions.ModifyParameters"</code>).
	 */
	public static final String MODIFY_PARAMETERS= "org.summer.sdt.ui.actions.ModifyParameters"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Pull Up global action
	 * (value <code>"org.summer.sdt.ui.actions.PullUp"</code>).
	 */
	public static final String PULL_UP= "org.summer.sdt.ui.actions.PullUp"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Push Down global action
	 * (value <code>"org.summer.sdt.ui.actions.PushDown"</code>).
	 *
	 * @since 2.1
	 */
	public static final String PUSH_DOWN= "org.summer.sdt.ui.actions.PushDown"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Move Element global action
	 * (value <code>"org.summer.sdt.ui.actions.Move"</code>).
	 */
	public static final String MOVE= "org.summer.sdt.ui.actions.Move"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Rename Element global action
	 * (value <code>"org.summer.sdt.ui.actions.Rename"</code>).
	 */
	public static final String RENAME= "org.summer.sdt.ui.actions.Rename"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Inline Temp global action
	 * (value <code>"org.summer.sdt.ui.actions.InlineTemp"</code>).
	 * @deprecated Use INLINE
	 */
	public static final String INLINE_TEMP= "org.summer.sdt.ui.actions.InlineTemp"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Temp global action
	 * (value <code>"org.summer.sdt.ui.actions.ExtractTemp"</code>).
	 */
	public static final String EXTRACT_TEMP= "org.summer.sdt.ui.actions.ExtractTemp"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Constant global action
	 * (value <code>"org.summer.sdt.ui.actions.ExtractConstant"</code>).
	 *
	 * @since 2.1
	 */
	public static final String EXTRACT_CONSTANT= "org.summer.sdt.ui.actions.ExtractConstant"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Introduce Parameter global action
	 * (value <code>"org.summer.sdt.ui.actions.IntroduceParameter"</code>).
	 *
	 * @since 3.0
	 */
	public static final String INTRODUCE_PARAMETER= "org.summer.sdt.ui.actions.IntroduceParameter"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Introduce Factory global action
	 * (value <code>"org.summer.sdt.ui.actions.IntroduceFactory"</code>).
	 *
	 * @since 3.0
	 */
	public static final String INTRODUCE_FACTORY= "org.summer.sdt.ui.actions.IntroduceFactory"; //$NON-NLS-1$

	/**
	 * Refactor menu> name of the standard Introduce Parameter Object action
	 * (value <code>"org.summer.sdt.ui.actions.IntroduceParameterObject"</code>).
	 *
	 * @since 3.4
	 */
	public static final String INTRODUCE_PARAMETER_OBJECT= "org.summer.sdt.ui.actions.IntroduceParameterObject"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Method global action
	 * (value <code>"org.summer.sdt.ui.actions.ExtractMethod"</code>).
	 */
	public static final String EXTRACT_METHOD= "org.summer.sdt.ui.actions.ExtractMethod"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Replace Invocations global action
	 * (value <code>"org.summer.sdt.ui.actions.ReplaceInvocations"</code>).
	 *
	 * @since 3.2
	 */
	public static final String REPLACE_INVOCATIONS="org.summer.sdt.ui.actions.ReplaceInvocations"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Introduce Indirection global action
	 * (value <code>"org.summer.sdt.ui.actions.IntroduceIndirection"</code>).
	 *
	 * @since 3.2
	 */
	public static final String INTRODUCE_INDIRECTION= "org.summer.sdt.ui.actions.IntroduceIndirection"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Inline global action
	 * (value <code>"org.summer.sdt.ui.actions.Inline"</code>).
	 *
	 * @since 2.1
	 */
	public static final String INLINE= "org.summer.sdt.ui.actions.Inline"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Interface global action
	 * (value <code>"org.summer.sdt.ui.actions.ExtractInterface"</code>).
	 *
	 * @since 2.1
	 */
	public static final String EXTRACT_INTERFACE= "org.summer.sdt.ui.actions.ExtractInterface"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Extract Class global action
	 * (value <code>"org.summer.sdt.ui.actions.ExtractClass"</code>).
	 *
	 * @since 3.4
	 */
	public static final String EXTRACT_CLASS= "org.summer.sdt.ui.actions.ExtractClass"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Generalize Declared Type global action
	 * (value <code>"org.summer.sdt.ui.actions.ChangeType"</code>).
	 *
	 * @since 3.0
	 */
	public static final String CHANGE_TYPE= "org.summer.sdt.ui.actions.ChangeType"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard global action to convert a nested type to a top level type
	 * (value <code>"org.summer.sdt.ui.actions.MoveInnerToTop"</code>).
	 *
	 * @since 2.1
	 */
	public static final String CONVERT_NESTED_TO_TOP= "org.summer.sdt.ui.actions.ConvertNestedToTop"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Use Supertype global action
	 * (value <code>"org.summer.sdt.ui.actions.UseSupertype"</code>).
	 *
	 * @since 2.1
	 */
	public static final String USE_SUPERTYPE= "org.summer.sdt.ui.actions.UseSupertype"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Infer Generic Type Arguments global action
	 * (value <code>"org.summer.sdt.ui.actions.InferTypeArguments"</code>).
	 *
	 * @since 3.1
	 */
	public static final String INFER_TYPE_ARGUMENTS= "org.summer.sdt.ui.actions.InferTypeArguments"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard global action to convert a local
	 * variable to a field (value <code>"org.summer.sdt.ui.actions.ConvertLocalToField"</code>).
	 *
	 * @since 2.1
	 */
	public static final String CONVERT_LOCAL_TO_FIELD= "org.summer.sdt.ui.actions.ConvertLocalToField"; //$NON-NLS-1$

	/**
	 * Refactor menu: name of standard Covert Anonymous to Nested global action
	 * (value <code>"org.summer.sdt.ui.actions.ConvertAnonymousToNested"</code>).
	 *
	 * @since 2.1
	 */
	public static final String CONVERT_ANONYMOUS_TO_NESTED= "org.summer.sdt.ui.actions.ConvertAnonymousToNested"; //$NON-NLS-1$

	// Search Menu

	/**
	 * Search menu: name of standard Find References in Workspace global action
	 * (value <code>"org.summer.sdt.ui.actions.ReferencesInWorkspace"</code>).
	 */
	public static final String FIND_REFERENCES_IN_WORKSPACE= "org.summer.sdt.ui.actions.ReferencesInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find References in Project global action
	 * (value <code>"org.summer.sdt.ui.actions.ReferencesInProject"</code>).
	 */
	public static final String FIND_REFERENCES_IN_PROJECT= "org.summer.sdt.ui.actions.ReferencesInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find References in Hierarchy global action
	 * (value <code>"org.summer.sdt.ui.actions.ReferencesInHierarchy"</code>).
	 */
	public static final String FIND_REFERENCES_IN_HIERARCHY= "org.summer.sdt.ui.actions.ReferencesInHierarchy"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find References in Working Set global action
	 * (value <code>"org.summer.sdt.ui.actions.ReferencesInWorkingSet"</code>).
	 */
	public static final String FIND_REFERENCES_IN_WORKING_SET= "org.summer.sdt.ui.actions.ReferencesInWorkingSet"; //$NON-NLS-1$



	/**
	 * Search menu: name of standard Find Declarations in Workspace global action
	 * (value <code>"org.summer.sdt.ui.actions.DeclarationsInWorkspace"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_WORKSPACE= "org.summer.sdt.ui.actions.DeclarationsInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Declarations in Project global action
	 * (value <code>"org.summer.sdt.ui.actions.DeclarationsInProject"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_PROJECT= "org.summer.sdt.ui.actions.DeclarationsInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Declarations in Hierarchy global action
	 * (value <code>"org.summer.sdt.ui.actions.DeclarationsInHierarchy"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_HIERARCHY= "org.summer.sdt.ui.actions.DeclarationsInHierarchy"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Declarations in Working Set global action
	 * (value <code>"org.summer.sdt.ui.actions.DeclarationsInWorkingSet"</code>).
	 */
	public static final String FIND_DECLARATIONS_IN_WORKING_SET= "org.summer.sdt.ui.actions.DeclarationsInWorkingSet"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Implementors in Workspace global action
	 * (value <code>"org.summer.sdt.ui.actions.ImplementorsInWorkspace"</code>).
	 */
	public static final String FIND_IMPLEMENTORS_IN_WORKSPACE= "org.summer.sdt.ui.actions.ImplementorsInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Implementors in Project global action
	 * (value <code>"org.summer.sdt.ui.actions.ImplementorsInProject"</code>).
	 */
	public static final String FIND_IMPLEMENTORS_IN_PROJECT= "org.summer.sdt.ui.actions.ImplementorsInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Implementors in Working Set global action
	 * (value <code>"org.summer.sdt.ui.actions.ImplementorsInWorkingSet"</code>).
	 */
	public static final String FIND_IMPLEMENTORS_IN_WORKING_SET= "org.summer.sdt.ui.actions.ImplementorsInWorkingSet"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Workspace global action
	 * (value <code>"org.summer.sdt.ui.actions.ReadAccessInWorkspace"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_WORKSPACE= "org.summer.sdt.ui.actions.ReadAccessInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Project global action
	 * (value <code>"org.summer.sdt.ui.actions.ReadAccessInProject"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_PROJECT= "org.summer.sdt.ui.actions.ReadAccessInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Hierarchy global action
	 * (value <code>"org.summer.sdt.ui.actions.ReadAccessInHierarchy"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_HIERARCHY= "org.summer.sdt.ui.actions.ReadAccessInHierarchy"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Working Set global action
	 * (value <code>"org.summer.sdt.ui.actions.ReadAccessInWorkingSet"</code>).
	 */
	public static final String FIND_READ_ACCESS_IN_WORKING_SET= "org.summer.sdt.ui.actions.ReadAccessInWorkingSet"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Write Access in Workspace global action
	 * (value <code>"org.summer.sdt.ui.actions.WriteAccessInWorkspace"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_WORKSPACE= "org.summer.sdt.ui.actions.WriteAccessInWorkspace"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Write Access in Project global action
	 * (value <code>"org.summer.sdt.ui.actions.WriteAccessInProject"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_PROJECT= "org.summer.sdt.ui.actions.WriteAccessInProject"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Hierarchy global action
	 * (value <code>"org.summer.sdt.ui.actions.WriteAccessInHierarchy"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_HIERARCHY= "org.summer.sdt.ui.actions.WriteAccessInHierarchy"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find Read Access in Working Set global action
	 * (value <code>"org.summer.sdt.ui.actions.WriteAccessInWorkingSet"</code>).
	 */
	public static final String FIND_WRITE_ACCESS_IN_WORKING_SET= "org.summer.sdt.ui.actions.WriteAccessInWorkingSet"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Occurrences in File global action (value
	 * <code>"org.summer.sdt.ui.actions.OccurrencesInFile"</code>).
	 *
	 * @since 2.1
	 */
	public static final String FIND_OCCURRENCES_IN_FILE= "org.summer.sdt.ui.actions.OccurrencesInFile"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find exception occurrences global action (value
	 * <code>"org.summer.sdt.ui.actions.ExceptionOccurrences"</code>).
	 *
	 * @since 3.0
	 */
	public static final String FIND_EXCEPTION_OCCURRENCES= "org.summer.sdt.ui.actions.ExceptionOccurrences"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find implement occurrences global action (value
	 * <code>"org.summer.sdt.ui.actions.ImplementOccurrences"</code>).
	 *
	 * @since 3.1
	 */
	public static final String FIND_IMPLEMENT_OCCURRENCES= "org.summer.sdt.ui.actions.ImplementOccurrences"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard method exit occurrences global action (value
	 * <code>"org.summer.sdt.ui.actions.MethodExitOccurrences"</code>).
	 *
	 * @since 3.4
	 */
	public static final String FIND_METHOD_EXIT_OCCURRENCES= "org.summer.sdt.ui.actions.MethodExitOccurrences"; //$NON-NLS-1$

	/**
	 * Search menu: name of standard Find break/continue occurrences global action (value
	 * <code>"org.summer.sdt.ui.actions.BreakContinueTargetOccurrences"</code>).
	 *
	 * @since 3.4
	 */
	public static final String FIND_BREAK_CONTINUE_TARGET_OCCURRENCES= "org.summer.sdt.ui.actions.BreakContinueTargetOccurrences"; //$NON-NLS-1$


}

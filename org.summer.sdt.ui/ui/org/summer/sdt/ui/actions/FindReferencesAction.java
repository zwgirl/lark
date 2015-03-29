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
package org.summer.sdt.ui.actions;

import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.IField;
import org.summer.sdt.core.IImportDeclaration;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.ILocalVariable;
import org.summer.sdt.core.IMethod;
import org.summer.sdt.core.IPackageDeclaration;
import org.summer.sdt.core.IPackageFragment;
import org.summer.sdt.core.IType;
import org.summer.sdt.core.ITypeParameter;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.core.search.IJavaSearchConstants;
import org.summer.sdt.core.search.IJavaSearchScope;
import org.summer.sdt.internal.ui.IJavaHelpContextIds;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.internal.ui.javaeditor.JavaEditor;
import org.summer.sdt.internal.ui.search.JavaSearchScopeFactory;
import org.summer.sdt.internal.ui.search.SearchMessages;
import org.summer.sdt.internal.ui.search.SearchUtil;
import org.summer.sdt.ui.search.ElementQuerySpecification;
import org.summer.sdt.ui.search.QuerySpecification;

/**
 * Finds references of the selected element in the workspace.
 * The action is applicable to selections representing a Java element.
 *
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 *
 * @since 2.0
 *
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FindReferencesAction extends FindAction {

	/**
	 * Creates a new <code>FindReferencesAction</code>. The action
	 * requires that the selection provided by the site's selection provider is of type
	 * <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 *
	 * @param site the site providing context information for this action
	 */
	public FindReferencesAction(IWorkbenchSite site) {
		super(site);
	}

	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the Java editor
	 *
	 * @noreference This constructor is not intended to be referenced by clients.
	 */
	public FindReferencesAction(JavaEditor editor) {
		super(editor);
	}

	@Override
	Class<?>[] getValidTypes() {
		return new Class[] { ICompilationUnit.class, IType.class, IMethod.class, IField.class, IPackageDeclaration.class, IImportDeclaration.class, IPackageFragment.class, ILocalVariable.class, ITypeParameter.class };
	}

	@Override
	void init() {
		setText(SearchMessages.Search_FindReferencesAction_label);
		setToolTipText(SearchMessages.Search_FindReferencesAction_tooltip);
		setImageDescriptor(JavaPluginImages.DESC_OBJS_SEARCH_REF);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.FIND_REFERENCES_IN_WORKSPACE_ACTION);
	}

	@Override
	int getLimitTo() {
		return IJavaSearchConstants.REFERENCES;
	}

	@Override
	QuerySpecification createQuery(IJavaElement element) throws JavaModelException, InterruptedException {
		JavaSearchScopeFactory factory= JavaSearchScopeFactory.getInstance();
		boolean isInsideJRE= factory.isInsideJRE(element);

		IJavaSearchScope scope= factory.createWorkspaceScope(isInsideJRE);
		String description= factory.getWorkspaceScopeDescription(isInsideJRE);
		return new ElementQuerySpecification(element, getLimitTo(), scope, description);
	}

	@Override
	public void run(IJavaElement element) {
		SearchUtil.warnIfBinaryConstant(element, getShell());
		super.run(element);
	}
}

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
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.ILocalVariable;
import org.summer.sdt.core.IMethod;
import org.summer.sdt.core.IType;
import org.summer.sdt.core.ITypeParameter;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.core.search.IJavaSearchScope;
import org.summer.sdt.core.search.SearchEngine;
import org.summer.sdt.internal.ui.IJavaHelpContextIds;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.internal.ui.javaeditor.JavaEditor;
import org.summer.sdt.internal.ui.search.JavaSearchScopeFactory;
import org.summer.sdt.internal.ui.search.SearchMessages;
import org.summer.sdt.ui.search.ElementQuerySpecification;
import org.summer.sdt.ui.search.QuerySpecification;

/**
 * Finds references of the selected element in its hierarchy.
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
public class FindReferencesInHierarchyAction extends FindReferencesAction {

	/**
	 * Creates a new <code>FindReferencesInHierarchyAction</code>. The action
	 * requires that the selection provided by the site's selection provider is of type
	 * <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 *
	 * @param site the site providing context information for this action
	 */
	public FindReferencesInHierarchyAction(IWorkbenchSite site) {
		super(site);
	}

	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the Java editor
	 *
	 * @noreference This constructor is not intended to be referenced by clients.
	 */
	public FindReferencesInHierarchyAction(JavaEditor editor) {
		super(editor);
	}

	@Override
	Class<?>[] getValidTypes() {
		return new Class[] { ICompilationUnit.class, IType.class, IMethod.class, IField.class, ILocalVariable.class, ITypeParameter.class };
	}

	@Override
	void init() {
		setText(SearchMessages.Search_FindHierarchyReferencesAction_label);
		setToolTipText(SearchMessages.Search_FindHierarchyReferencesAction_tooltip);
		setImageDescriptor(JavaPluginImages.DESC_OBJS_SEARCH_REF);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.FIND_REFERENCES_IN_HIERARCHY_ACTION);
	}

	@Override
	QuerySpecification createQuery(IJavaElement element) throws JavaModelException, InterruptedException {
		IType type= getType(element);
		if (type == null) {
			return super.createQuery(element);
		}
		JavaSearchScopeFactory factory= JavaSearchScopeFactory.getInstance();
		IJavaSearchScope scope= SearchEngine.createHierarchyScope(type);
		String description= factory.getHierarchyScopeDescription(type);
		return new ElementQuerySpecification(element, getLimitTo(), scope, description);
	}

}

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
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IType;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.core.search.IJavaSearchConstants;
import org.summer.sdt.internal.ui.IJavaHelpContextIds;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.internal.ui.javaeditor.JavaEditor;
import org.summer.sdt.internal.ui.search.SearchMessages;

/**
 * Finds implementors of the selected element in the workspace.
 * The action is applicable to selections representing a Java interface.
 *
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 *
 * @since 2.0
 *
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FindImplementorsAction extends FindAction {

	/**
	 * Creates a new <code>FindImplementorsAction</code>. The action
	 * requires that the selection provided by the site's selection provider is of type
	 * <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 *
	 * @param site the site providing context information for this action
	 */
	public FindImplementorsAction(IWorkbenchSite site) {
		super(site);
	}

	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the Java editor
	 *
	 * @noreference This constructor is not intended to be referenced by clients.
	 */
	public FindImplementorsAction(JavaEditor editor) {
		super(editor);
	}

	@Override
	void init() {
		setText(SearchMessages.Search_FindImplementorsAction_label);
		setToolTipText(SearchMessages.Search_FindImplementorsAction_tooltip);
		setImageDescriptor(JavaPluginImages.DESC_OBJS_SEARCH_DECL);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.FIND_IMPLEMENTORS_IN_WORKSPACE_ACTION);
	}

	@Override
	Class<?>[] getValidTypes() {
		return new Class[] { ICompilationUnit.class, IType.class};
	}

	@Override
	boolean canOperateOn(IJavaElement element) {
		if (!super.canOperateOn(element))
			return false;

		if (element.getElementType() == IJavaElement.TYPE)
			try {
				return ((IType) element).isInterface();
			} catch (JavaModelException ex) {
				return false;
			}
		// should not happen: handled by super.canOperateOn
		return false;
	}

	@Override
	int getLimitTo() {
		return IJavaSearchConstants.IMPLEMENTORS;
	}

	@Override
	String getOperationUnavailableMessage() {
		return SearchMessages.JavaElementAction_operationUnavailable_interface;
	}
}


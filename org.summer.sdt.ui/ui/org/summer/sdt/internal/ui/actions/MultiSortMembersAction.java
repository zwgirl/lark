/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Alex Blewitt - Bug 133277 Allow Sort Members to be performed on package and project levels
 *******************************************************************************/
package org.summer.sdt.internal.ui.actions;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IParent;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.internal.corext.fix.CleanUpConstants;
import org.summer.sdt.internal.ui.IJavaHelpContextIds;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.dialogs.SortMembersMessageDialog;
import org.summer.sdt.internal.ui.fix.SortMembersCleanUp;
import org.summer.sdt.internal.ui.javaeditor.JavaEditor;
import org.summer.sdt.ui.cleanup.CleanUpOptions;
import org.summer.sdt.ui.cleanup.ICleanUp;

public class MultiSortMembersAction extends CleanUpAction {

	public MultiSortMembersAction(IWorkbenchSite site) {
		super(site);

		setText(ActionMessages.SortMembersAction_label);
		setDescription(ActionMessages.SortMembersAction_description);
		setToolTipText(ActionMessages.SortMembersAction_tooltip);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.SORT_MEMBERS_ACTION);
	}

	public MultiSortMembersAction(JavaEditor editor) {
		super(editor);

		setText(ActionMessages.SortMembersAction_label);
		setDescription(ActionMessages.SortMembersAction_description);
		setToolTipText(ActionMessages.SortMembersAction_tooltip);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.SORT_MEMBERS_ACTION);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ICleanUp[] getCleanUps(ICompilationUnit[] units) {
		try {
	        if (!hasMembersToSort(units)) {
				MessageDialog.openInformation(getShell(), ActionMessages.MultiSortMembersAction_noElementsToSortDialog_title, ActionMessages.MultiSortMembersAction_noElementsToSortDialog_message);
	        	return null;
	        }
        } catch (JavaModelException e) {
        	JavaPlugin.log(e);
	        return null;
        }

		Map<String, String> settings= getSettings();
		if (settings == null)
			return null;

		return new ICleanUp[] {
			new SortMembersCleanUp(settings)
		};
	}

	protected Map<String, String> getSettings() {
		SortMembersMessageDialog dialog= new SortMembersMessageDialog(getShell());
		if (dialog.open() != Window.OK)
			return null;

		Hashtable<String, String> settings= new Hashtable<String, String>();
		settings.put(CleanUpConstants.SORT_MEMBERS, CleanUpOptions.TRUE);
		settings.put(CleanUpConstants.SORT_MEMBERS_ALL, !dialog.isNotSortingFieldsEnabled() ? CleanUpOptions.TRUE : CleanUpOptions.FALSE);
		return settings;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getActionName() {
		return ActionMessages.SortMembersAction_dialog_title;
	}

	private boolean hasMembersToSort(ICompilationUnit[] units) throws JavaModelException {
		for (int i= 0; i < units.length; i++) {
			if (hasMembersToSort(units[i].getTypes()))
				return true;
		}

		return false;
	}

	private boolean hasMembersToSort(IJavaElement[] members) throws JavaModelException {
		if (members.length > 1)
			return true;

		if (members.length == 0)
			return false;

		IJavaElement elem= members[0];
		if (!(elem instanceof IParent))
			return false;

		return hasMembersToSort(((IParent)elem).getChildren());
	}

}

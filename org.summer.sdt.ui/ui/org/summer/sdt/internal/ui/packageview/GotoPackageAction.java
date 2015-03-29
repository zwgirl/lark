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
package org.summer.sdt.internal.ui.packageview;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.progress.IProgressService;
import org.summer.sdt.core.IPackageFragment;
import org.summer.sdt.core.search.IJavaSearchScope;
import org.summer.sdt.core.search.SearchEngine;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.internal.ui.IJavaHelpContextIds;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.dialogs.PackageSelectionDialog;
import org.summer.sdt.ui.JavaElementLabels;

class GotoPackageAction extends Action {

	private PackageExplorerPart fPackageExplorer;

	GotoPackageAction(PackageExplorerPart part) {
		super(PackagesMessages.GotoPackage_action_label);
		setDescription(PackagesMessages.GotoPackage_action_description);
		fPackageExplorer= part;
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.GOTO_PACKAGE_ACTION);
	}

	@Override
	public void run() {
		Shell shell= JavaPlugin.getActiveWorkbenchShell();
		SelectionDialog dialog= createAllPackagesDialog(shell);
		dialog.setTitle(getDialogTitle());
		dialog.setMessage(PackagesMessages.GotoPackage_dialog_message);
		dialog.open();
		Object[] res= dialog.getResult();
		if (res != null && res.length == 1)
			gotoPackage((IPackageFragment)res[0]);
	}

	private SelectionDialog createAllPackagesDialog(Shell shell) {
		IProgressService progressService= PlatformUI.getWorkbench().getProgressService();
		IJavaSearchScope scope= SearchEngine.createWorkspaceScope();
		int flag= PackageSelectionDialog.F_HIDE_EMPTY_INNER;
		PackageSelectionDialog dialog= new PackageSelectionDialog(shell, progressService, flag, scope);
		dialog.setFilter(""); //$NON-NLS-1$
		dialog.setIgnoreCase(false);
		dialog.setMultipleSelection(false);
		return dialog;
	}

	private void gotoPackage(IPackageFragment p) {
		fPackageExplorer.selectReveal(new StructuredSelection(p));
		if (!p.equals(getSelectedElement())) {
			MessageDialog.openInformation(fPackageExplorer.getSite().getShell(),
				getDialogTitle(),
				Messages.format(PackagesMessages.PackageExplorer_element_not_present, JavaElementLabels.getElementLabel(p, JavaElementLabels.ALL_DEFAULT)));
		}
	}

	private Object getSelectedElement() {
		return ((IStructuredSelection)fPackageExplorer.getSite().getSelectionProvider().getSelection()).getFirstElement();
	}

	private String getDialogTitle() {
		return PackagesMessages.GotoPackage_dialog_title;
	}

}

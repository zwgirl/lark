/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.refactoring.nls;

import org.summer.sdt.core.IJavaProject;
import org.summer.sdt.core.IPackageFragment;
import org.summer.sdt.core.IPackageFragmentRoot;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.refactoring.contentassist.JavaPackageCompletionProcessor;
import org.summer.sdt.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.summer.sdt.ui.JavaElementLabelProvider;

public final class PackageSelectionDialogButtonField extends StringButtonDialogField {

	private IPackageFragment fPackageFragment;

	public PackageSelectionDialogButtonField(String label, String button, PackageBrowseAdapter adapter, IJavaProject root) {
		super(adapter);
		setContentAssistProcessor(new JavaPackageCompletionProcessor(new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_ROOT)));

		IPackageFragmentRoot[] roots;
		try {
			roots= root.getAllPackageFragmentRoots();
			((JavaPackageCompletionProcessor)getContentAssistProcessor()).setPackageFragmentRoot(roots[0]);
		} catch (JavaModelException e) {
			JavaPlugin.log(e);
			// if exception no content assist .. but thats no problem
		}
		setLabelText(label);
		setButtonLabel(button);
		adapter.setReceiver(this);
	}

	public void setPackage(IPackageFragment packageFragment) {
		fPackageFragment= packageFragment;
		if (fPackageFragment != null) {
			setText(fPackageFragment.getElementName());

			JavaPackageCompletionProcessor contentAssist= (JavaPackageCompletionProcessor)getContentAssistProcessor();
			contentAssist.setPackageFragmentRoot((IPackageFragmentRoot)packageFragment.getParent());
		}
	}
}

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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.summer.sdt.internal.ui.IJavaHelpContextIds;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.actions.ActionMessages;
import org.summer.sdt.internal.ui.util.ExceptionHandler;
import org.summer.sdt.ui.JavaUI;

/**
 * Action to programmatically open a Java perspective.
 *
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 *
 * @since 2.0
 *
 * @noextend This class is not intended to be subclassed by clients.
 */
public class OpenJavaPerspectiveAction extends Action {

	/**
	 * Create a new <code>OpenJavaPerspectiveAction</code>.
	 */
	public OpenJavaPerspectiveAction() {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.OPEN_JAVA_PERSPECTIVE_ACTION);
	}

	@Override
	public void run() {
		IWorkbench workbench= JavaPlugin.getDefault().getWorkbench();
		IWorkbenchWindow window= workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page= window.getActivePage();
		IAdaptable input;
		if (page != null)
			input= page.getInput();
		else
			input= ResourcesPlugin.getWorkspace().getRoot();
		try {
			workbench.showPerspective(JavaUI.ID_PERSPECTIVE, window, input);
		} catch (WorkbenchException e) {
			ExceptionHandler.handle(e, window.getShell(),
				ActionMessages.OpenJavaPerspectiveAction_dialog_title,
				ActionMessages.OpenJavaPerspectiveAction_error_open_failed);
		}
	}
}

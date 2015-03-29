/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.ui.classpath;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.summer.sdt.internal.launching.DefaultProjectClasspathEntry;
import org.summer.sdt.launching.IRuntimeClasspathEntry;

public class DefaultClasspathEntryEditor implements IClasspathEditor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.internal.debug.ui.classpath.IClasspathEditor#canEdit(org.eclipse.debug.core.ILaunchConfiguration,
	 * org.summer.sdt.launching.IRuntimeClasspathEntry[])
	 */
	public boolean canEdit(ILaunchConfiguration configuration, IRuntimeClasspathEntry[] entries) {
		return entries.length == 1 && entries[0] instanceof DefaultProjectClasspathEntry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.internal.debug.ui.classpath.IClasspathEditor#edit(org.eclipse.swt.widgets.Shell,
	 * org.eclipse.debug.core.ILaunchConfiguration, org.summer.sdt.launching.IRuntimeClasspathEntry[])
	 */
	public IRuntimeClasspathEntry[] edit(Shell shell, ILaunchConfiguration configuration, IRuntimeClasspathEntry[] entries) {
		DefaultClasspathEntryDialog dialog = new DefaultClasspathEntryDialog(shell, entries[0]);
		if (dialog.open() == Window.OK) {
			return entries;
		}
		return null;
	}

}

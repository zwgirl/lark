/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.summer.sdt.internal.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.summer.sdt.core.IClasspathEntry;
import org.summer.sdt.core.IJavaProject;
import org.summer.sdt.launching.IRuntimeClasspathEntry;
import org.summer.sdt.launching.IRuntimeClasspathEntryResolver;
import org.summer.sdt.launching.IVMInstall;
import org.summer.sdt.launching.JavaRuntime;


public class VariableClasspathResolver implements IRuntimeClasspathEntryResolver {

	/* (non-Javadoc)
	 * @see org.summer.sdt.launching.IRuntimeClasspathEntryResolver#resolveRuntimeClasspathEntry(org.summer.sdt.launching.IRuntimeClasspathEntry, org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(IRuntimeClasspathEntry entry, ILaunchConfiguration configuration) throws CoreException {
		return resolveRuntimeClasspathEntry(entry);
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.launching.IRuntimeClasspathEntryResolver#resolveRuntimeClasspathEntry(org.summer.sdt.launching.IRuntimeClasspathEntry, org.summer.sdt.core.IJavaProject)
	 */
	public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(IRuntimeClasspathEntry entry, IJavaProject project) throws CoreException {
		return resolveRuntimeClasspathEntry(entry);
	}

	private IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(IRuntimeClasspathEntry entry) throws CoreException{
		String variableString = ((VariableClasspathEntry)entry).getVariableString();
		String strpath = VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(variableString);
		IPath path = new Path(strpath).makeAbsolute();
		IRuntimeClasspathEntry archiveEntry = JavaRuntime.newArchiveRuntimeClasspathEntry(path);
		return new IRuntimeClasspathEntry[] { archiveEntry };	
	}
	
	/* (non-Javadoc)
	 * @see org.summer.sdt.launching.IRuntimeClasspathEntryResolver#resolveVMInstall(org.summer.sdt.core.IClasspathEntry)
	 */
	public IVMInstall resolveVMInstall(IClasspathEntry entry) throws CoreException {
		return null;
	}
}

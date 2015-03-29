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
package org.summer.sdt.internal.ui.filters;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IPackageFragment;
import org.summer.sdt.core.JavaCore;

public class NoPackageContainingFoldersFilter extends ViewerFilter {

	/*
	 * @see ViewerFilter
	 */
	@Override
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IFolder) {
			try {
				if (containsPackage((IFolder)element))
					return true;
				else
					return false;
			} catch (CoreException e) {
				return true;
			}
		}

		return true;
	}

	private boolean containsPackage(IFolder folder) throws CoreException {
		IJavaElement element= JavaCore.create(folder);
		if (element instanceof IPackageFragment)
			return true;
		IResource[] resources= folder.members();
		for (int i= 0; i < resources.length; i++) {
			if (resources[i] instanceof IFolder && containsPackage((IFolder)resources[i]))
				return true;
		}
		return false;
	}

}

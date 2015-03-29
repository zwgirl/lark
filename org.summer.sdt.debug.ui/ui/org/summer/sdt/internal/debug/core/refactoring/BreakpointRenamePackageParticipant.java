/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.core.refactoring;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.ltk.core.refactoring.Change;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IPackageFragment;
import org.summer.sdt.core.IPackageFragmentRoot;
import org.summer.sdt.core.IType;
import org.summer.sdt.debug.core.IJavaBreakpoint;
import org.summer.sdt.internal.debug.ui.BreakpointUtils;

/**
 * Breakpoint participant for package rename.
 * 
 * @since 3.2
 */
public class BreakpointRenamePackageParticipant extends BreakpointRenameParticipant {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.internal.debug.core.refactoring.BreakpointRenameParticipant#accepts(org.summer.sdt.core.IJavaElement)
	 */
	@Override
	protected boolean accepts(IJavaElement element) {
		return element instanceof IPackageFragment;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.internal.debug.core.refactoring.BreakpointRenameParticipant#gatherChanges(org.eclipse.core.resources.IMarker[],
	 * java.util.List, java.lang.String)
	 */
	@Override
	protected void gatherChanges(IMarker[] markers, List<Change> changes, String destPackageName) throws CoreException, OperationCanceledException {
		IPackageFragment originalPackage = (IPackageFragment) getOriginalElement();
		String originalPackageName = originalPackage.getElementName();
		IPackageFragmentRoot root = (IPackageFragmentRoot)originalPackage.getParent();
		for (int i = 0; i < markers.length; i++) {
			IMarker marker = markers[i];
			IBreakpoint breakpoint = getBreakpoint(marker);
			if (breakpoint instanceof IJavaBreakpoint) {
				IJavaBreakpoint javaBreakpoint = (IJavaBreakpoint) breakpoint;
				IType breakpointType = BreakpointUtils.getType(javaBreakpoint);
				if (breakpointType != null) {
					String breakpointPackageName = breakpointType.getPackageFragment().getElementName();
					String destBreakpointPackageName = destPackageName;
					if (breakpointPackageName.length() > originalPackageName.length()) {
						destBreakpointPackageName += breakpointPackageName.substring(originalPackageName.length());
					}
					IPackageFragment destBreakpointPackage = root.getPackageFragment(destBreakpointPackageName);
					ICompilationUnit cu = destBreakpointPackage.getCompilationUnit(breakpointType.getCompilationUnit().getElementName());
					IJavaElement element = BreakpointChange.findElement(cu, breakpointType);
					if (element != null) {
						if (element instanceof IType) {
							IType destType = (IType) element;
							changes.add(createTypeChange(javaBreakpoint, destType, breakpointType));
						}
					}
				}
			}
		}
	}

}

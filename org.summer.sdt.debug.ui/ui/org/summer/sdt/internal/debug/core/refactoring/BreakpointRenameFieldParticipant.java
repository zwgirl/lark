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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.summer.sdt.core.IField;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IType;
import org.summer.sdt.debug.core.IJavaWatchpoint;
import org.summer.sdt.internal.debug.core.breakpoints.JavaWatchpoint;
import org.summer.sdt.internal.debug.ui.BreakpointUtils;

/**
 * Breakpoint participant for field rename.
 * 
 * @since 3.2
 */
public class BreakpointRenameFieldParticipant extends BreakpointRenameParticipant {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.internal.debug.core.refactoring.BreakpointRenameParticipant#accepts(org.summer.sdt.core.IJavaElement)
	 */
	@Override
	protected boolean accepts(IJavaElement element) {
		return element instanceof IField;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.internal.debug.core.refactoring.BreakpointRenameParticipant#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		List<Change> changes = new ArrayList<Change>();
		IResource resource = getBreakpointContainer();
		IMarker[] markers = resource.findMarkers(JavaWatchpoint.JAVA_WATCHPOINT, true, IResource.DEPTH_INFINITE);
		gatherChanges(markers, changes, getArguments().getNewName());
		if (changes.size() > 1) {
			return new CompositeChange(RefactoringMessages.BreakpointRenameParticipant_1, changes.toArray(new Change[changes.size()]));
		} else if (changes.size() == 1) {
			return changes.get(0);
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.internal.debug.core.refactoring.BreakpointRenameParticipant#gatherChanges(org.eclipse.core.resources.IMarker[],
	 * java.util.List, java.lang.String)
	 */
	@Override
	protected void gatherChanges(IMarker[] markers, List<Change> changes, String destFieldName) throws CoreException, OperationCanceledException {
		IField originalField = (IField) getOriginalElement();
		for (int i = 0; i < markers.length; i++) {
			IMarker marker = markers[i];
			IBreakpoint breakpoint = getBreakpoint(marker);
			if (breakpoint instanceof IJavaWatchpoint) {
				IJavaWatchpoint watchpoint = (IJavaWatchpoint) breakpoint;
				if(originalField.getElementName().equals(watchpoint.getFieldName())) {
					IType breakpointType = BreakpointUtils.getType(watchpoint);
					if (breakpointType != null && originalField.getDeclaringType().equals(breakpointType)) {
						IField destField = originalField.getDeclaringType().getField(destFieldName);
						changes.add(new WatchpointFieldChange(watchpoint, destField));
					}
				}
			}
		}
	}


}

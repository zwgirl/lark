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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.osgi.util.NLS;
import org.summer.sdt.core.IType;
import org.summer.sdt.debug.core.IJavaExceptionBreakpoint;
import org.summer.sdt.debug.core.JDIDebugModel;
import org.summer.sdt.internal.debug.ui.BreakpointUtils;

/**
 * @since 3.2
 *
 */
public class ExceptionBreakpointTypeChange extends ExceptionBreakpointChange {
	
	private IType fDestType;
	
	public ExceptionBreakpointTypeChange(IJavaExceptionBreakpoint breakpoint, IType destType) throws CoreException {
		super(breakpoint);
		fDestType = destType;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return NLS.bind(RefactoringMessages.ExceptionBreakpointTypeChange_0,
				new String[] {getBreakpointLabel(getOriginalBreakpoint()), fDestType.getElementName()});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		Map<String, Object> map = new HashMap<String, Object>();
		BreakpointUtils.addJavaBreakpointAttributes(map, fDestType);
		IResource resource = BreakpointUtils.getBreakpointResource(fDestType);
		IJavaExceptionBreakpoint breakpoint = JDIDebugModel.createExceptionBreakpoint(
				resource,
				fDestType.getFullyQualifiedName(),
				isCaught(),
				isUncaught(),
				isChecked(),
				true,
				map);
		apply(breakpoint);
		getOriginalBreakpoint().delete();
		return new DeleteBreakpointChange(breakpoint);
	}

}

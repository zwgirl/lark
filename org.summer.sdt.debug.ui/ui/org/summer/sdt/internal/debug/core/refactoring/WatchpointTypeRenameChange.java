/*******************************************************************************
 *  Copyright (c) 2005, 2014 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.core.refactoring;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.summer.sdt.core.IField;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IType;
import org.summer.sdt.core.refactoring.IJavaElementMapper;
import org.summer.sdt.core.refactoring.RenameTypeArguments;
import org.summer.sdt.debug.core.IJavaWatchpoint;
import org.summer.sdt.debug.core.JDIDebugModel;
import org.summer.sdt.internal.debug.ui.BreakpointUtils;

public class WatchpointTypeRenameChange extends WatchpointTypeChange {

	private RefactoringProcessor fProcessor;
	private RenameTypeArguments fArguments;

	public WatchpointTypeRenameChange(IJavaWatchpoint watchpoint, IType destType, IType originalType, RefactoringProcessor processor, RenameTypeArguments arguments) throws CoreException {
		super(watchpoint, destType, originalType);
		fProcessor = processor;
		fArguments = arguments;
	}
	
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		IField originalField = getOriginalType().getField(getFieldName());
		IField destinationField = null;
		
		if (fArguments.getUpdateSimilarDeclarations()) {
			IJavaElement[] similarDeclarations = fArguments.getSimilarDeclarations();
			if (similarDeclarations != null) {
				for (int i = 0; i < similarDeclarations.length; i++) {
					IJavaElement element = similarDeclarations[i];
					if (element.equals(originalField)) {
						IJavaElementMapper elementMapper = (IJavaElementMapper) fProcessor.getAdapter(IJavaElementMapper.class);
						destinationField = (IField) elementMapper.getRefactoredJavaElement(originalField);
						break;
					}
				}
			}
		}
		if (destinationField == null) {
			destinationField = getDestinationType().getField(getFieldName());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		BreakpointUtils.addJavaBreakpointAttributes(map, destinationField);
		map.put(IInternalDebugUIConstants.WORKING_SET_NAME, getOriginalWorkingSets()); // creating breakpoint in the original working set
		IResource resource = BreakpointUtils.getBreakpointResource(destinationField);
		int[] range = getNewLineNumberAndRange(destinationField);
		IJavaWatchpoint breakpoint = JDIDebugModel.createWatchpoint(
				resource,
				getDestinationType().getFullyQualifiedName(),
				destinationField.getElementName(),
				NO_LINE_NUMBER,
				range[1],
				range[2],
				getHitCount(),
				true,
				map);
		apply(breakpoint);
		getOriginalBreakpoint().delete();
		return new DeleteBreakpointChange(breakpoint);
	}

}

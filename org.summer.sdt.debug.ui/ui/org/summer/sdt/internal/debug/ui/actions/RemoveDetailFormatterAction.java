/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.ui.actions;


import org.eclipse.debug.core.DebugException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.summer.sdt.debug.core.IJavaType;
import org.summer.sdt.debug.core.IJavaValue;
import org.summer.sdt.debug.core.IJavaVariable;
import org.summer.sdt.internal.debug.ui.DetailFormatter;
import org.summer.sdt.internal.debug.ui.JavaDetailFormattersManager;
import org.summer.sdt.internal.debug.ui.display.JavaInspectExpression;

public class RemoveDetailFormatterAction extends ObjectActionDelegate {

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection selection= getCurrentSelection();
		if (selection == null || selection.size() != 1) {
			return;
		}
		Object element= selection.getFirstElement();
		IJavaType type;
		try {
			IJavaValue value;
			if (element instanceof IJavaVariable) {
				value = ((IJavaValue)((IJavaVariable) element).getValue());
			} else if (element instanceof JavaInspectExpression) {
				value = ((IJavaValue)((JavaInspectExpression) element).getValue());
			} else {
				return;
			}
			type= value.getJavaType();
		} catch (DebugException e) {
			return;
		}
		JavaDetailFormattersManager detailFormattersManager= JavaDetailFormattersManager.getDefault();
		DetailFormatter detailFormatter= detailFormattersManager.getAssociatedDetailFormatter(type);
		detailFormattersManager.removeAssociatedDetailFormatter(detailFormatter);
	}

}

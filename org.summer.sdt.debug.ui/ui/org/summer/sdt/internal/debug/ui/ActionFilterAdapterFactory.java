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
package org.summer.sdt.internal.debug.ui;

 
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IActionFilter;
import org.summer.sdt.core.IMember;
import org.summer.sdt.debug.core.IJavaStackFrame;
import org.summer.sdt.debug.core.IJavaThread;
import org.summer.sdt.debug.core.IJavaVariable;
import org.summer.sdt.internal.debug.ui.display.JavaInspectExpression;

/**
 * UI adapter factory for JDI Debug
 */
/*package*/ class ActionFilterAdapterFactory implements IAdapterFactory {

	/**
	 * @see IAdapterFactory#getAdapter(Object, Class)
	 */
	public Object getAdapter(Object obj, Class adapterType) {
		if (adapterType.isInstance(obj)) {
			return obj;
		}
		if (adapterType == IActionFilter.class) {
			if (obj instanceof IJavaThread) {
				return new JavaThreadActionFilter();
			} 
			else if (obj instanceof IJavaStackFrame) {
				return new JavaStackFrameActionFilter();
			} 
			else if (obj instanceof IMember) {
				return new MemberActionFilter();
			} 
			else if((obj instanceof IJavaVariable) || (obj instanceof JavaInspectExpression)) {
				return new JavaVarActionFilter();
			}
		}
		return null;
	}

	/**
	 * @see IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[] {
			IActionFilter.class 
		};
	}
}



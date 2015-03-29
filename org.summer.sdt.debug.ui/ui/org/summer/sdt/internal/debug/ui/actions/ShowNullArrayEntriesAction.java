/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.Viewer;
import org.summer.sdt.debug.core.IJavaDebugTarget;
import org.summer.sdt.internal.debug.core.model.JDIArrayEntryVariable;
import org.summer.sdt.internal.debug.ui.IJDIPreferencesConstants;
import org.summer.sdt.internal.debug.ui.JDIDebugUIPlugin;

/**
 * Shows non-final static variables
 */
public class ShowNullArrayEntriesAction extends ViewFilterAction {

	public ShowNullArrayEntriesAction() {
		super();
	}

	/**
	 * @see ViewFilterAction#getPreferenceKey()
	 */
	@Override
	protected String getPreferenceKey() {
		return IJDIPreferencesConstants.PREF_SHOW_NULL_ARRAY_ENTRIES; 
	}
	
	/**
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (getValue()) {
			// when on, filter nothing
			return true;
		}
		if (element instanceof JDIArrayEntryVariable) {
			JDIArrayEntryVariable variable = (JDIArrayEntryVariable)element;
			try {
				return !variable.getValue().equals(((IJavaDebugTarget)variable.getDebugTarget()).nullValue());				
			} catch (DebugException e) {
				JDIDebugUIPlugin.log(e);
			} 
		}
		return true;
	}	
	
	@Override
	protected boolean getPreferenceValue() {
		String key = getCompositeKey();
		IPreferenceStore store = getPreferenceStore();
		boolean value = false;
		if (store.contains(key)) {
			value = store.getBoolean(key);
		}
		return value;
	}
}

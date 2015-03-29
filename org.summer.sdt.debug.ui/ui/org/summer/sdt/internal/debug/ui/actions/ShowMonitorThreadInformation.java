/*******************************************************************************
 * Copyright (c) 2004, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.ui.actions;

import org.summer.sdt.debug.ui.IJavaDebugUIConstants;

/**
 * Toggle to display the thread and monitor information in the debug view.
 */
public class ShowMonitorThreadInformation extends ToggleBooleanPreferenceAction {

	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.debug.ui.actions.ViewFilterAction#getPreferenceKey()
	 */
	@Override
	protected String getPreferenceKey() {
		return IJavaDebugUIConstants.PREF_SHOW_MONITOR_THREAD_INFO;
	}
	
	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.debug.ui.actions.ViewFilterAction#getCompositeKey()
	 */
	@Override
	protected String getCompositeKey() {
		return getPreferenceKey();
	}	
}

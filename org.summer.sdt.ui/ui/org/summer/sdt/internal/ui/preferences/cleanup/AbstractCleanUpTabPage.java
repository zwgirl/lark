/*******************************************************************************
 * Copyright (c) 2008, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.preferences.cleanup;

import java.util.Map;

import org.summer.sdt.internal.ui.fix.AbstractCleanUp;
import org.summer.sdt.internal.ui.fix.MapCleanUpOptions;
import org.summer.sdt.ui.cleanup.CleanUpOptions;

public abstract class AbstractCleanUpTabPage extends CleanUpTabPage {

	private AbstractCleanUp[] fPreviewCleanUps;
	private Map<String, String> fValues;

	public AbstractCleanUpTabPage() {
		super();
	}

	protected abstract AbstractCleanUp[] createPreviewCleanUps(Map<String, String> values);

	/* 
	 * @see org.summer.sdt.internal.ui.preferences.cleanup.CleanUpTabPage#setWorkingValues(java.util.Map)
	 */
	@Override
	public void setWorkingValues(Map<String, String> workingValues) {
		super.setWorkingValues(workingValues);
		fValues= workingValues;
		setOptions(new MapCleanUpOptions(workingValues));
	}

	/* 
	 * @see org.summer.sdt.internal.ui.preferences.cleanup.ICleanUpTabPage#setOptions(org.summer.sdt.internal.ui.fix.CleanUpOptions)
	 */
	public void setOptions(CleanUpOptions options) {
	}

	/* 
	 * @see org.summer.sdt.internal.ui.preferences.cleanup.ICleanUpTabPage#getPreview()
	 */
	public String getPreview() {
		if (fPreviewCleanUps == null) {
			fPreviewCleanUps= createPreviewCleanUps(fValues);
		}
	
		StringBuffer buf= new StringBuffer();
		for (int i= 0; i < fPreviewCleanUps.length; i++) {
			buf.append(fPreviewCleanUps[i].getPreview());
			buf.append("\n"); //$NON-NLS-1$
		}
		return buf.toString();
	}

}

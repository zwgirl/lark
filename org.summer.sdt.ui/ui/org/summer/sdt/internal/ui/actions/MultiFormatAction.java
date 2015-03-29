/*******************************************************************************
 * Copyright (c) 2007, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.actions;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.ui.IWorkbenchSite;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.internal.corext.fix.CleanUpConstants;
import org.summer.sdt.internal.ui.fix.CodeFormatCleanUp;
import org.summer.sdt.ui.cleanup.CleanUpOptions;
import org.summer.sdt.ui.cleanup.ICleanUp;

/**
 * @since 3.4
 */
public class MultiFormatAction extends CleanUpAction {

	public MultiFormatAction(IWorkbenchSite site) {
		super(site);
	}

	/*
	 * @see org.summer.sdt.internal.ui.actions.CleanUpAction#createCleanUps(org.summer.sdt.core.ICompilationUnit[])
	 */
	@Override
	protected ICleanUp[] getCleanUps(ICompilationUnit[] units) {
		Map<String, String> settings= new Hashtable<String, String>();
		settings.put(CleanUpConstants.FORMAT_SOURCE_CODE, CleanUpOptions.TRUE);

		return new ICleanUp[] {
				new CodeFormatCleanUp(settings)
		};
	}

	/*
	 * @see org.summer.sdt.internal.ui.actions.CleanUpAction#getActionName()
	 */
	@Override
	protected String getActionName() {
		return ActionMessages.MultiFormatAction_name;
	}

}

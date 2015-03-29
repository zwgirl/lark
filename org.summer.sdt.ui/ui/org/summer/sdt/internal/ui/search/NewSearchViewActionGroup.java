/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.search;

import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionGroup;
import org.summer.sdt.internal.ui.actions.CompositeActionGroup;
import org.summer.sdt.ui.actions.GenerateActionGroup;
import org.summer.sdt.ui.actions.JavaSearchActionGroup;
import org.summer.sdt.ui.actions.OpenEditorActionGroup;
import org.summer.sdt.ui.actions.OpenViewActionGroup;
import org.summer.sdt.ui.actions.RefactorActionGroup;

class NewSearchViewActionGroup extends CompositeActionGroup {

	public NewSearchViewActionGroup(IViewPart part) {
		Assert.isNotNull(part);
		OpenViewActionGroup openViewActionGroup;
		setGroups(new ActionGroup[]{
			new OpenEditorActionGroup(part),
			openViewActionGroup= new OpenViewActionGroup(part),
			new GenerateActionGroup(part),
			new RefactorActionGroup(part),
			new JavaSearchActionGroup(part)
		});
		openViewActionGroup.containsShowInMenu(false);
	}
}


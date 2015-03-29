/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.typehierarchy;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.internal.ui.dnd.ViewerInputDropAdapter;
import org.summer.sdt.internal.ui.util.OpenTypeHierarchyUtil;
import org.summer.sdt.internal.ui.util.SelectionUtil;

public class TypeHierarchyTransferDropAdapter extends ViewerInputDropAdapter {

	private TypeHierarchyViewPart fTypeHierarchyViewPart;

	public TypeHierarchyTransferDropAdapter(TypeHierarchyViewPart viewPart, AbstractTreeViewer viewer) {
		super(viewer);
		fTypeHierarchyViewPart= viewPart;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doInputView(Object inputElement) {
		fTypeHierarchyViewPart.setInputElement((IJavaElement) inputElement);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object getInputElement(ISelection selection) {
		Object single= SelectionUtil.getSingleElement(selection);
		if (single == null)
			return null;

		IJavaElement[] candidates= OpenTypeHierarchyUtil.getCandidates(single);
		if (candidates != null && candidates.length > 0)
			return candidates[0];

		return null;
	}

}

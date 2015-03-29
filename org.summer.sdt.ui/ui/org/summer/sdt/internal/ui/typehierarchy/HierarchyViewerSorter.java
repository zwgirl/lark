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

import org.summer.sdt.core.IType;
import org.summer.sdt.core.ITypeHierarchy;

/**
  */
public class HierarchyViewerSorter extends AbstractHierarchyViewerSorter {

	private final TypeHierarchyLifeCycle fHierarchy;
	private boolean fSortByDefiningType;

	public HierarchyViewerSorter(TypeHierarchyLifeCycle cycle) {
		fHierarchy= cycle;
	}

	public void setSortByDefiningType(boolean sortByDefiningType) {
		fSortByDefiningType= sortByDefiningType;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.ui.typehierarchy.AbstractHierarchyViewerSorter#getTypeKind(org.summer.sdt.core.IType)
	 */
	@Override
	protected int getTypeFlags(IType type) {
		ITypeHierarchy hierarchy= getHierarchy(type);
		if (hierarchy != null) {
			return fHierarchy.getHierarchy().getCachedFlags(type);
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.summer.sdt.internal.ui.typehierarchy.AbstractHierarchyViewerSorter#isSortByDefiningType()
	 */
	@Override
	public boolean isSortByDefiningType() {
		return fSortByDefiningType;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.ui.typehierarchy.AbstractHierarchyViewerSorter#isSortAlphabetically()
	 */
	@Override
	public boolean isSortAlphabetically() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.ui.typehierarchy.AbstractHierarchyViewerSorter#getHierarchy(org.summer.sdt.core.IType)
	 */
	@Override
	protected ITypeHierarchy getHierarchy(IType type) {
		return fHierarchy.getHierarchy(); // hierarchy contains all types shown
	}

}

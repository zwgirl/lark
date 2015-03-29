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
package org.summer.sdt.internal.ui.browsing;

import org.eclipse.core.runtime.CoreException;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.ISourceRange;
import org.summer.sdt.core.ISourceReference;
import org.summer.sdt.core.IType;
import org.summer.sdt.internal.ui.viewsupport.ImageDescriptorRegistry;
import org.summer.sdt.ui.ProblemsLabelDecorator;

/**
 * Decorates top-level types with problem markers that
 * are above the first type.
 */
class TopLevelTypeProblemsLabelDecorator extends ProblemsLabelDecorator {

	public TopLevelTypeProblemsLabelDecorator(ImageDescriptorRegistry registry) {
		super(registry);
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.ProblemsLabelDecorator#isInside(int, ISourceReference)
	 */
	@Override
	protected boolean isInside(int pos, ISourceReference sourceElement) throws CoreException {
//		XXX: Work in progress for problem decorator being a workbench decorator
//		IDecoratorManager decoratorMgr= PlatformUI.getWorkbench().getDecoratorManager();
//		if (!decoratorMgr.getEnabled("org.summer.sdt.ui.problem.decorator")) //$NON-NLS-1$
//			return false;

		if (!(sourceElement instanceof IType) || ((IType)sourceElement).getDeclaringType() != null)
			return false;

		ICompilationUnit cu= ((IType)sourceElement).getCompilationUnit();
		if (cu == null)
			return false;
		IType[] types= cu.getTypes();
		if (types.length < 1)
			return false;

		int firstTypeStartOffset= -1;
		ISourceRange range= types[0].getSourceRange();
		if (range != null)
			firstTypeStartOffset= range.getOffset();

		int lastTypeEndOffset= -1;
		range= types[types.length-1].getSourceRange();
		if (range != null)
			lastTypeEndOffset= range.getOffset() + range.getLength() - 1;

		return pos < firstTypeStartOffset || pos > lastTypeEndOffset || isInside(pos, sourceElement.getSourceRange());
	}

	private boolean isInside(int pos, ISourceRange range) {
		if (range == null)
			return false;
		int offset= range.getOffset();
		return offset <= pos && pos < offset + range.getLength();
	}
}
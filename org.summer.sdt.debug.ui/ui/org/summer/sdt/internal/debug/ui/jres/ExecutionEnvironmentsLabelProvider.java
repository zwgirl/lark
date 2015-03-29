/*******************************************************************************
 * Copyright (c) 2005, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.ui.jres;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.summer.sdt.launching.environments.IExecutionEnvironment;
import org.summer.sdt.ui.ISharedImages;
import org.summer.sdt.ui.JavaUI;

/**
 * Label provider for JREs.
 * @since 3.2
 */
public class ExecutionEnvironmentsLabelProvider extends LabelProvider {
	
	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
        return JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_LIBRARY);
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return ((IExecutionEnvironment)element).getId();
	}
}

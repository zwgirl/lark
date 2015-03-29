/*******************************************************************************
 * Copyright (c) 2010, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.javaeditor;

import java.util.List;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.IMethod;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.internal.corext.util.JavaModelUtil;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.actions.SelectionConverter;
import org.summer.sdt.ui.actions.SelectionDispatchAction;

/**
 * Java element method return type hyperlink detector.
 * 
 * @since 3.7
 */
public class JavaElementHyperlinkReturnTypeDetector extends JavaElementHyperlinkDetector {


	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.ui.javaeditor.JavaElementHyperlinkDetector#createHyperlink(org.eclipse.jface.text.IRegion, org.summer.sdt.ui.actions.SelectionDispatchAction, org.summer.sdt.core.IJavaElement, boolean, org.summer.sdt.internal.ui.javaeditor.JavaEditor)
	 */
	@Override
	protected void addHyperlinks(List<IHyperlink> hyperlinksCollector, IRegion wordRegion, SelectionDispatchAction openAction, IJavaElement element, boolean qualify, JavaEditor editor) {
		try {
			if (element.getElementType() == IJavaElement.METHOD && !JavaModelUtil.isPrimitive(((IMethod)element).getReturnType()) && SelectionConverter.canOperateOn(editor)) {
				hyperlinksCollector.add(new JavaElementReturnTypeHyperlink(wordRegion, openAction, (IMethod)element, qualify));
			}
		} catch (JavaModelException e) {
			JavaPlugin.log(e);
		}
	}
}

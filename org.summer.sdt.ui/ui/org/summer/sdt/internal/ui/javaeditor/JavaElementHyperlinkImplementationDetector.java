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
import org.summer.sdt.internal.ui.actions.SelectionConverter;
import org.summer.sdt.ui.actions.SelectionDispatchAction;


/**
 * Java element implementation hyperlink detector for methods.
 * 
 * @since 3.5
 */
public class JavaElementHyperlinkImplementationDetector extends JavaElementHyperlinkDetector {

	/*
	 * @see org.summer.sdt.internal.ui.javaeditor.JavaElementHyperlinkDetector#createHyperlink(org.eclipse.jface.text.IRegion, org.summer.sdt.ui.actions.SelectionDispatchAction, org.summer.sdt.core.IJavaElement, boolean, org.eclipse.ui.texteditor.ITextEditor)
	 * @since 3.5
	 */
	@Override
	protected void addHyperlinks(List<IHyperlink> hyperlinksCollector, IRegion wordRegion, SelectionDispatchAction openAction, IJavaElement element, boolean qualify, JavaEditor editor) {
		if (element.getElementType() == IJavaElement.METHOD && SelectionConverter.canOperateOn(editor)) {
			hyperlinksCollector.add(new JavaElementImplementationHyperlink(wordRegion, openAction, (IMethod)element, qualify, editor));
		}
	}
}

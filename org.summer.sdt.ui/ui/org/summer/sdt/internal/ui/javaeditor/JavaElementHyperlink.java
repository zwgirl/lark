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
package org.summer.sdt.internal.ui.javaeditor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.ui.JavaElementLabels;
import org.summer.sdt.ui.actions.OpenAction;
import org.summer.sdt.ui.actions.SelectionDispatchAction;


/**
 * Java element hyperlink.
 *
 * @since 3.1
 */
public class JavaElementHyperlink implements IHyperlink {

	private final IRegion fRegion;
	private final SelectionDispatchAction fOpenAction;
	private final IJavaElement fElement;
	private final boolean fQualify;


	/**
	 * Creates a new Java element hyperlink.
	 * 
	 * @param region the region of the link
	 * @param openAction the action to use to open the java elements
	 * @param element the java element to open or <code>null</code> if {@link OpenAction} should be
	 *            invoked at the given region
	 * @param qualify <code>true</code> if the hyperlink text should show a qualified name for
	 *            element
	 */
	public JavaElementHyperlink(IRegion region, SelectionDispatchAction openAction, IJavaElement element, boolean qualify) {
		Assert.isNotNull(openAction);
		Assert.isNotNull(region);

		fRegion= region;
		fOpenAction= openAction;
		fElement= element;
		fQualify= qualify;
	}

	/*
	 * @see org.summer.sdt.internal.ui.javaeditor.IHyperlink#getHyperlinkRegion()
	 * @since 3.1
	 */
	public IRegion getHyperlinkRegion() {
		return fRegion;
	}

	/*
	 * @see org.summer.sdt.internal.ui.javaeditor.IHyperlink#open()
	 * @since 3.1
	 */
	public void open() {
		if (fElement != null)
			fOpenAction.run(new StructuredSelection(fElement));
		else
			fOpenAction.run(new TextSelection(fRegion.getOffset(), fRegion.getLength()));
	}

	/*
	 * @see org.summer.sdt.internal.ui.javaeditor.IHyperlink#getTypeLabel()
	 * @since 3.1
	 */
	public String getTypeLabel() {
		return null;
	}

	/*
	 * @see org.summer.sdt.internal.ui.javaeditor.IHyperlink#getHyperlinkText()
	 * @since 3.1
	 */
	public String getHyperlinkText() {
		if (fQualify && fElement != null) {
			String elementLabel= JavaElementLabels.getElementLabel(fElement, JavaElementLabels.ALL_POST_QUALIFIED);
			return Messages.format(JavaEditorMessages.JavaElementHyperlink_hyperlinkText_qualified, new Object[] { elementLabel });
		} else {
			return JavaEditorMessages.JavaElementHyperlink_hyperlinkText;
		}
	}
}

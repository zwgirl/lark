/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.text;


import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.ui.IEditorPart;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.ITypeParameter;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.internal.ui.actions.SelectionConverter;
import org.summer.sdt.internal.ui.javaeditor.EditorUtility;
import org.summer.sdt.internal.ui.javaeditor.JavaEditor;

/**
 * Provides a Java element to be displayed in by an information presenter.
 */
public class JavaElementProvider implements IInformationProvider, IInformationProviderExtension {

	private JavaEditor fEditor;
	private boolean fUseCodeResolve;

	public JavaElementProvider(IEditorPart editor) {
		fUseCodeResolve= false;
		if (editor instanceof JavaEditor)
			fEditor= (JavaEditor)editor;
	}

	public JavaElementProvider(IEditorPart editor, boolean useCodeResolve) {
		this(editor);
		fUseCodeResolve= useCodeResolve;
	}

	/*
	 * @see IInformationProvider#getSubject(ITextViewer, int)
	 */
	public IRegion getSubject(ITextViewer textViewer, int offset) {
		if (textViewer != null && fEditor != null) {
			IRegion region= JavaWordFinder.findWord(textViewer.getDocument(), offset);
			if (region != null)
				return region;
			else
				return new Region(offset, 0);
		}
		return null;
	}

	/*
	 * @see IInformationProvider#getInformation(ITextViewer, IRegion)
	 */
	public String getInformation(ITextViewer textViewer, IRegion subject) {
		return getInformation2(textViewer, subject).toString();
	}

	/*
	 * @see IInformationProviderExtension#getElement(ITextViewer, IRegion)
	 */
	public Object getInformation2(ITextViewer textViewer, IRegion subject) {
		if (fEditor == null)
			return null;

		try {
			if (fUseCodeResolve) {
				IStructuredSelection sel= SelectionConverter.getStructuredSelection(fEditor);
				if (!sel.isEmpty()) {
					Object element= sel.getFirstElement();
					if (!(element instanceof ITypeParameter))
						return element;
				}
			}
			IJavaElement element= SelectionConverter.getElementAtOffset(fEditor, false);
			if (element != null)
				return element;

			return EditorUtility.getEditorInputJavaElement(fEditor, false);
		} catch (JavaModelException e) {
			return null;
		}
	}
}

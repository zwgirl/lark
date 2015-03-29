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
import org.summer.sdt.core.IField;
import org.summer.sdt.core.IJavaElement;
import org.summer.sdt.core.ILocalVariable;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.core.Signature;
import org.summer.sdt.internal.corext.util.JavaModelUtil;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.actions.SelectionConverter;
import org.summer.sdt.ui.actions.SelectionDispatchAction;

/**
 * Java element variable declaration type hyperlink detector.
 * 
 * @since 3.7
 */
public class JavaElementHyperlinkDeclaredTypeDetector extends JavaElementHyperlinkDetector {


	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.ui.javaeditor.JavaElementHyperlinkDetector#createHyperlink(org.eclipse.jface.text.IRegion, org.summer.sdt.ui.actions.SelectionDispatchAction, org.summer.sdt.core.IJavaElement, boolean, org.summer.sdt.internal.ui.javaeditor.JavaEditor)
	 */
	@Override
	protected void addHyperlinks(List<IHyperlink> hyperlinksCollector, IRegion wordRegion, SelectionDispatchAction openAction, IJavaElement element, boolean qualify, JavaEditor editor) {
		try {
			if (element.getElementType() == IJavaElement.FIELD || element.getElementType() == IJavaElement.LOCAL_VARIABLE) {
				String typeSignature= getTypeSignature(element);
				if (!JavaModelUtil.isPrimitive(typeSignature) && SelectionConverter.canOperateOn(editor)) {
					if (Signature.getTypeSignatureKind(typeSignature) == Signature.INTERSECTION_TYPE_SIGNATURE) {
						String[] bounds= Signature.getIntersectionTypeBounds(typeSignature);
						qualify|= bounds.length >= 2;
						for (int i= 0; i < bounds.length; i++) {
							hyperlinksCollector.add(new JavaElementDeclaredTypeHyperlink(wordRegion, openAction, element, bounds[i], qualify));
						}
					} else {
						hyperlinksCollector.add(new JavaElementDeclaredTypeHyperlink(wordRegion, openAction, element, qualify));
					}
				}
			}
		} catch (JavaModelException e) {
			JavaPlugin.log(e);
		}
	}

	/**
	 * Returns the type signature of the element.
	 * 
	 * @param element an instance of <code>ILocalVariable</code> or <code>IField</code>
	 * @return the type signature of the element
	 * @throws JavaModelException if this element does not exist or if an exception occurs while
	 *             accessing its corresponding resource.
	 */
	static String getTypeSignature(IJavaElement element) throws JavaModelException {
		if (element instanceof ILocalVariable) {
			return ((ILocalVariable)element).getTypeSignature();
		} else if (element instanceof IField) {
			return ((IField)element).getTypeSignature();
		}
		throw new IllegalArgumentException();
	}
}

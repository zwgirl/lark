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
package org.summer.sdt.internal.ui.wizards.buildpaths;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.summer.sdt.core.IClasspathAttribute;
import org.summer.sdt.core.JavaCore;
import org.summer.sdt.internal.ui.JavaPluginImages;
import org.summer.sdt.internal.ui.wizards.NewWizardMessages;
import org.summer.sdt.launching.JavaRuntime;
import org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration;


public class NativeLibAttributeConfiguration extends ClasspathAttributeConfiguration {

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration#getImageDescriptor(org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration.ClasspathAttributeAccess)
	 */
	@Override
	public ImageDescriptor getImageDescriptor(ClasspathAttributeAccess attribute) {
		return JavaPluginImages.DESC_OBJS_NATIVE_LIB_PATH_ATTRIB;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration#getNameLabel(org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration.ClasspathAttributeAccess)
	 */
	@Override
	public String getNameLabel(ClasspathAttributeAccess attribute) {
		return NewWizardMessages.CPListLabelProvider_native_library_path;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration#getValueLabel(org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration.ClasspathAttributeAccess)
	 */
	@Override
	public String getValueLabel(ClasspathAttributeAccess attribute) {
		String arg= attribute.getClasspathAttribute().getValue();
		if (arg == null) {
			arg= NewWizardMessages.CPListLabelProvider_none;
		}
		return arg;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration#canEdit(org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration.ClasspathAttributeAccess)
	 */
	@Override
	public boolean canEdit(ClasspathAttributeAccess attribute) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration#canRemove(org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration.ClasspathAttributeAccess)
	 */
	@Override
	public boolean canRemove(ClasspathAttributeAccess attribute) {
		return attribute.getClasspathAttribute().getValue() != null;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration#performEdit(org.eclipse.swt.widgets.Shell, org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration.ClasspathAttributeAccess)
	 */
	@Override
	public IClasspathAttribute performEdit(Shell shell, ClasspathAttributeAccess attribute) {
		NativeLibrariesDialog dialog= new NativeLibrariesDialog(shell, attribute.getClasspathAttribute().getValue(), attribute.getParentClasspassEntry());
		if (dialog.open() == Window.OK) {
			return JavaCore.newClasspathAttribute(JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY, dialog.getNativeLibraryPath());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration#performRemove(org.summer.sdt.ui.wizards.ClasspathAttributeConfiguration.ClasspathAttributeAccess)
	 */
	@Override
	public IClasspathAttribute performRemove(ClasspathAttributeAccess attribute) {
		return JavaCore.newClasspathAttribute(JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY, null);
	}

}

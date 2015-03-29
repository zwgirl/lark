/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.launching;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.AbstractSourceContainerTypeDelegate;
import org.summer.sdt.core.IJavaProject;
import org.summer.sdt.core.JavaCore;
import org.summer.sdt.launching.sourcelookup.containers.JavaProjectSourceContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Java project source container type.
 */
public class JavaProjectSourceContainerTypeDelegate extends AbstractSourceContainerTypeDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.internal.core.sourcelookup.ISourceContainerTypeDelegate#createSourceContainer(java.lang.String)
	 */
	public ISourceContainer createSourceContainer(String memento) throws CoreException {
		Node node = parseDocument(memento);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element)node;
			if ("javaProject".equals(element.getNodeName())) { //$NON-NLS-1$
				String string = element.getAttribute("name"); //$NON-NLS-1$
				if (string == null || string.length() == 0) {
					abort(LaunchingMessages.JavaProjectSourceContainerTypeDelegate_5, null); 
				}
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IProject project = workspace.getRoot().getProject(string);
				IJavaProject javaProject = JavaCore.create(project);
				return new JavaProjectSourceContainer(javaProject);
			}
			abort(LaunchingMessages.JavaProjectSourceContainerTypeDelegate_6, null); 
		}
		abort(LaunchingMessages.JavaProjectSourceContainerTypeDelegate_7, null); 
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.internal.core.sourcelookup.ISourceContainerTypeDelegate#getMemento(org.eclipse.debug.internal.core.sourcelookup.ISourceContainer)
	 */
	public String getMemento(ISourceContainer container) throws CoreException {
		JavaProjectSourceContainer project = (JavaProjectSourceContainer) container;
		Document document = newDocument();
		Element element = document.createElement("javaProject"); //$NON-NLS-1$
		element.setAttribute("name", project.getName()); //$NON-NLS-1$
		document.appendChild(element);
		return serializeDocument(document);
	}
}

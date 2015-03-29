/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.ui.snippeteditor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.summer.sdt.internal.debug.ui.JDIDebugUIPlugin;
import org.summer.sdt.ui.text.IJavaPartitions;
import org.summer.sdt.ui.text.JavaTextTools;

/**
 * The document setup participant for the Java Snippet Editor
 */
public class SnippetDocumentSetupParticipant  implements IDocumentSetupParticipant {

	public SnippetDocumentSetupParticipant() {
	}
	
	/*
	 * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
	 */
	public void setup(IDocument document) {
		if (document != null) {
			JavaTextTools tools= JDIDebugUIPlugin.getDefault().getJavaTextTools();
			tools.setupJavaDocumentPartitioner(document, IJavaPartitions.JAVA_PARTITIONING);
		}
	}
}

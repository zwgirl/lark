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
package org.summer.sdt.internal.debug.ui.contentassist;

import java.util.Arrays;

import org.summer.sdt.core.CompletionProposal;
import org.summer.sdt.core.IJavaProject;
import org.summer.sdt.internal.debug.eval.ast.engine.ArrayRuntimeContext;
import org.summer.sdt.ui.text.java.CompletionProposalCollector;

/**
 * Proposal collector that filters the special local variable used for content
 * assist on arrays.
 * 
 * @since 3.2
 */
public class JavaDebugCompletionProposalCollector extends CompletionProposalCollector {
	
	private static final char[] fgHiddenLocal = ArrayRuntimeContext.ARRAY_THIS_VARIABLE.toCharArray();

	/**
	 * Constructs a proposal collector on the given project.
	 * 
	 * @param project
	 */
	public JavaDebugCompletionProposalCollector(IJavaProject project) {
		super(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.ui.text.java.CompletionProposalCollector#isFiltered(org.summer.sdt.core.CompletionProposal)
	 */
	@Override
	protected boolean isFiltered(CompletionProposal proposal) {
		if (proposal.getKind() == CompletionProposal.LOCAL_VARIABLE_REF) {
			if (Arrays.equals(proposal.getName(), fgHiddenLocal)) {
				return true;
			}
		}
		return super.isFiltered(proposal);
	}


}

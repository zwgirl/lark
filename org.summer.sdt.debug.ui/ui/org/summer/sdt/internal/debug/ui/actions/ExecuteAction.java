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
package org.summer.sdt.internal.debug.ui.actions;


import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.summer.sdt.debug.eval.IEvaluationResult;
import org.summer.sdt.internal.debug.ui.JDIDebugUIPlugin;
import org.summer.sdt.internal.debug.ui.display.IDataDisplay;
import org.summer.sdt.internal.debug.ui.snippeteditor.JavaSnippetEditor;

public class ExecuteAction extends EvaluateAction {

	/**
	 * @see org.summer.sdt.internal.debug.ui.actions.EvaluateAction#displayResult(org.summer.sdt.debug.eval.IEvaluationResult)
	 */
	@Override
	protected void displayResult(final IEvaluationResult result) {
		if (result.hasErrors()) {
			final Display display = JDIDebugUIPlugin.getStandardDisplay();
			display.asyncExec(new Runnable() {
				public void run() {
					if (display.isDisposed()) {
						return;
					}
					reportErrors(result);
					evaluationCleanup();
				}
			});
		} else {			
			evaluationCleanup();
		}
	}

	/**
	 * @see org.summer.sdt.internal.debug.ui.actions.EvaluateAction#run()
	 */
	@Override
	protected void run() {
		IWorkbenchPart part= getTargetPart();
		if (part instanceof JavaSnippetEditor) {
			((JavaSnippetEditor)part).evalSelection(JavaSnippetEditor.RESULT_RUN);
			return;
		}
		super.run();	
	}

	/**
	 * @see org.summer.sdt.internal.debug.ui.actions.EvaluateAction#getDataDisplay()
	 */
	@Override
	protected IDataDisplay getDataDisplay() {
		return super.getDirectDataDisplay();
	}

}

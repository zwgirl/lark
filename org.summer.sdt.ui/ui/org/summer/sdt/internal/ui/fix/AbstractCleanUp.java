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
package org.summer.sdt.internal.ui.fix;

import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.IJavaProject;
import org.summer.sdt.ui.cleanup.CleanUpContext;
import org.summer.sdt.ui.cleanup.CleanUpOptions;
import org.summer.sdt.ui.cleanup.CleanUpRequirements;
import org.summer.sdt.ui.cleanup.ICleanUp;
import org.summer.sdt.ui.cleanup.ICleanUpFix;

public abstract class AbstractCleanUp implements ICleanUp {

	private CleanUpOptions fOptions;

	protected AbstractCleanUp() {
	}

	protected AbstractCleanUp(Map<String, String> settings) {
		setOptions(new MapCleanUpOptions(settings));
	}

	/*
	 * @see org.summer.sdt.ui.cleanup.ICleanUp#setOptions(org.summer.sdt.ui.cleanup.CleanUpOptions)
	 * @since 3.5
	 */
	public void setOptions(CleanUpOptions options) {
		Assert.isLegal(options != null);
		fOptions= options;
	}


	/*
	 * @see org.summer.sdt.ui.cleanup.ICleanUp#getStepDescriptions()
	 * @since 3.5
	 */
	public String[] getStepDescriptions() {
		return new String[0];
	}

	/**
	 * @return code snippet complying to current options
	 */
	public String getPreview() {
		return ""; //$NON-NLS-1$
	}

	/*
	 * @see org.summer.sdt.ui.cleanup.ICleanUp#getRequirements()
	 * @since 3.5
	 */
	public CleanUpRequirements getRequirements() {
		return new CleanUpRequirements(false, false, false, null);
	}

	/*
	 * @see org.summer.sdt.ui.cleanup.ICleanUp#checkPreConditions(org.summer.sdt.core.IJavaProject, org.summer.sdt.core.ICompilationUnit[], org.eclipse.core.runtime.IProgressMonitor)
	 * @since 3.5
	 */
	public RefactoringStatus checkPreConditions(IJavaProject project, ICompilationUnit[] compilationUnits, IProgressMonitor monitor) throws CoreException {
		return new RefactoringStatus();
	}

	/*
	 * @see org.summer.sdt.ui.cleanup.ICleanUp#createFix(org.summer.sdt.ui.cleanup.CleanUpContext)
	 * @since 3.5
	 */
	public ICleanUpFix createFix(CleanUpContext context) throws CoreException {
		return null;
	}

	/*
	 * @see org.summer.sdt.ui.cleanup.ICleanUp#checkPostConditions(org.eclipse.core.runtime.IProgressMonitor)
	 * @since 3.5
	 */
	public RefactoringStatus checkPostConditions(IProgressMonitor monitor) throws CoreException {
		return new RefactoringStatus();
	}

	/**
	 * @param key the name of the option
	 * @return <code>true</code> if option with <code>key</code> is enabled
	 */
	protected boolean isEnabled(String key) {
		Assert.isNotNull(fOptions);
		Assert.isLegal(key != null);
		return fOptions.isEnabled(key);
	}

}

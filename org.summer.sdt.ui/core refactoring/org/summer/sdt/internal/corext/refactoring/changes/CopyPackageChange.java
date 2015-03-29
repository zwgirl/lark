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
package org.summer.sdt.internal.corext.refactoring.changes;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.summer.sdt.core.IPackageFragment;
import org.summer.sdt.core.IPackageFragmentRoot;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.internal.corext.refactoring.RefactoringCoreMessages;
import org.summer.sdt.internal.corext.refactoring.reorg.INewNameQuery;
import org.summer.sdt.internal.corext.util.Messages;
import org.summer.sdt.ui.JavaElementLabels;

public class CopyPackageChange extends PackageReorgChange {

	public CopyPackageChange(IPackageFragment pack, IPackageFragmentRoot dest, INewNameQuery nameQuery){
		super(pack, dest, nameQuery);
	}

	@Override
	protected Change doPerformReorg(IProgressMonitor pm) throws JavaModelException, OperationCanceledException {
		getPackage().copy(getDestination(), null, getNewName(), true, pm);
		return null;
	}

	@Override
	public String getName() {
		String packageName= JavaElementLabels.getElementLabel(getPackage(), JavaElementLabels.ALL_DEFAULT);
		String destinationName= JavaElementLabels.getElementLabel(getDestination(), JavaElementLabels.ALL_DEFAULT);
		return Messages.format(RefactoringCoreMessages.CopyPackageChange_copy, new String[]{ packageName, destinationName});
	}
}

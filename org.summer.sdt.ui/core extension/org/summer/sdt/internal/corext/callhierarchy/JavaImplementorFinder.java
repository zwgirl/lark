/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jesper Kamstrup Linnet (eclipse@kamstrup-linnet.dk) - initial API and implementation
 * 			(report 36180: Callers/Callees view)
 *******************************************************************************/
package org.summer.sdt.internal.corext.callhierarchy;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.summer.sdt.core.IType;
import org.summer.sdt.core.ITypeHierarchy;
import org.summer.sdt.core.JavaModelException;
import org.summer.sdt.internal.ui.JavaPlugin;

public class JavaImplementorFinder implements IImplementorFinder {
    /* (non-Javadoc)
	 * @see org.summer.sdt.internal.corext.callhierarchy.IImplementorFinder#findImplementingTypes(org.summer.sdt.core.IType, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Collection<IType> findImplementingTypes(IType type, IProgressMonitor progressMonitor) {
        ITypeHierarchy typeHierarchy;

        try {
            typeHierarchy = type.newTypeHierarchy(progressMonitor);

            IType[] implementingTypes = typeHierarchy.getAllClasses();
            HashSet<IType> result = new HashSet<IType>(Arrays.asList(implementingTypes));

            return result;
        } catch (JavaModelException e) {
            JavaPlugin.log(e);
        }

        return null;
    }

	/* (non-Javadoc)
	 * @see org.summer.sdt.internal.corext.callhierarchy.IImplementorFinder#findInterfaces(org.summer.sdt.core.IType, org.eclipse.core.runtime.IProgressMonitor)
     */
    public Collection<IType> findInterfaces(IType type, IProgressMonitor progressMonitor) {
        ITypeHierarchy typeHierarchy;

        try {
            typeHierarchy = type.newSupertypeHierarchy(progressMonitor);

            IType[] interfaces = typeHierarchy.getAllSuperInterfaces(type);
            HashSet<IType> result = new HashSet<IType>(Arrays.asList(interfaces));

            return result;
        } catch (JavaModelException e) {
            JavaPlugin.log(e);
        }

        return null;
    }
}

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
package org.summer.sdt.internal.debug.eval.ast.engine;

import org.eclipse.core.runtime.CoreException;
import org.summer.sdt.core.IJavaProject;
import org.summer.sdt.debug.core.IJavaDebugTarget;
import org.summer.sdt.debug.core.IJavaObject;
import org.summer.sdt.debug.core.IJavaReferenceType;
import org.summer.sdt.debug.core.IJavaThread;
import org.summer.sdt.debug.core.IJavaVariable;

public class JavaObjectRuntimeContext extends AbstractRuntimeContext {

	/**
	 * <code>this</code> object or this context.
	 */
	private IJavaObject fThisObject;

	/**
	 * The thread for this context.
	 */
	private IJavaThread fThread;

	/**
	 * ObjectValueRuntimeContext constructor.
	 * 
	 * @param thisObject
	 *            <code>this</code> object of this context.
	 * @param javaProject
	 *            the project for this context.
	 * @param thread
	 *            the thread for this context.
	 */
	public JavaObjectRuntimeContext(IJavaObject thisObject,
			IJavaProject javaProject, IJavaThread thread) {
		super(javaProject);
		fThisObject = thisObject;
		fThread = thread;
	}

	/**
	 * @see IRuntimeContext#getVM()
	 */
	public IJavaDebugTarget getVM() {
		return (IJavaDebugTarget) fThisObject.getDebugTarget();
	}

	/**
	 * @see IRuntimeContext#getThis()
	 */
	public IJavaObject getThis() {
		return fThisObject;
	}

	/**
	 * @see IRuntimeContext#getReceivingType()
	 */
	public IJavaReferenceType getReceivingType() throws CoreException {
		return (IJavaReferenceType) getThis().getJavaType();
	}

	/**
	 * @see IRuntimeContext#getLocals()
	 */
	public IJavaVariable[] getLocals() {
		return new IJavaVariable[0];
	}

	/**
	 * @see IRuntimeContext#getThread()
	 */
	public IJavaThread getThread() {
		return fThread;
	}

	/**
	 * @see IRuntimeContext#isConstructor()
	 */
	public boolean isConstructor() {
		return false;
	}

}

/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.debug.ui.breakpoints;

import org.eclipse.debug.core.DebugException;
import org.summer.sdt.core.dom.Message;
import org.summer.sdt.debug.core.IJavaBreakpoint;
import org.summer.sdt.debug.core.IJavaBreakpointListener;
import org.summer.sdt.debug.core.IJavaDebugTarget;
import org.summer.sdt.debug.core.IJavaLineBreakpoint;
import org.summer.sdt.debug.core.IJavaStackFrame;
import org.summer.sdt.debug.core.IJavaThread;
import org.summer.sdt.debug.core.IJavaType;
import org.summer.sdt.internal.debug.ui.JDIDebugUIPlugin;
import org.summer.sdt.internal.debug.ui.JavaDebugOptionsManager;

/**
 * Breakpoint listener extension for the "suspend on uncaught exceptions" exception breakpoint.
 * Changed to a breakpoint specific listener in 3.5 when breakpoint specific listeners were
 * introduced.
 * 
 * @since 3.5
 */
public class SuspendOnUncaughtExceptionListener implements IJavaBreakpointListener {
	
	public static final String ID_UNCAUGHT_EXCEPTION_LISTENER = JDIDebugUIPlugin.getUniqueIdentifier() + ".uncaughtExceptionListener"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.debug.core.IJavaBreakpointListener#addingBreakpoint(org.summer.sdt.debug.core.IJavaDebugTarget,
	 * org.summer.sdt.debug.core.IJavaBreakpoint)
	 */
	public void addingBreakpoint(IJavaDebugTarget target, IJavaBreakpoint breakpoint) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.debug.core.IJavaBreakpointListener#breakpointHasCompilationErrors(org.summer.sdt.debug.core.IJavaLineBreakpoint,
	 * org.summer.sdt.core.dom.Message[])
	 */
	public void breakpointHasCompilationErrors(IJavaLineBreakpoint breakpoint, Message[] errors) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.debug.core.IJavaBreakpointListener#breakpointHasRuntimeException(org.summer.sdt.debug.core.IJavaLineBreakpoint,
	 * org.eclipse.debug.core.DebugException)
	 */
	public void breakpointHasRuntimeException(IJavaLineBreakpoint breakpoint, DebugException exception) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.debug.core.IJavaBreakpointListener#breakpointHit(org.summer.sdt.debug.core.IJavaThread,
	 * org.summer.sdt.debug.core.IJavaBreakpoint)
	 */
	public int breakpointHit(IJavaThread thread, IJavaBreakpoint breakpoint) {
		// the "uncaught" exceptions breakpoint subsumes the "compilation error" breakpoint
		// since "Throwable" is a supertype of "Error". Thus, if there is actually a compilation
		// error here, but the option to suspend on compilation errors is off, we should
		// resume (i.e. do not suspend)
		if (!JavaDebugOptionsManager.getDefault().isSuspendOnCompilationErrors()) {
		    try {
		    	IJavaStackFrame frame = (IJavaStackFrame)thread.getTopStackFrame();
				if (frame != null) {
			        if (JavaDebugOptionsManager.getDefault().getProblem(frame) != null) {
			            return DONT_SUSPEND;
			        }
				}
		    } catch (DebugException e) {
		        JDIDebugUIPlugin.log(e);
		        // unable to determine if there was a compilation problem, so fall thru and suspend
		    }
		}
		return SUSPEND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.debug.core.IJavaBreakpointListener#breakpointInstalled(org.summer.sdt.debug.core.IJavaDebugTarget,
	 * org.summer.sdt.debug.core.IJavaBreakpoint)
	 */
	public void breakpointInstalled(IJavaDebugTarget target, IJavaBreakpoint breakpoint) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.debug.core.IJavaBreakpointListener#breakpointRemoved(org.summer.sdt.debug.core.IJavaDebugTarget,
	 * org.summer.sdt.debug.core.IJavaBreakpoint)
	 */
	public void breakpointRemoved(IJavaDebugTarget target, IJavaBreakpoint breakpoint) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.summer.sdt.debug.core.IJavaBreakpointListener#installingBreakpoint(org.summer.sdt.debug.core.IJavaDebugTarget,
	 * org.summer.sdt.debug.core.IJavaBreakpoint, org.summer.sdt.debug.core.IJavaType)
	 */
	public int installingBreakpoint(IJavaDebugTarget target, IJavaBreakpoint breakpoint, IJavaType type) {
		return DONT_CARE;
	}

}

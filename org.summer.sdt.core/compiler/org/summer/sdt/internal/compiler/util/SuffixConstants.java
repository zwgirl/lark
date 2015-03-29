/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.compiler.util;

//public interface SuffixConstants {
//	public final static String EXTENSION_class = "class"; //$NON-NLS-1$
//	public final static String EXTENSION_CLASS = "CLASS"; //$NON-NLS-1$
//	public final static String EXTENSION_java = "java"; //$NON-NLS-1$
//	public final static String EXTENSION_JAVA = "JAVA"; //$NON-NLS-1$
//	
//	//cym 2014-11-21
//	public final static String EXTENSION_js = "js"; //$NON-NLS-1$
//	public final static String EXTENSION_JS = "JS"; //$NON-NLS-1$
//	
//	//cym 2014-11-21
//	public final static String EXTENSION_html = "html"; //$NON-NLS-1$
//	public final static String EXTENSION_HTML = "HTML"; //$NON-NLS-1$
//
//	public final static String SUFFIX_STRING_class = "." + EXTENSION_class; //$NON-NLS-1$
//	public final static String SUFFIX_STRING_CLASS = "." + EXTENSION_CLASS; //$NON-NLS-1$
//	public final static String SUFFIX_STRING_java = "." + EXTENSION_java; //$NON-NLS-1$
//	public final static String SUFFIX_STRING_JAVA = "." + EXTENSION_JAVA; //$NON-NLS-1$
//
//	public final static char[] SUFFIX_class = SUFFIX_STRING_class.toCharArray();
//	public final static char[] SUFFIX_CLASS = SUFFIX_STRING_CLASS.toCharArray();
//	public final static char[] SUFFIX_java = SUFFIX_STRING_java.toCharArray();
//	public final static char[] SUFFIX_JAVA = SUFFIX_STRING_JAVA.toCharArray();
//}

public interface SuffixConstants {
	public final static String EXTENSION_class = "class"; //$NON-NLS-1$
	public final static String EXTENSION_CLASS = "CLASS"; //$NON-NLS-1$
	public final static String EXTENSION_lark = "lark"; //$NON-NLS-1$
	public final static String EXTENSION_LARK = "LARK"; //$NON-NLS-1$
	
	public final static String EXTENSION_java = "java"; //$NON-NLS-1$
	public final static String EXTENSION_JAVA = "JAVA"; //$NON-NLS-1$
	
	//cym 2014-11-21
	public final static String EXTENSION_js = "js"; //$NON-NLS-1$
	public final static String EXTENSION_JS = "JS"; //$NON-NLS-1$
	
	//cym 2014-11-21
	public final static String EXTENSION_html = "html"; //$NON-NLS-1$
	public final static String EXTENSION_HTML = "HTML"; //$NON-NLS-1$

	public final static String SUFFIX_STRING_class = "." + EXTENSION_class; //$NON-NLS-1$
	public final static String SUFFIX_STRING_CLASS = "." + EXTENSION_CLASS; //$NON-NLS-1$
	public final static String SUFFIX_STRING_java = "." + EXTENSION_lark; //$NON-NLS-1$
	public final static String SUFFIX_STRING_JAVA = "." + EXTENSION_LARK; //$NON-NLS-1$

	public final static char[] SUFFIX_class = SUFFIX_STRING_class.toCharArray();
	public final static char[] SUFFIX_CLASS = SUFFIX_STRING_CLASS.toCharArray();
	public final static char[] SUFFIX_java = SUFFIX_STRING_java.toCharArray();
	public final static char[] SUFFIX_JAVA = SUFFIX_STRING_JAVA.toCharArray();
}

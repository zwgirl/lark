/*******************************************************************************
 * Copyright (c) 2013, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.codeassist.select;

import org.summer.sdt.core.compiler.CharOperation;
import org.summer.sdt.internal.compiler.ast.ReferenceExpression;
import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.MethodBinding;
import org.summer.sdt.internal.compiler.lookup.PolyTypeBinding;
import org.summer.sdt.internal.compiler.lookup.ProblemReferenceBinding;
import org.summer.sdt.internal.compiler.lookup.TypeBinding;

public class SelectionOnReferenceExpressionName extends ReferenceExpression {

	public SelectionOnReferenceExpressionName() {
		super();
	}

	public StringBuffer printExpression(int indent, StringBuffer output) {
		output.append("<SelectionOnReferenceExpressionName:"); //$NON-NLS-1$
		super.printExpression(indent, output);
		return output.append('>');
	}
	
	// See SelectionScanner#scanIdentifierOrKeyword
	public boolean isConstructorReference() {
		return CharOperation.equals(this.selector, "new".toCharArray()); //$NON-NLS-1$
	}
	
	// See SelectionScanner#scanIdentifierOrKeyword
	public boolean isMethodReference() {
		return !CharOperation.equals(this.selector, "new".toCharArray()); //$NON-NLS-1$
	}

	public TypeBinding resolveType(BlockScope scope) {
		TypeBinding type = super.resolveType(scope);
		if (type == null || type instanceof ProblemReferenceBinding || type instanceof PolyTypeBinding)
			return type;
		MethodBinding method = getMethodBinding();
		if (method != null && method.isValidBinding() && !method.isSynthetic())
			throw new SelectionNodeFound(this.actualMethodBinding);
		throw new SelectionNodeFound();
	}
}

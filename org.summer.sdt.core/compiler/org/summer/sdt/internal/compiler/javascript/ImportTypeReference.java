package org.summer.sdt.internal.compiler.javascript;

import org.summer.sdt.internal.compiler.lookup.TypeBinding;

public interface ImportTypeReference {
	TypeBinding typeBinding();

	boolean isQualified();
}

package org.summer.sdt.internal.compiler.lookup;

import org.summer.sdt.internal.compiler.ast.XAMLNode;
import org.summer.sdt.internal.compiler.problem.ProblemReporter;

public class ElementScope extends BlockScope {
	public final XAMLNode context;
	public ElementScope(XAMLNode context, Scope parent) {
		super(ELEMENT_SCOPE, parent);
		this.context = context;
		this.context.scope = this;
	}

	@Override
	public ProblemReporter problemReporter() {
		return parent.problemReporter();
	}

	@Override
	public boolean hasDefaultNullnessFor(int location) {
		// TODO Auto-generated method stub
		return false;
	}

}

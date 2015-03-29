package org.summer.sdt.internal.compiler.lookup;

import org.summer.sdt.core.compiler.CharOperation;
import org.summer.sdt.internal.compiler.ast.LocalDeclaration;
import org.summer.sdt.internal.compiler.ast.QualifiedTypeReference;
import org.summer.sdt.internal.compiler.classfmt.ClassFileConstants;
import org.summer.sdt.internal.compiler.impl.Constant;
import org.summer.sdt.internal.compiler.problem.ProblemReporter;
/**
 * 
 * @author cym
 * @since 2014-12-12
 *
 */
public class WindowScope extends Scope{
	public LocalVariableBinding[] locals;
	public int localIndex; // position for next variable
	private ReferenceBinding window;
	
	protected WindowScope(Scope parent) {
		super(WINDOW_SCOPE, parent);
	}
	
	public static final char[] LOCAL_WINDOW_VARIABLE = "window".toCharArray();

	@Override
	public ProblemReporter problemReporter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasDefaultNullnessFor(int location) {
		// TODO Auto-generated method stub
		return false;
	}

	public ReferenceBinding window() {
		if(window == null){
			QualifiedTypeReference winType = new QualifiedTypeReference(TypeConstants.JAVA_LANG_WINDOW, new long[]{0});
			window = (ReferenceBinding) winType.resolveType((GlobalScope)this.parent);
		} 
		
		return window;
	}

	/* Insert a local variable into a given scope, updating its position
	 * and checking there are not too many locals or arguments allocated.
	 */
	public final void addLocalVariable(LocalVariableBinding binding) {
//		checkAndSetModifiersForVariable(binding);
		// insert local in scope
		if (this.localIndex == this.locals.length)
			System.arraycopy(
				this.locals,
				0,
				(this.locals = new LocalVariableBinding[this.localIndex * 2]),
				0,
				this.localIndex);
		this.locals[this.localIndex++] = binding;
	
		// update local variable binding
		binding.windowScope = this;
//		binding.id = outerMostMethodScope().analysisIndex++;
		binding.id = 0;
		// share the outermost method scope analysisIndex
	}
	
	public LocalVariableBinding findVariable(char[] variableName) {
		if(locals == null){
			locals = new LocalVariableBinding[1];
			// allocate #index secret variable (of type int)
			LocalDeclaration windowVarDecl = new LocalDeclaration(LOCAL_WINDOW_VARIABLE, 0, 0);
			windowVarDecl.type = new QualifiedTypeReference(TypeConstants.JAVA_LANG_WINDOW, new long[]{0});
			windowVarDecl.modifiers |= ClassFileConstants.AccFinal;
			LocalVariableBinding windowVariable = new LocalVariableBinding(windowVarDecl, window(), ClassFileConstants.AccFinal, true);
			addLocalVariable(windowVariable);
			windowVariable.setConstant(Constant.NotAConstant); // not inlinable
		}
		int varLength = variableName.length;
		for (int i = this.localIndex-1; i >= 0; i--) { // lookup backward to reach latest additions first
			LocalVariableBinding local;
			char[] localName;
			if ((localName = (local = this.locals[i]).name).length == varLength && CharOperation.equals(localName, variableName))
				return local;
		}
		return null;
	}
}

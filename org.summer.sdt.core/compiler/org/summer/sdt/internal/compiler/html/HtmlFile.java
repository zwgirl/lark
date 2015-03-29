package org.summer.sdt.internal.compiler.html;

import javax.naming.CompoundName;

import org.summer.sdt.core.compiler.CategorizedProblem;
import org.summer.sdt.core.compiler.CharOperation;
import org.summer.sdt.internal.compiler.ClassFile;
import org.summer.sdt.internal.compiler.CompilationResult;
import org.summer.sdt.internal.compiler.ast.AbstractMethodDeclaration;
import org.summer.sdt.internal.compiler.ast.TypeDeclaration;
import org.summer.sdt.internal.compiler.classfmt.ClassFileConstants;
import org.summer.sdt.internal.compiler.lookup.Binding;
import org.summer.sdt.internal.compiler.lookup.FieldBinding;
import org.summer.sdt.internal.compiler.lookup.MethodBinding;
import org.summer.sdt.internal.compiler.lookup.ReferenceBinding;
import org.summer.sdt.internal.compiler.lookup.SourceTypeBinding;
import org.summer.sdt.internal.compiler.lookup.TagBits;
import org.summer.sdt.internal.compiler.lookup.TypeVariableBinding;
import org.summer.sdt.internal.compiler.util.Util;

/**
 * 
 * @author cym
 *
 */
public class HtmlFile {
	public final StringBuffer content;
	public SourceTypeBinding referenceBinding;
	private char[] fullFileName;
	
	public HtmlFile(SourceTypeBinding referenceBinding){
		content = new StringBuffer();
		this.referenceBinding = referenceBinding;
	}

	public static HtmlFile getNewInstance(SourceTypeBinding typeBinding) {
		return new HtmlFile(typeBinding);
	}
	
	/**
	 * INTERNAL USE-ONLY
	 * Request the creation of a ClassFile compatible representation of a problematic type
	 *
	 * @param typeDeclaration org.summer.sdt.internal.compiler.ast.TypeDeclaration
	 * @param unitResult org.summer.sdt.internal.compiler.CompilationUnitResult
	 */
	public static void createProblemType(TypeDeclaration typeDeclaration, CompilationResult unitResult) {
		SourceTypeBinding typeBinding = typeDeclaration.binding;
		HtmlFile classFile = HtmlFile.getNewInstance(typeBinding);
//		classFile.initialize(typeBinding, null, true);
//
//		if (typeBinding.hasMemberTypes()) {
//			// see bug 180109
//			ReferenceBinding[] members = typeBinding.memberTypes;
//			for (int i = 0, l = members.length; i < l; i++)
//				classFile.recordInnerClasses(members[i]);
//		}
//		// TODO (olivier) handle cases where a field cannot be generated (name too long)
//		// TODO (olivier) handle too many methods
//		// inner attributes
//		if (typeBinding.isNestedType()) {
//			classFile.recordInnerClasses(typeBinding);
//		}
//		TypeVariableBinding[] typeVariables = typeBinding.typeVariables();
//		for (int i = 0, max = typeVariables.length; i < max; i++) {
//			TypeVariableBinding typeVariableBinding = typeVariables[i];
//			if ((typeVariableBinding.tagBits & TagBits.ContainsNestedTypeReferences) != 0) {
//				Util.recordNestedType(classFile, typeVariableBinding);
//			}
//		}
//		// add its fields
//		FieldBinding[] fields = typeBinding.fields();
//		if ((fields != null) && (fields != Binding.NO_FIELDS)) {
//			classFile.addFieldInfos();
//		} else {
//			// we have to set the number of fields to be equals to 0
//			classFile.contents[classFile.contentsOffset++] = 0;
//			classFile.contents[classFile.contentsOffset++] = 0;
//		}
//		// leave some space for the methodCount
//		classFile.setForMethodInfos();
//		// add its user defined methods
//		int problemsLength;
//		CategorizedProblem[] problems = unitResult.getErrors();
//		if (problems == null) {
//			problems = new CategorizedProblem[0];
//		}
//		CategorizedProblem[] problemsCopy = new CategorizedProblem[problemsLength = problems.length];
//		System.arraycopy(problems, 0, problemsCopy, 0, problemsLength);
//
//		AbstractMethodDeclaration[] methodDecls = typeDeclaration.methods;
//		boolean abstractMethodsOnly = false;
//		if (methodDecls != null) {
//			if (typeBinding.isInterface()) {
//				if (typeBinding.scope.compilerOptions().sourceLevel < ClassFileConstants.JDK1_8)
//					abstractMethodsOnly = true;
//				// We generate a clinit which contains all the problems, since we may not be able to generate problem methods (< 1.8) and problem constructors (all levels).
//				classFile.addProblemClinit(problemsCopy);
//			}
//			for (int i = 0, length = methodDecls.length; i < length; i++) {
//				AbstractMethodDeclaration methodDecl = methodDecls[i];
//				MethodBinding method = methodDecl.binding;
//				if (method == null) continue;
//				if (abstractMethodsOnly) {
//					method.modifiers = ClassFileConstants.AccPublic | ClassFileConstants.AccAbstract;
//				}
//				if (method.isConstructor()) {
//					if (typeBinding.isInterface()) continue;
//					classFile.addProblemConstructor(methodDecl, method, problemsCopy);
//				} else if (method.isAbstract()) {
//					classFile.addAbstractMethod(methodDecl, method);
//				} else {
//					classFile.addProblemMethod(methodDecl, method, problemsCopy);
//				}
//			}
//			// add abstract methods
//			classFile.addDefaultAbstractMethods();
//		}
//
//		// propagate generation of (problem) member types
//		if (typeDeclaration.memberTypes != null) {
//			for (int i = 0, max = typeDeclaration.memberTypes.length; i < max; i++) {
//				TypeDeclaration memberType = typeDeclaration.memberTypes[i];
//				if (memberType.binding != null) {
//					JavascriptFile.createProblemType(memberType, unitResult);
//				}
//			}
//		}
//		classFile.addAttributes();
		unitResult.record(typeBinding.constantPoolName(), classFile);
	}
	
	/**
	 * EXTERNAL API
	 * Answer the compound name of the class file.
	 * @return char[][]
	 * e.g. {{java}, {util}, {Hashtable}}.
	 */
	public char[][] getCompoundName() {
		return referenceBinding.compoundName;
	}

	public char[] fileName() {
		return CharOperation.concatWith(referenceBinding.compoundName, '/');
	}

	public byte[] getBytes() {
		return content.toString().getBytes();
	}
}

/*******************************************************************************
 * Copyright (c) 2008, 2009 Mateusz Matela and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mateusz Matela <mateusz.matela@gmail.com> - [code manipulation] [dcr] toString() builder wizard - https://bugs.eclipse.org/bugs/show_bug.cgi?id=26070
 *     Mateusz Matela <mateusz.matela@gmail.com> - [toString] finish toString() builder wizard - https://bugs.eclipse.org/bugs/show_bug.cgi?id=267710
 *******************************************************************************/
package org.summer.sdt.internal.corext.codemanipulation.tostringgeneration;

import org.summer.sdt.core.ICompilationUnit;
import org.summer.sdt.core.dom.AST;
import org.summer.sdt.core.dom.ITypeBinding;
import org.summer.sdt.core.dom.rewrite.ASTRewrite;
import org.summer.sdt.core.dom.rewrite.ImportRewrite;
import org.summer.sdt.internal.corext.refactoring.structure.CompilationUnitRewrite;


class ToStringGenerationContext {

	private Object[] fSelectedMembers;

	private ToStringTemplateParser fParser;

	private ITypeBinding fType;

	private ToStringGenerationSettings fSettings;
	
	private ToStringGenerationSettings.CustomBuilderSettings fCustomBuilderSettings;

	private CompilationUnitRewrite fRewrite;

	ToStringGenerationContext(ToStringTemplateParser parser, Object[] selectedMembers, ToStringGenerationSettings settings, ITypeBinding type,
			CompilationUnitRewrite rewrite) {
		fParser= parser;
		fSelectedMembers= selectedMembers;
		fSettings= settings;
		fCustomBuilderSettings= settings.getCustomBuilderSettings();
		fType= type;
		fRewrite= rewrite;
	}

	public ASTRewrite getASTRewrite() {
		return fRewrite.getASTRewrite();
	}

	public AST getAST() {
		return fRewrite.getAST();
	}

	public ICompilationUnit getCompilationUnit() {
		return fRewrite.getCu();
	}

	public ImportRewrite getImportRewrite() {
		return fRewrite.getImportRewrite();
	}

	public int getLimitItemsValue() {
		return fSettings.limitValue;
	}

	public Object[] getSelectedMembers() {
		return fSelectedMembers;
	}

	public ToStringTemplateParser getTemplateParser() {
		return fParser;
	}

	public ITypeBinding getTypeBinding() {
		return fType;
	}

	public boolean is50orHigher() {
		return fSettings.is50orHigher;
	}

	public boolean is60orHigher() {
		return fSettings.is60orHigher;
	}

	public boolean isCreateComments() {
		return fSettings.createComments;
	}

	public boolean isCustomArray() {
		return fSettings.customArrayToString;
	}

	public boolean isForceBlocks() {
		return fSettings.useBlocks;
	}

	public boolean isKeywordThis() {
		return fSettings.useKeywordThis;
	}

	public boolean isLimitItems() {
		return fSettings.limitElements;
	}

	public boolean isOverrideAnnotation() {
		return fSettings.overrideAnnotation;
	}

	public boolean isSkipNulls() {
		return fSettings.skipNulls;
	}
	
	public String getCustomBuilderClass() {
		return fCustomBuilderSettings.className;
	}
	
	public String getCustomBuilderVariableName() {
		return fCustomBuilderSettings.variableName;
	}
	
	public String getCustomBuilderAppendMethod() {
		return fCustomBuilderSettings.appendMethod;
	}
	
	public String getCustomBuilderResultMethod() {
		return fCustomBuilderSettings.resultMethod;
	}
	
	public boolean isCustomBuilderChainedCalls() {
		return fCustomBuilderSettings.chainCalls;
	}

}
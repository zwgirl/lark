/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.corext.util;

import org.summer.sdt.core.dom.ASTMatcher;
import org.summer.sdt.core.dom.ASTVisitor;
import org.summer.sdt.core.dom.NodeFinder;
import org.summer.sdt.core.dom.rewrite.ASTRewrite;
import org.summer.sdt.internal.corext.codemanipulation.StubUtility;
import org.summer.sdt.internal.corext.codemanipulation.StubUtility2;
import org.summer.sdt.internal.corext.dom.ASTNodeFactory;
import org.summer.sdt.internal.corext.dom.ASTNodes;
import org.summer.sdt.internal.corext.dom.Bindings;
import org.summer.sdt.internal.corext.dom.BodyDeclarationRewrite;
import org.summer.sdt.internal.corext.dom.DimensionRewrite;
import org.summer.sdt.internal.corext.dom.GenericVisitor;
import org.summer.sdt.internal.corext.dom.HierarchicalASTVisitor;
import org.summer.sdt.internal.corext.dom.ModifierRewrite;
import org.summer.sdt.internal.corext.dom.NecessaryParenthesesChecker;
import org.summer.sdt.internal.corext.dom.ReplaceRewrite;
import org.summer.sdt.internal.corext.dom.StatementRewrite;
import org.summer.sdt.internal.corext.dom.TypeRules;
import org.summer.sdt.internal.corext.dom.VariableDeclarationRewrite;
import org.summer.sdt.internal.corext.refactoring.structure.ASTNodeSearchUtil;
import org.summer.sdt.internal.corext.refactoring.structure.CompilationUnitRewrite;
import org.summer.sdt.internal.corext.refactoring.util.JavaElementUtil;
import org.summer.sdt.internal.ui.text.correction.ASTResolving;

/**
 * The org.summer.sdt.ui bundle contains a few internal helper classes that simplify
 * common tasks when dealing with JDT Core or UI APIs. Here's a list of the most important ones:
 * 
 * <h2>Java Model</h2>
 * <p>
 * APIs in {@link org.summer.sdt.core}.
 * </p>
 * 
 * <p>
 * Static helper methods for analysis in {@link org.summer.sdt.internal.corext.util} and elsewhere:
 * </p>
 * <ul>
 * <li>{@link JavaModelUtil}</li>
 * <li>{@link JavaElementUtil}</li>
 * <li>{@link JdtFlags}</li>
 * <li>{@link JavaConventionsUtil}</li>
 * <li>{@link MethodOverrideTester}</li>
 * <li>{@link SuperTypeHierarchyCache}</li>
 * </ul>
 * 
 * <p>
 * Static helper methods for stubs creation:
 * </p>
 * <ul>
 * <li>{@link StubUtility}</li>
 * </ul>
 * 
 * 
 * <h2>DOM AST</h2>
 * <p>
 * APIs in {@link org.summer.sdt.core.dom} and {@link org.summer.sdt.core.dom.rewrite}.<br>
 * Core API classes that are easy to miss: {@link NodeFinder}, {@link ASTVisitor}, {@link ASTMatcher}.
 * </p>
 * 
 * <p>
 * Static helper methods for analysis:
 * </p>
 * <ul>
 * <li>{@link ASTNodes}</li>
 * <li>{@link ASTNodeSearchUtil}</li>
 * <li>{@link ASTResolving}</li>
 * <li>{@link Bindings}</li>
 * <li>{@link TypeRules}</li>
 * </ul>
 * 
 * <p>
 * Static helper methods for node/stubs creation:
 * </p>
 * <ul>
 * <li>{@link ASTNodeFactory}</li>
 * <li>{@link StubUtility2}</li>
 * </ul>
 * 
 * <p>
 * Helper classes in {@link org.summer.sdt.internal.corext.dom}, e.g.:
 * </p>
 * <ul>
 * <li>{@link GenericVisitor}</li>
 * <li>{@link HierarchicalASTVisitor}</li>
 * <li>{@link NecessaryParenthesesChecker}</li>
 * </ul>
 * 
 * <p>
 * Helper classes for {@link ASTRewrite}:
 * </p>
 * <ul>
 * <li>{@link CompilationUnitRewrite}</li>
 * <li>{@link BodyDeclarationRewrite}</li>
 * <li>{@link DimensionRewrite}</li>
 * <li>{@link ModifierRewrite}</li>
 * <li>{@link ReplaceRewrite}</li>
 * <li>{@link StatementRewrite}</li>
 * <li>{@link VariableDeclarationRewrite}</li>
 * </ul>
 * 
 * @noreference This class is not intended to be referenced by clients
 */
public final class JDTUIHelperClasses {
	private JDTUIHelperClasses() {
		// no instances
	}
}

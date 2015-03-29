package org.summer.sdt.core.dom;

public class MarkupExtension extends XAMLElement{

	MarkupExtension(AST ast) {
		super(ast);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int getNodeType0() {
		return XAML_MARKUP_EXTENSION;
	}
}

package org.summer.sdt.core.dom;

public abstract class XAMLNode extends Expression{

	XAMLNode(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
	}
	
	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int memSize() {
		return BASE_NODE_SIZE + 3 * 4;
	}

}

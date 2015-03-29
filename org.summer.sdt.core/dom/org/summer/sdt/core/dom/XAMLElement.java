package org.summer.sdt.core.dom;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class XAMLElement extends XAMLNode {
	
	/**
	 * The "type" structural property of this node type (child type: {@link Type}).
	 * @since 3.0
	 */
	public static final ChildPropertyDescriptor TYPE_PROPERTY =
		new ChildPropertyDescriptor(XAMLElement.class, "type", Type.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	
	public static final ChildPropertyDescriptor CLOSE_TYPE_PROPERTY =
			new ChildPropertyDescriptor(XAMLElement.class, "closeType", Type.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "attributes" structural property of this node type (element type: {@link XAMLAttribute}).
	 * @since 3.0
	 */
	public static final ChildListPropertyDescriptor ATTRIBUTES_PROPERTY =
		new ChildListPropertyDescriptor(XAMLElement.class, "attributes", Attribute.class, CYCLE_RISK); //$NON-NLS-1$
	
	/**
	 * The "children" structural property of this node type (element type: {@link XAMLAttribute}).
	 * @since 3.0
	 */
	public static final ChildListPropertyDescriptor CHILDREN_PROPERTY =
		new ChildListPropertyDescriptor(XAMLElement.class, "children", XAMLElement.class, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List PROPERTY_DESCRIPTORS;

	static {
		List propertyList = new ArrayList(3);
		createPropertyList(XAMLElement.class, propertyList);
		addProperty(TYPE_PROPERTY, propertyList);
		addProperty(CLOSE_TYPE_PROPERTY, propertyList);
		addProperty(ATTRIBUTES_PROPERTY, propertyList);
		addProperty(CHILDREN_PROPERTY, propertyList);
		PROPERTY_DESCRIPTORS = reapPropertyList(propertyList);
	}

	/**
	 * Returns a list of structural property descriptors for this node type.
	 * Clients must not modify the result.
	 *
	 * @param apiLevel the API level; one of the
	 * <code>AST.JLS*</code> constants
	 * @return a list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor})
	 * @since 3.0
	 */
	public static List propertyDescriptors(int apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	private Type type = null;
	
	private Type closeType = null;

	/**
	 * The statements and SwitchCase nodes
	 * (element type: {@link Statement}).
	 * Defaults to an empty list.
	 */
	private ASTNode.NodeList attributes =
		new ASTNode.NodeList(ATTRIBUTES_PROPERTY);
	
	private ASTNode.NodeList children =
			new ASTNode.NodeList(CHILDREN_PROPERTY);
	
	XAMLElement(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == TYPE_PROPERTY) {
			if (get) {
				return getType();
			} else {
				setType((Type) child);
				return null;
			}
		}
		
		if (property == CLOSE_TYPE_PROPERTY) {
			if (get) {
				return getCloseType();
			} else {
				setCloseType((Type) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final List internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == ATTRIBUTES_PROPERTY) {
			return attributes();
		} else if (property == CHILDREN_PROPERTY) {
			return attributes();
		}
		
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int getNodeType0() {
		return XAML_ELEMENT;
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	ASTNode clone0(AST target) {
		XAMLElement result = new XAMLElement(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.setType(
				(Type) ASTNode.copySubtree(target, getType()));
		result.setCloseType(
				(Type) ASTNode.copySubtree(target, getCloseType()));

		result.attributes().addAll(
			ASTNode.copySubtrees(target, attributes()));
		result.children().addAll(
				ASTNode.copySubtrees(target, children()));
		return result;
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final boolean subtreeMatch0(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	void accept0(ASTVisitor visitor) {
		boolean visitChildren = visitor.visit(this);
		if (visitChildren) {
			// visit children in normal left to right reading order
			acceptChild(visitor, getType());
			acceptChild(visitor, getCloseType());
			acceptChildren(visitor, this.attributes);
			acceptChildren(visitor, this.children);
		}
		visitor.endVisit(this);
	}

	/**
	 * Returns the type of this element.
	 *
	 * @return the Type node
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Sets the Type of this element.
	 *
	 * @param Type the Type node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */
	public void setType(Type type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.type;
		preReplaceChild(oldChild, type, TYPE_PROPERTY);
		this.type = type;
		postReplaceChild(oldChild, type, TYPE_PROPERTY);
	}
	
	/**
	 * Sets the close Type of this element.
	 *
	 * @param Type the Type node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */
	public void setCloseType(Type closeType) {
		if (closeType == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.closeType;
		preReplaceChild(oldChild, closeType, CLOSE_TYPE_PROPERTY);
		this.closeType = closeType;
		postReplaceChild(oldChild, closeType, CLOSE_TYPE_PROPERTY);
	}
	
	/**
	 * Returns the close type of this element.
	 *
	 * @return the Type node
	 */
	public Type getCloseType() {
		return this.closeType;
	}

	/**
	 * Returns the live ordered list of statements for this switch statement.
	 * Within this list, <code>SwitchCase</code> nodes mark the start of
	 * the switch groups.
	 *
	 * @return the live list of statement nodes
	 *    (element type: {@link Statement})
	 */
	public List attributes() {
		return this.attributes;
	}
	
	public List children() {
		return this.children;
	}


	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int memSize() {
		return super.memSize() + 4 * 4;
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int treeSize() {
		return
			memSize()
			+ (this.attributes == null ? 0 : this.attributes.listSize())
			+ (this.children == null ? 0 : this.children.listSize())
			+ (this.type == null ? 0 : getType().treeSize())
			+ (this.closeType == null ? 0 : getCloseType().treeSize());
	}
}

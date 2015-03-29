package org.summer.sdt.core.dom;

import java.util.ArrayList;
import java.util.List;

public class Attribute extends XAMLNode{
	/**
	 * The "type" structural property of this node type (child type: {@link Type}).
	 * @since 3.0
	 */
	public static final ChildPropertyDescriptor VALUE_PROPERTY =
		new ChildPropertyDescriptor(XAMLElement.class, "value", Expression.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "attributes" structural property of this node type (element type: {@link XAMLAttribute}).
	 * @since 3.0
	 */
	public static final ChildPropertyDescriptor PROPERTY_PROPERTY =
		new ChildPropertyDescriptor(XAMLElement.class, "property", FieldAccess.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List PROPERTY_DESCRIPTORS;

	static {
		List propertyList = new ArrayList(3);
		createPropertyList(XAMLElement.class, propertyList);
		addProperty(VALUE_PROPERTY, propertyList);
		addProperty(PROPERTY_PROPERTY, propertyList);
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

	/**
	 * The expression; lazily initialized; defaults to a unspecified, but legal,
	 * expression.
	 */
	private Expression value = null;
	
	private FieldAccess property = null;
	
	Attribute(AST ast) {
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
		if (property == VALUE_PROPERTY) {
			if (get) {
				return getValue();
			} else {
				setValue((Expression) value);
				return null;
			}
		}
		if (property == PROPERTY_PROPERTY) {
			if (get) {
				return getProperty();
			} else {
				setProperty( (FieldAccess) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	final int getNodeType0() {
		return XAML_ATTRIBUTE;
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	ASTNode clone0(AST target) {
		Attribute result = new Attribute(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.setValue((Expression) getValue().clone(target));
		result.setProperty((FieldAccess) getProperty().clone(target));
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
			acceptChild(visitor, getValue());
			acceptChild(visitor, getProperty());
		}
		visitor.endVisit(this);
	}

	/**
	 * Returns the expression of this field access expression.
	 *
	 * @return the expression node
	 */
	public Expression getValue() {
		return this.value;
	}

	/**
	 * Sets the expression of this field access expression.
	 *
	 * @param expression the new expression
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */
	public void setValue(Expression value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.value;
		preReplaceChild(oldChild, value, VALUE_PROPERTY);
		this.value = value;
		postReplaceChild(oldChild, value, VALUE_PROPERTY);
	}
	
	/**
	 * Returns the expression of this field access expression.
	 *
	 * @return the expression node
	 */
	public FieldAccess getProperty() {
		return this.property;
	}

	/**
	 * Sets the expression of this field access expression.
	 *
	 * @param expression the new expression
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */
	public void setProperty(FieldAccess value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.property;
		preReplaceChild(oldChild, value, PROPERTY_PROPERTY);
		this.property = value;
		postReplaceChild(oldChild, value, PROPERTY_PROPERTY);
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int memSize() {
		// treat Code as free
		return BASE_NODE_SIZE + 2 * 4;
	}

	/* (omit javadoc for this method)
	 * Method declared on ASTNode.
	 */
	int treeSize() {
		return
			memSize()
			+ (this.value == null ? 0 : getValue().treeSize())
			+ (this.property == null ? 0 : getProperty().treeSize());
	}
}

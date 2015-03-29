package org.summer.sdt.internal.compiler.javascript;

/**
 * A mapping from a given position in an input source file to a given position
 * in the generated code.
 */
public class Mapping {
	private static final int UNMAPPED = -1;

	/**
	 * A unique ID for this mapping for record keeping purposes.
	 */
	int id = UNMAPPED;

	/**
	 * The source file index.
	 */
	String sourceFile;

	/**
	 * The position of the code in the input source file. Both the line number
	 * and the character index are indexed by 1 for legacy reasons via the Rhino
	 * Node class.
	 */
	FilePosition originalPosition;

	/**
	 * The starting position of the code in the generated source file which this
	 * mapping represents. Indexed by 0.
	 */
	FilePosition startPosition;

	/**
	 * The ending position of the code in the generated source file which this
	 * mapping represents. Indexed by 0.
	 */
	FilePosition endPosition;

	/**
	 * The original name of the token found at the position represented by this
	 * mapping (if any).
	 */
	String originalName;

	/**
	 * Whether the mapping is actually used by the source map.
	 */
	boolean used = false;
}
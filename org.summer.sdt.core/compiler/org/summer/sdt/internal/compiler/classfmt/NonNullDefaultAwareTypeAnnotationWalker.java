/*******************************************************************************
 * Copyright (c) 2014 GK Software AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Herrmann - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.compiler.classfmt;

import org.summer.sdt.core.compiler.CharOperation;
import org.summer.sdt.internal.compiler.env.IBinaryAnnotation;
import org.summer.sdt.internal.compiler.env.IBinaryElementValuePair;
import org.summer.sdt.internal.compiler.env.IBinaryTypeAnnotation;
import org.summer.sdt.internal.compiler.lookup.Binding;
import org.summer.sdt.internal.compiler.lookup.LookupEnvironment;
import org.summer.sdt.internal.compiler.lookup.TypeIds;

/**
 * A type annotation walker that adds missing NonNull annotations according to the current default.
 */
public class NonNullDefaultAwareTypeAnnotationWalker extends TypeAnnotationWalker {

	private int defaultNullness;
	private boolean atDefaultLocation;
	private boolean nextIsDefaultLocation;
	private boolean atTypeBound;
	private boolean nextIsTypeBound;
	private boolean isEmpty;
	IBinaryAnnotation nonNullAnnotation;

	/** Create initial walker with non-empty type annotations. */
	public NonNullDefaultAwareTypeAnnotationWalker(IBinaryTypeAnnotation[] typeAnnotations,
						int defaultNullness, LookupEnvironment environment) {
		super(typeAnnotations);
		this.nonNullAnnotation = getNonNullAnnotation(environment);
		this.defaultNullness = defaultNullness;
	}
	
	/** Create an initial walker without 'real' type annotations, but with a nonnull default. */
	public NonNullDefaultAwareTypeAnnotationWalker(int defaultNullness, LookupEnvironment environment) {
		this(defaultNullness, getNonNullAnnotation(environment), false, false);
	}

	/** Get restricted walker, still with non-empty type annotations. */
	NonNullDefaultAwareTypeAnnotationWalker(IBinaryTypeAnnotation[] typeAnnotations, long newMatches, int newPathPtr,
						int defaultNullness, IBinaryAnnotation nonNullAnnotation, boolean atDefaultLocation, boolean atTypeBound) {
		super(typeAnnotations, newMatches, newPathPtr);
		this.defaultNullness = defaultNullness;
		this.nonNullAnnotation = nonNullAnnotation;
		this.atDefaultLocation = atDefaultLocation;
		this.atTypeBound = atTypeBound;
	}

	/** Create a restricted walker without 'real' type annotations, but with a nonnull default. */
	NonNullDefaultAwareTypeAnnotationWalker(int defaultNullness, IBinaryAnnotation nonNullAnnotation, boolean atDefaultLocation, boolean atTypeBound) {
		super(null, 0, 0);
		this.nonNullAnnotation = nonNullAnnotation;
		this.defaultNullness = defaultNullness;
		this.atDefaultLocation = atDefaultLocation;
		this.atTypeBound = atTypeBound;
		this.isEmpty = true;
	}
	
	private static IBinaryAnnotation getNonNullAnnotation(LookupEnvironment environment) {
		final char[] nonNullAnnotationName = CharOperation.concat(
						'L', CharOperation.concatWith(environment.getNonNullAnnotationName(), '/'), ';');
		// create the synthetic annotation:
		return new IBinaryAnnotation() {
			@Override
			public char[] getTypeName() {
				return nonNullAnnotationName;
			}
			@Override
			public IBinaryElementValuePair[] getElementValuePairs() {
				return null;
			}
		};
	}

	protected TypeAnnotationWalker restrict(long newMatches, int newPathPtr) {
		// considers nextIsDefaultLocation as the new atDefaultLocation
		try {
			// do we have any change at all?
			if (this.matches == newMatches && this.pathPtr == newPathPtr
					&& this.atDefaultLocation == this.nextIsDefaultLocation && this.atTypeBound == this.nextIsTypeBound)
				return this;
			// are we running out of real type annotations?
			if (newMatches == 0 || this.typeAnnotations == null || this.typeAnnotations.length == 0)
				return new NonNullDefaultAwareTypeAnnotationWalker(this.defaultNullness, this.nonNullAnnotation, 
												this.nextIsDefaultLocation, this.nextIsTypeBound);
			// proceed as normal, but pass on our specific fields, too:
			return new NonNullDefaultAwareTypeAnnotationWalker(this.typeAnnotations, newMatches, newPathPtr,
												this.defaultNullness, this.nonNullAnnotation, this.nextIsDefaultLocation, this.nextIsTypeBound);
		} finally {
			this.nextIsDefaultLocation = false; // expire
			this.nextIsTypeBound = false;
		}
	}
	
	@Override
	public TypeAnnotationWalker toMethodParameter(short index) {
		// don't set nextIsDefaultLocation, because signature-level nullness is handled by ImplicitNullAnnotationVerifier
		if (this.isEmpty) return restrict(this.matches, this.pathPtr);
		return super.toMethodParameter(index);
	}

	@Override
	public TypeAnnotationWalker toMethodReturn() {
		// don't set nextIsDefaultLocation, because signature-level nullness is handled by ImplicitNullAnnotationVerifier
		if (this.isEmpty) return restrict(this.matches, this.pathPtr);
		return super.toMethodReturn();
	}

	@Override
	public TypeAnnotationWalker toTypeBound(short boundIndex) {
		this.nextIsDefaultLocation = (this.defaultNullness & Binding.DefaultLocationTypeBound) != 0;
		this.nextIsTypeBound = true;
		if (this.isEmpty) return restrict(this.matches, this.pathPtr);
		return super.toTypeBound(boundIndex);
	}

	@Override
	public TypeAnnotationWalker toTypeParameterBounds(boolean isClassTypeParameter, int parameterRank) {
		this.nextIsDefaultLocation = (this.defaultNullness & Binding.DefaultLocationTypeBound) != 0;
		this.nextIsTypeBound = true;
		if (this.isEmpty) return restrict(this.matches, this.pathPtr);
		return super.toTypeParameterBounds(isClassTypeParameter, parameterRank);
	}

	@Override
	public TypeAnnotationWalker toTypeArgument(int rank) {
		this.nextIsDefaultLocation = (this.defaultNullness & Binding.DefaultLocationTypeArgument) != 0;
		this.nextIsTypeBound = false;
		if (this.isEmpty) return restrict(this.matches, this.pathPtr);
		return super.toTypeArgument(rank);
	}

	@Override
	public TypeAnnotationWalker toTypeParameter(boolean isClassTypeParameter, int rank) {
		this.nextIsDefaultLocation = (this.defaultNullness & Binding.DefaultLocationTypeParameter) != 0;
		this.nextIsTypeBound = false;
		if (this.isEmpty) return restrict(this.matches, this.pathPtr);
		return super.toTypeParameter(isClassTypeParameter, rank);
	}

	@Override
	public IBinaryAnnotation[] getAnnotationsAtCursor(int currentTypeId) {
		IBinaryAnnotation[] normalAnnotations = this.isEmpty ? null : super.getAnnotationsAtCursor(currentTypeId);
		if (this.atDefaultLocation && 
				!(this.atTypeBound && currentTypeId == TypeIds.T_JavaLangObject)) // for CLIMB-to-top consider a j.l.Object type bound as no explicit type bound
		{
			if (normalAnnotations == null || normalAnnotations.length == 0)
				return new IBinaryAnnotation[] { this.nonNullAnnotation };
			int len = normalAnnotations.length;
			IBinaryAnnotation[] newAnnots = new IBinaryAnnotation[len+1];
			System.arraycopy(normalAnnotations, 0, newAnnots, 0, len);
			newAnnots[len] = this.nonNullAnnotation;
			return newAnnots;
		}
		return normalAnnotations;
	}
}

package com.github.yferras.javartint.core.constraint;

/*
 * #%L
 * Crow JavArtInt Core
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.github.yferras.javartint.core.IterativeAlgorithm;
import com.github.yferras.javartint.core.Solution;

/**
 * This class can be used to create constrains to iterative algorithms.
 *
 * @param <A>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class MaxIterationsConstraint<A extends IterativeAlgorithm<? extends Solution>>
		extends AbstractIterationConstraint<A> {

	/**
	 * Constructor.
	 *
	 * @param constraintType
	 *            constrain type.
	 * @param maxIterations
	 *            max allowed iterations.
	 */
	public MaxIterationsConstraint(ConstraintType constraintType, long maxIterations) {
		super(constraintType, maxIterations);
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * If the iterations of algorithm is greater than or equals to the max
	 * allowed, in this case returns <code>true</code>, otherwise returns
	 * <code>false</code>.
	 */
	@Override
	public boolean evaluate(A algorithm) {
		return algorithm.getIterations() >= maxIterations;
	}

}

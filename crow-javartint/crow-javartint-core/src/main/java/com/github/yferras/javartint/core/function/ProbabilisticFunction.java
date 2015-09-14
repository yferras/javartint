package com.github.yferras.javartint.core.function;

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


import java.util.Random;

/**
 * Interface to create generic probabilistic functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface ProbabilisticFunction<R, P> extends Function<R, P> {

	/**
	 * Gets the probability, a number between {@code 0.0} and {@code 0.0}.
	 *
	 * @return the probability
	 */
	double getProbability();

	/**
	 * Sets the probability, a number that must between {@code 0.0} and
	 * {@code 1.0}.
	 *
	 * @param probability the probability
	 * @throws java.lang.IllegalArgumentException if {@code probability is not
	 *                                            inside 0.0 and 1.0}
	 */
	void setProbability(final double probability)
		throws IllegalArgumentException;

	/**
	 * Gets the random instance.
	 *
	 * @return the random instance.
	 */
	Random getRandom();

	/**
	 * Sets the random generator. Is used to generate random probabilities.
	 *
	 * @param random the random instance
	 * @throws java.lang.IllegalArgumentException if {@code random} param
	 *                                            is {@code null}
	 */
	void setRandom(final Random random)
		throws IllegalArgumentException;

	/**
	 * {@inheritDoc}
	 *
	 * The result is constrained by the returned value of {@link #getProbability()}.
	 */
	@Override
	R evaluate(P params);
}

package crow.javartint.core.util.function;

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

import crow.javartint.core.util.RandomGenerator;

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
     * @return the probability
     */
    double getProbability();

    /**
     * Sets the probability, a number that must between {@code 0.0} and
     * {@code 1.0}.
     *
     * @param probability the probability
     *
     * @throws java.lang.IllegalArgumentException if {@code probability is not
     * inside 0.0 and 1.0}
     */
    void setProbability(final double probability)
            throws IllegalArgumentException;

    /**
     * Gets the random generator.
     * @return the random generator
     */
    RandomGenerator getRandomGenerator();

    /**
     * Sets the random generator. Is used to generate random probabilities.
     *
     * @param randomGenerator the random generator
     * @throws java.lang.IllegalArgumentException if {@code randomGenerator}
     * is {@code null}
     */
    void setRandomGenerator(final RandomGenerator randomGenerator)
            throws IllegalArgumentException;

    /**
     * Evaluates the params and the result is constrained by the returned
     * value of {@link #getProbability()}.
     * @param params parameters to evaluate.
     * @return {@inheritDoc}
     */
    @Override
    R evaluate(P params);
}

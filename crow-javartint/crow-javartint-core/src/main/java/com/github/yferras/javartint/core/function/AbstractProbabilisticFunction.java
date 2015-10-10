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

import com.github.yferras.javartint.core.util.ValidationException;

import java.util.Random;

/**
 * Class that implements partially {@link com.github.yferras.javartint.core.function.ProbabilisticFunction} to create
 * generic probabilistic functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractProbabilisticFunction<R, P>
    implements ProbabilisticFunction<R, P> {

    /** Constant <code>DEFAULT_PROBABILITY=.05</code> */
    public static final double DEFAULT_PROBABILITY = .5;

    protected double probability;
    protected Random random;

    /**
     * Constructor, initializes instances with the given parameters.
     * Internally calls {@link #setProbability(double)} and
     * {@link #setRandom(java.util.Random)}
     *
     * @param probability value of probability
     * @param random      random instance
     * @throws com.github.yferras.javartint.core.util.ValidationException see {@link #setProbability(double)} and see {@link #setRandom(java.util.Random)}
     */
    protected AbstractProbabilisticFunction(double probability, Random random) throws ValidationException {
        setProbability(probability);
        setRandom(random);
    }

    /**
     * Constructor, initializes instances with probability specified by
     * {@code probability} parameter and random is an instance of
     * {@link java.util.Random}.
     *
     * @param probability value of probability
     * @throws com.github.yferras.javartint.core.util.ValidationException see {@link #setProbability}
     */
    protected AbstractProbabilisticFunction(double probability) throws ValidationException {
        this(probability, new Random());
    }

    /**
     * Constructor, initializes instances with default probability (.5) and random is an instance of
     * {@link java.util.Random}.
     *
     * @since 1.0.1
     */
    protected AbstractProbabilisticFunction() {
        this.probability = DEFAULT_PROBABILITY;
        this.random = new Random();
    }

    /** {@inheritDoc} */
    @Override
    public double getProbability() {
        return probability;
    }

    /** {@inheritDoc} */
    @Override
    public final void setProbability(final double probability) throws ValidationException {
        if (probability < 0 || probability > 1.0) {
            throw new ValidationException (
                "'probability' must between 0.0 and 1.0");
        }
        this.probability = probability;
    }

    /** {@inheritDoc} */
    @Override
    public Random getRandom() {
        return random;
    }

    /** {@inheritDoc} */
    @Override
    public void setRandom(final Random random) throws ValidationException {
        if (random == null) {
            throw new ValidationException("'random' can't be null");
        }
        this.random = random;
    }

}

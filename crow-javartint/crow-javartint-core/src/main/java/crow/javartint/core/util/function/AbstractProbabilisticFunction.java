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
 * Class that implements partially {@link ProbabilisticFunction} to create
 * generic probabilistic functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
abstract public class AbstractProbabilisticFunction<R, P>
        implements ProbabilisticFunction<R, P> {

    private double probability;
    private RandomGenerator randomGenerator;

    /**
     * Constructor, initializes instances with the given parameters.
     * Internally calls {@link #setProbability(double)} and
     * {@link #setRandomGenerator(crow.javartint.core.util.RandomGenerator)}
     *
     * @param probability     probability of crossover
     * @param randomGenerator random generator
     * @throws java.lang.IllegalArgumentException see {@link #setProbability}
     * and see {@link #setRandomGenerator}
     */
    protected AbstractProbabilisticFunction(double probability,
                                            RandomGenerator randomGenerator)
            throws IllegalArgumentException {
        setProbability(probability);
        setRandomGenerator(randomGenerator);
    }

    /**
     * Constructor, initializes instances with probability specified by
     * {@code probability} parameter and random generator is an instance of
     * {@link crow.javartint.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     *
     * @param probability probability of crossover
     * @throws java.lang.IllegalArgumentException see {@link #setProbability}
     * and see {@link #setRandomGenerator}
     */
    protected AbstractProbabilisticFunction(double probability)
            throws IllegalArgumentException {
        this(probability, new RandomGenerator.SystemDefaultRandomGenerator());
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void setProbability(final double probability)
            throws IllegalArgumentException {
        if (probability < 0 || probability > 1.0) {
            throw new IllegalArgumentException(
                    "'probability' must between 0.0 and 1.0");
        }
        this.probability = probability;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    @Override
    public void setRandomGenerator(final RandomGenerator randomGenerator)
            throws IllegalArgumentException {
        if (randomGenerator == null) {
            throw new IllegalArgumentException("'randomGenerator' can't be " +
                    "null");
        }
        this.randomGenerator = randomGenerator;
    }

}

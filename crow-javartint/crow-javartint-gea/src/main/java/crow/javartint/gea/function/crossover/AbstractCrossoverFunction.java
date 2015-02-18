package crow.javartint.gea.function.crossover;

/*
 * #%L
 * Crow JavArtInt GEA
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
import crow.javartint.core.util.function.AbstractProbabilisticFunction;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * Abstract class that represents crossover function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.3
 */
abstract public class AbstractCrossoverFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractProbabilisticFunction<T[], T[]>
        implements CrossoverFunction<T> {

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability     probability of crossover
     * @param randomGenerator random generator
     */
    protected AbstractCrossoverFunction(double probability,
                                        RandomGenerator randomGenerator) {
        super(probability, randomGenerator);
    }

    /**
     * Constructor, initializes instances with probability of crossover
     * specified by {@code probability} parameter and random generator is an
     * instance of
     * {@link crow.javartint.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     *
     * @param probability probability of crossover
     */
    protected AbstractCrossoverFunction(double probability) {
        super(probability);
    }

    /**
     * Default constructor, initializes instances with probability of crossover
     * equals to {@code .75} and random generator is an instance of
     * {@link crow.javartint.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     */
    protected AbstractCrossoverFunction() {
        super(.75);
    }

    /**
     * Performs the specific crossover process. This method is called inside
     * the method {@link #evaluate(crow.javartint.gea.genome.Genome[])}
     *
     * @param parent1 first parent
     * @param parent2 second parent
     * @return the offspring.
     */
    protected abstract T[] recombine(T parent1, T parent2)
            throws CloneNotSupportedException;

    /**
     * Ensures that parameters are valid.
     *
     * @param params parameters to validate.
     * @throws IllegalArgumentException if {@code params} is null
     *                                  or {@code params.length} is less than 2.
     */
    protected void validate(T... params) throws IllegalArgumentException {
        if (params == null) {
            throw new IllegalArgumentException("'params' can't be null.");
        }
        if (params.length < 2) {
            throw new IllegalArgumentException(
                    "'params' must contain at less two elements");
        }
    }

    /**
     * Accepts two genomes (parents) to perform the crossover process,
     * and retrieves an array containing the offspring.
     *
     * @param params parents.
     * @return the offspring.
     */
    @Override
    public T[] evaluate(T... params) {
        validate(params);
        double randomProbability = getRandomGenerator().nextDouble();
        if (randomProbability > getProbability() ||
                params[0].equals(params[1])) {
            return params.clone();
        }
        try {
            return recombine(params[0], params[1]);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

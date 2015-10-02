package com.github.yferras.javartint.gea.function.recombination;

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

import com.github.yferras.javartint.core.function.AbstractProbabilisticFunction;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class that represents recombination function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.3
 */
abstract public class AbstractRecombinationFunction<T extends Genome<? extends Chromosome<? extends Gene<?>>>>
    extends AbstractProbabilisticFunction<T[], T[]>
    implements RecombinationFunction<T> {

    public static final double DEFAULT_PROBABILITY = .75;

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of recombination
     * @param random      random instance
     */
    protected AbstractRecombinationFunction(double probability,
                                            Random random) {
        super(probability, random);
    }

    /**
     * Constructor, initializes instances with probability of recombination
     * specified by {@code probability} parameter and random is an
     * instance of {@link java.util.Random}.
     *
     * @param probability probability of recombination
     */
    protected AbstractRecombinationFunction(double probability) {
        super(probability);
    }

    /**
     * Default constructor, initializes instances with probability of recombination
     * equals to {@code .75} and random generator is an instance of {@link java.util.Random}.
     */
    protected AbstractRecombinationFunction() {
        super(DEFAULT_PROBABILITY);
    }

    /**
     * Performs the specific recombination process. This method is called inside
     * the method {@link #evaluate(Genome[])}
     *
     * @param parent1 first parent
     * @param parent2 second parent
     * @return the offspring.
     * @throws java.lang.CloneNotSupportedException if any.
     */
    protected abstract T[] recombine(T parent1, T parent2)
        throws CloneNotSupportedException;

    /**
     * Ensures that parameters are valid.
     *
     * @param params parameters to validate.
     * @throws java.lang.IllegalArgumentException if {@code params} is null
     *                                            or {@code params.length} is less than 2.
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
     * {@inheritDoc}
     * <p/>
     * Accepts two genomes (parents) to perform the recombination process,
     * and retrieves an array containing the offspring.
     */
    @Override
    public T[] evaluate(T... params) {
        validate(params);
        double randomProbability = getRandom().nextDouble();
        if (randomProbability > getProbability() ||
            params[0].equals(params[1])) {
            return params.clone();
        }
        try {
            return recombine(params[0], params[1]);
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}

package com.github.yferras.javartint.gea.function.recombination;

import java.util.Random;

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

/**
 * Abstract class that represents recombination function.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.3
 */
public abstract class AbstractRecombinationFunction<T extends Genome<? extends Chromosome<? extends Gene<?>>>>
		extends AbstractProbabilisticFunction<T[], T[]> implements RecombinationFunction<T> {

	/** Constant <code>DEFAULT_PROBABILITY=.75</code> */
	public static final double DEFAULT_PROBABILITY = .75;

	private static final int MAX_PARAMS = 2;

	/**
	 * Default constructor, initializes instances with probability of
	 * recombination equals to {@code .75} and random generator is an instance
	 * of {@link java.util.Random}.
	 */
	protected AbstractRecombinationFunction() {
		super();
		this.probability = DEFAULT_PROBABILITY;
	}

	/**
	 * Constructor, initializes instances with probability of recombination
	 * specified by {@code probability} parameter and random is an instance of
	 * {@link java.util.Random}.
	 *
	 * @param probability
	 *            probability of recombination
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if any.
	 */
	protected AbstractRecombinationFunction(double probability) {
		super(probability);
	}

	/**
	 * Constructor, initializes instances with the given parameters.
	 *
	 * @param probability
	 *            probability of recombination
	 * @param random
	 *            random instance
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if any.
	 */
	protected AbstractRecombinationFunction(double probability, Random random) {
		super(probability, random);
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * Accepts two genomes (parents) to perform the recombination process, and
	 * retrieves an array containing the offspring.
	 */
	@Override
	public T[] evaluate(T... params) {
		validate(params);
		double randomProbability = getRandom().nextDouble();
		if (randomProbability > getProbability() || params[0].equals(params[1])) {
			return params.clone();
		}
		try {
			return recombine((T) params[0].clone(), (T) params[1].clone());
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Cloning params.", e);
		}
	}

	/**
	 * Performs the specific recombination process. This method is called inside
	 * the method {@link #evaluate(Genome[])}
	 *
	 * @param parent1
	 *            first parent
	 * @param parent2
	 *            second parent
	 * @return the offspring.
	 * @throws java.lang.CloneNotSupportedException
	 *             if any.
	 */
	protected abstract T[] recombine(T parent1, T parent2) throws CloneNotSupportedException;

	/**
	 * Ensures that parameters are valid.
	 *
	 * @param params
	 *            parameters to validate.
	 * @throws java.lang.IllegalArgumentException
	 *             if {@code params} is null or {@code params.length} is less
	 *             than 2.
	 */
	protected void validate(T... params) throws IllegalArgumentException {
		if (params == null) {
			throw new IllegalArgumentException("'params' can't be null.");
		}
		if (params.length < MAX_PARAMS) {
			throw new IllegalArgumentException("'params' must contain at less two elements");
		}
	}
}

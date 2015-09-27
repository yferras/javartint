package com.github.yferras.javartint.gea.function.mutation;

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

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.genome.Genome;
import com.github.yferras.javartint.core.function.AbstractProbabilisticFunction;
import com.github.yferras.javartint.gea.gene.Gene;

import java.util.Random;

/**
 * Abstract class that represents mutation function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
abstract public class AbstractMutationFunction<T extends Genome<? extends Chromosome<? extends Gene<?>>>>
	extends AbstractProbabilisticFunction<T, T> implements MutationFunction<T> {

	/**
	 * Constructor, initializes instances with the given parameters.
	 *
	 * @param probability probability of mutation
	 * @param random      random instance
	 */
	protected AbstractMutationFunction(double probability,
	                                   Random random) {
		super(probability, random);
	}

	/**
	 * Constructor, initializes instances with probability of mutation
	 * specified by {@code probability} parameter and random  is an
	 * instance of
	 * {@link java.util.Random}.
	 *
	 * @param probability probability of mutation
	 */
	protected AbstractMutationFunction(double probability) {
		super(probability);
	}

	/**
	 * Default constructor, initializes instances with probability of mutation
	 * equals to {@code .05} and random generator is an instance of
	 * {@link java.util.Random}.
	 */
	protected AbstractMutationFunction() {
		super(.05);
	}

	/**
	 * Performs the specific mutation process. This method is called inside
	 * the method {@link #evaluate(Genome)}
	 *
	 * @param subject individual which will be mutate
	 * @return mutated genome
	 * @throws java.lang.CloneNotSupportedException if
	 */
	protected abstract T mutate(T subject) throws CloneNotSupportedException;

	/**
	 * Ensures that parameter are valid.
	 *
	 * @param param parameter to validate.
	 * @throws java.lang.IllegalArgumentException if {@code param} is {@code null}.
	 */
	protected void validate(T param) throws IllegalArgumentException {
		if (param == null) {
			throw new IllegalArgumentException("'params' can't be null.");
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * If validation process is ok and generated random probability
	 * is in bounds, performs the mutation process with a copy of
	 * genome and returns a mutated genome.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T evaluate(T params) {
		validate(params);
		if (getRandom().nextDouble() > getProbability()) {
			return params;
		}
		try {
			return mutate((T) params.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}

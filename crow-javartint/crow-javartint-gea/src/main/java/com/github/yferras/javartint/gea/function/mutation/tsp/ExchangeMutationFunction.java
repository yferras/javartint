package com.github.yferras.javartint.gea.function.mutation.tsp;

/*
 * #%L
 * Crow JavArtInt GEA
 * %%
 * Copyright (C) 2014 - 2016 Eng. Ferrás Cecilio, Yeinier
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

import com.github.yferras.javartint.gea.function.mutation.AbstractMutationFunction;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.TspGenome;

/**
 * <p>
 * Specific mutation function in TSP.
 * </p>
 * <p>
 * Takes the original genome and generates tow random indices based on genome's
 * size. After that, the genes on that indices are exchanged.
 * </p>
 * <p>
 * Example, given the following genome:
 * </p>
 * 
 * <pre>
 *     TSPGenome:
 *     [ 8, 5, 2, 1, 6, 3, 7, 0, 9, 4 ]
 *     Generating section:
 *     index1 = rand(9),    index1 = 5
 *     index2 = rand(10),   index2 = 1
 *     Exchanging:
 *     [ 8, 5, 2, 1, 6, 3, 7, 0, 9, 4 ]
 *          ^           ^
 *     [ 8, 3, 2, 1, 6, 5, 7, 0, 9, 4 ]
 *
 * </pre>
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class ExchangeMutationFunction<T extends TspGenome> extends AbstractMutationFunction<T> {

	/**
	 * <p>
	 * Constructor for ExchangeMutationFunction.
	 * </p>
	 *
	 * @param probability
	 *            a double.
	 * @param random
	 *            a {@link java.util.Random} object.
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if any.
	 */
	public ExchangeMutationFunction(double probability, Random random) {
		super(probability, random);
	}

	/**
	 * <p>
	 * Constructor for ExchangeMutationFunction.
	 * </p>
	 *
	 * @param probability
	 *            a double.
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if any.
	 */
	public ExchangeMutationFunction(double probability) {
		super(probability);
	}

	/**
	 * <p>
	 * Constructor for ExchangeMutationFunction.
	 * </p>
	 */
	public ExchangeMutationFunction() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	protected T mutate(T subject) throws CloneNotSupportedException {
		int pos1 = getRandom().nextInt(subject.getChromosome().size() - 1);
		int pos2 = getRandom().nextInt(subject.getChromosome().size());
		while (pos2 == pos1) {
			pos2 = getRandom().nextInt(subject.getChromosome().size());
		}
		DefaultGene<Integer> gene1 = subject.getChromosome().getGene(pos1);
		subject.getChromosome().setGene(pos1, subject.getChromosome().getGene(pos2));
		subject.getChromosome().setGene(pos2, gene1);
		return subject;
	}
}

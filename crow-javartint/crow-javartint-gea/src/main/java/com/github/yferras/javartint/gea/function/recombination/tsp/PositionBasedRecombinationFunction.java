package com.github.yferras.javartint.gea.function.recombination.tsp;

/*
 * #%L
 * Crow JavArtInt GEA
 *
 %%
 Copyright (C) 2014 - 2016 Eng. Ferrás Cecilio, Yeinier
 *
 %%
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as
 published by the Free Software Foundation, either version 3 of the
 License, or (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public
 License along with this program.  If not, see
 <http://www.gnu.org/licenses/gpl-3.0.html>.
 #L%
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.github.yferras.javartint.gea.function.recombination.AbstractRecombinationFunction;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.TspGenome;

/**
 * <p/>
 * Specific recombination function in TSP.
 * <p/>
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class PositionBasedRecombinationFunction<T extends TspGenome> extends AbstractRecombinationFunction<T> {

	/**
	 * Default constructor, initializes instances with probability of
	 * recombination equals to {@code .75} and random generator is an instance
	 * of {@link java.util.Random}.
	 */
	public PositionBasedRecombinationFunction() {
		super();
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
	public PositionBasedRecombinationFunction(double probability) {
		this(probability, new Random());
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
	public PositionBasedRecombinationFunction(double probability, Random random) {
		super(probability, random);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
		TspGenome[] offspring = new TspGenome[] { parent1.clone(), parent2.clone() };

		int n = parent1.getChromosome().size();
		List<DefaultGene<Integer>>[] genes = new List[] { new ArrayList<>(Collections.nCopies(n, null)),
				new ArrayList<>(Collections.nCopies(n, null)) };

		int pos = getRandom().nextInt(n - 1);
		int index = 0;
		while (pos < n) {
			genes[0].set(index, offspring[0].getChromosome().getGene(pos));
			genes[1].set(index++, offspring[1].getChromosome().getGene(pos));
			pos += getRandom().nextInt(n - pos) + 1;
		}

		int count0 = 0;
		int count1 = 0;
		for (int i = 0; i < n; i++) {
			while ((count1 < n) && (genes[1].get(count1) != null)) {
				count1++;
			}
			if (!genes[1].contains(offspring[0].getChromosome().getGene(i))) {
				genes[1].set(count1, offspring[0].getChromosome().getGene(i));
			}

			while ((count0 < n) && (genes[0].get(count0) != null)) {
				count0++;
			}
			if (!genes[0].contains(offspring[1].getChromosome().getGene(i))) {
				genes[0].set(count0, offspring[1].getChromosome().getGene(i));
			}
		}

		offspring[0].getChromosome().setGenes(genes[0].toArray(new DefaultGene[n]));
		offspring[1].getChromosome().setGenes(genes[1].toArray(new DefaultGene[n]));

		return (T[]) offspring;
	}
}

package com.github.yferras.javartint.gea.function.recombination.tsp;

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

import com.github.yferras.javartint.gea.function.recombination.AbstractRecombinationFunction;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.TspGenome;

import java.util.Iterator;
import java.util.Random;

/**
 * <p/>
 * Specific recombination function in TSP.
 * <p/>
 *
 * @param <T> Any derived class from {@link TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class PermutationRecombinationFunction<T extends TspGenome>
	extends AbstractRecombinationFunction<T> {

	/**
	 * Constructor, initializes instances with the given parameters.
	 *
	 * @param probability probability of recombination
	 * @param random      random instance
	 */
	public PermutationRecombinationFunction(double probability, Random random) {
		super(probability, random);
	}

	/**
	 * Constructor, initializes instances with probability of recombination
	 * specified by {@code probability} parameter and random is an
	 * instance of {@link java.util.Random}.
	 *
	 * @param probability probability of recombination
	 */
	public PermutationRecombinationFunction(double probability) {
		this(probability, new Random());
	}

	/**
	 * Default constructor, initializes instances with probability of recombination
	 * equals to {@code .75} and random generator is an instance of {@link java.util.Random}.
	 */
	public PermutationRecombinationFunction() {
		this(.75);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
		TspGenome[] offspring = new TspGenome[]{
			parent1.clone(),
			parent2.clone()
		};

		int n = parent1.getChromosome().size();
		int start = getRandom().nextInt(n - 1);
		int end = getRandom().nextInt(n);
		while (end <= start) {
			end = getRandom().nextInt(n);
		}

		for (int i = start; i < end; i++) {
			DefaultGene<Integer> gene0 = offspring[0].getChromosome().getGene(i);
			DefaultGene<Integer> gene1 = offspring[1].getChromosome().getGene(i);

			if (!gene0.equals(gene1)) {
				int indexOfGene0 = indexOf((T)offspring[0], gene0);
				int indexOfGene1 = indexOf((T)offspring[0], gene1);
				offspring[0].getChromosome().setGene(indexOfGene0, (DefaultGene)gene1.clone());
				offspring[0].getChromosome().setGene(indexOfGene1, (DefaultGene)gene0.clone());
				indexOfGene0 = indexOf((T)offspring[1], gene0);
				indexOfGene1 = indexOf((T)offspring[1], gene1);
				offspring[1].getChromosome().setGene(indexOfGene0, (DefaultGene)gene1.clone());
				offspring[1].getChromosome().setGene(indexOfGene1, (DefaultGene)gene0.clone());
			}
		}

		return (T[]) offspring;
	}

	private int indexOf(T genome, Gene gene) {
		final Iterator<DefaultGene<Integer>> iterator = genome.getChromosome().iterator();
		int index = 0;
		while (iterator.hasNext()) {
			if (iterator.next().equals(gene)){
				return index;
			}
			index++;
		}
		return  -1;
	}
}
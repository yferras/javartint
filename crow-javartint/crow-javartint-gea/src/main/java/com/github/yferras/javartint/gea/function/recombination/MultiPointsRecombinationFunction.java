package com.github.yferras.javartint.gea.function.recombination;

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

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * Specific recombination function, that performs the recombination process if
 * in each iteration the random probability is in the threshold. This function
 * can be applied to any type of genome.
 * <p>
 * While iterates through the genes of the genomes, if the generated random
 * number is less than or equal to the probability of recombination then the
 * genes of this position are exchanged.
 * </p>
 * <p>
 * Example shows two genomes, each one has six genes.<br />
 * <code>
 * [GA0,GA1,GA2,GA3,GA4,GA5] // Parent A<br />
 * [GB0,GB1,GB2,GB3,GB4,GB5] // Parent B<br />
 * [___,''',___,''',''',___] // Underline represents the position that will be exchanged<br />
 * [GB0,GA1,GB2,GA3,GA4,GA5] // Child A after recombination process<br />
 * [GA0,GB1,GA2,GB3,GB4,GB5] // Child B after recombination process<br />
 * </code>
 * </p>
 *
 * @param <T>
 *            any derived type from
 *            {@link com.github.yferras.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public class MultiPointsRecombinationFunction<T extends Genome<? extends Chromosome<? extends Gene<?>>>>
		extends AbstractRecombinationFunction<T> {

	/**
	 * <p>
	 * Constructor for MultiPointsRecombinationFunction.
	 * </p>
	 *
	 * @param probability
	 *            a double.
	 * @param random
	 *            a {@link java.util.Random} object.
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if any.
	 */
	public MultiPointsRecombinationFunction(double probability, Random random) {
		super(probability, random);
	}

	/**
	 * <p>
	 * Constructor for MultiPointsRecombinationFunction.
	 * </p>
	 *
	 * @param probability
	 *            a double.
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if any.
	 */
	public MultiPointsRecombinationFunction(double probability) {
		super(probability);
	}

	/**
	 * <p>
	 * Constructor for MultiPointsRecombinationFunction.
	 * </p>
	 */
	public MultiPointsRecombinationFunction() {
		super();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
		Genome[] offspring = new Genome[] { ((Genome) parent1).clone(), ((Genome) parent2).clone() };
		int numberOfChromosomes = parent1.size();
		for (int i = 0; i < numberOfChromosomes; i++) {
			int numberOfGenes = offspring[0].getChromosome(i).size();
			for (int j = 0; j < numberOfGenes; j++) {
				if (getRandom().nextDouble() <= getProbability()) {
					Gene aux = offspring[0].getChromosome(i).getGene(j);
					offspring[0].getChromosome(i).setGene(j, offspring[1].getChromosome(i).getGene(j));
					offspring[1].getChromosome(i).setGene(j, aux);
				}
			}
		}
		return (T[]) offspring;
	}
}

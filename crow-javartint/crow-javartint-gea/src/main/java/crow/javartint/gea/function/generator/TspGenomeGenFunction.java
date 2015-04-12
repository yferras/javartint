package crow.javartint.gea.function.generator;

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

import crow.javartint.gea.chromosome.DefaultChromosome;
import crow.javartint.gea.gene.ByteArrayGene;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is a genome generator function for create genomes for TSP (Traveller Sales Problem).
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class TspGenomeGenFunction
	extends AbstractGenomeGeneratorFunction<DefaultGenome<DefaultChromosome<DefaultGene<Integer>>>> {

	/**
	 * Initializes this instance.
	 *
	 * @param genomeSize    the number of chromosomes.
	 * @param numberOfGenes the number of genes.
	 */
	public TspGenomeGenFunction(int genomeSize, int numberOfGenes) {
		super(genomeSize, numberOfGenes, 1);
	}

	/**
	 * Initializes this instance. By default {@code genomeSize} is 1.
	 *
	 * @param numberOfGenes the number of genes.
	 */
	public TspGenomeGenFunction(int numberOfGenes) {
		super(numberOfGenes, 1);
	}

	/**
	 * Generates genomes for TSP
	 *
	 * @param genomeSize     the number of chromosomes.
	 * @param lengthsOfGenes the array that contains the length of each gene.
	 * @return an instance of {@link crow.javartint.gea.genome.DefaultGenome}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> generate(int genomeSize, int[] lengthsOfGenes) {
		DefaultChromosome<DefaultGene<Integer>>[] chromosomes = new DefaultChromosome[genomeSize];
		for (DefaultChromosome<DefaultGene<Integer>> chromosome : chromosomes) {
			List<DefaultGene<Integer>> cities = new ArrayList<>(lengthsOfGenes.length);
			for (int i = 0; i < lengthsOfGenes.length; i++) {
				cities.add(new DefaultGene<>(i));
			}
			Collections.shuffle(cities);
			chromosome.setGenes(cities.toArray(new DefaultGene[lengthsOfGenes.length]));
		}
		DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> genome = new DefaultGenome<>();
		genome.setChromosomes(chromosomes);
		return genome;
	}

}

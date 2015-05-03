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

import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.TspGenome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is a genome generator function for create genomes for TSP (Traveller Sales Problem).
 * The genomes generates by this, has only one chromosome.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class TspGenomeGenFunction
	extends AbstractGenomeGeneratorFunction<TspGenome> {

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
	 * @param genomeSize     the number of chromosomes, always is 1.
	 * @param lengthsOfGenes the array that contains the length of each gene.
	 * @return an instance of {@link crow.javartint.gea.genome.DefaultGenome}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected TspGenome generate(int genomeSize, int[] lengthsOfGenes) {
		TspGenome genome = new TspGenome();
		List<DefaultGene<Integer>> cities = new ArrayList<>(lengthsOfGenes.length);
		for (int j = 0; j < lengthsOfGenes.length; j++) {
			cities.add(new DefaultGene<>(j));
		}
		Collections.shuffle(cities);
		genome.getChromosome().setGenes(cities.toArray(new DefaultGene[lengthsOfGenes.length]));
		return genome;
	}

}

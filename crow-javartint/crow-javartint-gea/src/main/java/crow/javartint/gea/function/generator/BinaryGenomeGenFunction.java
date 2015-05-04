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
import crow.javartint.gea.genome.BinaryGenome;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Random;

/**
 * This class is a binary genome generator function.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class BinaryGenomeGenFunction
	extends AbstractGenomeGeneratorFunction<BinaryGenome> {

	/**
	 * Initializes the number of genes and the length of each gene.
	 *
	 * @param genomeSize    the number of chromosomes.
	 * @param numberOfGenes the number of genes.
	 * @param lengthOfGene  the length of each gene.
	 */
	public BinaryGenomeGenFunction(int genomeSize, int numberOfGenes, int lengthOfGene) {
		super(genomeSize, numberOfGenes, lengthOfGene);
	}

	/**
	 * Initializes the number of genes and the length of each gene. By default {@code genomeSize} is 1.
	 *
	 * @param numberOfGenes the number of genes.
	 * @param lengthOfGene  the length of each gene.
	 */
	public BinaryGenomeGenFunction(int numberOfGenes, int lengthOfGene) {
		super(numberOfGenes, lengthOfGene);
	}

	/**
	 * Initializes the lengths of genes. The {@code lengthsOfGenes.length}
	 * if the number of genes and the value in each position is the size of each gene.
	 *
	 * @param genomeSize     the number of chromosomes.
	 * @param lengthsOfGenes array that contains the length of genes.
	 */
	public BinaryGenomeGenFunction(int genomeSize, int[] lengthsOfGenes) {
		super(genomeSize, lengthsOfGenes);
	}

	/**
	 * Initializes the lengths of genes. The {@code lengthsOfGenes.length}
	 * if the number of genes and the value in each position is the size of each gene.
	 * By default {@code genomeSize} is 1.
	 *
	 * @param lengthsOfGenes array that contains the length of genes.
	 */
	public BinaryGenomeGenFunction(int[] lengthsOfGenes) {
		super(lengthsOfGenes);
	}

	/**
	 * Generates binary genomes.
	 *
	 * @param genomeSize     the number of chromosomes.
	 * @param lengthsOfGenes the array that contains the length of each gene.
	 * @return an instance of {@link crow.javartint.gea.genome.DefaultGenome}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected BinaryGenome generate(int genomeSize, int[] lengthsOfGenes) {
		Random random = new Random();
		BinaryGenome genome = new BinaryGenome();
		for (int i = 0; i < genomeSize; i++) {
			ByteArrayGene[] genes = new ByteArrayGene[lengthsOfGenes.length];
			for (int j = 0; j < lengthsOfGenes.length; j++) {
				Byte[] data = new Byte[lengthsOfGenes[j]];
				for (int k = 0; k < data.length; k++) {
					data[k] = (byte) random.nextInt(2);
				}
				genes[j] = new ByteArrayGene(data);
			}
			genome.addChromosome(new DefaultChromosome<ByteArrayGene>());
			genome.getChromosome(i).setGenes(genes);
		}
		return genome;
	}

}

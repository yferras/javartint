package com.github.yferras.javartint.gea.function.generator;

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


import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * This class implements the interface {@link GeneratorFunction}.
 * This class can be derived to create a functions to generate genomes.
 *
 * @param <T> Any derived class from {@link Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public abstract class AbstractGenomeGeneratorFunction<T extends Genome<? extends Chromosome<? extends Gene<?>>>>
	implements GeneratorFunction<T> {

	private final int[] lengthsOfGenes;
	private final int genomeSize;

	/**
	 * Initializes the number of genes and the length of each gene.
	 *
	 * @param genomeSize    the number of chromosomes.
	 * @param numberOfGenes the number of genes.
	 * @param lengthOfGene  the length of each gene.
	 */
	public AbstractGenomeGeneratorFunction(int genomeSize, int numberOfGenes, int lengthOfGene) {
		this.genomeSize = genomeSize;
		this.lengthsOfGenes = new int[numberOfGenes];
		for (int i = 0; i < lengthsOfGenes.length; i++) {
			lengthsOfGenes[i] = lengthOfGene;
		}
	}

	/**
	 * Initializes the number of genes and the length of each gene. By default {@code genomeSize} is 1.
	 *
	 * @param numberOfGenes the number of genes.
	 * @param lengthOfGene  the length of each gene.
	 */
	public AbstractGenomeGeneratorFunction(int numberOfGenes, int lengthOfGene) {
		this(1, numberOfGenes, lengthOfGene);
	}

	/**
	 * Initializes the lengths of genes. The {@code lengthsOfGenes.length}
	 * if the number of genes and the value in each position is the size of each gene.
	 *
	 * @param genomeSize     the number of chromosomes.
	 * @param lengthsOfGenes array that contains the length of genes.
	 */
	public AbstractGenomeGeneratorFunction(int genomeSize, int[] lengthsOfGenes) {
		validate(lengthsOfGenes);
		this.genomeSize = genomeSize;
		this.lengthsOfGenes = lengthsOfGenes;
	}

	/**
	 * Initializes the lengths of genes. The {@code lengthsOfGenes.length}
	 * if the number of genes and the value in each position is the size of each gene.
	 * By default {@code genomeSize} is 1.
	 *
	 * @param lengthsOfGenes array that contains the length of genes.
	 */
	public AbstractGenomeGeneratorFunction(int[] lengthsOfGenes) {
		this(1, lengthsOfGenes);
	}

	/**
	 * Validates the input params.
	 *
	 * @param params params to validate.
	 * @throws java.lang.IllegalArgumentException if argument is {@code null}
	 */
	protected void validate(int[] params) {
		if (params == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This method must be implemented to generate a genome.
	 *
	 * @param genomeSize     the number of chromosomes.
	 * @param lengthsOfGenes the array that contains the length of each gene.
	 * @return an instance of {@link Genome}
	 */
	protected abstract T generate(final int genomeSize, final int[] lengthsOfGenes);

	@Override
	public T evaluate(Void... params) {
		return generate(genomeSize, lengthsOfGenes);
	}

}
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

import crow.javartint.core.util.Range;
import crow.javartint.gea.chromosome.DefaultChromosome;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Random;


/**
 * This class is a genome generator function for create genomes with genes in defined ranges.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class RangeGenomeGenFunction
	extends AbstractGenomeGeneratorFunction<DefaultGenome<DefaultChromosome<DefaultGene<Double>>>> {

	private final int precision;
	private Range<Double>[] ranges;

	/**
	 * Initializes this instance.
	 *
	 * @param genomeSize the number of chromosomes.
	 * @param precision  decimal precision.
	 * @param ranges     Array of ranges. The length of this array is the size of genome, and each instance of {@link Range}
	 *                   is used to generate a random value inside range.
	 */
	public RangeGenomeGenFunction(int genomeSize, int precision, Range<Double>... ranges) {
		super(genomeSize, ranges.length, 1);
		this.precision = precision;
		this.ranges = ranges;
	}

	/**
	 * Initializes this instance. By default {@code genomeSize} is 1.
	 *
	 * @param precision decimal precision.
	 * @param ranges    Array of ranges. The length of this array is the size of genome, and each instance of {@link Range}
	 *                  is used to generate a random value inside range.
	 */
	public RangeGenomeGenFunction(int precision, Range<Double>... ranges) {
		super(1, ranges.length, 1);
		this.precision = precision;
		this.ranges = ranges;
	}

	/**
	 * Generates a genome with default set of genes, each of these genes have a double value.
	 *
	 * @param genomeSize     the number of chromosomes.
	 * @param lengthsOfGenes the array that contains the length of each gene.
	 * @return an instance of {@link crow.javartint.gea.genome.DefaultGenome}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected DefaultGenome<DefaultChromosome<DefaultGene<Double>>> generate(int genomeSize, int[] lengthsOfGenes) {
		Random random = new Random();
		DefaultGenome<DefaultChromosome<DefaultGene<Double>>> genome = new DefaultGenome<>();
		for (int i = 0; i < genomeSize; i++) {
			DefaultGene<Double>[] genes = new DefaultGene[lengthsOfGenes.length];
			for (int j = 0; j < lengthsOfGenes.length; j++) {
				Range<Double> range = ranges[j];
				double v = round(random.nextDouble() * (range.getMax() - range.getMin()) + range.getMin());
				genes[j] = new DefaultGene<>(v);
			}
			genome.addChromosome(new DefaultChromosome<DefaultGene<Double>>());
			genome.getChromosome(i).setGenes(genes);
		}
		return genome;
	}

	private double round(double value) {
		double p = Math.pow(10.0, precision);
		value *= p;
		return Math.round(value) / p;
	}

}

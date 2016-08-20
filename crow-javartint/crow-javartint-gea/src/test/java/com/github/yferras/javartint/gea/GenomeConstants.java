package com.github.yferras.javartint.gea;

import java.util.Random;

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

import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class GenomeConstants {
	public static final Random RANDOM_GENERATOR_1 = new Random() {
		@Override
		public int nextInt(int n) {
			return n / 2 - 1;
		}

		@Override
		public double nextDouble() {
			return .5;
		}
	};

	public static final Random RANDOM_GENERATOR_2 = new Random() {
		private int aux = 0;

		@Override
		public int nextInt(int n) {
			if (aux == 0) {
				aux = 1;
				return n / 3;
			} else {
				aux = 0;
				return 2 * n / 3;
			}
		}

		@Override
		public double nextDouble() {
			return .5;
		}
	};

	/**
	 * Generates alternates constant probabilities
	 */
	public static final Random RANDOM_GENERATOR_3 = new Random() {
		private int aux = -1;
		private Integer n;

		/**
		 * In this case the method is tricked to set a different behaviour from
		 * the original one
		 * 
		 * @param n
		 *            the bound on the random number to be returned. Must be
		 *            positive.
		 * @return 0
		 */
		@Override
		public int nextInt(int n) {
			if (this.n == null) {
				this.n = n;
			}
			return 0;
		}

		/**
		 * Generates alternates constant probabilities alternates between 0.5
		 * and 0.95 values, begins by 0.5
		 * 
		 * @return the probability
		 */
		@Override
		public double nextDouble() {
			if (aux > n) {
				aux = -1;
				n = null;
			} else {
				aux++;
			}
			return (aux % 2 == 0) ? .5 : .95;
		}

	};

	/**
	 * Used in BinaryMutationFunctionIT
	 */
	public static final Random RANDOM_GENERATOR_4 = new Random() {

		@Override
		public int nextInt(int n) {
			return 0;
		}

		@Override
		public double nextDouble() {
			return .025;
		}
	};

	/**
	 * Used in BinaryMutationFunctionIT
	 */
	public static final Random RANDOM_GENERATOR_5 = new Random() {

		private final double[] pos = { .025, .1, .1, .2, .3, .4, .5, .6, .1, .2, .3, .4, .01, .1, .2, .3, .4, .5, .6,
				.1, .02, .3, .04 };
		private int index = 0;

		@Override
		public int nextInt(int n) {
			return n / 2;
		}

		@Override
		public double nextDouble() {
			if (index >= pos.length)
				index = 0;
			return pos[index++];
		}
	};

	public static final DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> GENOME_1 = new DefaultGenome<>();
	public static final DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> GENOME_2 = new DefaultGenome<>();
	public static final DefaultGenome[] GENOMES = new DefaultGenome[2];
	public static final int CHROMOSOME_SIZE = 11;

	static {

		GENOME_1.addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
		GENOME_2.addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
		for (int i = 0; i < CHROMOSOME_SIZE; i++) {
			GENOME_1.getChromosome(0).addGene(new DefaultGene<>(i));
			GENOME_2.getChromosome(0).addGene(new DefaultGene<>(CHROMOSOME_SIZE - i));
		}
		GENOMES[0] = GENOME_1;
		GENOMES[1] = GENOME_2;
	}
}

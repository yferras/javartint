package com.github.yferras.javartint.gea.function.mutation.binary;

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

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.function.mutation.AbstractMutationFunction;
import com.github.yferras.javartint.gea.gene.ByteArrayGene;
import com.github.yferras.javartint.gea.genome.BinaryGenome;

import java.util.Random;

/**
 * Class that represents an binary mutation function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.BinaryGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class BinaryMutationFunction<T extends BinaryGenome>
	extends AbstractMutationFunction<T> {

	/**
	 * <p>Constructor for BinaryMutationFunction.</p>
	 *
	 * @param probability a double.
	 * @param random a {@link java.util.Random} object.
	 */
	public BinaryMutationFunction(double probability, Random random) {
		super(probability, random);
	}

	/**
	 * <p>Constructor for BinaryMutationFunction.</p>
	 *
	 * @param probability a double.
	 */
	public BinaryMutationFunction(double probability) {
		super(probability);
	}

	/**
	 * <p>Constructor for BinaryMutationFunction.</p>
	 */
	public BinaryMutationFunction() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	protected T mutate(T subject) {
		for (Chromosome<ByteArrayGene> chromosome : subject) {
			for (ByteArrayGene gene : chromosome) {
				int index = getRandom().nextInt(gene.length());
				byte val = gene.getAllele(index);
				gene.setAllele(index, (byte) (val == 0 ? 1 : 0));
			}
		}
		return subject;
	}
}

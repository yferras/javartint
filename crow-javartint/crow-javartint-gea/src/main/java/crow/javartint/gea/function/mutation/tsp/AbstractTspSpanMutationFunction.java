package crow.javartint.gea.function.mutation.tsp;

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
import crow.javartint.gea.function.mutation.AbstractMutationFunction;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Random;

/**
 * <p>
 * Mutation function in TSP a bit more specialized.
 * </p>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractTspSpanMutationFunction extends AbstractMutationFunction<DefaultGenome<DefaultChromosome<DefaultGene<Integer>>>> {

	private int minSpanSize;

	/**
	 * Constructor, initializes instances with the given parameters.
	 *
	 * @param probability probability of mutation
	 * @param random      random instance
	 * @param minSpanSize min span size
	 */
	public AbstractTspSpanMutationFunction(double probability, Random random, int minSpanSize) {
		super(probability, random);
		this.minSpanSize = minSpanSize;
	}

	/**
	 * Constructor, initializes instances with the given parameters.
	 * By default it uses as random generator an instance of {@link java.util.Random}.
	 *
	 * @param probability probability of mutation
	 * @param minSpanSize min span size
	 */
	public AbstractTspSpanMutationFunction(double probability, int minSpanSize) {
		this(probability, new Random(), minSpanSize);
	}

	/**
	 * Constructor, initializes instances with the given parameters.
	 * By default it uses as random generator an instance of {@link java.util.Random},
	 * and <code>probability = 0.05</code>.
	 *
	 * @param minSpanSize min span size
	 */
	public AbstractTspSpanMutationFunction(int minSpanSize) {
		this(.05, minSpanSize);
	}

	/**
	 * Default constructor. By default it uses as random generator an instance of {@link java.util.Random},
	 * <code>probability = 0.05</code> and <code>minSpanSize = 2</code>.
	 */
	public AbstractTspSpanMutationFunction() {
		this(2);
	}

	/**
	 * Gets the min span of section.
	 *
	 * @return the size of span
	 */
	public int getMinSpanSize() {
		return minSpanSize;
	}
}

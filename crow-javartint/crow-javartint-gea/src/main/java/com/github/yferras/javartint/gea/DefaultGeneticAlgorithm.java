package com.github.yferras.javartint.gea;

import java.util.Properties;

/*
 * #%L
 * Crow JavArtInt GEA
 *
 %%
 Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 *
 %%
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as
 published by the Free Software Foundation, either version 3 of the
 License, or (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public
 License along with this program.  If not, see
 <http://www.gnu.org/licenses/gpl-3.0.html>.
 #L%
 */

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * Default implementation of a GA.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.genome.Genome}
 * @param <D>
 *            Type of decoded value.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class DefaultGeneticAlgorithm<T extends Genome<? extends Chromosome<? extends Gene<?>>>, D>
		extends AbstractGeneticAlgorithm<T, D> {

	/**
	 * 
	 * @author Eng. Ferrás Cecilio, Yeinier.
	 *
	 * @param <T>
	 *            Any class that implements {@link Genome}
	 * @param <D>
	 *            Type of decoded value.
	 */
	public static final class Builder<T extends Genome<? extends Chromosome<? extends Gene<?>>>, D>
			extends AbstractGeneticAlgorithm.Builder<DefaultGeneticAlgorithm<T, D>, T, D> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected DefaultGeneticAlgorithm<T, D> buildObject() {
			return new DefaultGeneticAlgorithm<>(getProperties());
		}
	}

	private DefaultGeneticAlgorithm(Properties properties) {
		super(properties);
	}
}

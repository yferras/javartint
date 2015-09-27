package com.github.yferras.javartint.gea;

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


import com.github.yferras.javartint.gea.function.mutation.MutationFunction;
import com.github.yferras.javartint.gea.function.recombination.RecombinationFunction;
import com.github.yferras.javartint.gea.function.selection.RandomSelectionFunction;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.function.decoder.DecoderFunction;
import com.github.yferras.javartint.gea.function.generator.GeneratorFunction;
import com.github.yferras.javartint.gea.function.selection.SelectionFunction;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * Default implementation of a GA.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}
 * @param <D> Type of decoded value.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class DefaultGeneticAlgorithm<T extends Genome<? extends Chromosome<? extends Gene<?>>>, D>
	extends AbstractGeneticAlgorithm<T, D> {
	/**
	 * Initializes this class.
	 * * By default selection function for parents is an instance of
	 * {@link com.github.yferras.javartint.gea.function.selection.RandomSelectionFunction}.
	 *
	 * @param populationSize        the population limit
	 * @param optimize              the optimization way
	 * @param decoder               function to decode the genome
	 * @param targetFunction        function to optimize
	 * @param generator             function to generate initial population of genomes
	 * @param recombinationFunction function to crossing process
	 * @param mutationFunction      function to mutation process
	 * @param selectionFunction     function to selection process
	 */
	public DefaultGeneticAlgorithm(int populationSize, Optimize optimize,
	                               DecoderFunction<D, T> decoder,
	                               Function<Double, D> targetFunction,
	                               GeneratorFunction<T> generator,
	                               RecombinationFunction<T> recombinationFunction,
	                               MutationFunction<T> mutationFunction,
	                               SelectionFunction<T> selectionFunction) {
		super(populationSize, optimize, decoder, targetFunction, generator,
			recombinationFunction, mutationFunction, selectionFunction);
	}

	/**
	 * Initializes this class.
	 * * By default selection function for parents is an instance of
	 * {@link com.github.yferras.javartint.gea.function.selection.RandomSelectionFunction}, and
	 * selection function for new generation is <code>null</code>.
	 *
	 * @param populationSize        the population limit
	 * @param optimize              the optimization way
	 * @param decoder               function to decode the genome
	 * @param targetFunction        function to optimize
	 * @param generator             function to generate initial population of genomes
	 * @param recombinationFunction function to crossing process
	 * @param mutationFunction      function to mutation process
	 */
	public DefaultGeneticAlgorithm(int populationSize, Optimize optimize,
	                               DecoderFunction<D, T> decoder,
	                               Function<Double, D> targetFunction,
	                               GeneratorFunction<T> generator,
	                               RecombinationFunction<T> recombinationFunction,
	                               MutationFunction<T> mutationFunction) {
		super(populationSize, optimize, decoder, targetFunction, generator,
			recombinationFunction, mutationFunction);
	}
}

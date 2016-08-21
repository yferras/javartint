package com.github.yferras.javartint.gea;

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

import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.function.decoder.DecoderFunction;
import com.github.yferras.javartint.gea.function.generator.GeneratorFunction;
import com.github.yferras.javartint.gea.function.mutation.MutationFunction;
import com.github.yferras.javartint.gea.function.recombination.RecombinationFunction;
import com.github.yferras.javartint.gea.function.selection.SelectionFunction;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * <p>
 * Interface to define genetics algorithm builders.
 * </p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 * @since 2.0.0
 */
public interface GeneticAlgorithmBuilder<A extends AbstractEvolutionaryAlgorithm<T, D>, T extends Genome<? extends Chromosome<? extends Gene<?>>>, D>
		extends EvolutionaryAlgorithmBuilder<A, T, D> {

	/** {@inheritDoc} */
	@Override
	GeneticAlgorithmBuilder<A, T, D> setDecoder(DecoderFunction<D, T> decoder);

	/** {@inheritDoc} */
	@Override
	GeneticAlgorithmBuilder<A, T, D> setGeneratorFunction(GeneratorFunction<T> generatorFunction);

	/**
	 * <p>
	 * Sets the mutation function.
	 * </p>
	 *
	 * @param mutationFunction
	 *            a
	 *            {@link com.github.yferras.javartint.gea.function.mutation.MutationFunction}
	 *            object.
	 * @return this instance of builder.
	 */
	GeneticAlgorithmBuilder<A, T, D> setMutationFunction(MutationFunction<T> mutationFunction);

	/** {@inheritDoc} */
	@Override
	GeneticAlgorithmBuilder<A, T, D> setOptimize(Optimize optimize);

	/** {@inheritDoc} */
	@Override
	GeneticAlgorithmBuilder<A, T, D> setPopulationSize(int size);

	/**
	 * <p>
	 * Sets the recombination function.
	 * </p>
	 *
	 * @param recombinationFunction
	 *            a
	 *            {@link com.github.yferras.javartint.gea.function.recombination.RecombinationFunction}
	 *            object.
	 * @return this instance of builder.
	 */
	GeneticAlgorithmBuilder<A, T, D> setRecombinationFunction(RecombinationFunction<T> recombinationFunction);

	/**
	 * <p>
	 * Sets the selection function.
	 * </p>
	 *
	 * @param selectionFunction
	 *            a
	 *            {@link com.github.yferras.javartint.gea.function.selection.SelectionFunction}
	 *            object.
	 * @return this instance of builder.
	 */
	GeneticAlgorithmBuilder<A, T, D> setSelectionFunction(SelectionFunction<T> selectionFunction);

	/**
	 * <p>
	 * Sets the selection function for parents.
	 * </p>
	 *
	 * @param selectionFunction
	 *            a
	 *            {@link com.github.yferras.javartint.gea.function.selection.SelectionFunction}
	 *            object.
	 * @return this instance of builder.
	 */
	GeneticAlgorithmBuilder<A, T, D> setSelectionFunctionForParents(SelectionFunction<T> selectionFunction);

	/** {@inheritDoc} */
	@Override
	GeneticAlgorithmBuilder<A, T, D> setTargetFunction(Function<Double, D> targetFunction);
}

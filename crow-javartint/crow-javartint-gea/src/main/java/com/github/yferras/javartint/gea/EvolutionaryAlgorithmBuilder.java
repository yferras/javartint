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

import com.github.yferras.javartint.core.AlgorithmBuilder;
import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.function.decoder.DecoderFunction;
import com.github.yferras.javartint.gea.function.generator.GeneratorFunction;

/**
 * <p>
 * Interface to define evolutionary algorithm builders.
 * </p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 1.0
 * @since 2.0.0
 */
public interface EvolutionaryAlgorithmBuilder<A extends AbstractEvolutionaryAlgorithm<T, D>, T extends Individual, D>
		extends AlgorithmBuilder<A> {
	/**
	 * <p>
	 * Sets the decoder function.
	 * </p>
	 *
	 * @param decoder
	 *            a
	 *            {@link com.github.yferras.javartint.gea.function.decoder.DecoderFunction}
	 *            object.
	 * @return this instance of builder.
	 */
	EvolutionaryAlgorithmBuilder<A, T, D> setDecoder(DecoderFunction<D, T> decoder);

	/**
	 * <p>
	 * Sets the generator function.
	 * </p>
	 *
	 * @param generatorFunction
	 *            a
	 *            {@link com.github.yferras.javartint.gea.function.generator.GeneratorFunction}
	 *            object.
	 * @return this instance of builder.
	 */
	EvolutionaryAlgorithmBuilder<A, T, D> setGeneratorFunction(GeneratorFunction<T> generatorFunction);

	/**
	 * <p>
	 * Sets the optimize direction.
	 * </p>
	 *
	 * @param optimize
	 *            a {@link com.github.yferras.javartint.core.util.Optimize}
	 *            object.
	 * @return this instance of builder.
	 */
	EvolutionaryAlgorithmBuilder<A, T, D> setOptimize(Optimize optimize);

	/**
	 * <p>
	 * Sets the population size.
	 * </p>
	 *
	 * @param size
	 *            population size.
	 * @return this instance of builder.
	 */
	EvolutionaryAlgorithmBuilder<A, T, D> setPopulationSize(int size);

	/**
	 * <p>
	 * Sets the target function.
	 * </p>
	 *
	 * @param targetFunction
	 *            a {@link com.github.yferras.javartint.core.function.Function}
	 *            object.
	 * @return this instance of builder.
	 */
	EvolutionaryAlgorithmBuilder<A, T, D> setTargetFunction(Function<Double, D> targetFunction);
}

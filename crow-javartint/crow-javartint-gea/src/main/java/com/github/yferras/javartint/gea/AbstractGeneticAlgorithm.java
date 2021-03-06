package com.github.yferras.javartint.gea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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

import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.AlgorithmRuntimeException;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.function.decoder.DecoderFunction;
import com.github.yferras.javartint.gea.function.generator.GeneratorFunction;
import com.github.yferras.javartint.gea.function.mutation.MutationFunction;
import com.github.yferras.javartint.gea.function.recombination.RecombinationFunction;
import com.github.yferras.javartint.gea.function.selection.RandomSelectionFunction;
import com.github.yferras.javartint.gea.function.selection.SelectionFunction;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;
import com.github.yferras.javartint.gea.util.GeaConfigConstants;

/**
 * Abstract class that provides a sets of functionalities to subclassing genetic
 * algorithms
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.genome.Genome}
 * @param <D>
 *            Type of decoded value.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 1.2
 */
public abstract class AbstractGeneticAlgorithm<T extends Genome<? extends Chromosome<? extends Gene<?>>>, D>
		extends AbstractEvolutionaryAlgorithm<T, D> {

	protected abstract static class Builder<A extends AbstractGeneticAlgorithm<T, D>, T extends Genome<? extends Chromosome<? extends Gene<?>>>, D>
			extends AbstractEvolutionaryAlgorithm.Builder<A, T, D> implements GeneticAlgorithmBuilder<A, T, D> {

		protected static final String[] REQUIRED_PROPERTY_KEYS = { GeaConfigConstants.DECODER_FUNCTION,
				GeaConfigConstants.GENERATOR_FUNCTION, GeaConfigConstants.OPTIMIZE, GeaConfigConstants.POPULATION_SIZE,
				GeaConfigConstants.TARGET_FUNCTION, GeaConfigConstants.MUTATION_FUNCTION,
				GeaConfigConstants.SELECTION_FUNCTION, GeaConfigConstants.RECOMBINATION_FUNCTION };

		protected Builder() {
			super(REQUIRED_PROPERTY_KEYS);
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setDecoder(DecoderFunction<D, T> decoder) {
			return (GeneticAlgorithmBuilder<A, T, D>) super.setDecoder(decoder);
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setGeneratorFunction(GeneratorFunction<T> generatorFunction) {
			return (GeneticAlgorithmBuilder<A, T, D>) super.setGeneratorFunction(generatorFunction);
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setMutationFunction(MutationFunction<T> mutationFunction) {
			getProperties().put(GeaConfigConstants.MUTATION_FUNCTION, mutationFunction);
			return this;
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setOptimize(Optimize optimize) {
			return (GeneticAlgorithmBuilder<A, T, D>) super.setOptimize(optimize);
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setPopulationSize(int size) {
			return (GeneticAlgorithmBuilder<A, T, D>) super.setPopulationSize(size);
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setRecombinationFunction(
				RecombinationFunction<T> recombinationFunction) {
			getProperties().put(GeaConfigConstants.RECOMBINATION_FUNCTION, recombinationFunction);
			return this;
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setSelectionFunction(SelectionFunction<T> selectionFunction) {
			getProperties().put(GeaConfigConstants.SELECTION_FUNCTION, selectionFunction);
			return this;
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setSelectionFunctionForParents(SelectionFunction<T> selectionFunction) {
			getProperties().put(GeaConfigConstants.SELECTION_FUNCTION_FOR_PARENTS, selectionFunction);
			return this;
		}

		@Override
		public GeneticAlgorithmBuilder<A, T, D> setTargetFunction(Function<Double, D> targetFunction) {
			return (GeneticAlgorithmBuilder<A, T, D>) super.setTargetFunction(targetFunction);
		}

	}

	private final MutationFunction<T> mutationFunction;

	private final RecombinationFunction<T> recombinationFunction;

	private final SelectionFunction<T> selectionFunction;

	private SelectionFunction<T> selectionFunctionToParents;

	/**
	 * <p>
	 * Constructor for AbstractGeneticAlgorithm.
	 * </p>
	 *
	 * @param properties
	 *            a {@link java.util.Properties} object.
	 */
	@SuppressWarnings("unchecked")
	protected AbstractGeneticAlgorithm(Properties properties) {
		super(properties);
		recombinationFunction = (RecombinationFunction<T>) properties.get(GeaConfigConstants.RECOMBINATION_FUNCTION);
		mutationFunction = (MutationFunction<T>) properties.get(GeaConfigConstants.MUTATION_FUNCTION);
		selectionFunction = (SelectionFunction<T>) properties.get(GeaConfigConstants.SELECTION_FUNCTION);
		selectionFunctionToParents = (SelectionFunction<T>) properties
				.get(GeaConfigConstants.SELECTION_FUNCTION_FOR_PARENTS);
		if (selectionFunctionToParents == null) {
			selectionFunctionToParents = new RandomSelectionFunction<>();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * <p>
	 * In this method a new population is created from the selection and
	 * crossing of individuals of the current population. After completing the
	 * number of individuals, the current population is replaced by the new. The
	 * methods are executed in this order:
	 * </p>
	 * <ol>
	 * <li>If {@link #getSelectionFunction()} is not {@code null}, selects
	 * (according selection method) the configured number of genomes to new
	 * population, otherwise continues.</li>
	 * <li>While the size o new population will be less than the size of current
	 * population.</li>
	 * <ol>
	 * <li>Selects the two parents.</li>
	 * <li>Crossing the pre-selected parents.</li>
	 * <li>Mutates the offspring.</li>
	 * <li>If {@link #getFilter()} is distinct of {@code null}, the new
	 * offspring will be filtered, and only the successfully filtered genomes
	 * will be added to the new generation, otherwise the offspring will be
	 * added to the new population.</li>
	 * </ol>
	 * <li>Old population is replaced by new.</li>
	 * </ol>
	 */
	@Override
	protected void epoch() throws Exception {
		Set<T> newGeneration = new LinkedHashSet<>(getPopulationSize());
		if (getSelectionFunction() != null) {
			newGeneration.addAll(getSelectionFunction().evaluate(getPopulation()));
		}
		while (newGeneration.size() < getPopulationSize()) {
			List<T> parents = getSelectionFunctionToParents().evaluate(getPopulation());
			@SuppressWarnings("unchecked")
			T[] offspring = getRecombinationFunction().evaluate(parents.get(0), parents.get(1));
			for (T genome : offspring) {
				getMutationFunction().evaluate(genome);
			}
			fillNewGeneration(newGeneration, offspring);
		}
		setPopulation(new ArrayList<>(newGeneration));
	}

	private void fillNewGeneration(Set<T> newGeneration, T[] offspring) {
		if (getFilter() != null) {
			for (T genome : offspring) {
				if (getFilter().accept(genome)) {
					newGeneration.add(genome);
				}
			}
		} else {
			Collections.addAll(newGeneration, offspring);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * <p>
	 * This method is the responsible for create and evolve of the population.
	 * </p>
	 */
	@Override
	public void evolve() throws Exception {
		createStartPopulation();
		while (isRunning()) {
			updateFitnessScores();
			if (testConstraint()) {
				stop();
				break;
			}
			epoch();
			increaseGenerations();
		}
	}

	/**
	 * Gets the instance of mutation function.
	 *
	 * @return the mutation function.
	 */
	public MutationFunction<T> getMutationFunction() {
		return mutationFunction;
	}

	/**
	 * Gets the instance of recombination function.
	 *
	 * @return the recombination function.
	 */
	public RecombinationFunction<T> getRecombinationFunction() {
		return recombinationFunction;
	}

	/**
	 * Gets the instance of selection function.
	 *
	 * @return the selection function.
	 */
	public SelectionFunction<T> getSelectionFunction() {
		return selectionFunction;
	}

	/**
	 * Gets the selection function used to select the parents and use them in
	 * the crossing process. By default is an instance of
	 * {@link com.github.yferras.javartint.gea.function.selection.RandomSelectionFunction}.
	 *
	 * @return an instance of
	 *         {@link com.github.yferras.javartint.gea.function.selection.SelectionFunction}
	 */
	public SelectionFunction<T> getSelectionFunctionToParents() {
		return selectionFunctionToParents;
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * This method sets {@code true} to {@code running} attribute, and calls
	 * {@link #evolve()} method. Implemented from Runnable interface.
	 */
	@Override
	public void run() {
		try {
			beginAlgorithm();
			evolve();
			fireAlgorithmFinishedEvent();
		} catch (Exception e) {
			throw new AlgorithmRuntimeException(e);
		}
	}

	/**
	 * Sets the selection function used to select the parents in the crossing
	 * process.
	 *
	 * @param selectionFunctionToParents
	 *            selection function instance.
	 */
	public void setSelectionFunctionToParents(SelectionFunction<T> selectionFunctionToParents) {
		this.selectionFunctionToParents = selectionFunctionToParents;
	}
}

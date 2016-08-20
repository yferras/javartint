package com.github.yferras.javartint.gea;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import com.github.yferras.javartint.core.AbstractAlgorithm;
import com.github.yferras.javartint.core.AbstractAlgorithmBuilder;
import com.github.yferras.javartint.core.IterativeAlgorithm;
import com.github.yferras.javartint.core.OptimizationAlgorithm;
import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.function.decoder.DecoderFunction;
import com.github.yferras.javartint.gea.function.generator.GeneratorFunction;
import com.github.yferras.javartint.gea.util.GeaConfigConstants;
import com.github.yferras.javartint.gea.util.IndividualFilter;

/**
 * Abstract class that provides a sets of functionalities to subclassing
 * evolutionary algorithms
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.Individual}
 * @param <D>
 *            Type of decoded value.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 1.2
 */
public abstract class AbstractEvolutionaryAlgorithm<T extends Individual, D> extends AbstractAlgorithm<T>
		implements OptimizationAlgorithm<T>, IterativeAlgorithm<T> {

	protected abstract static class Builder<A extends AbstractEvolutionaryAlgorithm<T, D>, T extends Individual, D>
			extends AbstractAlgorithmBuilder<A> implements EvolutionaryAlgorithmBuilder<A, T, D> {

		public static final String[] REQUIRED_PROPERTY_KEYS = { GeaConfigConstants.DECODER_FUNCTION,
				GeaConfigConstants.GENERATOR_FUNCTION, GeaConfigConstants.OPTIMIZE, GeaConfigConstants.POPULATION_SIZE,
				GeaConfigConstants.TARGET_FUNCTION };

		protected Builder() {
			this(REQUIRED_PROPERTY_KEYS);
		}

		protected Builder(String... requiredPropertyKeys) {
			super(requiredPropertyKeys);
		}

		@Override
		public EvolutionaryAlgorithmBuilder<A, T, D> setDecoder(DecoderFunction<D, T> decoder) {
			getProperties().put(GeaConfigConstants.DECODER_FUNCTION, decoder);
			return this;
		}

		@Override
		public EvolutionaryAlgorithmBuilder<A, T, D> setGeneratorFunction(GeneratorFunction<T> generatorFunction) {
			getProperties().put(GeaConfigConstants.GENERATOR_FUNCTION, generatorFunction);
			return this;
		}

		@Override
		public EvolutionaryAlgorithmBuilder<A, T, D> setOptimize(Optimize optimize) {
			getProperties().put(GeaConfigConstants.OPTIMIZE, optimize);
			return this;
		}

		@Override
		public EvolutionaryAlgorithmBuilder<A, T, D> setPopulationSize(int size) {
			getProperties().put(GeaConfigConstants.POPULATION_SIZE, size);
			return this;
		}

		@Override
		public EvolutionaryAlgorithmBuilder<A, T, D> setTargetFunction(Function<Double, D> targetFunction) {
			getProperties().put(GeaConfigConstants.TARGET_FUNCTION, targetFunction);
			return this;
		}
	}
	private double bestFitnessScore;
	private final DecoderFunction<D, T> decoder;
	private IndividualFilter<T> filter;
	private long generations;
	private final GeneratorFunction<T> generator;
	private Optimize optimize;
	private List<T> population;
	private int populationSize;

	private final Function<Double, D> targetFunction;

	/**
	 * <p>
	 * Constructor for AbstractEvolutionaryAlgorithm.
	 * </p>
	 *
	 * @param properties
	 *            a {@link java.util.Properties} object.
	 */
	@SuppressWarnings("unchecked")
	protected AbstractEvolutionaryAlgorithm(Properties properties) {
		populationSize = (int) properties.get(GeaConfigConstants.POPULATION_SIZE);
		decoder = (DecoderFunction<D, T>) properties.get(GeaConfigConstants.DECODER_FUNCTION);
		targetFunction = (Function<Double, D>) properties.get(GeaConfigConstants.TARGET_FUNCTION);
		generator = (GeneratorFunction<T>) properties.get(GeaConfigConstants.GENERATOR_FUNCTION);
		optimize = (Optimize) properties.get(GeaConfigConstants.OPTIMIZE);
		setPopulation(new ArrayList<T>(populationSize));
		setOptimize(optimize);
	}

	/**
	 * Used to create the start population.
	 */
	protected void createStartPopulation() {
		while (getPopulationSize() > getPopulation().size()) {
			T genome = getGenerator().evaluate();
			if (getFilter() != null && getFilter().accept(genome)) {
				getPopulation().add(genome);
			} else {
				getPopulation().add(genome);
			}
		}
	}

	/**
	 * This method must be implemented to evolve one generation
	 *
	 * @throws java.lang.Exception
	 *             if any error occurs.
	 */
	protected abstract void epoch() throws Exception;

	/**
	 * This method must be implemented by subclasses to simulate the evolution
	 * process.
	 *
	 * @throws java.lang.Exception
	 *             if any error occurs.
	 */
	public abstract void evolve() throws Exception;

	/**
	 * Gets the value of best fitness score.
	 *
	 * @return the value of best fitness score.
	 */
	public double getBestFitnessScore() {
		return bestFitnessScore;
	}

	/**
	 * Gets an instance of
	 * {@link com.github.yferras.javartint.core.function.Function} that decodes
	 * the the genome.
	 *
	 * @return an instance of
	 *         {@link com.github.yferras.javartint.core.function.Function}
	 */
	public Function<D, T> getDecoder() {
		return decoder;
	}

	/**
	 * Gets the genome filter.
	 *
	 * @return an instance of
	 *         {@link com.github.yferras.javartint.gea.util.IndividualFilter}.
	 */
	protected IndividualFilter<T> getFilter() {
		return filter;
	}

	/**
	 * Retrieves the generations.
	 *
	 * @return generations.
	 */
	public long getGenerations() {
		return generations;
	}

	/**
	 * Gets the function that generates genome
	 *
	 * @return the instance of
	 *         {@link com.github.yferras.javartint.gea.function.generator.GeneratorFunction}
	 */
	public GeneratorFunction<T> getGenerator() {
		return generator;
	}

	/** {@inheritDoc} */
	@Override
	public Long getIterations() {
		return getGenerations();
	}

	/** {@inheritDoc} */
	@Override
	public Optimize getOptimize() {
		return optimize;
	}

	/**
	 * Gets the population.
	 *
	 * @return a list with a population.
	 */
	protected List<T> getPopulation() {
		return population;
	}

	/**
	 * Gets the population size.
	 *
	 * @return population size.
	 */
	public int getPopulationSize() {
		return populationSize;
	}

	/**
	 * Gets the target function to optimize.
	 *
	 * @return function to optimize
	 */
	public Function<Double, D> getTargetFunction() {
		return targetFunction;
	}

	/**
	 * Each time that's method is invoked the {@code generations} attribute
	 * increases its value in one.
	 */
	protected void increaseGenerations() {
		generations++;
	}

	/**
	 * Updates the value of best fitness score.
	 *
	 * @param bestFitnessScore
	 *            new value of best fitness score
	 */
	protected void setBestFitnessScore(double bestFitnessScore) {
		this.bestFitnessScore = bestFitnessScore;
	}

	/**
	 * Sets the genome filter
	 *
	 * @param filter
	 *            an instance of
	 *            {@link com.github.yferras.javartint.gea.util.IndividualFilter}
	 */
	protected void setFilter(IndividualFilter<T> filter) {
		this.filter = filter;
	}

	/** {@inheritDoc} */
	@Override
	public final void setOptimize(Optimize optimize) {
		switch (optimize) {
		case MAX:
			setBestFitnessScore(Double.NEGATIVE_INFINITY);
			break;
		case MIN:
			setBestFitnessScore(Double.POSITIVE_INFINITY);
			break;
		default:
			break;
		}
		this.optimize = optimize;
	}

	/**
	 * Sets the new population.
	 *
	 * @param population
	 *            new population list.
	 */
	protected void setPopulation(List<T> population) {
		this.population = population;
	}

	/**
	 * <p>
	 * updateFitnessScores.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	protected void updateFitnessScores() {
		for (T individual : getPopulation()) {
			D decodedValue = getDecoder().evaluate(individual);
			Double currentFitnessScore = getTargetFunction().evaluate(decodedValue);
			individual.setFitness(currentFitnessScore);

			if ((getOptimize() == Optimize.MAX && currentFitnessScore > getBestFitnessScore())
					^ (getOptimize() == Optimize.MIN && currentFitnessScore < getBestFitnessScore())) {
				setBestFitnessScore(currentFitnessScore);
				try {
					setSolution((T) individual.clone());
				} catch (CloneNotSupportedException e) {
					setSolution(null);
					throw new RuntimeException("Cloning the solution.", e);
				}
			}
		}
	}
}

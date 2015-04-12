package crow.javartint.gea;

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

import crow.javartint.core.AbstractAlgorithm;
import crow.javartint.core.IterativeAlgorithm;
import crow.javartint.core.OptimizationAlgorithm;
import crow.javartint.core.util.Optimize;
import crow.javartint.core.function.Function;
import crow.javartint.gea.function.decoder.DecoderFunction;
import crow.javartint.gea.function.generator.GeneratorFunction;
import crow.javartint.gea.util.IndividualFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that provides a sets of functionalities to subclassing evolutionary algorithms
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.Individual}
 * @param <D> Type of decoded value.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractEvolutionaryAlgorithm<T extends Individual, D>
	extends AbstractAlgorithm<T>
	implements OptimizationAlgorithm<T>, IterativeAlgorithm<T> {

	final private DecoderFunction<D, T> decoder;
	final private Function<Double, D> targetFunction;
	final private GeneratorFunction<T> generator;
	private IndividualFilter<T> filter;
	private List<T> population;
	private int populationSize;
	private long generations;
	private Optimize optimize;
	private double bestFitnessScore;

	/**
	 * Initializes this class.
	 *
	 * @param populationSize the population limit
	 * @param optimize       the optimization way
	 * @param decoder        function to decode the genome
	 * @param targetFunction function to optimize
	 * @param generator      function to generate genomes
	 */
	public AbstractEvolutionaryAlgorithm(int populationSize,
	                                     Optimize optimize,
	                                     DecoderFunction<D, T> decoder,
	                                     Function<Double, D> targetFunction,
	                                     GeneratorFunction<T> generator) {
		this.populationSize = populationSize;
		this.decoder = decoder;
		this.targetFunction = targetFunction;
		this.generator = generator;
		setPopulation(new ArrayList<T>(populationSize));
		setOptimize(optimize);
	}

	/**
	 * This method must be implemented by subclasses to simulate the evolution process.
	 */
	public abstract void evolve() throws Exception;

	/**
	 * This method must be implemented to evolve one generation
	 */
	protected abstract void epoch();

	@SuppressWarnings("unchecked")
	protected void updateFitnessScores() {
		for (T individual : getPopulation()) {
			D decodedValue = getDecoder().evaluate(individual);
			Double currentFitnessScore = getTargetFunction().evaluate(decodedValue);
			individual.setFitness(currentFitnessScore);

			if ((getOptimize() == Optimize.MAX
				&& currentFitnessScore > getBestFitnessScore())
				^ (getOptimize() == Optimize.MIN
				&& currentFitnessScore < getBestFitnessScore())) {
				setBestFitnessScore(currentFitnessScore);
				try {
					setSolution((T) individual.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
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
	 * Each time that's method is invoked the {@code generations} attribute increases its value in one.
	 */
	protected void increaseGenerations() {
		generations++;
	}

	@Override
	public Long getIterations() {
		return getGenerations();
	}

	@Override
	public Optimize getOptimize() {
		return optimize;
	}

	@Override
	final public void setOptimize(Optimize optimize) {
		switch (optimize) {
			case MAX:
				setBestFitnessScore(-Double.MAX_VALUE);
				break;
			case MIN:
				setBestFitnessScore(Double.MAX_VALUE);
				break;
		}
		this.optimize = optimize;
	}

	/**
	 * Gets an instance of {@link crow.javartint.core.function.Function} that decodes the the genome.
	 *
	 * @return an instance of {@link crow.javartint.core.function.Function}
	 */
	public Function<D, T> getDecoder() {
		return decoder;
	}

	/**
	 * Gets the value of best fitness score.
	 *
	 * @return the value of best fitness score.
	 */
	public double getBestFitnessScore() {
		return bestFitnessScore;
	}

	/**
	 * Updates the value of best fitness score.
	 *
	 * @param bestFitnessScore new value of best fitness score
	 */
	protected void setBestFitnessScore(double bestFitnessScore) {
		this.bestFitnessScore = bestFitnessScore;
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
	 * Gets the population size.
	 *
	 * @return population size.
	 */
	public int getPopulationSize() {
		return populationSize;
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
	 * Sets the new population.
	 *
	 * @param population new population list.
	 */
	protected void setPopulation(List<T> population) {
		this.population = population;
	}

	/**
	 * Gets the genome filter.
	 *
	 * @return an instance of {@link crow.javartint.gea.util.IndividualFilter}.
	 */
	protected IndividualFilter<T> getFilter() {
		return filter;
	}

	/**
	 * Sets the genome filter
	 *
	 * @param filter an instance of {@link crow.javartint.gea.util.IndividualFilter}
	 */
	protected void setFilter(IndividualFilter<T> filter) {
		this.filter = filter;
	}

	/**
	 * Gets the function that generates genome
	 *
	 * @return the instance of {@link crow.javartint.gea.function.generator.GeneratorFunction}
	 */
	public GeneratorFunction<T> getGenerator() {
		return generator;
	}

	/**
	 * Gets the target function to optimize.
	 *
	 * @return function to optimize
	 */
	public Function<Double, D> getTargetFunction() {
		return targetFunction;
	}
}

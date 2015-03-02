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

import crow.javartint.core.util.Optimize;
import crow.javartint.core.util.function.Function;
import crow.javartint.gea.function.crossover.CrossoverFunction;
import crow.javartint.gea.function.decoder.DecoderFunction;
import crow.javartint.gea.function.generator.GeneratorFunction;
import crow.javartint.gea.function.mutation.MutationFunction;
import crow.javartint.gea.function.selection.RandomSelectionFunction;
import crow.javartint.gea.function.selection.SelectionFunction;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.*;

/**
 * Abstract class that provides a sets of functionalities to subclassing genetic algorithms
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @param <D> Type of decoded value.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractGeneticAlgorithm<T extends Genome<? extends Gene<?>>, D>
        extends AbstractEvolutionaryAlgorithm<T, D> {

    private final CrossoverFunction<T> crossoverFunction;

    private final MutationFunction<T> mutationFunction;

    private final SelectionFunction<T> selectionFunction;

    private SelectionFunction<T> selectionFunctionToParents;

    /**
     * Initializes this class.
     * @param populationSize    the population limit
     * @param optimize          the optimization way
     * @param decoder           function to decode the genome
     * @param generator         function to generate genomes
     * @param crossoverFunction function to crossing process
     * @param mutationFunction  function to mutation process
     * @param selectionFunction function to selection process
     */
    public AbstractGeneticAlgorithm(int populationSize,
                                    Optimize optimize,
                                    DecoderFunction<D, T> decoder,
                                    Function<Double, D> targetFunction,
                                    GeneratorFunction<T> generator,
                                    CrossoverFunction<T> crossoverFunction,
                                    MutationFunction<T> mutationFunction,
                                    SelectionFunction<T> selectionFunction) {
        super(populationSize, optimize, decoder, targetFunction, generator);
        this.crossoverFunction = crossoverFunction;
        this.mutationFunction = mutationFunction;
        this.selectionFunction = selectionFunction;
        selectionFunctionToParents = new RandomSelectionFunction<>();
    }

    /**
     * This method sets {@code true} to {@code running} attribute, and calls
     * {@link #evolve()} method. Implemented from Runnable interface.
     */
    @Override
    public void run() {
        beginAlgorithm();
        evolve();
        fireAlgorithmFinishedEvent();
    }

    /**
     * <p>This method is the responsible for create and evolve of the population.</p>
     */
    @Override
    public void evolve() {
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
     * <li>While the size o new population will be less than the size of
     * current population.</li>
     * <ol>
     * <li>Selects the two parents.</li>
     * <li>Crossing the pre-selected parents.</li>
     * <li>Mutates the offspring.</li>
     * <li>If {@link #getGenomeFilter()} is distinct of {@code null},
     * the new offspring will be filtered,
     * and only the successfully filtered genomes will be added to the new generation,
     * otherwise the offspring will be added to the new population.</li>
     * </ol>
     * <li>Old population is replaced by new.</li>
     * </ol>
     */
    @Override
    protected void epoch() {
        Set<T> newGeneration = new LinkedHashSet<>(getPopulationSize());
        if (getSelectionFunction() != null) {
            newGeneration.addAll(getSelectionFunction().evaluate(getPopulation()));
        }
        while (newGeneration.size() < getPopulationSize()) {
            List<T> parents = getSelectionFunctionToParents().evaluate(getPopulation());
            @SuppressWarnings("unchecked")
            T[] offspring = getCrossoverFunction().evaluate(parents.get(0), parents.get(1));
            for (T genome : offspring) {
                getMutationFunction().evaluate(genome);
            }
            if (getGenomeFilter() != null) {
                for (T genome : offspring) {
                    if (getGenomeFilter().accept(genome)) {
                        newGeneration.add(genome);
                    }
                }
            } else {
                Collections.addAll(newGeneration, offspring);
            }
        }
        setPopulation(new ArrayList<>(newGeneration));
    }

    /**
     * Gets the instance of crossover function.
     *
     * @return the crossover function.
     */
    public CrossoverFunction<T> getCrossoverFunction() {
        return crossoverFunction;
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
     * Gets the instance of selection function.
     *
     * @return the selection function.
     */
    public SelectionFunction<T> getSelectionFunction() {
        return selectionFunction;
    }


    /**
     * Gets the selection function used to select the parents and use them in the crossing process.
     * By default is an instance of {@link crow.javartint.gea.function.selection.RandomSelectionFunction}.
     *
     * @return an instance of {@link crow.javartint.gea.function.selection.SelectionFunction}
     */
    public SelectionFunction<T> getSelectionFunctionToParents() {
        return selectionFunctionToParents;
    }

    /**
     * Sets the selection function used to select the parents in the crossing process.
     *
     * @param selectionFunctionToParents selection function instance.
     */
    public void setSelectionFunctionToParents(SelectionFunction<T> selectionFunctionToParents) {
        this.selectionFunctionToParents = selectionFunctionToParents;
    }
}

package crow.javartint.gea.genome;

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

import crow.javartint.core.Solution;
import crow.javartint.gea.chromosome.Chromosome;
import crow.javartint.gea.gene.Gene;

import java.io.Serializable;

/**
 * This interface represents a generic genome.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.3
 */
public interface Genome<T extends Chromosome<? extends Gene<?>>>
        extends Cloneable, Iterable<T>, Solution, Serializable,
        Comparable<Genome<?>> {

    /**
     * Gets the array of chromosomes.
     *
     * @return The value of chromosomes.
     */
    T[] getChromosomes();

    /**
     * Sets the array of chromosomes.
     *
     * @throws IllegalArgumentException if {@code chromosomes} argument is
     *                                  {@code null}.
     */
    void setChromosomes(T[] chromosomes) throws IllegalArgumentException;

    /**
     * Gets the value of fitness.
     *
     * @return The value of fitness.
     */
    double getFitness();

    /**
     * Sets the value of fitness.
     *
     * @param fitness New value of fitness.
     */
    void setFitness(double fitness);

    /**
     * Gets the chromosome at specific index.
     *
     * @param index index
     * @return chromosome
     */
    T getChromosome(int index);

    /**
     * Sets the chromosome at specific index.
     * @param index index to place new chromosome
     * @param newChromosome chromosome
     */
    void setChromosome(int index, T newChromosome);

    /**
     * Gets the number of chromosomes of the genome.
     *
     * @return The number of chromosomes of the genome.
     */
    int size();

    /**
     * Adds new chromosome to genome.
     *
     * @param chromosome new chromosome.
     */
    void add(T chromosome);

    Genome<T> clone() throws CloneNotSupportedException;
}

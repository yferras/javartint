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
import crow.javartint.gea.gene.Gene;

import java.io.Serializable;
import java.util.Iterator;

/**
 * This interface represents a generic genome.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public interface Genome<T extends Gene<?>>
        extends Cloneable, Iterable<T>, Iterator<T>, Solution, Serializable,
        Comparable<Genome<? extends Gene<?>>> {

    /**
     * Gets the value of genes.
     *
     * @return The value of genes.
     */
    Object[] getChromosome();

    /**
     * Sets the value of genes.
     *
     * @throws IllegalArgumentException if {@code genes} argument is
     *                                  {@code null}.
     */
    void setChromosome(T[] genes) throws IllegalArgumentException;

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
     * Gets the value of gene at specific <code>index</code>. A gene is a
     * sub-array of the genes array.
     *
     * @param index Specific <code>index</code>.
     * @return This method returns a gene
     * @throws IndexOutOfBoundsException if the <code>index</code> is out of
     *                                   range.
     */
    T getGene(int index) throws IndexOutOfBoundsException;

    /**
     * Gets the number of genes of the genome.
     *
     * @return The number of genes of the genome.
     */
    int size();

    /**
     * Sets the value of <code>newGene</code> at specified <code>index</code>.
     *
     * @param index   Specific <code>index</code> where will be replaced the gene.
     * @param newGene new value of gene.
     */
    void setGene(int index, T newGene);

    /**
     * Adds a new gene to the genome.
     *
     * @param gene gene to add
     */
    void addGene(T gene);

    Genome<T> clone() throws CloneNotSupportedException;
}

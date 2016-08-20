package com.github.yferras.javartint.gea.genome;

import java.io.Serializable;

import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;

/**
 * This interface represents a generic genome.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.4
 */
public interface Genome<T extends Chromosome<? extends Gene<?>>>
    extends Cloneable, Individual, Iterable<T>, Serializable {

    /**
     * Gets the array of chromosomes.
     *
     * @return The value of chromosomes.
     */
    T[] getChromosomes();

    /**
     * Sets the array of chromosomes.
     *
     * @param chromosomes an array of T objects.
     * @throws com.github.yferras.javartint.core.util.ValidationException if {@code chromosomes} argument is {@code null}.
     */
    void setChromosomes(T[] chromosomes) ;

    /**
     * Gets the chromosome at specific index.
     *
     * @param index index
     * @return chromosome
     */
    T getChromosome(int index);

    /**
     * Sets the chromosome at specific index.
     *
     * @param index         index to place new chromosome
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
    void addChromosome(T chromosome);

    /**
     * <p>clone.</p>
     *
     * @return a {@link com.github.yferras.javartint.gea.genome.Genome} object.
     * @throws java.lang.CloneNotSupportedException if any.
     */
    Genome<T> clone() throws CloneNotSupportedException;

    /**
     * Gets the genome type by the chromosomes parity.
     *
     * @return genome type.
     */
    GenomeType getGenomeType();
}

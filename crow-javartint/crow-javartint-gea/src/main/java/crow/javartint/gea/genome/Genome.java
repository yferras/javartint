package crow.javartint.gea.genome;

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
        extends Cloneable, Iterable<T>, Iterator<T>, Solution, Serializable {

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
    int getNumberOfGenes();

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

package crow.jai.gea.genome;

import crow.jai.core.Solution;
import crow.jai.gea.gene.Gene;
import java.util.Iterator;

/**
 * This interface represents a generic genome.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 * @param <T> Any derived class from {@link Gene} interface.
 *
 */
public interface Genome<T extends Gene<?>> 
    extends Cloneable, Iterable<T>, Iterator<T>, Solution {

    /**
     * Gets the value of genes.
     *
     * @return The value of genes.
     */
    T[] getChromosome();

    /**
     * Gets the value of fitness.
     *
     * @return The value of fitness.
     */
    double getFitness();

    /**
     * Gets the value of gene at specific <code>index</code>. A gene is a
     * sub-array of the genes array.
     *
     * @param index Specific <code>index</code>.
     * @return This method returns a gene
     * @throws IndexOutOfBoundsException if the <code>index</code> is out of
     * range.
     */
    T getGene(int index) throws IndexOutOfBoundsException;

    /**
     * Gets the number of genes of the genome.
     *
     * @return The number of genes of the genome.
     */
    int getNumberOfGenes();

    /**
     * Sets the value of genes.
     *
     * @param genes New value of genes.
     * @throws IllegalArgumentException if {@code genes} argument is
     * {@code null}.
     */
    void setChromosome(T[] genes) throws IllegalArgumentException;

    /**
     * Sets the value of fitness.
     *
     * @param fitness New value of fitness.
     */
    void setFitness(double fitness);

    /**
     * Sets the value of <code>newGene</code> at specified <code>index</code>.
     *
     * @param index Specific <code>index</code> where will be replaced the gene.
     * @param newGene new value of gene.
     */
    void setGene(int index, T newGene);
    
}

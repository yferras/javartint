package crow.jai.gea.genome;

import crow.jai.gea.gene.Gene;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a default genome.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 * @param <T> Any derived class from {@link Gene} interface.
 *
 */
public class DefaultGenome<T extends Gene<?>>
        implements Genome<T> {

    private int iteratorIndex = 0;
    /**
     * Genome fitness.
     */
    protected double fitness;
    /**
     * Array of genes that contains the genome information.
     */
    protected T[] genes;

    /**
     * Default constructor.
     */
    public DefaultGenome() {
        genes = null;
        fitness = 0.0;
    }


    @Override
    public T[] getChromosome() {
        return genes;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public T getGene(int index) throws IndexOutOfBoundsException {
        return genes[index];
    }

    @Override
    public int getNumberOfGenes() {
        return genes.length;
    }

    @Override
    public boolean hasNext() {
        return iteratorIndex < genes.length;
    }

    @Override
    public Iterator<T> iterator() {
        iteratorIndex = 0;
        return this;
    }

    @Override
    public T next() {
        int index = iteratorIndex;
        if (index >= genes.length) {
            throw new NoSuchElementException();
        }
        iteratorIndex = index + 1;
        return genes[index];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Size of array is fixed");
    }

    @Override
    public void setChromosome(T[] genes) throws IllegalArgumentException {
        if (genes == null) {
            throw new IllegalArgumentException("THE 'GENES' ARRAY CAN'T BE NULL.");
        }
        this.genes = genes;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public void setGene(int index, T newGene) {
        genes[index] = newGene;
    }
}

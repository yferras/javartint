package crow.javartint.gea.genome;

import crow.javartint.gea.gene.Gene;

import java.io.*;
import java.util.*;

/**
 * This class is an abstract implementation of {@link crow.javartint.gea.genome.Genome}.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public abstract class AbstractGenome<T extends Gene<?>> implements Genome<T> {
    /**
     * Array of genes that contains the genome information.
     */
    final protected List<T> genes;
    /**
     * Genome fitness.
     */
    protected double fitness;
    private Iterator<T> iterator;

    protected AbstractGenome() {
        fitness = 0.0;
        genes = new LinkedList<>();
    }

    private Iterator<T> getIterator() {
        if (iterator == null) {
            setIterator(this.genes.iterator());
        }
        return iterator;
    }

    private void setIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Object[] getChromosome() {
        return genes.toArray();
    }

    @Override
    public void setChromosome(T[] genes) throws IllegalArgumentException {
        this.genes.clear();
        this.genes.addAll(Arrays.asList(genes));
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public T getGene(int index) throws IndexOutOfBoundsException {
        return genes.get(index);
    }

    @Override
    public int getNumberOfGenes() {
        return genes.size();
    }

    @Override
    public boolean hasNext() {
        return getIterator().hasNext();
    }

    @Override
    public Iterator<T> iterator() {
        setIterator(null);
        return getIterator();
    }

    @Override
    public T next() {
        return getIterator().next();
    }

    @Override
    public void remove() {
        //getIterator().remove();
    }

    @Override
    public void setGene(int index, T newGene) {
        genes.set(index, newGene);
    }

    @Override
    public void addGene(T gene) {
        genes.add(gene);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Genome<T> clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream
                    (outputStream.toByteArray());
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(inputStream);
            DefaultGenome<T> cloned =
                    (DefaultGenome<T>) objectInputStream.readObject();
            objectInputStream.close();
            return cloned;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.fitness) ^ (Double.doubleToLongBits(this.fitness) >>> 32));
        hash = 97 * hash + Arrays.deepHashCode(getChromosome());
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractGenome)) return false;

        AbstractGenome that = (AbstractGenome) o;

        return Double.compare(that.fitness, fitness) == 0 && genes.equals(that.genes);

    }
}

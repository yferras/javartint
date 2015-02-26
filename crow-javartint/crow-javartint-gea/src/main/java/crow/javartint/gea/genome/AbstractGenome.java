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

import crow.javartint.gea.gene.Gene;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    public int size() {
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
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            objectInputStream = new ObjectInputStream(inputStream);
            return (Genome<T>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CloneNotSupportedException(e.getMessage());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    @Override
    public int compareTo(Genome<? extends Gene<?>> o) {
        return Double.compare(getFitness(), o.getFitness());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("Fitness: ").append(getFitness()).append(';');
        stringBuilder.append(" Genes: (").append(size() != 0 ? getGene(0) : "");
        for (int i = 1; i < size(); i++) {
            stringBuilder.append("; ").append(getGene(i));
        }
        return stringBuilder.append(")").append("}").toString();
    }
}

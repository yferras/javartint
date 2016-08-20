package com.github.yferras.javartint.gea.genome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

import com.github.yferras.javartint.core.util.AbstractItemIterator;
import com.github.yferras.javartint.core.util.ValidationException;
import com.github.yferras.javartint.gea.AbstractIndividual;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;

/**
 * This class is an abstract implementation of {@link com.github.yferras.javartint.gea.genome.Genome}.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractGenome<T extends Chromosome<? extends Gene<?>>>
    extends AbstractIndividual implements Genome<T> {
    private static final int HASH_CODE_CONST_31 = 31;
    private static final int HASH_CODE_CONST_32 = 32;

    /**
     * Array of chromosomes that contains the genome information.
     */
    protected List<T> chromosomes;

    /**
     * <p>Constructor for AbstractGenome.</p>
     */
    protected AbstractGenome() {
        chromosomes = new LinkedList<>();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public T[] getChromosomes() {
        return (T[])chromosomes.toArray(new Chromosome<?>[size()]);
    }

    /** {@inheritDoc} */
    @Override
    public void setChromosomes(T[] chromosomes)  {
        if (chromosomes == null) {
            throw new ValidationException("'chromosomes' array can't be null.");
        }
        this.chromosomes.clear();
        this.chromosomes.addAll(Arrays.asList(chromosomes));
    }

    /** {@inheritDoc} */
    @Override
    public T getChromosome(int index) {
        return this.chromosomes.get(index);
    }

    /** {@inheritDoc} */
    @Override
    public void setChromosome(int index, T newChromosome) {
        this.chromosomes.set(index, newChromosome);
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return chromosomes.size();
    }

    /** {@inheritDoc} */
    @Override
    public void addChromosome(T chromosome) {
        chromosomes.add(chromosome);
    }

    /** {@inheritDoc} */
    @Override
    public GenomeType getGenomeType() {
        if (size() == 0) {
            return null;
        }
        return size() % 2 == 0 ? GenomeType.DIPLOID : GenomeType.HAPLOID;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> iterator() {
        return new AbstractItemIterator<T>() {
            @Override
            public T getItem(int index) {
                return getChromosome(index);
            }

            @Override
            public int itemsCount() {
                return size();
            }
        };
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Genome<T> clone() throws CloneNotSupportedException {
        final AbstractGenome<T> copy = (AbstractGenome<T>) super.clone();
        copy.chromosomes = new ArrayList<>(size());
        for (T t : this) {
            copy.chromosomes.add((T) t.clone());
        }
        return copy;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractGenome that = (AbstractGenome) o;

        return Double.compare(that.getFitness(), getFitness()) == 0 && chromosomes.equals(that.chromosomes);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = chromosomes.hashCode();
        temp = Double.doubleToLongBits(getFitness());
        result = HASH_CODE_CONST_31 * result + (int) (temp ^ (temp >>> HASH_CODE_CONST_32));
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("Fitness: ").append(getFitness()).append(';');
        stringBuilder.append(" Chromosome: (").append(size() != 0 ? getChromosome(0) : "");
        for (int i = 1; i < size(); i++) {
            stringBuilder.append("; ").append(getChromosome(i));
        }
        return stringBuilder.append(")").append("}").toString();
    }
}

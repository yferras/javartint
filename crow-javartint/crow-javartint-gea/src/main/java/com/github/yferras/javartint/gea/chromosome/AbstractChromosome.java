package com.github.yferras.javartint.gea.chromosome;

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
import com.github.yferras.javartint.gea.gene.Gene;

import java.util.*;

/**
 * This class is an abstract implementation of {@link com.github.yferras.javartint.gea.chromosome.Chromosome}.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public abstract class AbstractChromosome<T extends Gene<?>> implements Chromosome<T> {
	/**
	 * List of genes that contains the chromosome information.
	 */
	protected List<T> genes;

	/**
	 * <p>Constructor for AbstractChromosome.</p>
	 */
	protected AbstractChromosome() {
		genes = new LinkedList<>();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public Gene<?>[] getGenes() {
		return genes.toArray(new Gene<?>[size()]);
	}

	/** {@inheritDoc} */
	@Override
	public void setGenes(T[] genes) throws IllegalArgumentException {
		this.genes.clear();
		this.genes.addAll(Arrays.asList(genes));
	}

	/** {@inheritDoc} */
	@Override
	public T getGene(int locus) throws IndexOutOfBoundsException {
		return genes.get(locus);
	}

	/** {@inheritDoc} */
	@Override
	public int size() {
		return genes.size();
	}

	/** {@inheritDoc} */
	@Override
	public Iterator<T> iterator() {
		return new AbstractItemIterator<T>() {
			@Override
			public T getItem(int index) {
				return getGene(index);
			}

			@Override
			public int itemsCount() {
				return size();
			}
		};
	}

	/** {@inheritDoc} */
	@Override
	public void setGene(int locus, T newGene) {
		genes.set(locus, newGene);
	}

	/** {@inheritDoc} */
	@Override
	public void addGene(T gene) {
		genes.add(gene);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public AbstractChromosome<T> clone() throws CloneNotSupportedException {
		AbstractChromosome<T> copy = (AbstractChromosome<T>) super.clone();
		copy.genes = new LinkedList<>();
		for (T t : this) {
			copy.genes.add((T) t.clone());
		}
		return copy;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractChromosome that = (AbstractChromosome) o;

		return genes.equals(that.genes);
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		return genes.hashCode();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("{");
		stringBuilder.append(" Genes: (").append(size() != 0 ? getGene(0) : "");
		for (int i = 1; i < size(); i++) {
			stringBuilder.append("; ").append(getGene(i));
		}
		return stringBuilder.append(")").append("}").toString();
	}

}

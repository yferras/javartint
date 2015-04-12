package crow.javartint.gea.chromosome;

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
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * This class is an abstract implementation of {@link crow.javartint.gea.chromosome.Chromosome}.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public abstract class AbstractChromosome<T extends Gene<?>> implements Chromosome<T> {
	/**
	 * List of genes that contains the chromosome information.
	 */
	final protected List<T> genes;

	protected AbstractChromosome() {
		genes = new LinkedList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Gene<?>[] getGenes() {
		return genes.toArray(new Gene<?>[size()]);
	}

	@Override
	public void setGenes(T[] genes) throws IllegalArgumentException {
		this.genes.clear();
		this.genes.addAll(Arrays.asList(genes));
	}

	@Override
	public T getGene(int locus) throws IndexOutOfBoundsException {
		return genes.get(locus);
	}

	@Override
	public int size() {
		return genes.size();
	}

	@Override
	public Iterator<T> iterator() {
		return new ChromosomeIterator();
	}

	@Override
	public void setGene(int locus, T newGene) {
		genes.set(locus, newGene);
	}

	@Override
	public void addGene(T gene) {
		genes.add(gene);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Chromosome<T> clone() throws CloneNotSupportedException {
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(this);

			ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			objectInputStream = new ObjectInputStream(inputStream);
			return (Chromosome<T>) objectInputStream.readObject();
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractChromosome that = (AbstractChromosome) o;

		return genes.equals(that.genes);
	}

	@Override
	public int hashCode() {
		return genes.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("{");
		stringBuilder.append(" Genes: (").append(size() != 0 ? getGene(0) : "");
		for (int i = 1; i < size(); i++) {
			stringBuilder.append("; ").append(getGene(i));
		}
		return stringBuilder.append(")").append("}").toString();
	}

	private class ChromosomeIterator implements Iterator<T> {

		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < size();
		}

		@Override
		public T next() {
			try {
				int i = cursor;
				T next = getGene(i);
				cursor = i + 1;
				return next;
			} catch (IndexOutOfBoundsException e) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("SIZE OF ARRAY IS FIXED");
		}
	}

}

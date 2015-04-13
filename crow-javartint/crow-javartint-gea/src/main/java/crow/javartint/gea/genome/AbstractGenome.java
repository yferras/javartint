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

import crow.javartint.gea.AbstractIndividual;
import crow.javartint.gea.chromosome.Chromosome;
import crow.javartint.gea.gene.Gene;

import java.io.*;
import java.util.*;

/**
 * This class is an abstract implementation of {@link crow.javartint.gea.genome.Genome}.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractGenome<T extends Chromosome<? extends Gene<?>>>
	extends AbstractIndividual implements Genome<T> {
	/**
	 * Array of chromosomes that contains the genome information.
	 */
	final protected List<T> chromosomes;

	protected AbstractGenome() {
		chromosomes = new LinkedList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Chromosome<? extends Gene<?>>[] getChromosomes() {
		return chromosomes.toArray(new Chromosome<?>[size()]);
	}

	@Override
	public void setChromosomes(T[] chromosomes) throws IllegalArgumentException {
		this.chromosomes.clear();
		this.chromosomes.addAll(Arrays.asList(chromosomes));
	}

	@Override
	public T getChromosome(int index) {
		return this.chromosomes.get(index);
	}

	@Override
	public void setChromosome(int index, T newChromosome) {
		this.chromosomes.set(index, newChromosome);
	}

	@Override
	public int size() {
		return chromosomes.size();
	}

	@Override
	public void addChromosome(T chromosome) {
		chromosomes.add(chromosome);
	}

	@Override
	public GenomeType getGenomeType() {
		if (size() == 0)
			return null;
		return size() % 2 == 0 ? GenomeType.DIPLOID : GenomeType.HAPLOID;
	}

	@Override
	public Iterator<T> iterator() {
		return new GenomeIterator();
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractGenome that = (AbstractGenome) o;

		return Double.compare(that.getFitness(), getFitness()) == 0 && chromosomes.equals(that.chromosomes);
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = chromosomes.hashCode();
		temp = Double.doubleToLongBits(getFitness());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

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

	private class GenomeIterator implements Iterator<T> {

		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < size();
		}

		@Override
		public T next() {
			try {
				int i = cursor;
				T next = getChromosome(i);
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

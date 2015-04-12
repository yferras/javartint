package crow.javartint.gea.gene;

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

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Default generic gene to represent arrays.
 *
 * @param <T> Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class ArrayGene<T> extends AbstractGene<T[]> implements
	Cloneable, Iterable<T> {

	public ArrayGene(T[] data) {
		super(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ArrayGene<?> other = (ArrayGene<?>) obj;
		return Arrays.deepEquals(this.getData(), other.getData());
	}

	/**
	 * Get the value of data at specified index.
	 *
	 * @param index specified index.
	 * @return the value of data at specified index
	 */
	public T getAllele(int index) {
		return this.getData()[index];
	}

	/**
	 * Returns the total elements in this gene.
	 *
	 * @return the length of this gene
	 */
	public int length() {
		return getData().length;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + Arrays.deepHashCode(this.getData());
		return hash;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayGeneIterator();
	}

	/**
	 * Set the value of data at specified index.
	 *
	 * @param index   index to set de value
	 * @param newData new value of data at specified index
	 */
	public void setAllele(int index, T newData) {
		this.data[index] = newData;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[")
			.append(length() != 0 ? getAllele(0) : "");
		for (int i = 1; i < getData().length; i++) {
			stringBuilder.append(", ").append(getAllele(i));
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	private class ArrayGeneIterator implements Iterator<T> {

		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < length();
		}

		@Override
		public T next() {
			try {
				int i = cursor;
				T next = getAllele(i);
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

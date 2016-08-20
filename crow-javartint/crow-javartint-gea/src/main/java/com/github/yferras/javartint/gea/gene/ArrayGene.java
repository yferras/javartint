package com.github.yferras.javartint.gea.gene;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

/**
 * Default generic gene to represent arrays.
 *
 * @param <T> Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class ArrayGene<T> extends AbstractGene<T[]> implements Cloneable, Iterable<T> {

	private static final long serialVersionUID = -450975978130625335L;
	private static final int HASH_CODE_CONST = 7 * 23;

    /**
     * <p>Constructor for ArrayGene.</p>
     *
     * @param data an array of T objects.
     */
    public ArrayGene(T[] data) {
        super(data);
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return HASH_CODE_CONST + Arrays.deepHashCode(this.getData());
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> iterator() {
        return new AbstractItemIterator<T>() {
            @Override
            public T getItem(int index) {
                return getAllele(index);
            }

            @Override
            public int itemsCount() {
                return length();
            }
        };
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
    	List<T> list = Arrays.asList(getData());
        return list.stream().map(element -> element.toString()).collect(Collectors.joining(", ", "[", "]"));
    }
}

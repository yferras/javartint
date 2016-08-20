package com.github.yferras.javartint.gea.gene;

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

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * Abstract implementation of {@link com.github.yferras.javartint.gea.gene.Gene}.
 *
 * @param <T> Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractGene<T> implements Gene<T> {

	private static final long serialVersionUID = 1919755543077335578L;

	private static final int HASH_CODE_CONST = 3 * 97;

    /**
     * The data.
     */
    protected T data;

    /**
     * <p>Constructor for AbstractGene.</p>
     *
     * @param data a T object.
     */
    public AbstractGene(T data) {
        this.data = data;
    }

    private static Object copy(Object src) {
        if (src instanceof Byte) {
            return new Byte((byte) src);
        } else if (src instanceof Character) {
            return new Character((char) src);
        } else if (src instanceof Short) {
            return new Short((short) src);
        } else if (src instanceof Integer) {
            return new Integer((int) src);
        } else if (src instanceof Long) {
            return new Long((long) src);
        } else if (src instanceof Float) {
            return new Float((float) src);
        } else if (src instanceof Double) {
            return new Double((double) src);
        } else if (src instanceof Boolean) {
            return new Boolean((boolean) src);
        } else if (src instanceof String) {
            return new String((String) src);
        } else if (src.getClass().isArray()) {
            final int length = Array.getLength(src);
            Object array = Array.newInstance(src.getClass().getComponentType(), length);
            copyArrays((Object[]) src, (Object[]) array);
            return array;
        } else {
            return src;
        }
    }

    private static void copyArrays(Object[] src, Object[] dst) {
        final int length = src.length;
        for (int i = 0; i < length; i++) {
            dst[i] = copy(src[i]);
        }
    }

    /** {@inheritDoc} */
    @Override
    public T getData() {
        return data;
    }

    /** {@inheritDoc} */
    @Override
    public void setData(T data) {
        this.data = data;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Gene<T> clone() throws CloneNotSupportedException {
        @SuppressWarnings("rawtypes")
		AbstractGene copy = (AbstractGene) super.clone();
        copy.data = copy(getData());
        return copy;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return HASH_CODE_CONST + Objects.hashCode(this.data);
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
        final DefaultGene<?> other = (DefaultGene<?>) obj;
        return Objects.equals(other.getData(), this.data);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getData() == null ? "" : getData().toString();
    }
}

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
 * Abstract implementation of {@link Gene}.
 *
 * @param <T> Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractGene<T> implements Gene<T> {

	/**
	 * The data.
	 */
	protected T data;

	public AbstractGene(T data) {
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public void setData(T data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Gene<T> clone() throws CloneNotSupportedException {
		AbstractGene copy = (AbstractGene) super.clone();
		copy.data = copy(getData());
		return copy;
	}

	private static Object copy(Object src) {
		if (src instanceof Byte)
			return new Byte((byte) src);
		else if (src instanceof Character)
			return new Character((char) src);
		else if (src instanceof Short)
			return new Short((short) src);
		else if (src instanceof Integer)
			return new Integer((int) src);
		else if (src instanceof Long)
			return new Long((long) src);
		else if (src instanceof Float)
			return new Float((float) src);
		else if (src instanceof Double)
			return new Double((double) src);
		else if (src instanceof Boolean)
			return new Boolean((boolean) src);
		else if (src instanceof String)
			return new String((String)src);
		else if (src.getClass().isArray()) {
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

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + Objects.hashCode(this.data);
		return hash;
	}

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

	@Override
	public String toString() {
		return getData() == null ? "" : getData().toString();
	}
}
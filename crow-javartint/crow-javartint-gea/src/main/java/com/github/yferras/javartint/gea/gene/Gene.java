package com.github.yferras.javartint.gea.gene;

/*
 * #%L
 * Crow JavArtInt GEA
 *
 %%
 Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 *
 %%
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as
 published by the Free Software Foundation, either version 3 of the
 License, or (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public
 License along with this program.  If not, see
 <http://www.gnu.org/licenses/gpl-3.0.html>.
 #L%
 */

import java.io.Serializable;

/**
 * This interface represents a generic gene.
 *
 * @param <T>
 *            Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface Gene<T> extends Cloneable, Serializable {

	/**
	 * <p>
	 * clone.
	 * </p>
	 *
	 * @return a {@link com.github.yferras.javartint.gea.gene.Gene} object.
	 * @throws java.lang.CloneNotSupportedException
	 *             if any.
	 */
	Gene<T> clone() throws CloneNotSupportedException;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	T getData();

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            data argument to set.
	 */
	void setData(T data);

}

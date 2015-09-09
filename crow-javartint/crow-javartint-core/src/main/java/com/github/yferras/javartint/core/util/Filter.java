package com.github.yferras.javartint.core.util;

/*
 * #%L
 * Crow JavArtInt Core
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

/**
 * This interfaces is useful to create custom filters.
 *
 * @param <T> Generic type.
 * @author Eng. Ferrás Cecilio, Yeinier Jul 18, 2011
 */
public interface Filter<T> {

	/**
	 * Serves to create custom filters.
	 *
	 * @param element element to check
	 * @return <code>true</code> if this element pass the filter, otherwise
	 * returns <code>false</code>
	 */
	boolean accept(T element);
}

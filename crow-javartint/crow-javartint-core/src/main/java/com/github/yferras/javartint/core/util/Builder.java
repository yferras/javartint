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
 * <p>
 * Interface to create builders.
 * </p>
 *
 * @param <T>
 *            any type.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 1.0
 * @since 1.1.0
 */
public interface Builder<T> {

	/**
	 * <p>
	 * This method is used to encapsulate the logic of build.
	 * </p>
	 *
	 * @return the built object.
	 */
	T build();
}

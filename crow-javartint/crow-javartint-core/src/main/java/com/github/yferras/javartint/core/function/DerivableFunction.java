package com.github.yferras.javartint.core.function;

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
 * Interface to create generic derivable functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @since 1.0.1
 */
public interface DerivableFunction<R, P> extends Function<R, P> {

	/**
	 * Gets the derived function.
	 *
	 * @return derived function. If the current function doesn't has derivative, will return {@code null}.
	 */
	DerivableFunction<R, P> derive();

	/**
	 * Gets the level of derived function. Normally a constant integer, for example for the first derived this method
	 * must return 1, for the second derived must return 2, and so on.
	 *
	 * @return an integer that represents the level of derived function.
	 */
	int getN();
}

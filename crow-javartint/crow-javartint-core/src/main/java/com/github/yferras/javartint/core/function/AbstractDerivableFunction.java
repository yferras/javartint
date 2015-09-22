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
 * Class that implements partially {@link com.github.yferras.javartint.core.function.DerivableFunction} to create
 * generic derivable functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @since 1.0.1
 */
public abstract class AbstractDerivableFunction<R, P> implements DerivableFunction<R, P> {

	private DerivableFunction parent;

	/**
	 * Constructor for AbstractDerivableFunction.
	 */
	public AbstractDerivableFunction() {
		this(null);
	}

	/**
	 * Constructor for AbstractDerivableFunction.
	 *
	 * @param parent a {@link com.github.yferras.javartint.core.function.DerivableFunction} object.
	 */
	public AbstractDerivableFunction(DerivableFunction parent) {
		this.parent = parent;
	}

	/** {@inheritDoc} */
	@Override
	public int getN() {
		return  this.parent == null ? 0 : this.parent.getN() + 1;
	}

}

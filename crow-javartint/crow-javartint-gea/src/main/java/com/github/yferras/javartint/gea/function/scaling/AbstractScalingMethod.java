package com.github.yferras.javartint.gea.function.scaling;

import java.util.ArrayList;
import java.util.List;

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

import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.core.util.ValidationException;
import com.github.yferras.javartint.gea.Individual;

/**
 * Abstract class that represents scaling method function.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractScalingMethod<T extends Individual> implements Function<List<T>, List<T>> {

	private final Optimize optimize;

	/**
	 * Constructor that initializes this instance.
	 *
	 * @param optimize
	 *            optimization way
	 */
	public AbstractScalingMethod(Optimize optimize) {
		this.optimize = optimize;
	}

	/**
	 * Get the value of optimize
	 *
	 * @return the value of optimize
	 */
	public Optimize getOptimize() {
		return optimize;
	}

	/**
	 * Method to perform scaling process
	 *
	 * @param individuals
	 *            list to scale.
	 */
	protected abstract void scale(List<T> individuals);

	/**
	 * Ensures that parameters are valid.
	 *
	 * @param params
	 *            parameters to validate.
	 */
	protected void validate(List<T> params) {
		if (params == null) {
			throw new ValidationException("'params' can't be null.");
		}
		if (params.isEmpty()) {
			throw new ValidationException("'params' can't be empty.");
		}
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * Validates the input parameters and performs the scaling process.
	 */
	@Override
	public List<T> evaluate(List<T> params) {
		validate(params);
		List<T> list = new ArrayList<>(params);
		scale(list);
		return list;
	}

}

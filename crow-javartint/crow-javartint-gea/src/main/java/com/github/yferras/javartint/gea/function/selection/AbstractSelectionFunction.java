package com.github.yferras.javartint.gea.function.selection;

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

import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod;

import java.util.List;

/**
 * Abstract class that represents selection function.
 *
 * @param <T> Any derived class from {@link Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractSelectionFunction<T extends Individual>
	implements SelectionFunction<T> {

	private int numToSelect = 1;

	private AbstractScalingMethod<T> scalingMethod = null;

	/**
	 * Constructor than initializes this instance.
	 *
	 * @param numToSelect   number of elements to select
	 * @param scalingMethod scaling method
	 */
	public AbstractSelectionFunction(int numToSelect, AbstractScalingMethod<T> scalingMethod) {
		if (numToSelect < 1) {
			throw new IllegalArgumentException("'numToSelect' can't be less than 1.");
		}
		this.numToSelect = numToSelect;
		this.scalingMethod = scalingMethod;
	}

	/**
	 * Constructor than initializes this instance.
	 *
	 * @param numToSelect number of elements to select
	 */
	public AbstractSelectionFunction(int numToSelect) {
		this(numToSelect, null);
	}

	/**
	 * Gets the number elements to select by this function
	 *
	 * @return the number elements to select
	 */
	public int getNumToSelect() {
		return numToSelect;
	}

	/**
	 * Gets the scaling method
	 *
	 * @return the instance of scaling method
	 */
	public AbstractScalingMethod<T> getScalingMethod() {
		return scalingMethod;
	}

	/**
	 * Ensures that parameter are valid.
	 *
	 * @param params parameters to validate.
	 * @throws IllegalArgumentException If {@code params} is {@code null}.
	 *                                  Or, the length is less than the number of elements to select
	 */
	private void validate(List<T> params) {
		if (params == null) {
			throw new IllegalArgumentException("'params' can't be null.");
		}
		if (params.size() < getNumToSelect()) {
			throw new IllegalArgumentException("'params.length' is less than getNumToSelect()");
		}
	}

	/**
	 * Selects from a list the desired number of individuals and retrieves then into
	 * list.
	 *
	 * @param individuals Source to select the number of desired individuals.
	 * @return a list with the selected individuals.
	 */
	protected abstract List<T> select(List<T> individuals);

	/**
	 * Performs the selection process after validating the input params ({@link #validate})
	 *
	 * @param params parameters to evaluate.
	 * @return an array with selected elements
	 */
	@Override
	public List<T> evaluate(List<T> params) {
		validate(params);
		return select(params);
	}


}
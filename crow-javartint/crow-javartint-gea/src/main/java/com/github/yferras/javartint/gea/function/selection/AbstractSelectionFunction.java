package com.github.yferras.javartint.gea.function.selection;

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

import com.github.yferras.javartint.core.util.ValidationException;
import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod;

/**
 * Abstract class that represents selection function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractSelectionFunction<T extends Individual>
    implements SelectionFunction<T> {

    /** Constant <code>DEFAULT_TO_SELECT=2</code> */
    public static final int DEFAULT_TO_SELECT = 2;

    private int numToSelect;

    private AbstractScalingMethod<T> scalingMethod;

    /**
     * Constructor than initializes this instance.
     *
     * @param numToSelect   number of elements to select
     * @param scalingMethod scaling method
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public AbstractSelectionFunction(int numToSelect, AbstractScalingMethod<T> scalingMethod)  {
        if (numToSelect < 1) {
            throw new ValidationException("'numToSelect' can't be less than 1.");
        }
        this.numToSelect = numToSelect;
        this.scalingMethod = scalingMethod;
    }

    /**
     * Constructor than initializes this instance.
     *
     * @param numToSelect number of elements to select
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public AbstractSelectionFunction(int numToSelect)  {
        this(numToSelect, null);
    }

    /**
     * Constructor than initializes this instance.
     *
     * @since 1.0.1
     */
    public AbstractSelectionFunction() {
        this.numToSelect = DEFAULT_TO_SELECT;
        this.scalingMethod = null;
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
     * @throws ValidationException If {@code params} is {@code null}.
     *                                  Or, the length is less than the number of elements to select
     */
    private void validate(List<T> params) {
        if (params == null) {
            throw new ValidationException("'params' can't be null.");
        }
        if (params.size() < getNumToSelect()) {
            throw new ValidationException("'params.length' is less than getNumToSelect()");
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
     * {@inheritDoc}
     * <p/>
     * Performs the selection process after validating the input params ({@link #validate})
     */
    @Override
    public List<T> evaluate(List<T> params) {
        validate(params);
        return select(params);
    }


}

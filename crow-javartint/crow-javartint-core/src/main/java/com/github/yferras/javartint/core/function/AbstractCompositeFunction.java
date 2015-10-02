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

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements partially {@link com.github.yferras.javartint.core.function.CompositeFunction} to create
 * generic composite functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @since 1.0.1
 */
public abstract class AbstractCompositeFunction<R, P> implements CompositeFunction<R, P> {

    private List<Function> functionStack;

    /**
     * Constructor for AbstractCompositeFunction.
     */
    public AbstractCompositeFunction() {
        functionStack = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getN() {
        return functionStack.size();
    }

    /**
     * Gets the list that contains the functions.
     *
     * @return a list with functions.
     */
    protected List<Function> getFunctions() {
        return functionStack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFunctions(Function... functions) {
        for (Function function : functions) {
            functionStack.add(function);
        }
    }
}

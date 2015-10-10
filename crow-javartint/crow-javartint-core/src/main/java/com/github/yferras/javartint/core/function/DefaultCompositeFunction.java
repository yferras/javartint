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

import com.github.yferras.javartint.core.util.ValidationException;

import java.util.Iterator;


/**
 * Default implementation of {@link com.github.yferras.javartint.core.function.AbstractCompositeFunction} class.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @since 1.0.1
 */
public class DefaultCompositeFunction<R, P> extends AbstractCompositeFunction<R, P> {

    /**
     * {@inheritDoc}
     * <p/>
     * This method invokes the composite functions in the same order that they was introduced. The output of the
     * first function will be the input of the next, and so on.
     */
    @SuppressWarnings("unchecked")
    @Override
    public R evaluate(P params) throws ValidationException {
        if (getFunctions().isEmpty()) {
            return null;
        }
        final Iterator<Function> iterator = getFunctions().iterator();
        Object result = iterator.next().evaluate(params);
        while (iterator.hasNext()) {
            result = iterator.next().evaluate(result);
        }
        return (R) result;
    }
}

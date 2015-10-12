package com.github.yferras.javartint.ann.function.propagationrule;

/*
 * #%L
 * Crow JavArtInt ANN
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

/**
 * <p>Abstract AbstractPropagationRuleFunction class.</p>
 *
 * @author yferras
 * @version $Id: $Id
 */
public abstract class AbstractPropagationRuleFunction implements PropagationRuleFunction {

    private static final int NUMBER_OF_PARAMS = 2;

    /**
     * {@inheritDoc}
     * <p/>
     * After all validations executes {@link AbstractPropagationRuleFunction#evaluate(Double[], Double[])}.
     */
    @Override
    public Double evaluate(Double[][] params) throws ValidationException {
        if (params == null) {
            throw new ValidationException("'params' can't be null.");
        }
        if (params.length != NUMBER_OF_PARAMS) {
            throw new ValidationException("'params' must contains exactly two arrays");
        }
        if (params[0] == null) {
            throw new ValidationException("'params[0]' can't be null.");
        }
        if (params[1] == null) {
            throw new ValidationException("'params[1]' can't be null.");
        }
        if (params[0].length != params[1].length) {
            throw new ValidationException("the length between two arrays must be the same.");
        }
        return evaluate(params[0], params[1]);
    }

    /**
     * Evaluates the arguments and returns a result.
     *
     * @param inputs  the array of inputs.
     * @param weights the array of weights.
     * @return the result.
     */
    protected abstract Double evaluate(Double[] inputs, Double[] weights);
}

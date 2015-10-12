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


/**
 * <p>EuclideanDistancePropagationRuleFunction class.</p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class EuclideanDistancePropagationRuleFunction extends AbstractPropagationRuleFunction {

    private static final double POW_2 = 2.0;

    /** {@inheritDoc} */
    @Override
    protected Double evaluate(Double[] inputs, Double[] weights) {
        double sum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            sum += Math.pow(weights[i] - inputs[i], POW_2);
        }
        return sum;
    }
}

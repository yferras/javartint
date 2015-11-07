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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <p>DefaultCompositeFunctionTest class.</p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @since 1.0.1
 */
public class CompositeFunctionTest {

    final Function<Double, Double[]> meanFunc = new Function<Double, Double[]>() {
        @Override
        public Double evaluate(Double[] params) {
            double sum = 0.0;
            for (Double param : params) {
                sum += param;
            }
            return sum / params.length;
        }
    };

    final Function<Double, Double> sqrtFunc = new Function<Double, Double>() {
        @Override
        public Double evaluate(Double x) {
            return Math.sqrt(x);
        }
    };

    final Function<Double, Double> towTimesFunc = new Function<Double, Double>() {
        @Override
        public Double evaluate(Double x) {
            return 2.0 * x;
        }
    };

    final Double[] params = {1.0, 17.0, 4.0, 14.0, 10.0, 8.0};

    @Test
    public void testEvaluate() throws Exception {
        CompositeFunction.Builder<Double, Double[]> builder = getDoubleBuilder();
        builder.append(meanFunc).append(sqrtFunc).append(towTimesFunc);
        CompositeFunction<Double, Double[]> function = builder.build();

        double expected =
            towTimesFunc.evaluate(
                sqrtFunc.evaluate(
                    meanFunc.evaluate(params)
                )
            );

        double actual = function.evaluate(params);

        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testEvaluate1() throws Exception {
        CompositeFunction.Builder<Double, Double[]> builder = getDoubleBuilder();
        builder.append(sqrtFunc).append(towTimesFunc).push(meanFunc);
        CompositeFunction<Double, Double[]> function = builder.build();

        double expected =
            towTimesFunc.evaluate(
                sqrtFunc.evaluate(
                    meanFunc.evaluate(params)
                )
            );
        double actual = function.evaluate(params);

        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testEvaluate2() throws Exception {
        CompositeFunction.Builder<Double, Double> builder = new CompositeFunction.Builder<>();
        builder.push(sqrtFunc).push(towTimesFunc);
        CompositeFunction<Double, Double> function = builder.build();

        double expected =
            sqrtFunc.evaluate(
                towTimesFunc.evaluate(8.0)
            );
        double actual = function.evaluate(8.0);

        assertEquals(expected, actual, 0.0);
    }

    private static CompositeFunction.Builder<Double, Double[]> getDoubleBuilder() {
        return new CompositeFunction.Builder<>();
    }

    @Test
    public void testSize() throws Exception {
        CompositeFunction.Builder<Double, Double[]> builder = getDoubleBuilder();
        builder.append(meanFunc).append(sqrtFunc).append(towTimesFunc);
        CompositeFunction<Double, Double[]> function = builder.build();
        assertTrue(function.size() == 3);
    }
}
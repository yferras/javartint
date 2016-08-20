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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class DerivableFunctionTest {

    final Function<Double, Double> f_3xP3 = new Function<Double, Double>() {
        @Override
        public Double evaluate(Double params) {
            return 3.0 * Math.pow(params, 3.0);
        }
    };

    final Function<Double, Double> f_3xP3_D1 = new Function<Double, Double>() {
        @Override
        public Double evaluate(Double params) {
            return 9.0 * Math.pow(params, 2.0);
        }
    };

    final Function<Double, Double> f_3xP3_D2 = new Function<Double, Double>() {
        @Override
        public Double evaluate(Double params) {
            return 18.0 * params;
        }
    };

    final Function<Double, Double> f_3xP3_D3 = new Function<Double, Double>() {
        @Override
        public Double evaluate(Double params) {
            return 18.0;
        }
    };


    @Test
    public void testBuild() throws Exception {
        DerivableFunction.Builder<Double, Double> builder = new DerivableFunction.Builder<>();
        builder.setBaseFunction(f_3xP3)
            .addDerived(f_3xP3_D1)
            .addDerived(f_3xP3_D2)
            .addDerived(f_3xP3_D3);
        DerivableFunction<Double, Double> derivableFunction = builder.build();

        List<Function<Double, Double>> list = new ArrayList<>();
        list.add(f_3xP3);
        list.add(f_3xP3_D1);
        list.add(f_3xP3_D2);
        list.add(f_3xP3_D3);
        final Iterator<Function<Double, Double>> functionIterator = list.iterator();
        int order = 0;
        while (derivableFunction != null && functionIterator.hasNext()) {
            final Function<Double, Double> function = functionIterator.next();
            for (Double i = 0.0; i < 10.0; i++) {
                Double actual = derivableFunction.evaluate(i);
                Double expected = function.evaluate(i);
                assertEquals(expected, actual, 0.0);
            }
            assertEquals(order, derivableFunction.getOrder());
            order++;
            derivableFunction = derivableFunction.derive();
        }
    }
}
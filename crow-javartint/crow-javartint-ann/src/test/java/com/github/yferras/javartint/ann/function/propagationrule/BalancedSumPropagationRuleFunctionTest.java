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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class BalancedSumPropagationRuleFunctionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEvaluate() throws Exception {
        BalancedSumPropagationRuleFunction function = new BalancedSumPropagationRuleFunction();
        Double[] inputs = {1.0, 1.0, 1.0};
        Double[] weights = {1.0, 1.0, 1.0};
        Double expected = 3.0;
        Double actual = function.evaluate(new Double[][]{inputs, weights});
        assertEquals(expected, actual);
    }
}
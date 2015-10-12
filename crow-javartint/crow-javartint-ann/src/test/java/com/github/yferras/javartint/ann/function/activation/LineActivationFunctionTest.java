package com.github.yferras.javartint.ann.function.activation;

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

import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class LineActivationFunctionTest extends TestCase {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(LineActivationFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    public void testDerive() throws Exception {
        System.out.println("derive");
        LineActivationFunction function = new LineActivationFunction();
        Double expected = 1.0;
        Double actual = function.derive().evaluate(56.0);
        assertEquals(expected, actual);
        actual = function.derive().evaluate(-64.0);
        assertEquals(expected, actual);
    }

    public void testGetN() throws Exception {
        System.out.println("getN");
        LineActivationFunction function = new LineActivationFunction();
        int expected = 0;
        assertEquals(expected, function.getN());
        expected = 1;
        assertEquals(expected, function.derive().getN());
    }

    public void testEvaluate() throws Exception {
        System.out.println("evaluate");
        LineActivationFunction function = new LineActivationFunction();
        Double expected = 1.0;
        Double actual = function.evaluate(1.0);
        assertEquals(expected, actual);
        expected = -64.0;
        actual = function.evaluate(-64.0);
        assertEquals(expected, actual);
    }
}
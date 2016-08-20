package com.github.yferras.javartint.gea.function.scaling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
* #%L
 * * Crow JavArtInt GEA
 * *
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * *
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

import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.genome.DefaultGenome;

public class LinearRankScalingMethodTest {

	@Before
	public void setUp() throws Exception {
		System.out.print(LinearRankScalingMethod.class.getName().concat("."));
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	@SuppressWarnings("unchecked")
	public void testEvaluate() throws Exception {
		System.out.println("evaluate (performed algorithm)");
		double p = 1.5;
		LinearRankScalingMethod<DefaultGenome> scalingMethod = new LinearRankScalingMethod(p, Optimize.MAX);
		List<DefaultGenome> genomes = new ArrayList<>(10);
		double[] exp = new double[10];
		for (int i = 0; i < 10; i++) {
			genomes.add(new DefaultGenome());
			exp[i] = 2.0 - p + (2.0 * i * (p - 1.0)) / (9.0);
		}
		List<DefaultGenome> evaluate = scalingMethod.evaluate(genomes);
		for (int i = 0; i < evaluate.size(); i++) {
			Assert.assertEquals(exp[i], evaluate.get(i).getFitness(), 0.0);
		}
	}

	@Test
	public void testGetSelectivePressure() throws Exception {
		LinearRankScalingMethod scalingMethod = new LinearRankScalingMethod(1.5, Optimize.MAX);
		assertEquals(1.5, scalingMethod.getSelectivePressure(), 0.0);
	}

	@Test
	public void testInit() throws Exception {
		System.out.println("constructor (validate for out of bounds)");
		boolean lower = true, upper = true;
		try {
			new LinearRankScalingMethod(0.999999, Optimize.MAX);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			lower = false;
		}
		try {
			new LinearRankScalingMethod(2.000001, Optimize.MAX);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			upper = false;
		}
		if (lower) {
			fail("'IllegalArgumentException' not raised when the argument is out of the lower bound.");
		}
		if (upper) {
			fail("'IllegalArgumentException' not raised when the argument is out of the upper bound.");
		}
	}
}

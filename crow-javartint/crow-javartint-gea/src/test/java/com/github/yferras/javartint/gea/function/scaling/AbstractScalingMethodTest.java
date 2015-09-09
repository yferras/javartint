package com.github.yferras.javartint.gea.function.scaling;

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

import com.github.yferras.javartint.gea.genome.DefaultGenome;
import com.github.yferras.javartint.gea.genome.Genome;
import com.github.yferras.javartint.gea.util.MathUtil;
import com.github.yferras.javartint.core.util.Optimize;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractScalingMethodTest {

	@Before
	public void setUp() throws Exception {
		System.out.print(AbstractScalingMethod.class.getName().concat("."));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOptimize() throws Exception {
		System.out.println("getOptimize");
		DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
		assertEquals(Optimize.MAX, scalingMethod.getOptimize());
	}

	@Test
	public void testEvaluate() throws Exception {
		System.out.println("evaluate (performed algorithm)");
		DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
		List<Double> list = new ArrayList<>();
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);

		List<Double> expected = new ArrayList<>();
		for (Double val : list) {
			expected.add(val * 2.0);
		}
		@SuppressWarnings("unchecked")
		List<Double> r = scalingMethod.evaluate(list);
		assertArrayEquals(expected.toArray(), r.toArray());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testEvaluate2() throws Exception {
		System.out.println("evaluate (validate null)");
		DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
		try {
			scalingMethod.evaluate(null);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		fail("'IllegalArgumentException' not raised for null param");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testEvaluate3() throws Exception {
		System.out.println("evaluate (validate empty)");
		DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
		try {
			scalingMethod.evaluate(new ArrayList());
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		fail("'IllegalArgumentException' not raised for empty list param");
	}

	@Test
	public void testMean() throws Exception {
		System.out.println("mean");
		List<Genome> list = new ArrayList<>();
		double exp = 0.0;
		for (int i = 0; i < 10; i++) {
			list.add(new DefaultGenome());
			list.get(i).setFitness(i + 1.0);
			exp += i + 1;
		}
		exp /= 10;
		double r = MathUtil.mean(list);
		assertEquals(exp, r, 0.0);
	}

	private static class DefaultScalingMethod extends AbstractScalingMethod {

		/**
		 * Constructor that initializes this instance.
		 *
		 * @param optimize optimization way
		 */
		public DefaultScalingMethod(Optimize optimize) {
			super(optimize);
		}

		@Override
		@SuppressWarnings("unchecked")
		protected void scale(List individuals) {
			for (int i = 0; i < individuals.size(); i++) {
				Double val = (Double) individuals.get(i);
				individuals.set(i, val * 2.0);
			}
		}
	}
}

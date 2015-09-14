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

import org.junit.*;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * <p>ProbabilisticFunctionTest class.</p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version $Id: $Id
 * @since 1.0.0
 */
public class ProbabilisticFunctionTest {

	/**
	 * <p>setUpClass.</p>
	 */
	@BeforeClass
	public static void setUpClass() {
	}

	/**
	 * <p>tearDownClass.</p>
	 */
	@AfterClass
	public static void tearDownClass() {
	}

	/**
	 * <p>setUp.</p>
	 */
	@Before
	public void setUp() {
		System.out.print(AbstractProbabilisticFunction.class.getName().concat
			("."));
	}

	/**
	 * <p>tearDown.</p>
	 */
	@After
	public void tearDown() {
	}

	/**
	 * <p>testConstructor1.</p>
	 */
	@Test
	public void testConstructor1() {
		System.out.println("constructor " +
			"(test probability less than 0.0)");
		try {
			new DefaultProbabilisticFunction<Double>(-.5);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	/**
	 * <p>testConstructor2.</p>
	 */
	@Test
	public void testConstructor2() {
		System.out.println("constructor " +
			"(test probability greater than 1.0)");
		try {
			new DefaultProbabilisticFunction<Double>(2.0);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	/**
	 * <p>testConstructor3.</p>
	 */
	@Test
	public void testConstructor3() {
		System.out.println("constructor " +
			"(test probability between 0.0 and 1.0)");
		try {
			new DefaultProbabilisticFunction<Double>(0.5);
		} catch (IllegalArgumentException e) {
			assertTrue(false);
			return;
		}
		assertTrue(true);
	}

	/**
	 * <p>testConstructor4.</p>
	 */
	@Test
	public void testConstructor4() {
		System.out.println("constructor " +
			"(test probability = 0.0)");
		try {
			new DefaultProbabilisticFunction<Double>(0.0);
		} catch (IllegalArgumentException e) {
			assertTrue(false);
			return;
		}
		assertTrue(true);
	}

	/**
	 * <p>testConstructor5.</p>
	 */
	@Test
	public void testConstructor5() {
		System.out.println("constructor " +
			"(test probability = 1.0)");
		try {
			new DefaultProbabilisticFunction<Double>(1.0);
		} catch (IllegalArgumentException e) {
			assertTrue(false);
			return;
		}
		assertTrue(true);
	}

	/**
	 * <p>testConstructor6.</p>
	 */
	@Test
	public void testConstructor6() {
		System.out.println("constructor " +
			"(test probability = 0.5 and randomGenerator is null)");
		try {
			new DefaultProbabilisticFunction<Double>(1.0, null);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * <p>testGetProbability.</p>
	 */
	@Test
	public void testGetProbability() {
		System.out.println("getProbability");
		final DefaultProbabilisticFunction<Double> function =
			new DefaultProbabilisticFunction<>(0.5);
		assertEquals(new Double(0.5), new Double(function.getProbability()));
	}

	/**
	 * <p>testGetRandomGenerator1.</p>
	 */
	@Test
	public void testGetRandomGenerator1() {
		System.out.println("getRandom (not null)");
		final DefaultProbabilisticFunction<Double> function =
			new DefaultProbabilisticFunction<>(0.5);
		assertNotNull(function.getRandom());
	}

	/**
	 * <p>testGetRandomGenerator2.</p>
	 */
	@Test
	public void testGetRandomGenerator2() {
		System.out.println("getRandom (instance of Random)");
		final DefaultProbabilisticFunction<Double> function =
			new DefaultProbabilisticFunction<>(0.5);
		assertTrue(function.getRandom() instanceof Random);
	}

	private static class DefaultProbabilisticFunction<T extends Number>
		extends AbstractProbabilisticFunction<Double, T[]> {

		private DefaultProbabilisticFunction(double probability,
		                                     Random random) {
			super(probability, random);
		}

		private DefaultProbabilisticFunction(double probability) {
			super(probability);
		}

		@Override
		public Double evaluate(T... params) {
			if (getRandom().nextDouble() > getProbability()) {
				return params[0].doubleValue() + params[1].doubleValue();
			}
			return params[0].doubleValue() - params[1].doubleValue();
		}
	}
}

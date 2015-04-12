package crow.javartint.core.function;

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
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class ProbabilisticFunctionTest {

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		System.out.print(AbstractProbabilisticFunction.class.getName().concat
			("."));
	}

	@After
	public void tearDown() {
	}

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

	@Test
	public void testGetProbability() {
		System.out.println("getProbability");
		final DefaultProbabilisticFunction<Double> function =
			new DefaultProbabilisticFunction<>(0.5);
		assertEquals(new Double(0.5), new Double(function.getProbability()));
	}

	@Test
	public void testGetRandomGenerator1() {
		System.out.println("getRandom (not null)");
		final DefaultProbabilisticFunction<Double> function =
			new DefaultProbabilisticFunction<>(0.5);
		assertNotNull(function.getRandom());
	}

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

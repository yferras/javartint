package com.github.yferras.javartint.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * RangeTest class.
 * </p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @since 1.0.0
 */
public class RangeTest {

	/**
	 * <p>
	 * setUp.
	 * </p>
	 *
	 * @throws java.lang.Exception
	 *             if any.
	 */
	@Before
	public void setUp() throws Exception {
		System.out.print(Range.class.getName().concat("."));
	}

	/**
	 * <p>
	 * testAccept.
	 * </p>
	 *
	 * @throws java.lang.Exception
	 *             if any.
	 */
	@Test
	public void testAccept() throws Exception {
		Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.BOTH);
		assertTrue(range.accept(-1.0));
		assertTrue(range.accept(1.0));
		assertTrue(range.accept(0.0));
		assertFalse(range.accept(-1.1));
		assertFalse(range.accept(1.05));
		range = new Range<>(-1.0, 1.0, Range.Use.NONE);
		assertFalse(range.accept(-1.0));
		assertFalse(range.accept(1.0));
		assertTrue(range.accept(0.0));
		assertFalse(range.accept(-1.1));
		assertFalse(range.accept(1.05));
		range = new Range<>(-1.0, 1.0, Range.Use.MAX);
		assertFalse(range.accept(-1.0));
		assertTrue(range.accept(1.0));
		assertTrue(range.accept(0.0));
		assertFalse(range.accept(-1.1));
		assertFalse(range.accept(1.05));
		range = new Range<>(-1.0, 1.0, Range.Use.MIN);
		assertTrue(range.accept(-1.0));
		assertFalse(range.accept(1.0));
		assertTrue(range.accept(0.0));
		assertFalse(range.accept(-1.1));
		assertFalse(range.accept(1.05));
	}

	/**
	 * <p>
	 * testConstructor3.
	 * </p>
	 */
	@Test
	public void testConstructor3() {
		System.out.println("constructor " + "(raise exception)");
		try {
			new Range<>(0.5, 0.0, Range.Use.BOTH);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		fail("'IllegalArgumentException' not raised");
	}

	/**
	 * <p>
	 * testEquals.
	 * </p>
	 *
	 * @throws java.lang.Exception
	 *             if any.
	 */
	@Test
	public void testEquals() throws Exception {
		Range<Double> range1 = new Range<Double>(-1.0, 1.0, Range.Use.BOTH);
		Range<Double> range2 = new Range<Double>(-1.0, 1.0, Range.Use.BOTH);
		Range<Double> range3 = new Range<Double>(-1.0, 1.0, Range.Use.NONE);
		assertEquals(range1, range2);
		assertNotEquals(range1, range3);
	}

	/**
	 * <p>
	 * testGetMax.
	 * </p>
	 *
	 * @throws java.lang.Exception
	 *             if any.
	 */
	@Test
	public void testGetMax() throws Exception {
		Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.BOTH);
		assertEquals(1.0, range.getMax(), 0.0);
	}

	/**
	 * <p>
	 * testGetMin.
	 * </p>
	 *
	 * @throws java.lang.Exception
	 *             if any.
	 */
	@Test
	public void testGetMin() throws Exception {
		Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.BOTH);
		assertEquals(-1.0, range.getMin(), 0.0);
	}

	/**
	 * <p>
	 * testGetUse.
	 * </p>
	 *
	 * @throws java.lang.Exception
	 *             if any.
	 */
	@Test
	public void testGetUse() throws Exception {
		Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.NONE);
		assertEquals(Range.Use.NONE, range.getUse());
	}
}

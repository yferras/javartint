package com.github.yferras.javartint.gea.gene;

/*
 * #%L
 * Crow JavArtInt GEA
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

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class ArrayGeneTest {

	public static final ArrayGene<Double> INSTANCE = new ArrayGene<>(
		new Double[]{
			1.0, 2.0, 3.0, 4.0, 5.0
		});

	public ArrayGeneTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		System.out.print(ArrayGene.class.getName().concat("."));
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getData method, of class ArrayGene.
	 */
	@Test
	public void testGetData() {
		System.out.println("getData");
		Object[] expResult = new Double[]{
			1.0, 2.0, 3.0, 4.0, 5.0
		};
		Object[] result = INSTANCE.getData();
		assertArrayEquals(expResult, result);
	}

	/**
	 * Test of setData method, of class ArrayGene.
	 */
	@Test
	public void testSetData() {
		System.out.println("setData");
		Double[] data = new Double[]{3.0, 1.0, 2.0, 5.0, 4.0};
		ArrayGene<Double> instance = new ArrayGene<>(null);
		instance.setData(data.clone());
		Double[] result = instance.getData();
		assertArrayEquals(data, result);
	}

	/**
	 * Test of clone method, of class ArrayGene.
	 *
	 * @throws Exception
	 */
	@Test
	public void testClone1() throws Exception {
		System.out.println("clone (check references)");
		ArrayGene<Double> result = (ArrayGene<Double>) INSTANCE.clone();
		assertFalse(INSTANCE == result);
	}

	/**
	 * Test of clone method, of class ArrayGene.
	 *
	 * @throws Exception
	 */
	@Test
	public void testClone2() throws Exception {
		System.out.println("clone (check identity)");
		ArrayGene<Double> result = (ArrayGene<Double>) INSTANCE.clone();
		assertEquals(INSTANCE, result);
	}


	/**
	 * Test of hashCode method, of class ArrayGene.
	 */
	@Test
	public void testHashCode() {
		System.out.println("hashCode");
		int expResult = new ArrayGene<>(new Double[]{
			1.0, 2.0, 3.0, 4.0, 5.0
		}).hashCode();
		int result = INSTANCE.hashCode();
		assertEquals(expResult, result);
	}

	/**
	 * Test of equals method, of class ArrayGene.
	 */
	@Test
	public void testEquals() {
		System.out.println("equals");
		Object obj = new ArrayGene<>(new Double[]{
			1.0, 2.0, 3.0, 4.0, 5.0
		});
		assertEquals(true, INSTANCE.equals(obj));
	}

	/**
	 * Test of length method, of class ArrayGene.
	 */
	@Test
	public void testLength() {
		System.out.println("length");
		assertEquals(5, INSTANCE.length());
	}

	/**
	 * Test of getAllele method, of class ArrayGene.
	 */
	@Test
	public void testGetAllele() {
		System.out.println("getAllele");
		assertEquals(new Double(5.0), INSTANCE.getAllele(4));
	}

	/**
	 * Test of setAllele method, of class ArrayGene.
	 */
	@Test
	public void testSetAllele() {
		System.out.println("setAllele");
		try {
			final ArrayGene<Double> gene = (ArrayGene<Double>) INSTANCE.clone();
			gene.setAllele(2, 10.0);
			assertEquals(new Double(10.0), gene.getAllele(2));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			fail("clone raised an exception");
		}
	}

	/**
	 * Test of Iterable
	 */
	@Test
	public void testIterable() {
		int i = 0;
		for (Double value : INSTANCE) {
			assertEquals(INSTANCE.getAllele(i++), value);
		}
	}

	/**
	 * Test of Iterable throw exception
	 */
	@Test
	public void testIterableThrowException() {
		Iterator<Double> iterator = INSTANCE.iterator();
		try {
			while (iterator.hasNext()) {
				iterator.next();
			}
			iterator.next();
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test of Iterable throw exception
	 */
	@Test
	public void testIterableThrowException1() {
		Iterator<Double> iterator = INSTANCE.iterator();
		try {
			if (iterator.hasNext()) {
				iterator.remove();
			}
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}


}

package com.github.yferras.javartint.gea.genome;

/*
 * #%L
 * Crow JavArtInt ANN
 * %%
 * Copyright (C) 2014 - 2016 Eng. Ferrás Cecilio, Yeinier
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.gene.DoubleArrayGene;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.gene.IntegerArrayGene;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class DefaultGenomeTest {

	private static final Gene<Integer> GENE1 = new DefaultGene<>(1);
	private static final Gene<Double[]> GENE2 = new DoubleArrayGene(new Double[] { 1.0, 5.0, 9.0 });
	private static final Gene<Integer[]> GENE3 = new IntegerArrayGene(new Integer[] { 1, 5, 9 });
	private static final Gene<Integer> GENE4 = new DefaultGene<>(4);
	private static final Gene<?>[] GENES = new Gene[] { GENE1, GENE2, GENE3, GENE4 };

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	public DefaultGenomeTest() {
	}

	@Before
	public void setUp() {
		System.out.print(DefaultGenome.class.getName().concat("."));
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of clone method, of class DefaultGenome.
	 *
	 * @throws java.lang.Exception
	 */
	@Test
	public void testClone() throws Exception {
		System.out.println("clone");
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		instance.getChromosome(0).setGenes(GENES);
		DefaultGenome<Chromosome<Gene<?>>> result = (DefaultGenome<Chromosome<Gene<?>>>) instance.clone();
		assertTrue(instance.equals(result));
		assertFalse(instance.chromosomes == result.chromosomes);
	}

	/**
	 * Test of getChromosome method, of class DefaultGenome.
	 */
	@Test
	public void testGetChromosome() {
		System.out.println("getChromosome");
		DefaultGenome<DefaultChromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		instance.getChromosome(0).setGenes(GENES);
		Object[] expResult = GENES.clone();
		Object[] result = instance.getChromosome(0).getGenes();
		assertArrayEquals(expResult, result);
	}

	/**
	 * Test of getFitness method, of class DefaultGenome.
	 */
	@Test
	public void testGetFitness() {
		System.out.println("getFitness");
		DefaultGenome<?> instance = new DefaultGenome<>();
		double expResult = 0.0;
		double result = instance.getFitness();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of getGene method, of class DefaultGenome.
	 */
	@Test
	public void testGetGene() {
		System.out.println("getGene");
		int index = 1;
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		instance.getChromosome(0).setGenes(GENES);
		Object expResult = GENE2;
		Object result = instance.getChromosome(0).getGene(index);
		assertEquals(expResult, result);
	}

	/**
	 * Test of itemsCount method, of class DefaultGenome.
	 */
	@Test
	public void testGetNumberOfGenes() {
		System.out.println("itemsCount");
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		instance.getChromosome(0).setGenes(GENES);
		int expResult = 4;
		int result = instance.getChromosome(0).size();
		assertEquals(expResult, result);
		assertEquals(1, instance.size());
	}

	/**
	 * Test of hasNext method, of class DefaultGenome.
	 */
	@Test
	public void testHasNext() {
		System.out.println("hasNext");
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		instance.getChromosome(0).setGenes(GENES);
		boolean result = instance.iterator().hasNext();
		assertEquals(true, result);
	}

	/**
	 * Test of iterator method, of class DefaultGenome.
	 */
	@Test
	public void testIterator() {
		System.out.println("iterator");
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		Iterator<?> result = instance.iterator();
		assertTrue(result != null);
	}

	/**
	 * Test of next method, of class DefaultGenome.
	 */
	@Test
	public void testNext() {
		System.out.println("next");
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		instance.getChromosome(0).setGenes(GENES);
		Object result = instance.iterator().next();
		assertTrue(result != null);
	}

	/**
	 * Test of remove method, of class DefaultGenome.
	 */
	@Test
	public void testRemove() {
		System.out.println("remove");
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		try {
			instance.iterator().remove();
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test of setChromosome method, of class DefaultGenome.
	 */
	@Test
	public void testSetChromosome() {
		System.out.println("setChromosome");
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		assertTrue(instance.getChromosome(0) != null);
	}

	/**
	 * Test of setFitness method, of class DefaultGenome.
	 */
	@Test
	public void testSetFitness() {
		System.out.println("setFitness");
		double fitness = 0.0;
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.setFitness(fitness);
		assertTrue(fitness == instance.getFitness());
	}

	/**
	 * Test of setGene method, of class DefaultGenome.
	 */
	@Test
	public void testSetGene() {
		System.out.println("setGene");
		int index = 2;
		Gene<Integer> newGene = new DefaultGene<>(0);
		DefaultGenome<Chromosome<Gene<?>>> instance = new DefaultGenome<>();
		instance.addChromosome(new DefaultChromosome<>());
		instance.getChromosome(0).setGenes(GENES);
		instance.getChromosome(0).setGene(index, newGene);
		assertEquals(newGene, instance.getChromosome(0).getGene(index));
	}

}

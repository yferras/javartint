package com.github.yferras.javartint.gea.function.recombination;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

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

import com.github.yferras.javartint.core.util.ValidationException;
import com.github.yferras.javartint.gea.GenomeConstants;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.gene.IntegerArrayGene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class RecombinationFunctionTest {

	public RecombinationFunctionTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		System.out.print(AbstractRecombinationFunction.class.getName().concat("."));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetProbability() throws Exception {
		System.out.println("getProbability");
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		function.setProbability(.5);
		assertEquals(new Double(.5), new Double(function.getProbability()));
	}

	@Test
	public void testSetProbability1() {
		System.out.println("setProbability (invalid argument)");
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		try {
			function.setProbability(-.5);
		} catch (ValidationException e) {
			assertTrue(true);
			return;
		}
		fail("IllegalArgumentException, not raised.");
	}

	@Test
	public void testSetProbability2() throws Exception {
		System.out.println("setProbability (valid argument)");
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		function.setProbability(.1);
		final Object result = function.getProbability();
		assertEquals(0.1, result);
	}

	@Test
	public void testDefaultCrossoverProbability() {
		System.out.println("DefaultCrossoverFunction");
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		final Object result = function.getProbability();
		assertEquals(0.75, result);
	}

	@SuppressWarnings("NullArgumentToVariableArgMethod")
	@Test
	public void testValidate1() {
		System.out.println("validate (params is null)");
		try {
			final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
			function.evaluate(null);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		fail("'IllegalArgumentException' not raised");
	}

	@Test
	public void testValidate2() {
		System.out.println("validate (params length is less than two)");
		try {
			final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
			function.evaluate(new DefaultGenome());
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEvaluate1() {
		System.out.println("evaluate (to invoke recombination process)");
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		DefaultGenome<DefaultChromosome<IntegerArrayGene>> genome1 = new DefaultGenome<>();
		genome1.addChromosome(new DefaultChromosome<IntegerArrayGene>());
		genome1.getChromosome(0).addGene(new IntegerArrayGene(new Integer[] { 1, 2, 3 }));
		DefaultGenome<DefaultChromosome<IntegerArrayGene>> genome2 = new DefaultGenome<>();
		genome2.addChromosome(new DefaultChromosome<IntegerArrayGene>());
		genome2.getChromosome(0).addGene(new IntegerArrayGene(new Integer[] { 4, 5, 7 }));
		Genome<? extends Chromosome<? extends Gene<?>>>[] result = function.evaluate(genome1, genome2);
		assertNull(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEvaluate2() {
		System.out.println("evaluate (two params are equals)");
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		DefaultGenome<DefaultChromosome<IntegerArrayGene>> genome1 = new DefaultGenome<>();
		genome1.addChromosome(new DefaultChromosome<IntegerArrayGene>());
		genome1.getChromosome(0).addGene(new IntegerArrayGene(new Integer[] { 1, 2, 3 }));
		DefaultGenome<DefaultChromosome<IntegerArrayGene>> genome2 = new DefaultGenome<>();
		genome2.addChromosome(new DefaultChromosome<IntegerArrayGene>());
		genome2.getChromosome(0).addGene(new IntegerArrayGene(new Integer[] { 1, 2, 3 }));
		Genome<? extends Chromosome<? extends Gene<?>>>[] result = function.evaluate(genome1, genome2);
		assertArrayEquals(new Genome[] { genome1, genome2 }, result);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEvaluate3() throws Exception {
		System.out.println("evaluate (probability constrain not meet)");
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		function.setProbability(0.0);
		DefaultGenome<DefaultChromosome<IntegerArrayGene>> genome1 = new DefaultGenome<>();
		genome1.addChromosome(new DefaultChromosome<IntegerArrayGene>());
		genome1.getChromosome(0).addGene(new IntegerArrayGene(new Integer[] { 1, 2, 3 }));
		DefaultGenome<DefaultChromosome<IntegerArrayGene>> genome2 = new DefaultGenome<>();
		genome2.addChromosome(new DefaultChromosome<IntegerArrayGene>());
		genome2.getChromosome(0).addGene(new IntegerArrayGene(new Integer[] { 4, 5, 7 }));
		Genome<? extends Chromosome<? extends Gene<?>>>[] result = function.evaluate(genome1, genome2);
		assertArrayEquals(new Genome[] { genome1, genome2 }, result);
	}

	@Test
	public void testSetRandomGenerator() throws Exception {
		final DefaultRecombinationFunction function = new DefaultRecombinationFunction();
		function.setRandom(GenomeConstants.RANDOM_GENERATOR_1);
		assertEquals(GenomeConstants.RANDOM_GENERATOR_1, function.getRandom());
	}

	private static class DefaultRecombinationFunction
			extends AbstractRecombinationFunction<Genome<? extends Chromosome<? extends Gene<?>>>> {

		private DefaultRecombinationFunction() {
			this.probability = 0.75;
			this.random = new Random() {
				@Override
				public int nextInt(int n) {
					return 0;
				}

				@Override
				public double nextDouble() {
					return 0.5;
				}
			};
		}

		@Override
		protected Genome<? extends Chromosome<? extends Gene<?>>>[] recombine(
				Genome<? extends Chromosome<? extends Gene<?>>> parent1,
				Genome<? extends Chromosome<? extends Gene<?>>> parent2) {
			return null;
		}

	}

}

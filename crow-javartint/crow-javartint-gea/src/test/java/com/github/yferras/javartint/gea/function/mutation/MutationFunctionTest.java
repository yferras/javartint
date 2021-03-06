package com.github.yferras.javartint.gea.function.mutation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class MutationFunctionTest {

	private static class DefaultMutationFunction
			extends AbstractMutationFunction<Genome<? extends Chromosome<? extends Gene<?>>>> {

		@Override
		protected Genome<? extends Chromosome<? extends Gene<?>>> mutate(
				Genome<? extends Chromosome<? extends Gene<?>>> subject) throws CloneNotSupportedException {
			subject.getChromosome(0).getGene(0).setData(null);
			return subject;
		}
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		System.out.print(AbstractMutationFunction.class.getName().concat("."));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testDefaultCrossoverProbability() {
		System.out.println("DefaultMutationFunction");
		final DefaultMutationFunction function = new DefaultMutationFunction();
		final Object result = function.getProbability();
		assertEquals(0.05, result);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEvaluate() throws Exception {
		System.out.println("evaluate (to invoke mutation process)");
		DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> genome = new DefaultGenome<>();
		genome.addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
		genome.getChromosome(0).setGenes(new DefaultGene[] { new DefaultGene(1), });
		final DefaultMutationFunction function = new DefaultMutationFunction();
		function.setRandom(GenomeConstants.RANDOM_GENERATOR_4);
		final Genome<? extends Chromosome<? extends Gene<?>>> result = function.evaluate(genome.clone());
		assertFalse(genome == result);
		assertNull(result.getChromosome(0).getGene(0).getData());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEvaluate2() throws Exception {
		System.out.println("evaluate (probability constrain not meet)");
		final DefaultMutationFunction function = new DefaultMutationFunction();
		function.setProbability(0.0);
		DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> genome = new DefaultGenome<>();
		genome.addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
		genome.getChromosome(0).setGenes(new DefaultGene[] { new DefaultGene(1), });
		final Genome<? extends Chromosome<? extends Gene<?>>> result = function.evaluate(genome.clone());
		assertFalse(genome == result);
		assertEquals(genome, result);
		assertNotNull(result.getChromosome(0).getGene(0).getData());
	}

	@Test
	public void testGetProbability() throws Exception {
		System.out.println("getProbability");
		final DefaultMutationFunction function = new DefaultMutationFunction();
		function.setProbability(.5);
		assertEquals(new Double(.5), new Double(function.getProbability()));
	}

	@Test
	public void testSetProbability1() {
		System.out.println("setProbability (invalid argument)");
		final DefaultMutationFunction function = new DefaultMutationFunction();
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
		final DefaultMutationFunction function = new DefaultMutationFunction();
		function.setProbability(.1);
		final Object result = function.getProbability();
		assertEquals(0.1, result);
	}

	@SuppressWarnings("NullArgumentToVariableArgMethod")
	@Test
	public void testValidate1() {
		System.out.println("validate (params is null)");
		try {
			final DefaultMutationFunction function = new DefaultMutationFunction();
			function.evaluate(null);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		fail("'IllegalArgumentException' not raised");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValidate2() {
		System.out.println("validate (params length is 0)");
		try {
			final DefaultMutationFunction function = new DefaultMutationFunction();
			function.evaluate(null);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		fail("'IllegalArgumentException' not raised");
	}
}

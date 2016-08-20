package com.github.yferras.javartint.gea.function.generator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.ByteArrayGene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;

public class BinaryGenomeGenFunctionTest {

	@Before
	public void setUp() throws Exception {
		System.out.print(BinaryGenomeGenFunction.class.getName().concat("."));
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testEvaluate() throws Exception {
		System.out.println("evaluate (performed algorithm)");
		BinaryGenomeGenFunction genFunction = new BinaryGenomeGenFunction(new int[] { 1, 7, 4 });
		DefaultGenome<DefaultChromosome<ByteArrayGene>> genome = genFunction.evaluate();
		assertEquals(1, genome.size());
		Assert.assertEquals(3, genome.getChromosome(0).size());
		assertEquals(1, genome.getChromosome(0).getGene(0).length());
		assertEquals(7, genome.getChromosome(0).getGene(1).length());
		assertEquals(4, genome.getChromosome(0).getGene(2).length());
	}
}
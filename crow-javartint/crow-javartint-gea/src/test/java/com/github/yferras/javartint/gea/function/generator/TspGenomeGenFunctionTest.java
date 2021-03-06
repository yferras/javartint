package com.github.yferras.javartint.gea.function.generator;

import static org.junit.Assert.assertEquals;

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
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;

public class TspGenomeGenFunctionTest {

	@Before
	public void setUp() throws Exception {
		System.out.print(TspGenomeGenFunction.class.getName().concat("."));
	}

	@Test
	public void testEvaluate() throws Exception {
		System.out.println("evaluate (performed algorithm)");
		TspGenomeGenFunction genFunction = new TspGenomeGenFunction(5);
		DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> genome = genFunction.evaluate();
		assertEquals(1, genome.size());
		assertEquals(5, genome.getChromosome(0).size());
	}
}
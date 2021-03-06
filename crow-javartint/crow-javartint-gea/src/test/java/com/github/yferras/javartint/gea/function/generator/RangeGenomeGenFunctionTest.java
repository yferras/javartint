package com.github.yferras.javartint.gea.function.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.github.yferras.javartint.core.util.Range;
import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;

public class RangeGenomeGenFunctionTest {

	@Before
	public void setUp() throws Exception {

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEvaluate() throws Exception {
		Range<Double> range1 = new Range<>(-1.0, 1.0);
		Range<Double> range2 = new Range<>(400.0, 500.0);
		Range<Double> range3 = new Range<>(-1000.0, -500.0);
		RangeGenomeGenFunction genFunction = new RangeGenomeGenFunction(3, range1, range2, range3);
		DefaultGenome<DefaultChromosome<DefaultGene<Double>>> genome = genFunction.evaluate();
		assertEquals(1, genome.size());
		assertEquals(3, genome.getChromosome(0).size());
		assertTrue(range1.accept(genome.getChromosome(0).getGene(0).getData()));
		assertTrue(range2.accept(genome.getChromosome(0).getGene(1).getData()));
		assertTrue(range3.accept(genome.getChromosome(0).getGene(2).getData()));
	}
}
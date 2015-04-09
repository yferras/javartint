package crow.javartint.gea.function.crossover;

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

import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.DefaultGenome;
import crow.javartint.gea.genome.Genome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DiscreteCrossoverFunctionTest {

	@Before
	public void setUp() {
		System.out.print(
			DiscreteCrossoverFunction.class.getName().concat("."));
	}

	@After
	public void tearDown() {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRecombine() throws Exception {
		System.out.println("recombine");
		DiscreteCrossoverFunction crossoverFunction = new DiscreteCrossoverFunction();

		Genome<Gene<Integer>> parent1 = new DefaultGenome<>();
		Genome<Gene<Integer>> parent2 = new DefaultGenome<>();

		Gene<Integer>[] genes1 = new DefaultGene[] {
			new DefaultGene<>(1),
			new DefaultGene<>(3),
			new DefaultGene<>(5),
			new DefaultGene<>(7),
			new DefaultGene<>(9),
		};

		Gene<Integer>[] genes2 = new DefaultGene[] {
			new DefaultGene<>(0),
			new DefaultGene<>(2),
			new DefaultGene<>(4),
			new DefaultGene<>(6),
			new DefaultGene<>(8),
		};

		parent1.setChromosome(genes1);
		parent2.setChromosome(genes2);

		crossoverFunction.setRandom(new Random(){
			private final int [] values = { 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 };
			private int index = 0;
			@Override
			public int nextInt(int n) {
				return values[index++];
			}
		});

		Genome<Gene<Integer>>[] result = crossoverFunction.recombine(parent1, parent2);
		Genome<Gene<Integer>>[] expected = new DefaultGenome[2];
		Gene<Integer>[] expected1 = new DefaultGene[] {
			new DefaultGene<>(1),
			new DefaultGene<>(3),
			new DefaultGene<>(4),
			new DefaultGene<>(6),
			new DefaultGene<>(9),
		};

		Gene<Integer>[] expected2 = new DefaultGene[] {
			new DefaultGene<>(1),
			new DefaultGene<>(2),
			new DefaultGene<>(4),
			new DefaultGene<>(7),
			new DefaultGene<>(9),
		};

		expected[0] = new DefaultGenome<>();
		expected[0].setChromosome(expected1);
		expected[1] = new DefaultGenome<>();
		expected[1].setChromosome(expected2);

		assertArrayEquals(expected, result);
	}
}
package com.github.yferras.javartint.gea.function.recombination.tsp;

import static org.junit.Assert.assertArrayEquals;

import java.util.Random;

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

import com.github.yferras.javartint.gea.genome.TspGenome;

public class PermutationRecombinationFunctionTest {

	@Test
	public void testRecombine() throws Exception {
		PermutationRecombinationFunction<TspGenome> function = new PermutationRecombinationFunction<>();
		function.setRandom(new Random() {
			private int index = 0;
			private int[] positions = { 4, 3, 7 };

			@Override
			public int nextInt(int n) {
				return positions[index++];
			}
		});

		TspGenome genomeA = new TspGenome(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
		TspGenome genomeB = new TspGenome(0, 9, 8, 7, 6, 5, 4, 3, 2, 1);

		final TspGenome[] offspring = function.recombine(genomeA, genomeB);

		TspGenome expectedA = new TspGenome(1, 2, 3, 7, 5, 6, 4, 8, 9, 0);
		TspGenome expectedB = new TspGenome(0, 9, 8, 4, 6, 5, 7, 3, 2, 1);

		assertArrayEquals(offspring, new TspGenome[] { expectedA, expectedB });
	}
}
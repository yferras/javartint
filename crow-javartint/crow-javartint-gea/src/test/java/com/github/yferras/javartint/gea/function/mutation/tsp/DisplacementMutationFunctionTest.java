package com.github.yferras.javartint.gea.function.mutation.tsp;

import java.util.Random;

import org.junit.Assert;
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

public class DisplacementMutationFunctionTest {

	@Test
	public void testMutate() throws Exception {
		DisplacementMutationFunction<TspGenome> function = new DisplacementMutationFunction<>(4);
		TspGenome genome = new TspGenome(8, 5, 2, 1, 6, 3, 7, 0, 9, 4);
		function.setRandom(new Random() {
			private int[] positions = { 3, 4 };
			private int index = 0;

			@Override
			public int nextInt(int n) {
				return positions[index++];
			}
		});
		genome = function.mutate(genome);
		TspGenome expected = new TspGenome(8, 5, 2, 0, 1, 6, 3, 7, 9, 4);

		Assert.assertArrayEquals(expected.getChromosome().getGenes(), genome.getChromosome().getGenes());

	}
}
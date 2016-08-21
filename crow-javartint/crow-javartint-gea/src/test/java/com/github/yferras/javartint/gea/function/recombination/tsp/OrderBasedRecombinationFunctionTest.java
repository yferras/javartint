package com.github.yferras.javartint.gea.function.recombination.tsp;

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

public class OrderBasedRecombinationFunctionTest {

	@Test
	public void testRecombine() throws Exception {
		OrderBasedRecombinationFunction<TspGenome> recombinationFunction = new OrderBasedRecombinationFunction<>();
		recombinationFunction.setRandom(

				new Random() {

					private int index = 0;
					private int[] positions = { 1, 0, 2, 4 };

					@Override
					public int nextInt(int n) {
						return positions[index++];
					}
				});

		final TspGenome genome1 = new TspGenome(2, 5, 0, 3, 6, 1, 4, 7);
		final TspGenome genome2 = new TspGenome(3, 4, 0, 7, 2, 5, 1, 6);
		final TspGenome[] offspring = recombinationFunction.recombine(genome1, genome2);

		final TspGenome expected0 = new TspGenome(2, 4, 0, 3, 6, 1, 5, 7);
		final TspGenome expected1 = new TspGenome(3, 4, 5, 7, 2, 0, 1, 6);

		Assert.assertArrayEquals(expected0.getChromosome().getGenes(), offspring[0].getChromosome().getGenes());
		Assert.assertArrayEquals(expected1.getChromosome().getGenes(), offspring[1].getChromosome().getGenes());
	}
}
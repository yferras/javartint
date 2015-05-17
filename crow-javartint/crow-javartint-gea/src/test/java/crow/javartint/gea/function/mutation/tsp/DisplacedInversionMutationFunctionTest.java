package crow.javartint.gea.function.mutation.tsp;

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

import crow.javartint.gea.genome.TspGenome;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DisplacedInversionMutationFunctionTest {

	@Test
	public void testMutate() throws Exception {
		DisplacedInversionMutationFunction<TspGenome> function =
			new DisplacedInversionMutationFunction<>(4);
		function.setRandom(
			new Random() {
				private int[] positions = {3, 4};
				private int index;
				@Override
				public int nextInt(int n) {
					return positions[index++];
				}
			}
		);
		TspGenome genome = new TspGenome(8, 5, 2, 1, 6, 3, 7, 0, 9, 4);

		genome = function.mutate(genome);

		final TspGenome expected = new TspGenome(8, 5, 2, 0, 7, 3, 6, 1, 9, 4);

		assertArrayEquals(
			expected.getChromosome().getGenes(),
			genome.getChromosome().getGenes()
		);
	}
}
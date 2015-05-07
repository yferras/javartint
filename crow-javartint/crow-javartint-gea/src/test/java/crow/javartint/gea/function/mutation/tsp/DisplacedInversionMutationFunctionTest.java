package crow.javartint.gea.function.mutation.tsp;

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
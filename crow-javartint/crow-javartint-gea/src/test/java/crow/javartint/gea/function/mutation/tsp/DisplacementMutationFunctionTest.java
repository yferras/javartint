package crow.javartint.gea.function.mutation.tsp;

import crow.javartint.gea.genome.TspGenome;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DisplacementMutationFunctionTest {

	@Test
	public void testMutate() throws Exception {
		DisplacementMutationFunction function = new DisplacementMutationFunction(4);
		TspGenome genome = new TspGenome(8, 5, 2, 1, 6, 3, 7, 0, 9, 4);
		function.setRandom(
			new Random() {
				private int [] positions = {3, 4};
				private int index = 0;
				@Override
				public int nextInt(int n) {
					return positions[index++];
				}
			}
		);
		genome = function.mutate(genome);
		TspGenome expected = new TspGenome(8, 5, 2, 0, 1, 6, 3, 7, 9, 4);

		assertArrayEquals(
			expected.getChromosome().getGenes(),
			genome.getChromosome().getGenes()
		);

	}
}
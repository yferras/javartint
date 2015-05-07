package crow.javartint.gea.function.mutation.tsp;

import crow.javartint.gea.genome.TspGenome;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Random;

public class ExchangeMutationFunctionTest {

	@Test
	public void testMutate() throws Exception {
		ExchangeMutationFunction<TspGenome> function = new ExchangeMutationFunction<>();
		function.setRandom(
			new Random() {
				private int[] positions = {5, 1};
				private int index = 0;
				@Override
				public int nextInt(int n) {
					return positions[index++];
				}
			}
		);

		TspGenome genome = new TspGenome(8, 5, 2, 1, 6, 3, 7, 0, 9, 4);

		genome = function.mutate(genome);

		TspGenome expected = new TspGenome(8, 3, 2, 1, 6, 5, 7, 0, 9, 4);

		assertArrayEquals(
			expected.getChromosome().getGenes(),
			genome.getChromosome().getGenes()
		);
	}
}
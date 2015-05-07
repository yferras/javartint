package crow.javartint.gea.function.mutation.tsp;

import crow.javartint.gea.genome.TspGenome;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class InversionMutationFunctionTest {

	@Test
	public void testMutate() throws Exception {
		InversionMutationFunction<TspGenome> function = new InversionMutationFunction<>(4);
		function.setRandom(
			new Random() {
				@Override
				public int nextInt(int n) {
					return 3;
				}
			}
		);
		TspGenome genome = new TspGenome(8, 5, 2, 1, 6, 3, 7, 0, 9, 4);

		genome = function.mutate(genome);

		TspGenome expected = new TspGenome(8, 5, 2, 7, 3, 6, 1, 0, 9, 4);

		assertArrayEquals(
			expected.getChromosome().getGenes(),
			genome.getChromosome().getGenes()
		);
	}
}
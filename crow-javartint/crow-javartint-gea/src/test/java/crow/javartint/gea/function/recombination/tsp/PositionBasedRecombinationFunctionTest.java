package crow.javartint.gea.function.recombination.tsp;

import crow.javartint.gea.genome.TspGenome;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class PositionBasedRecombinationFunctionTest {

	@Test
	public void testRecombine() throws Exception {
		PositionBasedRecombinationFunction<TspGenome> function =
			new PositionBasedRecombinationFunction<>();

		function.setRandom(
			new Random() {
				private int[] positions = {4, 3, 7};
				private int index = 0;

				@Override
				public int nextInt(int n) {
					return positions[index++];
				}
			}
		);

		TspGenome genomeA = new TspGenome(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
		TspGenome genomeB = new TspGenome(0, 9, 8, 7, 6, 5, 4, 3, 2, 1);

		final TspGenome[] genomes = function.recombine(genomeA, genomeB);


		TspGenome[] expecteds = new TspGenome[]{
			new TspGenome(5, 9, 0, 8, 7, 6, 4, 3, 2, 1),
			new TspGenome(6, 2, 1, 3, 4, 5, 7, 8, 9, 0)
		};
		Assert.assertArrayEquals(expecteds, genomes);
	}
}
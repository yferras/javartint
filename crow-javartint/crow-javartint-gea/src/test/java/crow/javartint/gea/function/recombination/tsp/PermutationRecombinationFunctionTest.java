package crow.javartint.gea.function.recombination.tsp;

import crow.javartint.gea.genome.TspGenome;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PermutationRecombinationFunctionTest {

	@Test
	public void testRecombine() throws Exception {
		PermutationRecombinationFunction<TspGenome> function = new PermutationRecombinationFunction<>();
		function.setRandom(
			new Random() {
				private int [] positions = {4,3,7};
				private int index = 0;
				@Override
				public int nextInt(int n) {
					return positions[index++];
				}
			}
		);

		TspGenome genomeA = new TspGenome(1,2,3,4,5,6,7,8,9,0);
		TspGenome genomeB = new TspGenome(0,9,8,7,6,5,4,3,2,1);

		final TspGenome[] offspring = function.recombine(genomeA, genomeB);

		TspGenome expectedA = new TspGenome(1,2,3,7,5,6,4,8,9,0);
		TspGenome expectedB = new TspGenome(0,9,8,4,6,5,7,3,2,1);

		assertArrayEquals(offspring, new TspGenome[]{expectedA, expectedB});
	}
}
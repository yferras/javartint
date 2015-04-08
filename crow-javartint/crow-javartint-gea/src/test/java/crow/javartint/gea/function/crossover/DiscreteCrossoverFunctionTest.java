package crow.javartint.gea.function.crossover;

import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.DefaultGenome;
import crow.javartint.gea.genome.Genome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DiscreteCrossoverFunctionTest {

	@Before
	public void setUp() {
		System.out.print(
			DiscreteCrossoverFunction.class.getName().concat("."));
	}

	@After
	public void tearDown() {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRecombine() throws Exception {
		System.out.println("recombine");
		DiscreteCrossoverFunction crossoverFunction = new DiscreteCrossoverFunction();

		Genome<Gene<Integer>> parent1 = new DefaultGenome<>();
		Genome<Gene<Integer>> parent2 = new DefaultGenome<>();

		Gene<Integer>[] genes1 = new DefaultGene[] {
			new DefaultGene<>(1),
			new DefaultGene<>(3),
			new DefaultGene<>(5),
			new DefaultGene<>(7),
			new DefaultGene<>(9),
		};

		Gene<Integer>[] genes2 = new DefaultGene[] {
			new DefaultGene<>(0),
			new DefaultGene<>(2),
			new DefaultGene<>(4),
			new DefaultGene<>(6),
			new DefaultGene<>(8),
		};

		parent1.setChromosome(genes1);
		parent2.setChromosome(genes2);

		crossoverFunction.setRandom(new Random(){
			private final int [] values = { 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 };
			private int index = 0;
			@Override
			public int nextInt(int n) {
				return values[index++];
			}
		});

		Genome<Gene<Integer>>[] result = crossoverFunction.recombine(parent1, parent2);
		Genome<Gene<Integer>>[] expected = new DefaultGenome[2];
		Gene<Integer>[] expected1 = new DefaultGene[] {
			new DefaultGene<>(1),
			new DefaultGene<>(3),
			new DefaultGene<>(4),
			new DefaultGene<>(6),
			new DefaultGene<>(9),
		};

		Gene<Integer>[] expected2 = new DefaultGene[] {
			new DefaultGene<>(1),
			new DefaultGene<>(2),
			new DefaultGene<>(4),
			new DefaultGene<>(7),
			new DefaultGene<>(9),
		};

		expected[0] = new DefaultGenome<>();
		expected[0].setChromosome(expected1);
		expected[1] = new DefaultGenome<>();
		expected[1].setChromosome(expected2);

		assertArrayEquals(expected, result);
	}
}
package crow.javartint.gea.function.mutation.binary;

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

import crow.javartint.gea.GenomeConstants;
import crow.javartint.gea.chromosome.DefaultChromosome;
import crow.javartint.gea.gene.ByteArrayGene;
import crow.javartint.gea.genome.BinaryGenome;
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class BinaryMutationFunctionTest {

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		System.out.print(BinaryMutationFunction.class.getName().concat("."));
	}

	@After
	public void tearDown() {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEvaluate() throws CloneNotSupportedException {
		System.out.println("evaluate (performed algorithm)");
		BinaryGenome genome = new BinaryGenome();
		genome.addChromosome(new DefaultChromosome<ByteArrayGene>());
		ByteArrayGene[] genes = new ByteArrayGene[]{
			new ByteArrayGene(new Byte[]{0}),
			new ByteArrayGene(new Byte[]{1, 1, 0, 1, 0, 0}),
			new ByteArrayGene(new Byte[]{1, 0, 0, 1}),
		};
		genome.getChromosome(0).setGenes(genes);

		final BinaryGenome clone = genome.clone();
		clone.getChromosome(0).getGene(0).setAllele(0, (byte) 1);
		clone.getChromosome(0).getGene(1).setAllele(3, (byte) 0);
		clone.getChromosome(0).getGene(2).setAllele(2, (byte) 1);

		BinaryMutationFunction<BinaryGenome> function = new
			BinaryMutationFunction<>();
		function.setRandom(GenomeConstants.RANDOM_GENERATOR_5);
		genome = function.evaluate(genome);
		assertArrayEquals(clone.getChromosome(0).getGenes(), genome.getChromosome(0).getGenes());
	}


}
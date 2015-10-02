package com.github.yferras.javartint.gea.function.recombination;

/*
* #%L
 * * Crow JavArtInt GEA
 * *
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * *
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

import com.github.yferras.javartint.gea.GenomeConstants;
import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;
import com.github.yferras.javartint.gea.genome.Genome;
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class SinglePointRecombinationFunctionTest {

    public SinglePointRecombinationFunctionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(
            SinglePointRecombinationFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSinglePointCrossoverFunction1() {
        System.out.println("SinglePointCrossoverFunction(probability)");
        final Double probability = new SinglePointRecombinationFunction<>(1.0)
            .getProbability();
        assertEquals(new Double(1.0), probability);
    }

    @Test
    public void testSinglePointCrossoverFunction2() {
        System.out.println("SinglePointCrossoverFunction()");
        final Double probability = new SinglePointRecombinationFunction<>()
            .getProbability();
        assertEquals(new Double(.75), probability);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate() {
        System.out.println("evaluate (performed algorithm)");
        SinglePointRecombinationFunction<DefaultGenome<DefaultChromosome<DefaultGene<Integer>>>> function =
            new SinglePointRecombinationFunction<>(0.75, GenomeConstants.RANDOM_GENERATOR_1);
        Genome<DefaultChromosome<DefaultGene<Integer>>>[] result = function.evaluate(GenomeConstants.GENOMES);
        DefaultGenome<DefaultChromosome<DefaultGene<Integer>>>[] expResult = new DefaultGenome[2];
        expResult[0] = new DefaultGenome<>();
        expResult[0].addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
        expResult[1] = new DefaultGenome<>();
        expResult[1].addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
        for (int i = 0; i < GenomeConstants.CHROMOSOME_SIZE; i++) {
            if (i < function.getRandom().nextInt(GenomeConstants.CHROMOSOME_SIZE)) {
                expResult[0].getChromosome(0).addGene(new DefaultGene<>(GenomeConstants.CHROMOSOME_SIZE - i));
                expResult[1].getChromosome(0).addGene(new DefaultGene<>(i));
            } else {
                expResult[1].getChromosome(0).addGene(new DefaultGene<>(GenomeConstants.CHROMOSOME_SIZE - i));
                expResult[0].getChromosome(0).addGene(new DefaultGene<>(i));
            }
        }
        assertArrayEquals(result[0].getChromosome(0).getGenes(),
            expResult[0].getChromosome(0).getGenes());
        assertArrayEquals(result[1].getChromosome(0).getGenes(),
            expResult[1].getChromosome(0).getGenes());
    }

}

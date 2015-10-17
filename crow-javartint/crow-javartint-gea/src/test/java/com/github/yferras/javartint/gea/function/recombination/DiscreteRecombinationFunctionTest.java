package com.github.yferras.javartint.gea.function.recombination;

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

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.DefaultGenome;
import com.github.yferras.javartint.gea.genome.Genome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class DiscreteRecombinationFunctionTest {

    @Before
    public void setUp() {
        System.out.print(
            DiscreteRecombinationFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testRecombine() throws Exception {
        System.out.println("recombine");
        DiscreteRecombinationFunction crossoverFunction = new DiscreteRecombinationFunction();

        Genome<Chromosome<Gene<Integer>>> parent1 = new DefaultGenome<>();
        Genome<Chromosome<Gene<Integer>>> parent2 = new DefaultGenome<>();

        Gene<Integer>[] genes1 = new DefaultGene[]{
            new DefaultGene<>(1),
            new DefaultGene<>(3),
            new DefaultGene<>(5),
            new DefaultGene<>(7),
            new DefaultGene<>(9),
        };

        Gene<Integer>[] genes2 = new DefaultGene[]{
            new DefaultGene<>(0),
            new DefaultGene<>(2),
            new DefaultGene<>(4),
            new DefaultGene<>(6),
            new DefaultGene<>(8),
        };

        parent1.addChromosome(new DefaultChromosome<Gene<Integer>>());
        parent2.addChromosome(new DefaultChromosome<Gene<Integer>>());
        parent1.getChromosome(0).setGenes(genes1);
        parent2.getChromosome(0).setGenes(genes2);

        crossoverFunction.setRandom(new Random() {
            private final int[] values = {0, 0, 0, 1, 1, 1, 1, 0, 0, 0};
            private int index = 0;

            @Override
            public int nextInt(int n) {
                return values[index++];
            }
        });

        Genome<Chromosome<Gene<Integer>>>[] result = crossoverFunction.recombine(parent1, parent2);
        Genome<Chromosome<Gene<Integer>>>[] expected = new DefaultGenome[2];
        Gene<Integer>[] expected1 = new DefaultGene[]{
            new DefaultGene<>(1),
            new DefaultGene<>(3),
            new DefaultGene<>(4),
            new DefaultGene<>(6),
            new DefaultGene<>(9),
        };

        Gene<Integer>[] expected2 = new DefaultGene[]{
            new DefaultGene<>(1),
            new DefaultGene<>(2),
            new DefaultGene<>(4),
            new DefaultGene<>(7),
            new DefaultGene<>(9),
        };

        expected[0] = new DefaultGenome<>();
        expected[0].addChromosome(new DefaultChromosome<Gene<Integer>>());
        expected[0].getChromosome(0).setGenes(expected1);
        expected[1] = new DefaultGenome<>();
        expected[1].addChromosome(new DefaultChromosome<Gene<Integer>>());
        expected[1].getChromosome(0).setGenes(expected2);

        assertArrayEquals(expected, result);
    }
}
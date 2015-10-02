package com.github.yferras.javartint.gea.function.recombination.tsp;

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

import com.github.yferras.javartint.gea.function.recombination.AbstractRecombinationFunction;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.TspGenome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p/>
 * Specific recombination function in TSP.
 * <p/>
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class OrderBasedRecombinationFunction<T extends TspGenome>
    extends AbstractRecombinationFunction<T> {


    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of recombination
     * @param random      random instance
     */
    public OrderBasedRecombinationFunction(double probability, Random random) {
        super(probability, random);
    }

    /**
     * Constructor, initializes instances with probability of recombination
     * specified by {@code probability} parameter and random is an
     * instance of {@link java.util.Random}.
     *
     * @param probability probability of recombination
     */
    public OrderBasedRecombinationFunction(double probability) {
        this(probability, new Random());
    }

    /**
     * Default constructor, initializes instances with probability of recombination
     * equals to {@code .75} and random generator is an instance of {@link java.util.Random}.
     */
    public OrderBasedRecombinationFunction() {
        this(.75);
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected T[] recombine(T parent1, T parent2)
        throws CloneNotSupportedException {
        TspGenome[] offspring = new TspGenome[]{
            parent1.clone(),
            parent2.clone()
        };

        List<DefaultGene<Integer>>[] genes = new List[]{
            new ArrayList<>(),
            new ArrayList<>()
        };

        int n = parent1.getChromosome().size();
        int pos = getRandom().nextInt(n - 1);
        while (pos < n) {
            genes[0].add(offspring[0].getChromosome().getGene(pos));
            genes[1].add(offspring[1].getChromosome().getGene(pos));
            pos += getRandom().nextInt(n - pos) + 1;
        }

        int auxPos0 = 0;
        int auxPos1 = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < genes[0].size(); k++) {
                if (offspring[1].getChromosome().getGene(j).equals(genes[0].get(k))) {
                    offspring[1].getChromosome().setGene(j, genes[0].get(auxPos0++));
                    break;
                }
            }
            for (int k = 0; k < genes[1].size(); k++) {
                if (offspring[0].getChromosome().getGene(j).equals(genes[1].get(k))) {
                    offspring[0].getChromosome().setGene(j, genes[1].get(auxPos1++));
                    break;
                }
            }
        }
        return (T[]) offspring;
    }
}

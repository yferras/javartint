package com.github.yferras.javartint.gea.function.recombination.numeric;

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

import com.github.yferras.javartint.core.util.ValidationException;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;

import java.util.Random;


/**
 * Specific recombination function to real valued genomes.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}<code>&lt;? extends </code>
 *            {@link com.github.yferras.javartint.gea.gene.Gene}<code>&lt;Double&gt;&gt;</code>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class LineRecombinationFunction<T extends Genome<? extends Chromosome<? extends Gene<Double>>>>
    extends IntermediateRecombinationFunction<T> {

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of mutation
     * @param random      random instance
     * @param distance    defines the length of the extra-segment for possible offspring.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public LineRecombinationFunction(double probability, Random random, double distance) throws ValidationException {
        super(probability, random, distance);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random}.
     *
     * @param probability probability of mutation
     * @param distance    defines the length of the extra-segment for possible offspring.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public LineRecombinationFunction(double probability, double distance) throws ValidationException {
        super(probability, distance);
    }


    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.75</code>.
     *
     * @param distance defines the length of the extra-segment for possible offspring.
     */
    public LineRecombinationFunction(double distance) {
        super(distance);
    }

    /**
     * Default constructor.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * <code>probability = 0.75</code> and <code>distance = 0.25</code>.
     */
    public LineRecombinationFunction() {
        this(DEFAULT_DISTANCE);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
        int numberOfGenes = parent1.size();
        Genome<? extends Chromosome<? extends Gene<Double>>>[] offspring = new Genome[]{
            ((Genome) parent1).clone(),
            ((Genome) parent2).clone()
        };
        double a = getRandom().nextDouble() * (1.0 + 2.0 * getDistance()) - getDistance();
        Chromosome<? extends Gene<Double>> chromosome0 = offspring[0].getChromosome(0);
        Chromosome<? extends Gene<Double>> chromosome1 = offspring[1].getChromosome(0);
        for (int i = 0; i < numberOfGenes; i++) {
            double value0 = chromosome0.getGene(i).getData();
            double value1 = chromosome1.getGene(i).getData();
            double newValue0 = value0 * a + value1 * (1.0 - a);
            double newValue1 = value1 * a + value0 * (1.0 - a);
            chromosome0.getGene(i).setData(newValue0);
            chromosome1.getGene(i).setData(newValue1);
        }
        return (T[]) offspring;
    }
}

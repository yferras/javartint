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
import com.github.yferras.javartint.gea.function.recombination.AbstractRecombinationFunction;
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
public class ExtendedLineRecombinationFunction<T extends Genome<? extends Chromosome<? extends Gene<Double>>>>
    extends AbstractRecombinationFunction<T> {

    /** Constant <code>DEFAULT_RANGE=.1</code> */
    public static final double DEFAULT_RANGE = .1;
    /** Constant <code>DEFAULT_PRECISION=6</code> */
    public static final double DEFAULT_PRECISION = 6;

    private double[] ranges;
    private double precision;

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of recombination
     * @param random      random instance
     * @param ranges      array with the recombination ranges, one per dimension. (Typical values from 0.1 to 10E-6)
     * @param precision   recombination precisions. (Typical values from 4 to 20)
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public ExtendedLineRecombinationFunction(double probability, Random random, double[] ranges,
                                             double precision) throws ValidationException {
        super(probability, random);
        this.ranges = ranges;
        this.precision = precision;
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random}.
     *
     * @param probability probability of recombination
     * @param ranges      array with the recombination ranges, one per dimension.
     * @param precision   recombination precisions.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public ExtendedLineRecombinationFunction(double probability, double[] ranges, double precision) throws ValidationException {
        this(probability, new Random(), ranges, precision);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.75</code>.
     *
     * @param ranges    array with the recombination ranges, one per dimension.
     * @param precision recombination precisions.
     */
    public ExtendedLineRecombinationFunction(double[] ranges, double precision) {
        super();
        this.ranges = ranges;
        this.precision = precision;
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.75</code>. This is useful when all dimensions have the
     * same values.
     *
     * @param range     recombination range.
     * @param length    the number of dimensions.
     * @param precision recombination precisions.
     */
    public ExtendedLineRecombinationFunction(double range, int length, double precision) {
        super();
        ranges = new double[length];
        this.precision = precision;
        for (int i = 0; i < length; i++) {
            ranges[i] = range;
        }
    }

    /**
     * Constructor, initializes instances with the given parameter.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * <code>probability = 0.75</code>, <code>range = 0.1</code> and <code>precision = 6</code>.
     *
     * @param length the number of dimensions.
     */
    public ExtendedLineRecombinationFunction(int length) {
        this(DEFAULT_RANGE, length, DEFAULT_PRECISION);
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
        double a = Math.pow(2, -precision * getRandom().nextDouble());
        Chromosome<? extends Gene<Double>> chromosome0 = offspring[0].getChromosome(0);
        Chromosome<? extends Gene<Double>> chromosome1 = offspring[1].getChromosome(0);
        for (int i = 0; i < numberOfGenes; i++) {
            double value0 = chromosome0.getGene(i).getData();
            double value1 = chromosome1.getGene(i).getData();
            double s = getRandom().nextInt(2) == 0 ? -1.0 : 1.0;
            double diff = value0 - value1;
            double newValue0 = value0 + s * ranges[i] * a * (diff / Math.abs(diff));
            double newValue1 = value1 + s * ranges[i] * a * (diff / Math.abs(diff));
            chromosome0.getGene(i).setData(newValue0);
            chromosome1.getGene(i).setData(newValue1);
        }
        return (T[]) offspring;
    }
}

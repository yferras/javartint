package com.github.yferras.javartint.gea.function.mutation.numeric;

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
import com.github.yferras.javartint.gea.function.mutation.AbstractMutationFunction;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;

import java.util.Random;

/**
 * Specific mutation function to real valued genomes.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}<code>&lt;? extends </code>
 *            {@link com.github.yferras.javartint.gea.gene.Gene}<code>&lt;Double&gt;&gt;</code>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class RealValuedMutationFunction<T extends Genome<? extends Chromosome<? extends Gene<Double>>>>
    extends AbstractMutationFunction<T> {

    private double[] ranges;
    private double[] precisions;

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of mutation
     * @param random      random instance
     * @param ranges      array with the mutation ranges, one per dimension. (Typical values from 0.1 to 10E-6)
     * @param precisions  array with mutation precisions, one per dimension. (Typical values from 4 to 20)
     * @throws java.lang.IllegalArgumentException if the length of arrays is not the same.
     */
    public RealValuedMutationFunction(double probability, Random random, double[] ranges,
                                      double[] precisions) {
        super(probability, random);
        if (precisions.length != ranges.length) {
            throw new IllegalArgumentException("the length of 'precisions' and 'ranges' mismatch.");
        }
        this.ranges = ranges;
        this.precisions = precisions;
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random}.
     *
     * @param probability probability of mutation
     * @param ranges      array with the mutation ranges, one per dimension.
     * @param precisions  array with mutation precisions, one per dimension.
     * @throws java.lang.IllegalArgumentException if the length of arrays is not the same.
     */
    public RealValuedMutationFunction(double probability, double[] ranges, double[] precisions) {
        this(probability, new Random(), ranges, precisions);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.05</code>.
     *
     * @param ranges     array with the mutation ranges, one per dimension.
     * @param precisions array with mutation precisions, one per dimension.
     * @throws java.lang.IllegalArgumentException if the length of arrays is not the same.
     */
    public RealValuedMutationFunction(double[] ranges, double[] precisions) {
        this(.05, ranges, precisions);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.05</code>. This is useful when all dimensions have the
     * same values.
     *
     * @param mutationRange     mutation range.
     * @param mutationPrecision mutation precisions.
     * @param length            the number of dimensions.
     */
    public RealValuedMutationFunction(double mutationRange, double mutationPrecision, int length) {
        super();
        ranges = new double[length];
        precisions = new double[length];
        for (int i = 0; i < length; i++) {
            ranges[i] = mutationRange;
            precisions[i] = mutationPrecision;
        }
    }

    /**
     * Constructor, initializes instances with the given parameter.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * <code>probability = 0.05</code>, <code>mutationRange = 0.1</code> and <code>mutationPrecision = 6</code>.
     *
     * @param length the number of dimensions.
     */
    public RealValuedMutationFunction(int length) {
        this(.1, 6, length);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Ensures that parameters are valid.
     */
    @Override
    protected void validate(T param) throws IllegalArgumentException {
        super.validate(param);
        if (param.size() != ranges.length) {
            throw new IllegalArgumentException("the size of genome and the length of 'precisions' and 'ranges' mismatch.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected T mutate(T subject) throws CloneNotSupportedException {
        int i = 0;
        for (Chromosome<? extends Gene<Double>> chromosome : subject) {
            int index = getRandom().nextInt(chromosome.size());
            double r = ranges[i];
            double k = precisions[i++];
            double u = getRandom().nextDouble();
            int s = getRandom().nextInt(2) == 0 ? -1 : 1;
            double a = Math.pow(2.0, -u * k);
            final Gene<Double> gene = chromosome.getGene(index);
            gene.setData(gene.getData() + s * r * a);
        }
        return subject;
    }

    /**
     * Gets the mutation range from index.
     *
     * @param index index
     * @return mutation range.
     */
    public double getMutationRange(int index) {
        return ranges[index];
    }

    /**
     * Gets the mutation precision from index.
     *
     * @param index index
     * @return mutation precision
     */
    public double getMutationPrecision(int index) {
        return precisions[index];
    }


}

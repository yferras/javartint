package crow.javartint.gea.function.mutation.numeric;

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

import crow.javartint.gea.function.mutation.AbstractMutationFunction;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.Random;

/**
 * Specific mutation function to real valued genomes.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}<code>&lt;? extends </code>
 * {@link crow.javartint.gea.gene.Gene}<code>&lt;Double&gt;&gt;</code>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class RealValuedMutationFunction<T extends Genome<? extends Gene<Double>>>
        extends AbstractMutationFunction<T> {

    private double[] mutationRanges;
    private double[] mutationPrecisions;

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability        probability of mutation
     * @param random             random instance
     * @param mutationRanges     array with the mutation ranges, one per dimension.
     * @param mutationPrecisions array with mutation precisions, one per dimension.
     * @throws java.lang.IllegalArgumentException if the length of arrays is not the same.
     */
    public RealValuedMutationFunction(double probability, Random random, double[] mutationRanges,
                                      double[] mutationPrecisions) {
        super(probability, random);
        if (mutationPrecisions.length != mutationRanges.length) {
            throw new IllegalArgumentException("the length of 'mutationPrecisions' and 'mutationRanges' mismatch.");
        }
        this.mutationRanges = mutationRanges;
        this.mutationPrecisions = mutationPrecisions;
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random}.
     *
     * @param probability        probability of mutation
     * @param mutationRanges     array with the mutation ranges, one per dimension.
     * @param mutationPrecisions array with mutation precisions, one per dimension.
     * @throws java.lang.IllegalArgumentException if the length of arrays is not the same.
     */
    public RealValuedMutationFunction(double probability, double[] mutationRanges, double[] mutationPrecisions) {
        this(probability, new Random(), mutationRanges, mutationPrecisions);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.05</code>.
     *
     * @param mutationRanges     array with the mutation ranges, one per dimension.
     * @param mutationPrecisions array with mutation precisions, one per dimension.
     * @throws java.lang.IllegalArgumentException if the length of arrays is not the same.
     */
    public RealValuedMutationFunction(double[] mutationRanges, double[] mutationPrecisions) {
        this(.05, mutationRanges, mutationPrecisions);
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
        mutationRanges = new double[length];
        mutationPrecisions = new double[length];
        for (int i = 0; i < length; i++) {
            mutationRanges[i] = mutationRange;
            mutationPrecisions[i] = mutationPrecision;
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
     * Ensures that parameters are valid.
     *
     * @param param parameter to validate.
     * @throws IllegalArgumentException if <code>param</code> is <code>null</code>,
     *                                  or the size of genome differs to the length of <code>mutationRanges</code>
     */
    @Override
    protected void validate(T param) throws IllegalArgumentException {
        super.validate(param);
        if (param.size() != mutationRanges.length) {
            throw new IllegalArgumentException("the size of genome and the length of 'mutationPrecisions' and 'mutationRanges' mismatch.");
        }
    }

    @Override
    protected T mutate(T subject) throws CloneNotSupportedException {
        boolean muted = false;
        do {
            int i = 0;
            for (Gene<Double> gene : subject) {
                if (getRandom().nextDouble() < getProbability()) {
                    double r = mutationRanges[i];
                    double k = mutationPrecisions[i];
                    double u = getRandom().nextDouble();
                    int s = getRandom().nextInt(2) == 0 ? -1 : 1;
                    double a = Math.pow(2.0, -u * k);
                    gene.setData(gene.getData() + s * r * a);
                    muted = true;
                }
            }
        } while (!muted);
        return subject;
    }

    /**
     * Gets the mutation range from index.
     *
     * @param index index
     * @return mutation range.
     */
    public double getMutationRange(int index) {
        return mutationRanges[index];
    }

    /**
     * Gets the mutation precision from index.
     *
     * @param index index
     * @return mutation precision
     */
    public double getMutationPrecision(int index) {
        return mutationPrecisions[index];
    }


}

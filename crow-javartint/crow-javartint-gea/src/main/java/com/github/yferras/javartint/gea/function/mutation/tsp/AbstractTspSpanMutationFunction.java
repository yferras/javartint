package com.github.yferras.javartint.gea.function.mutation.tsp;

import java.util.Random;

import com.github.yferras.javartint.gea.function.mutation.AbstractMutationFunction;
import com.github.yferras.javartint.gea.genome.TspGenome;

/**
 * <p>
 * Mutation function in TSP a bit more specialized.
 * </p>
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractTspSpanMutationFunction<T extends TspGenome> extends AbstractMutationFunction<T> {

    /** Constant <code>MIN_SPAN_SIZE=2</code> */
    public static final int MIN_SPAN_SIZE = 2;
    private int minSpanSize;

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of mutation
     * @param random      random instance
     * @param minSpanSize min span size
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public AbstractTspSpanMutationFunction(double probability, Random random, int minSpanSize)  {
        super(probability, random);
        this.minSpanSize = minSpanSize;
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random}.
     *
     * @param probability probability of mutation
     * @param minSpanSize min span size
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public AbstractTspSpanMutationFunction(double probability, int minSpanSize)  {
        this(probability, new Random(), minSpanSize);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.05</code>.
     *
     * @param minSpanSize min span size
     */
    public AbstractTspSpanMutationFunction(int minSpanSize) {
        super();
        this.minSpanSize = minSpanSize;
    }

    /**
     * Default constructor. By default it uses as random generator an instance of {@link java.util.Random},
     * <code>probability = 0.05</code> and <code>minSpanSize = 2</code>.
     */
    public AbstractTspSpanMutationFunction() {
        this(MIN_SPAN_SIZE);
    }

    /**
     * Gets the min span of section.
     *
     * @return the size of span
     */
    public int getMinSpanSize() {
        return minSpanSize;
    }
}

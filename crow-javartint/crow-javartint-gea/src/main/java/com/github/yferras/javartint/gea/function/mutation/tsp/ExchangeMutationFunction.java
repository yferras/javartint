package com.github.yferras.javartint.gea.function.mutation.tsp;

import java.util.Random;

import com.github.yferras.javartint.gea.function.mutation.AbstractMutationFunction;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.TspGenome;

/**
 * <p>
 * Specific mutation function in TSP.
 * </p>
 * <p>
 * Takes the original genome and generates tow random indices based on genome's size. After that, the genes on that
 * indices are exchanged.
 * </p>
 * <p>
 * Example, given the following genome:
 * </p>
 * <pre>
 *     TSPGenome:
 *     [ 8, 5, 2, 1, 6, 3, 7, 0, 9, 4 ]
 *     Generating section:
 *     index1 = rand(9),    index1 = 5
 *     index2 = rand(10),   index2 = 1
 *     Exchanging:
 *     [ 8, 5, 2, 1, 6, 3, 7, 0, 9, 4 ]
 *          ^           ^
 *     [ 8, 3, 2, 1, 6, 5, 7, 0, 9, 4 ]
 *
 * </pre>
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class ExchangeMutationFunction<T extends TspGenome>
    extends AbstractMutationFunction<T> {

    /**
     * <p>Constructor for ExchangeMutationFunction.</p>
     *
     * @param probability a double.
     * @param random      a {@link java.util.Random} object.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public ExchangeMutationFunction(double probability, Random random)  {
        super(probability, random);
    }

    /**
     * <p>Constructor for ExchangeMutationFunction.</p>
     *
     * @param probability a double.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public ExchangeMutationFunction(double probability)  {
        super(probability);
    }

    /**
     * <p>Constructor for ExchangeMutationFunction.</p>
     */
    public ExchangeMutationFunction() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    protected T mutate(T subject)
        throws CloneNotSupportedException {
        int pos1 = getRandom().nextInt(subject.getChromosome().size() - 1);
        int pos2 = getRandom().nextInt(subject.getChromosome().size());
        while (pos2 == pos1) {
            pos2 = getRandom().nextInt(subject.getChromosome().size());
        }
        DefaultGene<Integer> gene1 = subject.getChromosome().getGene(pos1);
        subject.getChromosome().setGene(pos1, subject.getChromosome().getGene(pos2));
        subject.getChromosome().setGene(pos2, gene1);
        return subject;
    }
}

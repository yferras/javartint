package com.github.yferras.javartint.gea.function.mutation.binary;

import java.util.Random;

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.function.mutation.AbstractMutationFunction;
import com.github.yferras.javartint.gea.gene.ByteArrayGene;
import com.github.yferras.javartint.gea.genome.BinaryGenome;

/**
 * Class that represents an binary mutation function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.BinaryGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class BinaryMutationFunction<T extends BinaryGenome>
    extends AbstractMutationFunction<T> {

    /**
     * <p>Constructor for BinaryMutationFunction.</p>
     *
     * @param probability a double.
     * @param random      a {@link java.util.Random} object.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public BinaryMutationFunction(double probability, Random random)  {
        super(probability, random);
    }

    /**
     * <p>Constructor for BinaryMutationFunction.</p>
     *
     * @param probability a double.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public BinaryMutationFunction(double probability)  {
        super(probability);
    }

    /**
     * <p>Constructor for BinaryMutationFunction.</p>
     */
    public BinaryMutationFunction() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    protected T mutate(T subject) {
        for (Chromosome<ByteArrayGene> chromosome : subject) {
            for (ByteArrayGene gene : chromosome) {
                int index = getRandom().nextInt(gene.length());
                byte val = gene.getAllele(index);
                gene.setAllele(index, (byte) (val == 0 ? 1 : 0));
            }
        }
        return subject;
    }
}

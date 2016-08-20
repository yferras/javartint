package com.github.yferras.javartint.gea.function.mutation.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.TspGenome;

/**
 * <p>
 * Specific mutation function in TSP.
 * </p>
 * <p>
 * Takes the original genome and generates a random section based on genome's size. After that, the genes inside
 * this section are stored in a list and removed from the source. The genes list reverses the order of their
 * elements, then is reinserted on same position in the source.
 * </p>
 * <p>
 * Example, given the following setting: <code>minSpanSize = 4</code> genome:<br/>
 * </p>
 * <pre>
 *     TSPGenome:
 *     [ 8, 5, 2, 1, 6, 3, 7, 0, 9, 4 ]
 *     Generating section:
 *     start = rand(10 - 4), start = 3
 *     end = 3 + 4 = 7
 *     [ 8, 5, 2,(1, 6, 3, 7), 0, 9, 4 ]
 *     section = (1, 6, 3, 7)
 *     Removing section from source:
 *     [ 8, 5, 2, 0, 9, 4 ]
 *     Reversing the order of section:
 *     section = (7, 3, 6, 1)
 *     Inserting at same place:
 *     [ 8, 5, 2,(7, 3, 6, 1), 0, 9, 4 ]
 *     Result:
 *     [ 8, 5, 2, 7, 3, 6, 1, 0, 9, 4 ]
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class InversionMutationFunction<T extends TspGenome>
    extends AbstractTspSpanMutationFunction<T> {

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of mutation
     * @param random      random instance
     * @param minSpanSize min span size
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public InversionMutationFunction(double probability, Random random, int minSpanSize)  {
        super(probability, random, minSpanSize);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random}.
     *
     * @param probability probability of mutation
     * @param minSpanSize min span size
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public InversionMutationFunction(double probability, int minSpanSize)  {
        super(probability, minSpanSize);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.05</code>.
     *
     * @param minSpanSize min span size
     */
    public InversionMutationFunction(int minSpanSize) {
        super(minSpanSize);
    }

    /**
     * Default constructor. By default it uses as random generator an instance of {@link java.util.Random},
     * <code>probability = 0.05</code> and <code>minSpanSize = 2</code>.
     */
    public InversionMutationFunction() {
        super();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    protected T mutate(T subject)
        throws CloneNotSupportedException {
        int start = getRandom().nextInt(subject.getChromosome().size() - getMinSpanSize());
        int end = start + getMinSpanSize();
        List<DefaultGene<Integer>> section = new ArrayList<>(getMinSpanSize());
        for (int i = start; i < end; i++) {
            section.add(subject.getChromosome().getGene(i));
        }
        Collections.reverse(section);
        final ListIterator<DefaultGene<Integer>> iterator = section.listIterator();
        for (int i = start; i < end; i++) {
            subject.getChromosome().setGene(i, iterator.next());
        }
        return subject;
    }
}

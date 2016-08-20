package com.github.yferras.javartint.gea.function.mutation.tsp;

/*
 * #%L
 * Crow JavArtInt GEA
 * %%
 * Copyright (C) 2014 - 2016 Eng. Ferrás Cecilio, Yeinier
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
 * this section are scrambled, then the section is reinserted on the same position.
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
 *     Scramble the section:
 *     section = (3, 7, 1, 6)
 *     Reinsert section:
 *     [ 8, 5, 2,(3, 7, 6, 1), 0, 9, 4 ]
 *     Result:
 *     [ 8, 5, 2, 3, 7, 6, 1, 0, 9, 4 ]
 * </pre>
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class ScrambleMutationFunction<T extends TspGenome>
    extends AbstractTspSpanMutationFunction<T> {

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of mutation
     * @param random      random instance
     * @param minSpanSize min span size
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public ScrambleMutationFunction(double probability, Random random, int minSpanSize)  {
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
    public ScrambleMutationFunction(double probability, int minSpanSize)  {
        super(probability, minSpanSize);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.05</code>.
     *
     * @param minSpanSize min span size
     */
    public ScrambleMutationFunction(int minSpanSize) {
        super(minSpanSize);
    }

    /**
     * Default constructor. By default it uses as random generator an instance of {@link java.util.Random},
     * <code>probability = 0.05</code> and <code>minSpanSize = 2</code>.
     */
    public ScrambleMutationFunction() {
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
        Collections.shuffle(section, getRandom());
        final ListIterator<DefaultGene<Integer>> iterator = section.listIterator();
        for (int i = start; i < end; i++) {
            subject.getChromosome().setGene(i, iterator.next());
        }
        return subject;
    }
}

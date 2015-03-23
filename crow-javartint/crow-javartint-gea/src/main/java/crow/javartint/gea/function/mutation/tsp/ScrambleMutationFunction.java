package crow.javartint.gea.function.mutation.tsp;

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

import crow.javartint.core.util.RandomGenerator;
import crow.javartint.gea.function.mutation.AbstractMutationFunction;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *     Specific mutation function in TSP.
 * </p>
 * <p>
 *     Takes the original genome and generates a random section based on genome's size. After that, the genes inside
 *     this section are scrambled, then the section is reinserted on the same position.
 * </p>
 * <p>
 *     Example, given the following setting: <code>minSpanSize = 4</code> genome:<br/>
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
 *     [ 8, 5, 2,(7, 3, 6, 1), 0, 9, 4 ]
 *     Result:
 *     [ 8, 5, 2, 7, 3, 6, 1, 0, 9, 4 ]
 * </pre>
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class ScrambleMutationFunction extends AbstractMutationFunction<DefaultGenome<DefaultGene<Integer>>> {


    private int minSpanSize;

    public ScrambleMutationFunction(double mutationRate) {
        this(mutationRate, 2);
    }

    public ScrambleMutationFunction(double mutationRate,
                                    int minSpanSize) {
        super(mutationRate);
        this.minSpanSize = minSpanSize;
    }

    public int getMinSpanSize() {
        return minSpanSize;
    }

    @Override
    protected void validate(DefaultGenome<DefaultGene<Integer>> param) throws IllegalArgumentException {
        super.validate(param);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected DefaultGenome<DefaultGene<Integer>> mutate(DefaultGenome<DefaultGene<Integer>> subject)
            throws CloneNotSupportedException {
        RandomGenerator randomGenerator = new RandomGenerator.SystemDefaultRandomGenerator();
        int start = randomGenerator.nextInt(subject.size() - getMinSpanSize());
        int end = start + getMinSpanSize();
        List genes = Arrays.asList(subject.getChromosome());
        List section = genes.subList(start, end);
        genes.removeAll(section);
        Collections.shuffle(section);
        genes.addAll(start, section);
        subject.setChromosome((DefaultGene<Integer>[]) genes.toArray());
        return subject;
    }
}

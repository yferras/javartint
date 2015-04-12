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

import crow.javartint.gea.chromosome.DefaultChromosome;
import crow.javartint.gea.function.mutation.AbstractMutationFunction;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *     Specific mutation function in TSP.
 * </p>
 * <p>
 *     Takes the original genome and generates a random section based on genome's size. After that, the genes inside
 *     this section are stored in a list and removed from the source. Then is reinserted on a random position in the
 *     source.
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
 *     Generating random position to insert section:
 *     pos = rand(6), pos = 4
 *     [ 8, 5, 2, 0, (1, 6, 3, 7), 9, 4 ]
 *     Result:
 *     [ 8, 5, 2, 0, 1, 6, 3, 7, 9, 4 ]
 * </pre>
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class DisplacementMutationFunction extends AbstractMutationFunction<DefaultGenome<DefaultChromosome<DefaultGene<Integer>>>> {


    private int minSpanSize;

    public DisplacementMutationFunction(double probability) {
        this(probability, 2);
    }

    public DisplacementMutationFunction(double probability,
                                        int minSpanSize) {
        super(probability);
        this.minSpanSize = minSpanSize;
    }

    public int getMinSpanSize() {
        return minSpanSize;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> mutate(DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> subject)
            throws CloneNotSupportedException {
        int start = getRandom().nextInt(subject.size() - getMinSpanSize());
        int end = start + getMinSpanSize();
        List genes = Arrays.asList(subject.getChromosome(0).getGenes());
        List section = genes.subList(start, end);
        genes.removeAll(section);
        int curPos = getRandom().nextInt(genes.size());
        genes.addAll(curPos, section);
        subject.getChromosome(0).setGenes((DefaultGene<Integer>[]) genes.toArray());
        return subject;
    }
}

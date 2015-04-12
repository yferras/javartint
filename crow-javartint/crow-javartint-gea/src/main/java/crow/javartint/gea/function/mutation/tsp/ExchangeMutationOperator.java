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

/**
 * <p>
 *     Specific mutation function in TSP.
 * </p>
 * <p>
 *     Takes the original genome and generates tow random indices based on genome's size. After that, the genes on that
 *     indices are exchanged.
 * </p>
 * <p>
 *     Example, given the following genome:
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
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class ExchangeMutationOperator extends AbstractMutationFunction<DefaultGenome<DefaultChromosome<DefaultGene<Integer>>>> {

    public ExchangeMutationOperator(double probability) {
        super(probability);
    }

    public ExchangeMutationOperator() {
        super();
    }

    @Override
    protected DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> mutate(DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> subject)
            throws CloneNotSupportedException {
        int pos1 = getRandom().nextInt(subject.getChromosome(0).size() - 1);
        int pos2 = getRandom().nextInt(subject.getChromosome(0).size());
        while (pos2 == pos1) {
            pos2 = getRandom().nextInt(subject.size());
        }
        DefaultGene<Integer> gene1 = subject.getChromosome(0).getGene(pos1);
        subject.getChromosome(0).setGene(pos1, subject.getChromosome(0).getGene(pos2));
        subject.getChromosome(0).setGene(pos2, gene1);
        return subject;
    }
}

package crow.javartint.gea.function.crossover;

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
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.AbstractGenome;
import crow.javartint.gea.genome.Genome;

/**
 * Specific crossover function, that performs the crossover process in a single
 * random point.
 *
 * <p>
 * The example shows two genomes, each one has six genes, and the raised random
 * position is 2.<br />
 * <code>
 * [GA0,GA1,GA2,GA3,GA4,GA5] //Parent A<br />
 * [GB0,GB1,GB2,GB3,GB4,GB5] //Parent B<br />
 * [   ,   ,___,___,___,___] //Underline represents the position that will be exchanged<br />
 * [GA0,GA1,GB2,GB3,GB4,GB5] //Child A after crossover<br />
 * [GB0,GB1,GA2,GA3,GA4,GA5] //Child B after crossover<br />
 * </code>
 * </p>
 *
 * @param <T> any derived type from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public class SinglePointCrossoverFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractCrossoverFunction<T> {

    public SinglePointCrossoverFunction(double probability, RandomGenerator randomGenerator) {
        super(probability, randomGenerator);
    }

    public SinglePointCrossoverFunction(double probability) {
        super(probability);
    }

    public SinglePointCrossoverFunction() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
        int numberOfGenes = parent1.size();
        Genome[] offspring = new Genome[2];
        offspring[0] = ((AbstractGenome)parent1).clone();
        offspring[1] = ((AbstractGenome)parent2).clone();
        int position = getRandomGenerator().nextInt(numberOfGenes);
        for (int i = 0; i < position; i++) {
            Gene aux = offspring[0].getGene(i);
            offspring[0].setGene(i, offspring[1].getGene(i));
            offspring[1].setGene(i, aux);
        }
        return (T[]) offspring;
    }
}

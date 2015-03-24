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

import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.Random;

/**
 * Specific crossover function, that performs the crossover process if in each
 * iteration the random probability is in the threshold.
 * <p>
 * While iterates through the genes of the genomes, if the generated random
 * number is less than or equal to the probability of crossover then the
 * genes of this position are exchanged.
 * </p>
 * <p>
 * Example shows two genomes, each one has six genes.<br />
 * <code>
 * [GA0,GA1,GA2,GA3,GA4,GA5] // Parent A<br />
 * [GB0,GB1,GB2,GB3,GB4,GB5] // Parent B<br />
 * [___,''',___,''',''',___] // Underline represents the position that will be exchanged<br />
 * [GB0,GA1,GB2,GA3,GA4,GA5] // Child A after crossover process<br />
 * [GA0,GB1,GA2,GB3,GB4,GB5] // Child B after crossover process<br />
 * </code>
 * </p>
 *
 * @param <T> any derived type from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public class MultiPointsCrossoverFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractCrossoverFunction<T> {

    public MultiPointsCrossoverFunction(double probability, Random random) {
        super(probability, random);
    }

    public MultiPointsCrossoverFunction(double probability) {
        super(probability);
    }

    public MultiPointsCrossoverFunction() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T[] recombine(T parent1, T parent2)
            throws CloneNotSupportedException {
        int numberOfGenes = parent1.size();
        Genome[] offspring = new Genome[]{
                ((Genome) parent1).clone(),
                ((Genome) parent2).clone()
        };
        for (int i = 0; i < numberOfGenes; i++) {
            if (getRandom().nextDouble() <= getProbability()) {
                Gene aux = offspring[0].getGene(i);
                offspring[0].setGene(i, offspring[1].getGene(i));
                offspring[1].setGene(i, aux);
            }
        }
        return (T[]) offspring;
    }
}

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

/**
 * <p>
 * Specific crossover function, that performs an exchange of variable values between the individuals.
 * For each position the parent who contributes its variable to the offspring is chosen randomly with equal probability.
 * This function can be applied to any type of genome.
 * </p>
 * <p>
 * Example shows two genomes, each one has six genes.<br />
 * <code>
 * [GA0,GA1,GA2,GA3,GA4,GA5] // Parent A<br />
 * [GB0,GB1,GB2,GB3,GB4,GB5] // Parent B<br />
 * [ A , B , B , B , A , A ] // first random contribution<br />
 * [ A , A , A , B , B , A ] // second random contribution<br />
 * [GA0,GB1,GB2,GB3,GA4,GA5] // Child A after crossover process<br />
 * [GA0,GB1,GA2,GB3,GB4,GA5] // Child B after crossover process<br />
 * </code>
 * </p>
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class DiscreteCrossoverFunction<T extends Genome<? extends Gene<?>>>
	extends AbstractCrossoverFunction<T> {

	@SuppressWarnings("unchecked")
	@Override
	protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
		int numberOfGenes = parent1.size();
		Genome[] offspring = new Genome[]{
			((Genome) parent1).clone(),
			((Genome) parent2).clone()
		};
		for (int i = 0; i < numberOfGenes; i++) {
			Gene<?> aux0 = offspring[getRandom().nextInt(2)].getGene(i);
			Gene<?> aux1 = offspring[getRandom().nextInt(2)].getGene(i);
			offspring[0].setGene(i, aux0.clone());
			offspring[1].setGene(i, aux1.clone());
		}
		return (T[]) offspring;
	}
}

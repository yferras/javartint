/*
 * Copyright (C) 2015  Eng. Ferrás Cecilio, Yeinier. <https://www.linkedin.com/in/yeinierferras>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package crow.javartint.gea.function.crossover.numeric;

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

import crow.javartint.gea.function.crossover.AbstractCrossoverFunction;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.Random;


/**
 * Specific crossover function to real valued genomes.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}<code>&lt;? extends </code>
 *            {@link crow.javartint.gea.gene.Gene}<code>&lt;Double&gt;&gt;</code>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class IntermediateCrossoverFunction<T extends Genome<? extends Gene<Double>>>
	extends AbstractCrossoverFunction<T> {

	private double distance;

	/**
	 * Constructor, initializes instances with the given parameters.
	 *
	 * @param probability probability of mutation
	 * @param random      random instance
	 * @param distance    defines the size of the area for possible offspring.
	 */
	public IntermediateCrossoverFunction(double probability, Random random, double distance) {
		super(probability, random);
		this.distance = distance;
	}

	/**
	 * Constructor, initializes instances with the given parameters.
	 * By default it uses as random generator an instance of {@link java.util.Random}.
	 *
	 * @param probability probability of mutation
	 * @param distance defines the size of the area for possible offspring.
	 */
	public IntermediateCrossoverFunction(double probability, double distance) {
		this(probability, new Random(), distance);
	}

	/**
	 * Constructor, initializes instances with the given parameters.
	 * By default it uses as random generator an instance of {@link java.util.Random},
	 * and <code>probability = 0.75</code>.
	 *
	 * @param distance defines the size of the area for possible offspring.
	 */
	public IntermediateCrossoverFunction(double distance) {
		super();
		this.distance = distance;
	}

	/**
	 * Default constructor.
	 * By default it uses as random generator an instance of {@link java.util.Random},
	 * <code>probability = 0.75</code> and <code>distance = 0.25</code>.
	 */
	public IntermediateCrossoverFunction() {
		this(.25);
	}

	public double getDistance() {
		return distance;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
		int numberOfGenes = parent1.size();
		Genome<? extends Gene<Double>>[] offspring = new Genome[]{
			((Genome) parent1).clone(),
			((Genome) parent2).clone()
		};
		for (int i = 0; i < numberOfGenes; i++) {
			double value0 = offspring[0].getGene(i).getData();
			double value1 = offspring[1].getGene(i).getData();
			double a = getRandom().nextDouble() * (1.0 + 2.0 * getDistance()) - getDistance();
			double newValue0 = value0 * a + value1 * (1.0 - a);
			double newValue1 = value1 * a + value0 * (1.0 - a);
			offspring[0].getGene(i).setData(newValue0);
			offspring[1].getGene(i).setData(newValue1);
		}
		return (T[]) offspring;
	}
}

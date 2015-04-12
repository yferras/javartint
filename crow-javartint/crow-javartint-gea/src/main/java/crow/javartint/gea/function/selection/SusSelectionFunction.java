package crow.javartint.gea.function.selection;

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

import crow.javartint.gea.Individual;
import crow.javartint.gea.function.scaling.AbstractScalingMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Stochastic universal sampling selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class SusSelectionFunction<T extends Individual>
	extends AbstractSelectionFunction<T> {

	public SusSelectionFunction(int numToSelect, AbstractScalingMethod<T> scalingMethod) {
		super(numToSelect, scalingMethod);
	}

	public SusSelectionFunction(int numToSelect) {
		this(numToSelect, null);
	}

	public SusSelectionFunction() {
		this(2);
	}


	@Override
	protected List<T> select(List<T> individuals) {
		if (getScalingMethod() != null) {
			getScalingMethod().evaluate(individuals);
		}
		Collections.sort(individuals);
		double worstFitness = individuals.get(individuals.size() - 1).getFitness();
		if (worstFitness < 0) {
			double absValue = Math.abs(worstFitness);
			for (Individual individual : individuals) {
				individual.setFitness(individual.getFitness() + absValue);
			}
		}
		double sum = 0.0;
		for (T genome : individuals) {
			sum += genome.getFitness();
		}
		for (T genome : individuals) {
			genome.setFitness(genome.getFitness() / sum);
		}
		double inc = 1.0 / (double) getNumToSelect();
		Random rand = new Random();
		double mark = rand.nextDouble() * inc;
		sum = 0.0;
		List<T> selected = new ArrayList<>(getNumToSelect());
		Collections.shuffle(individuals);
		for (T genome : individuals) {
			sum += genome.getFitness();
			if (sum > mark) {
				selected.add(genome);
				if (selected.size() == getNumToSelect()) {
					break;
				}
				mark += inc;
			}
		}
		return selected;
	}
}

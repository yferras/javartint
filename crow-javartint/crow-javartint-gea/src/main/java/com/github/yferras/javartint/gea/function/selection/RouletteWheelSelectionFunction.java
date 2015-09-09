package com.github.yferras.javartint.gea.function.selection;

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

import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod;
import com.github.yferras.javartint.gea.util.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Roulette wheel selection function.
 *
 * @param <T> Any derived class from {@link Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class RouletteWheelSelectionFunction<T extends Individual>
	extends AbstractSelectionFunction<T> {


	public RouletteWheelSelectionFunction(int numToSelect, AbstractScalingMethod<T> scalingMethod) {
		super(numToSelect, scalingMethod);
	}

	public RouletteWheelSelectionFunction(int numToSelect) {
		this(numToSelect, null);
	}

	@Override
	protected List<T> select(List<T> individuals) {
		if (getScalingMethod() != null) {
			getScalingMethod().evaluate(individuals);
		}
		double totalFitnessScore = MathUtil.total(individuals);
		Random rand = new Random();

		List<T> selected = new ArrayList<>(getNumToSelect());
		for (int i = 0; i < getNumToSelect(); i++) {
			double sumAux = 0.0;
			double randNumb = rand.nextDouble() * totalFitnessScore;
			for (T genome : individuals) {
				if ((sumAux += Math.abs(genome.getFitness())) >= randNumb) {
					selected.add(i, genome);
					break;
				}
			}
		}
		return selected;
	}
}

package com.github.yferras.javartint.gea.function.scaling;

import java.util.List;

/*
 * #%L
 * Crow JavArtInt GEA
 *
 %%
 Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 *
 %%
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as
 published by the Free Software Foundation, either version 3 of the
 License, or (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public
 License along with this program.  If not, see
 <http://www.gnu.org/licenses/gpl-3.0.html>.
 #L%
 */

import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.util.MathUtil;

/**
 * Boltzmann scaling method function.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public final class BoltzmannScalingMethod<T extends Individual> extends AbstractScalingMethod<T> {

	private static final double A = 2.0;
	private static final double BOLTZMANN_DELTA_TEMP = 0.05;
	private static final double BOLTZMANN_MIN_TEMP = 1.0;

	private double boltzmannTemp;

	/**
	 * Constructor that initializes this instance.
	 *
	 * @param cities
	 *            number of cities
	 */
	public BoltzmannScalingMethod(int cities) {
		super(null);
		boltzmannTemp = A * cities;
	}

	/**
	 * Gets the Boltzmann temperature
	 *
	 * @return Boltzmann temperature value
	 */
	public double getBoltzmannTemp() {
		return boltzmannTemp;
	}

	/** {@inheritDoc} */
	@Override
	protected void scale(List<T> individuals) {
		boltzmannTemp -= BOLTZMANN_DELTA_TEMP;
		if (boltzmannTemp < BOLTZMANN_MIN_TEMP) {
			boltzmannTemp = BOLTZMANN_MIN_TEMP;
		}
		final double divider = MathUtil.mean(individuals) / boltzmannTemp;
		for (T individual : individuals) {
			individual.setFitness(individual.getFitness() / divider);
		}
	}
}

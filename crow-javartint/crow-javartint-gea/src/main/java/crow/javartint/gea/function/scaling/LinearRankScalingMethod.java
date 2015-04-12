package crow.javartint.gea.function.scaling;

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

import crow.javartint.core.util.Optimize;
import crow.javartint.gea.genome.Genome;

import java.util.Collections;
import java.util.List;

/**
 * Linear rank scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class LinearRankScalingMethod<T extends Genome<?>>
	extends AbstractScalingMethod<T> {

	private double selectivePressure;

	/**
	 * Constructor that initializes this instance.
	 *
	 * @param selectivePressure this value must be between 1.0 and 2.0
	 * @param optimize          optimization way
	 * @throws java.lang.IllegalArgumentException if {@code selectivePressure} value is out of 1.0 and 2.0
	 */
	public LinearRankScalingMethod(double selectivePressure, Optimize optimize) {
		super(optimize);
		if (selectivePressure < 1.0 || selectivePressure > 2.0) {
			throw new IllegalArgumentException("SELECTIVE PRESSURE MUST BE BETWEEN 1.0 AND 2.0");
		}
		this.selectivePressure = selectivePressure;
	}

	/**
	 * Gets the selective pressure
	 *
	 * @return selective pressure value
	 */
	public double getSelectivePressure() {
		return selectivePressure;
	}

	@Override
	protected void scale(List<T> genomes) {
		Collections.sort(genomes); // Sorts ascending
		for (int i = 0, n = genomes.size(); i < n; i++) {
			double newValue = 2.0 - getSelectivePressure() + 2.0 * (getSelectivePressure() - 1) * i / (n - 1);
			genomes.get(i).setFitness(newValue);
		}
	}
}

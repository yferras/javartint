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
 * Sigma scaling method function.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public final class SigmaScalingMethod<T extends Individual> extends AbstractScalingMethod<T> {
	/**
	 * Constructor that initializes this instance.
	 */
	public SigmaScalingMethod() {
		super(null);
	}

	/** {@inheritDoc} */
	@Override
	protected void scale(List<T> individuals) {
		final Double mean = MathUtil.mean(individuals);
		final double sigma = Math.sqrt(MathUtil.variance(individuals, mean));
		for (T individual : individuals) {
			individual.setFitness((individual.getFitness() - mean) / sigma);
		}
	}
}

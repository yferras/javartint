package com.github.yferras.javartint.gea.function.scaling;

import java.util.Collections;
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

import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.Individual;

/**
 * Rank scaling method function.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class RankScalingMethod<T extends Individual> extends AbstractScalingMethod<T> {

	/**
	 * Constructor that initializes this instance.
	 *
	 * @param optimize
	 *            optimization way
	 */
	public RankScalingMethod(Optimize optimize) {
		super(optimize);
	}

	/** {@inheritDoc} */
	@Override
	protected void scale(List<T> individuals) {
		// Sorts ascending
		Collections.sort(individuals);
		for (int i = 0; i < individuals.size(); i++) {
			individuals.get(i).setFitness(i + 1.0);
		}
	}
}

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

import crow.javartint.gea.genome.Genome;
import crow.javartint.gea.util.MathUtil;

import java.util.List;

/**
 * Sigma scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public final class SigmaScalingMethod<T extends Genome>
	extends AbstractScalingMethod<T> {
	/**
	 * Constructor that initializes this instance.
	 */
	public SigmaScalingMethod() {
		super(null);
	}

	@Override
	protected void scale(List<T> genomes) {
		final Double mean = MathUtil.mean(genomes);
		final double sigma = Math.sqrt(MathUtil.variance(genomes, mean));
		for (T genome : genomes) {
			genome.setFitness((genome.getFitness() - mean) / sigma);
		}
	}
}

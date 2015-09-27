package com.github.yferras.javartint.gea.function.generator;

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
import com.github.yferras.javartint.gea.genome.Genome;
import com.github.yferras.javartint.core.function.Function;

/**
 * This interface is used to create a functions to generate genomes.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public interface GeneratorFunction<T extends Individual>
	extends Function<T, Void[]> {

	/**
	 * {@inheritDoc}
	 *
	 * This method must be implemented to generate dynamically instances
	 * of {@link Genome}
	 */
	@Override
	T evaluate(Void... params);
}

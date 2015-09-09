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

import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.gea.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Elitism selection function.
 *
 * @param <T> Any derived class from {@link Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class ElitismSelectionFunction<T extends Individual>
	extends AbstractSelectionFunction<T> {

	private final Optimize optimize;

	/**
	 * Constructor that initializes this instance.
	 *
	 * @param numToSelect number of elements to select
	 * @param optimize    the optimization way
	 */
	public ElitismSelectionFunction(int numToSelect, Optimize optimize) {
		super(numToSelect);
		this.optimize = optimize;
	}


	/**
	 * Gets the optimization way.
	 *
	 * @return optimization way value.
	 */
	public Optimize getOptimize() {
		return optimize;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<T> select(List<T> individuals) {
		List<T> list = new ArrayList<>(individuals);
		Collections.sort(list);
		if (optimize == Optimize.MIN) return list.subList(0, getNumToSelect());
		else return list.subList(list.size() - getNumToSelect(), list.size());
	}
}

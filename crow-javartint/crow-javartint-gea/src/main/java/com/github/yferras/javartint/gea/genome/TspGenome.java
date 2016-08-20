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
package com.github.yferras.javartint.gea.genome;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

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

import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.gene.DefaultGene;

/**
 * This class represents a specialized TSP genome.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class TspGenome extends DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> {

	/**
	 * Constructor, initializes this instances with the given cities
	 *
	 * @param cities
	 *            array of cities
	 */
	public TspGenome(Integer... cities) {
		this(new LinkedHashSet<>(Arrays.asList(cities)));
	}

	/**
	 * Constructor, initializes this instances with the given cities
	 *
	 * @param cities
	 *            set with the cities
	 */
	public TspGenome(Set<Integer> cities) {
		addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
		if (cities != null) {
			for (Integer city : cities) {
				getChromosome().addGene(new DefaultGene<>(city));
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public TspGenome clone() throws CloneNotSupportedException {
		return (TspGenome) super.clone();
	}

	/**
	 * Gets the only chromosome inside the genome.
	 *
	 * @return instance of
	 *         {@link com.github.yferras.javartint.gea.chromosome.DefaultChromosome}
	 */
	public DefaultChromosome<DefaultGene<Integer>> getChromosome() {
		return super.getChromosome(0);
	}

	/**
	 * Sets the chromosome.
	 *
	 * @param newChromosome
	 *            the new chromosome.
	 */
	public void setChromosome(DefaultChromosome<DefaultGene<Integer>> newChromosome) {
		super.setChromosome(0, newChromosome);
	}
}

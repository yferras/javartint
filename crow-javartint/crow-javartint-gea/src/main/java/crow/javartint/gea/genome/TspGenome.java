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
package crow.javartint.gea.genome;

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


import crow.javartint.gea.chromosome.DefaultChromosome;
import crow.javartint.gea.gene.DefaultGene;

/**
 * This class represents a specialized TSP genome.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class TspGenome extends DefaultGenome<DefaultChromosome<DefaultGene<Integer>>> {

	public TspGenome() {
		addChromosome(new DefaultChromosome<DefaultGene<Integer>>());
	}

	/**
	 * Gets the only chromosome inside the genome.
	 *
	 * @return instance of {@link crow.javartint.gea.chromosome.DefaultChromosome}
	 */
	public DefaultChromosome<DefaultGene<Integer>> getChromosome() {
		return super.getChromosome(0);
	}

	/**
	 * Sets the chromosome.
	 *
	 * @param newChromosome the new chromosome.
	 */
	public void setChromosome(DefaultChromosome<DefaultGene<Integer>> newChromosome) {
		super.setChromosome(0, newChromosome);
	}

	@Override
	public TspGenome clone() throws CloneNotSupportedException {
		return (TspGenome)super.clone();
	}
}

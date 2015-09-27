package com.github.yferras.javartint.gea.function.decoder;

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

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.genome.Genome;
import com.github.yferras.javartint.gea.gene.Gene;

/**
 * This class implements the interface {@link com.github.yferras.javartint.gea.function.decoder.DecoderFunction}.
 * This class can be derived to create a functions to decode genomes.
 *
 * @param <D> Type of decoded result
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractDecoderFunction<D, T extends Genome<? extends Chromosome<? extends Gene<?>>>>
	implements DecoderFunction<D, T> {

	/**
	 * Validates the input params.
	 *
	 * @param genome genome to validate.
	 * @throws java.lang.IllegalArgumentException if argument is {@code null}
	 */
	protected void validate(T genome) {
		if (genome == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This method must be implemented to decode the genome.
	 *
	 * @param genome genome to decode
	 * @return decoded value
	 */
	protected abstract D decode(T genome);

	/**
	 * {@inheritDoc}
	 *
	 * This method validate and performs the decode process.
	 */
	@Override
	public D evaluate(T genome) {
		validate(genome);
		return decode(genome);
	}
}

package com.github.yferras.javartint.gea.genome;

/*
 * #%L
 * Crow JavArtInt GEA
 *
 %%
 Copyright (C) 2014 - 2016 Eng. Ferrás Cecilio, Yeinier
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

import java.io.Serializable;

import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;

/**
 * This interface represents a generic genome.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.4
 */
public interface Genome<T extends Chromosome<? extends Gene<?>>>
		extends Cloneable, Individual, Iterable<T>, Serializable {

	/**
	 * Adds new chromosome to genome.
	 *
	 * @param chromosome
	 *            new chromosome.
	 */
	void addChromosome(T chromosome);

	/**
	 * <p>
	 * clone.
	 * </p>
	 *
	 * @return a {@link com.github.yferras.javartint.gea.genome.Genome} object.
	 * @throws java.lang.CloneNotSupportedException
	 *             if any.
	 */
	Genome<T> clone() throws CloneNotSupportedException;

	/**
	 * Gets the chromosome at specific index.
	 *
	 * @param index
	 *            index
	 * @return chromosome
	 */
	T getChromosome(int index);

	/**
	 * Gets the array of chromosomes.
	 *
	 * @return The value of chromosomes.
	 */
	T[] getChromosomes();

	/**
	 * Gets the genome type by the chromosomes parity.
	 *
	 * @return genome type.
	 */
	GenomeType getGenomeType();

	/**
	 * Sets the chromosome at specific index.
	 *
	 * @param index
	 *            index to place new chromosome
	 * @param newChromosome
	 *            chromosome
	 */
	void setChromosome(int index, T newChromosome);

	/**
	 * Sets the array of chromosomes.
	 *
	 * @param chromosomes
	 *            an array of T objects.
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if {@code chromosomes} argument is {@code null}.
	 */
	void setChromosomes(T[] chromosomes);

	/**
	 * Gets the number of chromosomes of the genome.
	 *
	 * @return The number of chromosomes of the genome.
	 */
	int size();
}

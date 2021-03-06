package com.github.yferras.javartint.gea.chromosome;

import java.io.Serializable;

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

import com.github.yferras.javartint.core.Solution;
import com.github.yferras.javartint.gea.gene.Gene;

/**
 * This interface represents a generic chromosome.
 *
 * @param <T>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public interface Chromosome<T extends Gene<?>> extends Cloneable, Iterable<T>, Solution, Serializable {

	/**
	 * Adds a new gene to the chromosome.
	 *
	 * @param gene
	 *            gene to add
	 */
	void addGene(T gene);

	/**
	 * <p>
	 * clone.
	 * </p>
	 *
	 * @return a {@link com.github.yferras.javartint.gea.chromosome.Chromosome}
	 *         object.
	 * @throws java.lang.CloneNotSupportedException
	 *             if any.
	 */
	Chromosome<T> clone() throws CloneNotSupportedException;

	/**
	 * Gets the gene at specific <code>locus</code>.
	 *
	 * @param locus
	 *            particular position on the chromosome.
	 * @return This method returns a gene
	 */
	T getGene(int locus);

	/**
	 * Gets the array of genes.
	 *
	 * @return the array of genes.
	 */
	T[] getGenes();

	/**
	 * Sets the <code>newGene</code> at specific <code>locus</code>.
	 *
	 * @param locus
	 *            particular position on the chromosome.
	 * @param newGene
	 *            new value of gene.
	 */
	void setGene(int locus, T newGene);

	/**
	 * Sets the array of genes.
	 *
	 * @param genes
	 *            the array of genes.
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if {@code genes} param is null.
	 */
	void setGenes(T[] genes);

	/**
	 * Gets the number of genes of the chromosome.
	 *
	 * @return The number of genes of the chromosome.
	 */
	int size();
}

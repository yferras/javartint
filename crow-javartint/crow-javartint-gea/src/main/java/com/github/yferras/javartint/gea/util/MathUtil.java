package com.github.yferras.javartint.gea.util;

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

import java.util.List;

/**
 * This class is a compilation of useful functions.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class MathUtil {

	private MathUtil() {
	}

	/**
	 * This method takes a list of individuals and determines the mean of their fitness.
	 *
	 * @param individuals list of individuals
	 * @param <T>     Any class derived from {@link Individual} interface
	 * @return the mean of fitness
	 */
	static public <T extends Individual> double mean(List<T> individuals) {
		return total(individuals) / individuals.size();
	}

	/**
	 * This method takes a list of individuals and determines the total of their fitness.
	 *
	 * @param individuals list of individuals
	 * @param <T>     Any class derived from {@link Individual} interface
	 * @return the total of fitness
	 */
	static public <T extends Individual> double total(List<T> individuals) {
		double sum = 0.0;
		for (T individual : individuals) {
			sum += individual.getFitness();
		}
		return sum;
	}

	/**
	 * This method takes a list of individuals and their mean of fitness, and determines the variance of their fitness.
	 *
	 * @param individuals list of individuals
	 * @param mean    the mean of the fitness
	 * @param <T>     Any class derived from {@link Individual} interface
	 * @return the variance of fitness
	 */
	static public <T extends Individual> double variance(List<T> individuals, Double mean) {
		double sum = 0.0;
		for (T individual : individuals) {
			double diff = individual.getFitness() - mean;
			sum += diff * diff;
		}
		return sum / individuals.size();
	}

	/**
	 * This method takes a list of individuals and determines the variance of their fitness.
	 *
	 * @param individuals list of individuals
	 * @param <T>     Any class derived from {@link Individual} interface
	 * @return the variance of fitness
	 */
	static public <T extends Individual> double variance(List<T> individuals) {
		return variance(individuals, mean(individuals));
	}


}
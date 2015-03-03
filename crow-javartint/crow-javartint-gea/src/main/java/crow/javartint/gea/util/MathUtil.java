package crow.javartint.gea.util;

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


import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

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
     * This method takes a list of genomes and determines the mean of their fitness.
     *
     * @param genomes list of genomes
     * @param <T> Any class derived from {@link crow.javartint.gea.genome.Genome} interface
     * @return the mean of fitness
     */
    static public <T extends Genome<? extends Gene<?>>> double mean(List<T> genomes) {
        double sum = 0.0;
        for (T genome : genomes) {
            sum += genome.getFitness();
        }
        return sum / genomes.size();
    }

    /**
     * This method takes a list of genomes and their mean of fitness, and determines the variance of their fitness.
     *
     * @param genomes list of genomes
     * @param mean the mean of the fitness
     * @param <T> Any class derived from {@link crow.javartint.gea.genome.Genome} interface
     * @return the variance of fitness
     */
    static public <T extends Genome<? extends Gene<?>>> double variance(List<T> genomes, Double mean) {
        double sum = 0.0;
        for (T genome : genomes) {
            double diff = genome.getFitness() - mean;
            sum += diff * diff;
        }
        return sum / genomes.size();
    }

    /**
     * This method takes a list of genomes and determines the variance of their fitness.
     *
     * @param genomes list of genomes
     * @param <T> Any class derived from {@link crow.javartint.gea.genome.Genome} interface
     * @return the variance of fitness
     */
    static public <T extends Genome<? extends Gene<?>>> double variance(List<T> genomes) {
        return variance(genomes, mean(genomes));
    }


}

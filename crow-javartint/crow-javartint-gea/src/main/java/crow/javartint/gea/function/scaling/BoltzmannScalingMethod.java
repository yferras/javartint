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

import crow.javartint.gea.chromosome.Chromosome;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;
import crow.javartint.gea.util.MathUtil;

import java.util.List;

/**
 * Boltzmann scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public final class BoltzmannScalingMethod<T extends Genome<?>>
        extends AbstractScalingMethod<T> {

    private final static double BOLTZMANN_DELTA_TEMP = 0.05;
    private final static double BOLTZMANN_MIN_TEMP = 1.0;
    private double boltzmannTemp;

    /**
     * Constructor that initializes this instance.
     *
     * @param cities number of cities
     */
    public BoltzmannScalingMethod(int cities) {
        super(null);
        boltzmannTemp = 2.0 * cities;
    }

    /**
     * Gets the Boltzmann temperature
     *
     * @return Boltzmann temperature value
     */
    public double getBoltzmannTemp() {
        return boltzmannTemp;
    }

    @Override
    protected void scale(List<T> genomes) {
        boltzmannTemp -= BOLTZMANN_DELTA_TEMP;
        if (boltzmannTemp < BOLTZMANN_MIN_TEMP) {
            boltzmannTemp = BOLTZMANN_MIN_TEMP;
        }
        final double divider = MathUtil.mean(genomes) / boltzmannTemp;
        for (T genome : genomes) {
            genome.setFitness(genome.getFitness() / divider);
        }
    }
}

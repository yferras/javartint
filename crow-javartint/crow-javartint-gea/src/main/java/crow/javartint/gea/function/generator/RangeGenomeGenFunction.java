package crow.javartint.gea.function.generator;

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

import crow.javartint.core.util.Range;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Random;

/**
 *
 */
public class RangeGenomeGenFunction
        extends AbstractGenomeGeneratorFunction<DefaultGenome<DefaultGene<Double>>> {

    private final int precision;
    private Range<Double>[] ranges;

    public RangeGenomeGenFunction(int precision, Range<Double>... ranges) {
        super(ranges.length, 1);
        this.precision = precision;
        this.ranges = ranges;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected DefaultGenome<DefaultGene<Double>> generate(int[] lengthsOfGenes) {
        DefaultGene<Double>[] genes = new DefaultGene[lengthsOfGenes.length];
        Random random = new Random();
        for (int i = 0; i < lengthsOfGenes.length; i++) {
            Range<Double> range = ranges[i];
            double v = round(random.nextDouble() * (range.getMax() - range.getMin()) + range.getMin());
            genes[i] = new DefaultGene<>(v);
        }
        DefaultGenome<DefaultGene<Double>> genome = new DefaultGenome<>();
        genome.setChromosome(genes);
        return genome;
    }

    private double round(double value) {
        double p = Math.pow(10.0, precision);
        value *= p;
        return Math.round(value) / p;
    }

}

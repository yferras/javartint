package crow.javartint.gea.function.generator;

import crow.javartint.core.util.Range;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

package crow.javartint.gea.function.scaling;

import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.List;

/**
 * Sigma scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class SigmaScalingMethod<T extends Genome<? extends Gene<?>>>
        extends AbstractScalingMethod<T> {
    /**
     * Constructor that initializes this instance.
     */
    public SigmaScalingMethod() {
        super(null);
    }

    @Override
    protected void scale(List<T> genomes) {
        final Double mean;
        final double sigma = Math.sqrt(variance(genomes, mean = mean(genomes)));
        for (T genome : genomes) {
            genome.setFitness((genome.getFitness() - mean) / sigma);
        }
    }

    double variance(List<T> genomes, Double meanValue) {
        double sum = 0.0;
        for (T genome : genomes) {
            double diff = genome.getFitness() - meanValue;
            sum += diff * diff;
        }
        return sum / genomes.size();
    }
}

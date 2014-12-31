package crow.javartint.gea.function.scaling;

import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.List;

/**
 * Boltzmann scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class BoltzmannScalingMethod<T extends Genome<? extends Gene<?>>>
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
        final double divider = mean(genomes) / boltzmannTemp;
        for (T genome : genomes) {
            double oldFitness = genome.getFitness();
            double newFitness = (oldFitness / boltzmannTemp) / divider;
            genome.setFitness(newFitness);
        }
    }
}

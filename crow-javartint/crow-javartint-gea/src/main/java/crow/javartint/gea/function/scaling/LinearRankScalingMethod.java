package crow.javartint.gea.function.scaling;

import crow.javartint.core.util.Optimize;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;
import crow.javartint.gea.util.Helper;

import java.util.Collections;
import java.util.List;

/**
 * Linear rank scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class LinearRankScalingMethod<T extends Genome<? extends Gene<?>>>
        extends AbstractScalingMethod<T> {

    private double selectivePressure;

    /**
     * Constructor that initializes this instance.
     *
     * @param optimize optimization way
     */
    public LinearRankScalingMethod(double selectivePressure, Optimize optimize) {
        super(optimize);
        if (selectivePressure < 1.0 || selectivePressure > 2.0) {
            throw new IllegalArgumentException("SELECTIVE PRESSURE MUST BE BETWEEN 1.0 AND 2.0");
        }
        this.selectivePressure = selectivePressure;
    }

    /**
     * Gets the selective pressure
     *
     * @return selective pressure value
     */
    public double getSelectivePressure() {
        return selectivePressure;
    }

    @Override
    protected void scale(List<T> genomes) {
        Collections.sort(genomes, Helper.getComparator(getOptimize()));
        for (int i = 0, n = genomes.size(); i < n; i++) {
            double newValue = 2.0 - getSelectivePressure() + 2.0 * (getSelectivePressure() - 1) * i / (n - 1);
            genomes.get(i).setFitness(newValue);
        }
    }
}

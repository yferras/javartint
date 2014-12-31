package crow.javartint.gea.function.scaling;

import crow.javartint.core.util.Optimize;
import crow.javartint.core.util.function.Function;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represents scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractScalingMethod<T extends Genome<? extends Gene<?>>>
        implements Function<List<T>, List<T>> {

    private final Optimize optimize;

    /**
     * Constructor that initializes this instance.
     *
     * @param optimize optimization way
     */
    public AbstractScalingMethod(Optimize optimize) {
        this.optimize = optimize;
    }

    /**
     * Get the value of optimize
     *
     * @return the value of optimize
     */
    public Optimize getOptimize() {
        return optimize;
    }

    /**
     * Method to perform scaling process
     *
     * @param genomes list to scale.
     */
    protected abstract void scale(List<T> genomes);

    /**
     * Ensures that parameters are valid.
     *
     * @param params parameters to validate.
     * @throws IllegalArgumentException if {@code params} is null
     *                                  or {@code params.isEmpty()} is {@code true}.
     */
    protected void validate(List<T> params) throws IllegalArgumentException {
        if (params == null) {
            throw new IllegalArgumentException("'params' can't be null.");
        }
        if (params.isEmpty()) {
            throw new IllegalArgumentException("'params' can't be empty.");
        }
    }

    /**
     * Validates the input parameters and performs the scaling process.
     *
     * @param params parameters to evaluate.
     * @return a list with scaled genomes
     * @throws java.lang.IllegalArgumentException see {@link #validate(java.util.List)}
     */
    @Override
    public List<T> evaluate(List<T> params) {
        validate(params);
        List<T> list = new ArrayList<>(params);
        scale(list);
        return list;
    }

    protected double mean(List<T> genomes) {
        double sum = 0.0;
        for (T genome : genomes) {
            sum += genome.getFitness();
        }
        return sum / genomes.size();
    }
}

package crow.jai.gea.function.crossing;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.Genome;

/**
 * Abstract class that implements interface {@link crow.jai.gea.function.crossing.CrossingFunction}
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
abstract public class AbstractCrossingFunction<T extends Genome<? extends Gene<?>>>
        implements CrossingFunction<T> {

    private double probability;
    private RandomGenerator randomGenerator;

    /**
     * Constructor.
     *
     * @param probability probability of crossing
     * @param randomGenerator random generator
     */
    protected AbstractCrossingFunction(double probability, RandomGenerator randomGenerator) {
        this.probability = probability;
        this.randomGenerator = randomGenerator;
    }

    /**
     * Constructor, initializes instances with probability of crossing
     * specified by {@code probability} parameter and random generator is an instance of
     * {@link crow.jai.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     *
     * @param probability probability of crossing
     */
    protected AbstractCrossingFunction(double probability) {
        this(probability, new RandomGenerator.SystemDefaultRandomGenerator());
    }

    /**
     * Default constructor, initializes instances with probability of crossing
     * equals to {@code .75} and random generator is an instance of
     * {@link crow.jai.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     */
    protected AbstractCrossingFunction() {
        this(.75);
    }

    @Override
    public double getCrossingProbability() {
        return probability;
    }

    @Override
    public void setCrossingProbability(final double probability) {
        this.probability = probability;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    @Override
    public void setRandomGenerator(final RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
}

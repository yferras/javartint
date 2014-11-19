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
     * @param probability     probability of crossing
     * @param randomGenerator random generator
     */
    protected AbstractCrossingFunction(double probability,
                                       RandomGenerator randomGenerator) {
        this.probability = probability;
        this.randomGenerator = randomGenerator;
    }

    /**
     * Constructor, initializes instances with probability of crossing
     * specified by {@code probability} parameter and random generator is an
     * instance of
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

    /**
     * Performs the specific crossing process. This method is called inside
     * the method {@link #evaluate(crow.jai.gea.genome.Genome[])}
     * @param parent1 first parent
     * @param parent2 second parent
     * @return the offspring.
     */
    protected abstract T[] crossingProcess(T  parent1, T parent2)
            throws CloneNotSupportedException;

    /**
     * Ensures that parameters are valid.
     *
     * @param params parameters to validate.
     * @throws IllegalArgumentException if {@code params} is null
     *                                  or {@code params.length} is less than 2.
     */
    protected void validate(T... params) throws IllegalArgumentException {
        if (params == null) {
            throw new IllegalArgumentException("'params' can't be null.");
        }
        if (params.length < 2) {
            throw new IllegalArgumentException(
                    "'params' must contain at less two elements");
        }
    }

    @Override
    public T[] evaluate(T... params) {
        validate(params);
        double randomProbability = getRandomGenerator().nextDouble();
        if (randomProbability > getCrossingProbability() ||
                params[0].equals(params[1])) {
            return params.clone();
        }
        try {
            return crossingProcess(params[0], params[1]);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getCrossingProbability() {
        return probability;
    }

    @Override
    public void setCrossingProbability(final double probability) {
        if (probability < 0 || probability > 1.0) {
            throw new IllegalArgumentException(
                    "'probability' must between 0.0 and 1.0");
        }
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

package crow.jai.gea.function.crossover;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.Genome;

/**
 * Abstract class that implements interface {@link CrossoverFunction}
 *
 * @param <T> Any derived class from {@link crow.jai.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
abstract public class AbstractCrossoverFunction<T extends Genome<? extends Gene<?>>>
        implements CrossoverFunction<T> {

    private double probability;
    private RandomGenerator randomGenerator;

    /**
     * Constructor.
     *
     * @param probability     probability of crossover
     * @param randomGenerator random generator
     */
    protected AbstractCrossoverFunction(double probability,
                                        RandomGenerator randomGenerator) {
        this.probability = probability;
        this.randomGenerator = randomGenerator;
    }

    /**
     * Constructor, initializes instances with probability of crossover
     * specified by {@code probability} parameter and random generator is an
     * instance of
     * {@link crow.jai.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     *
     * @param probability probability of crossover
     */
    protected AbstractCrossoverFunction(double probability) {
        this(probability, new RandomGenerator.SystemDefaultRandomGenerator());
    }

    /**
     * Default constructor, initializes instances with probability of crossover
     * equals to {@code .75} and random generator is an instance of
     * {@link crow.jai.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     */
    protected AbstractCrossoverFunction() {
        this(.75);
    }

    /**
     * Performs the specific crossover process. This method is called inside
     * the method {@link #evaluate(crow.jai.gea.genome.Genome[])}
     * @param parent1 first parent
     * @param parent2 second parent
     * @return the offspring.
     */
    protected abstract T[] recombine(T parent1, T parent2)
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
        if (randomProbability > getProbability() ||
                params[0].equals(params[1])) {
            return params.clone();
        }
        try {
            return recombine(params[0], params[1]);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void setProbability(final double probability) {
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

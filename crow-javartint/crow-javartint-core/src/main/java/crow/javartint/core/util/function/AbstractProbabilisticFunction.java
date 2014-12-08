package crow.javartint.core.util.function;

import crow.javartint.core.util.RandomGenerator;

/**
 * Class that implements partially {@link ProbabilisticFunction} to create
 * generic probabilistic functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
abstract public class AbstractProbabilisticFunction<R, P>
        implements ProbabilisticFunction<R, P> {

    private double probability;
    private RandomGenerator randomGenerator;

    /**
     * Constructor, initializes instances with the given parameters.
     * Internally calls {@link #setProbability(double)} and
     * {@link #setRandomGenerator(crow.javartint.core.util.RandomGenerator)}
     *
     * @param probability     probability of crossover
     * @param randomGenerator random generator
     * @throws java.lang.IllegalArgumentException see {@see #setProbability}
     * and see {@see #setRandomGenerator}
     */
    protected AbstractProbabilisticFunction(double probability,
                                            RandomGenerator randomGenerator)
            throws IllegalArgumentException {
        setProbability(probability);
        setRandomGenerator(randomGenerator);
    }

    /**
     * Constructor, initializes instances with probability specified by
     * {@code probability} parameter and random generator is an instance of
     * {@link crow.javartint.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     *
     * @param probability probability of crossover
     * @throws java.lang.IllegalArgumentException see {@see #setProbability}
     * and see {@see #setRandomGenerator}
     */
    protected AbstractProbabilisticFunction(double probability)
            throws IllegalArgumentException {
        this(probability, new RandomGenerator.SystemDefaultRandomGenerator());
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void setProbability(final double probability)
            throws IllegalArgumentException {
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
    public void setRandomGenerator(final RandomGenerator randomGenerator)
            throws IllegalArgumentException {
        if (randomGenerator == null) {
            throw new IllegalArgumentException("'randomGenerator' can't be " +
                    "null");
        }
        this.randomGenerator = randomGenerator;
    }

}

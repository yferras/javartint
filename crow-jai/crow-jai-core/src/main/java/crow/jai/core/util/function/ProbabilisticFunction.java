package crow.jai.core.util.function;

import crow.jai.core.util.RandomGenerator;

/**
 * Interface to create generic probabilistic functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface ProbabilisticFunction<R, P> extends Function<R, P> {

    /**
     * Gets the probability, a number between {@code 0.0} and {@code 0.0}.
     * @return the probability
     */
    double getProbability();

    /**
     * Sets the probability, a number that must between {@code 0.0} and
     * {@code 1.0}.
     *
     * @param probability the probability
     *
     * @throws java.lang.IllegalArgumentException if {@code probability is not
     * inside 0.0 and 1.0}
     */
    void setProbability(final double probability)
            throws IllegalArgumentException;

    /**
     * Gets the random generator.
     * @return the random generator
     */
    RandomGenerator getRandomGenerator();

    /**
     * Sets the random generator.
     *
     * @param randomGenerator the random generator
     */
    void setRandomGenerator(final RandomGenerator randomGenerator);

}

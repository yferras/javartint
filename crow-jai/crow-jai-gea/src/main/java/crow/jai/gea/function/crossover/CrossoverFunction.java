package crow.jai.gea.function.crossover;


import crow.jai.core.util.function.Function;
import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.Genome;

/**
 * Interface to represent the crossover process.
 *
 * @param <T> Any derived class from {@link crow.jai.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public interface CrossoverFunction<T extends Genome<? extends Gene<?>>>
        extends Function<T[], T> {

    /**
     * Gets the probability of crossover, a number between {@code 0.0} and {@code 0.0}.
     * @return the probability of crossover
     */
    double getProbability();

    /**
     * Sets the probability of crossover, a number that must between {@code 0.0} and {@code 0.0}.
     *
     * @param probability the probability of crossover
     */
    void setProbability(final double probability);

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

    /**
     * Accepts two genomes (parents) to perform the crossover process,
     * and retrieves an array containing the offspring.
     *
     * @param params parents.
     * @return the offspring.
     */
    @Override
    T[] evaluate(T... params);
}

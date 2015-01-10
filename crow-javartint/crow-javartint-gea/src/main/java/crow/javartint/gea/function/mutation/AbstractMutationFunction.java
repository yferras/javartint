package crow.javartint.gea.function.mutation;

import crow.javartint.core.util.RandomGenerator;
import crow.javartint.core.util.function.AbstractProbabilisticFunction;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * Abstract class that represents mutation function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
abstract public class AbstractMutationFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractProbabilisticFunction<T, T> implements MutationFunction<T> {

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability     probability of mutation
     * @param randomGenerator random generator
     */
    protected AbstractMutationFunction(double probability,
                                       RandomGenerator randomGenerator) {
        super(probability, randomGenerator);
    }

    /**
     * Constructor, initializes instances with probability of mutation
     * specified by {@code probability} parameter and random generator is an
     * instance of
     * {@link crow.javartint.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     *
     * @param probability probability of mutation
     */
    protected AbstractMutationFunction(double probability) {
        super(probability);
    }

    /**
     * Default constructor, initializes instances with probability of mutation
     * equals to {@code .05} and random generator is an instance of
     * {@link crow.javartint.core.util.RandomGenerator.SystemDefaultRandomGenerator}.
     */
    protected AbstractMutationFunction() {
        super(.05);
    }

    /**
     * Performs the specific mutation process. This method is called inside
     * the method {@link #evaluate(crow.javartint.gea.genome.Genome)}
     *
     * @param subject individual which will be mutate
     * @return mutated genome
     * @throws java.lang.CloneNotSupportedException if 
     */
    protected abstract T mutate(T subject) throws CloneNotSupportedException;

    /**
     * Ensures that parameter are valid.
     *
     * @param param parameter to validate.
     * @throws IllegalArgumentException if {@code param} is {@code null}.
     */
    protected void validate(T param) throws IllegalArgumentException {
        if (param == null) {
            throw new IllegalArgumentException("'params' can't be null.");
        }
    }

    /**
     * If validation process is ok and generated random probability
     * is in bounds, performs the mutation process with a copy of
     * genome and returns a mutated genome.
     *
     * @param params genome to mutate.
     * @return if the mutation process is done, a copy of mutated
     * genome is returned, otherwise if a CloneNotSupportedException
     * is raised  a null value is returned.
     *
     * @throws java.lang.IllegalArgumentException see {@link #validate(crow.javartint.gea.genome.Genome)}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T evaluate(T params) {
        validate(params);
        try {
            if (getRandomGenerator().nextDouble() > getProbability()) {
                return (T) params.clone();
            }
            return mutate((T) params.clone());
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }
}

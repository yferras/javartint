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
     * the method {@link #evaluate}
     *
     * @param subject individual which will be mutate
     */
    protected abstract void mutate(T subject);

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

    @Override
    public T evaluate(T params) {
        validate(params);
        double randomProbability = getRandomGenerator().nextDouble();
        if (randomProbability > getProbability()) {
            return params;
        }
        mutate(params);
        return params;
    }
}

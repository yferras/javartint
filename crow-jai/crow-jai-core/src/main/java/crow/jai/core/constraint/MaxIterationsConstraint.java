package crow.jai.core.constraint;

import crow.jai.core.IterativeAlgorithm;
import crow.jai.core.Solution;

/**
 * This class can be used to create constrains to iterative algorithms.
 *
 * @param <A>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class MaxIterationsConstraint<A extends IterativeAlgorithm<? extends Solution>>
        extends AbstractIterationConstraint<A> {

    /**
     * Constructor.
     *
     * @param constraintType constrain type.
     * @param maxIterations  max allowed iterations.
     */
    public MaxIterationsConstraint(ConstraintType constraintType,
                                   long maxIterations) {
        super(constraintType, maxIterations);
    }

    /**
     * Tests if the iterations of algorithm is greater than or equals to the max
     * allowed, in this case returns <code>true</code>, otherwise returns
     * <code>false</code>.
     *
     * @param algorithm instance derived from {@link IterativeAlgorithm}
     * @return <code>true</code> if the iterations of algorithm is greater than
     * or equals to the max allowed, otherwise returns <code>false</code>.
     * @throws IllegalArgumentException
     */
    @Override
    public boolean evaluate(A algorithm) {
        return algorithm.getIterations() >= maxIterations;
    }

}

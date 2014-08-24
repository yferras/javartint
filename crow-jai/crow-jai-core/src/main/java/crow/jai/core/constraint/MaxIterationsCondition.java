package crow.jai.core.constraint;

import crow.jai.core.Algorithm;
import crow.jai.core.IterativeAlgorithm;

/**
 * This class can be used to create conditions to iterative algorithms.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class MaxIterationsCondition extends AbstractIterationConstraint {

    /**
     * Constructor.
     *
     * @param constraintType condition type.
     * @param maxIterations max allowed iterations.
     */
    public MaxIterationsCondition(ConstraintType constraintType, 
            long maxIterations) {
        super(constraintType, maxIterations);
    }

    /**
     * Tests if the iterations of algorithm is greater than or equals to the max
     * allowed, in this case returns <code>true</code>, otherwise returns
     * <code>false</code> .
     *
     * @param algorithm instance derived from {@link IterativeAlgorithm}
     * @return <code>true</code> if the iterations of algorithm is greater than
     * or equals to the max allowed, otherwise returns <code>false</code>.
     * 
     * @throws IllegalArgumentException
     */
    @Override
    public boolean eval(Algorithm algorithm) {
        if (algorithm instanceof IterativeAlgorithm) {
            IterativeAlgorithm iterativeAlgorithm = (IterativeAlgorithm) algorithm;
            return iterativeAlgorithm.getIterations() >= maxIterations;
        }
        throw new IllegalArgumentException();
    }

}

package crow.jai.core.constraint;

import crow.jai.core.Algorithm;
import crow.jai.core.IterativeAlgorithm;

/**
 * This class can be used to create constrains to iterative algorithms.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class MaxIterationsConstraint extends AbstractIterationConstraint {

    /**
     * Constructor.
     *
     * @param constraintType constrain type.
     * @param maxIterations max allowed iterations.
     */
    public MaxIterationsConstraint(ConstraintType constraintType, 
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

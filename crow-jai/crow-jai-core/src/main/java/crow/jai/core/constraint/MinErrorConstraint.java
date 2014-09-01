package crow.jai.core.constraint;

import crow.jai.core.ErrorBasedAlgorithm;
import crow.jai.core.Solution;

/**
 * This class can be used to create constrains to algorithms based on errors.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 * @param <A> Any derived class from {@link ErrorBasedAlgorithm} interface.
 */
public class MinErrorConstraint<A extends ErrorBasedAlgorithm<? extends Solution>>
        extends AbstractThresholdConstraint<Double, A> {

    /**
     * Constructor.
     *
     * @param constraintType constrain type.
     * @param threshold error threshold.
     */
    public MinErrorConstraint(ConstraintType constraintType, double threshold) {
        super(constraintType, threshold);
    }

    /**
     * Tests if the actual error of algorithm is less than the threshold, in
     * this case returns <code>true</code>, otherwise returns
     * <code>false</code>.
     *
     * @param algorithm instance derived from {@link ErrorBasedAlgorithm}
     * @return <code>true</code> if the actual error of algorithm is greater
     * than the threshold, otherwise returns <code>false</code>.
     */
    @Override
    public boolean eval(A algorithm) {
         return algorithm.getCurrentError().compareTo(getThreshold()) < 0;
    }

}

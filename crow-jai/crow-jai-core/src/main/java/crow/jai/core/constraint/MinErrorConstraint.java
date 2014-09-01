package crow.jai.core.constraint;

import crow.jai.core.Algorithm;
import crow.jai.core.ErrorBasedAlgorithm;

/**
 * This class can be used to create constrains to algorithms based on errors.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class MinErrorConstraint extends AbstractThresholdConstraint {

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
    public boolean eval(Algorithm algorithm) {
        if (algorithm instanceof ErrorBasedAlgorithm) {
            ErrorBasedAlgorithm basedOnErrorAlgorithm = (ErrorBasedAlgorithm) algorithm;
            return basedOnErrorAlgorithm.getCurrentError() < threshold;
        }
        throw new IllegalArgumentException();
    }

}

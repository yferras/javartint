package crow.javartint.core.constraint;

import crow.javartint.core.Solution;
import crow.javartint.core.ThresholdBasedAlgorithm;

/**
 * This abstract class can be used to create constrains to algorithms based on
 * thresholds.
 *
 * @param <T> Any derived class from {@link Comparable} interface
 * @param <A> Any derived class from {@link ThresholdBasedAlgorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractThresholdConstraint<T extends Comparable<T>, A extends ThresholdBasedAlgorithm<? extends Solution, T>>
        extends AbstractConstraint<A> implements ThresholdConstraint<T, A> {

    private T threshold;

    /**
     * Constructor.
     *
     * @param constraintType constraint type.
     * @param threshold      threshold
     */
    public AbstractThresholdConstraint(ConstraintType constraintType,
                                       T threshold) {
        super(constraintType);
        this.threshold = threshold;
    }

    /**
     * Gets the value of threshold.
     *
     * @return the value of threshold.
     */
    @Override
    public T getThreshold() {
        return threshold;
    }

    /**
     * Sets the value of threshold.
     *
     * @param threshold the value of threshold.
     */
    public void setThreshold(T threshold) {
        this.threshold = threshold;
    }
}

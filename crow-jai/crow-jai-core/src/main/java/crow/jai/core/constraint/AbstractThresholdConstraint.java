package crow.jai.core.constraint;

/**
 * This abstract class can be used to create constrains to algorithms based on
 * thresholds.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractThresholdConstraint extends AbstractConstraint {

    /**
     * The threshold.
     */
    protected double threshold;

    /**
     * Constructor.
     *
     * @param constraintType constraint type.
     * @param threshold threshold
     */
    public AbstractThresholdConstraint(ConstraintType constraintType,
            double threshold) {
        super(constraintType);
        this.threshold = threshold;
    }

    /**
     * Sets the value of threshold.
     *
     * @param threshold the value of threshold.
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Gets the value of threshold.
     *
     * @return the value of threshold.
     */
    double getThreshold() {
        return threshold;
    }
}

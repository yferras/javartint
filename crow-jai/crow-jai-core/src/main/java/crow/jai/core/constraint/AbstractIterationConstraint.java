package crow.jai.core.constraint;

/**
 * This abstract class can be used to create constraints to iterative
 * algorithms.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractIterationConstraint extends AbstractConstraint {

    /**
     * Max iterations.
     */
    protected long maxIterations;

    /**
     * Constructor
     *
     * @param constraintType condition type.
     * @param maxIterations max allowed iterations.
     */
    public AbstractIterationConstraint(ConstraintType constraintType,
            long maxIterations) {
        super(constraintType);
        this.maxIterations = maxIterations;
    }

    /**
     * Gets the value of max iterations.
     *
     * @return the value of max iterations.
     */
    public long getMaxIterations() {
        return maxIterations;
    }

    /**
     * Sets the value of max iterations.
     *
     * @param maxIterations the value of max iterations.
     */
    public void setMaxIterations(long maxIterations) {
        this.maxIterations = maxIterations;
    }

}

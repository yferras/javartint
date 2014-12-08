package crow.javartint.core.constraint;

import crow.javartint.core.IterativeAlgorithm;
import crow.javartint.core.Solution;

/**
 * This abstract class can be used to create constraints to iterative
 * algorithms.
 *
 * @param <A> Any derived class from {@link IterativeAlgorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractIterationConstraint<A extends IterativeAlgorithm<? extends Solution>>
        extends AbstractConstraint<A> {

    /**
     * Max iterations.
     */
    protected long maxIterations;

    /**
     * Constructor
     *
     * @param constraintType constrain type.
     * @param maxIterations  max allowed iterations.
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

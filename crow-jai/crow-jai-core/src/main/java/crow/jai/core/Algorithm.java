package crow.jai.core;

import crow.jai.core.constraint.Constraint;

/**
 * This interface represents in the general way an algorithm.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 * @param <S> Any class derived from {@link Solution} interface.
 */
public interface Algorithm<S extends Solution> extends Runnable {

    /**
     * Gets the final solution of algorithm.
     *
     * @return the final solution.
     */
    public S getSolution();

    /**
     * Adds a new constraint.
     *
     * @param constraint new constraint.
     */
    void addConstraint(Constraint<? extends Algorithm<? extends Solution>> constraint);

    /**
     * Returns an array filled with the constraints.
     *
     * @return constraint array.
     */
    Constraint<? extends Algorithm<? extends Solution>>[] getConstraints();

    /**
     * Gets the elapsed time in milliseconds.
     *
     * @return the elapsed time in milliseconds.
     */
    long getElapsedTime();

    /**
     * Retrieves <code>true</code> if the current algorithm still running,
     * otherwise <code>false</code>.
     *
     * @return <code>true</code> if the current algorithm still running,
     * otherwise <code>false</code>.
     */
    boolean isRunning();

    /**
     * Removes a specific constraint from algorithm.
     *
     * @param constraint constraint to remove.
     */
    void removeConstraint(Constraint<? extends Algorithm<? extends Solution>> constraint);

    /**
     * Serves to stop the algorithm.
     */
    void stop();

    /**
     * This method evaluates all constraints and returns <code>true</code> in
     * the following cases:
     * <ul>
     * <li>
     * If and only if all mandatory constraints return <code>true</code>.</li>
     * <li>
     * Or in the case that will not exist mandatory constrains, with only one
     * optional constraint that return <code>true</code>.</li>
     * </ul>
     * Otherwise returns <code>false</code>.
     *
     * @return see the method description.
     */
    boolean testConstraint();
}

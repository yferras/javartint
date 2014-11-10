package crow.jai.core.constraint;

import crow.jai.core.Algorithm;
import crow.jai.core.Solution;

/**
 * Serves to create constraints to stop the algorithm's execution.
 *
 * @param <A> Any derived class from {@link Algorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public interface Constraint<A extends Algorithm<? extends Solution>> {

    /**
     * Test any parameter of the algorithm and if this constraint is
     * satisfied then returns <code>true</code>, otherwise returns
     * <code>false</code>.
     *
     * @param algorithm algorithm to test.
     * @return if this constraint is satisfied then returns
     * <code>true</code>, otherwise returns <code>false</code>.
     */
    boolean evaluate(A algorithm);

    /**
     * Gets the constraint type.
     *
     * @return the constraint type.
     */
    ConstraintType getConstraintType();

    /**
     * Sets the constraint type.
     *
     * @param constraintType constraint type.
     */
    void setConstraintType(ConstraintType constraintType);
}

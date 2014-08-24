package crow.jai.core.constraint;

import crow.jai.core.Algorithm;

/**
 * Serves to create constraints to stop the algorithm's execution.
 * 
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface Constraint {

	/**
	 * Test any parameter of the algorithm and if this constraint is 
         * satisfied then returns <code>true</code>, otherwise returns 
         * <code>false</code>.
	 * 
	 * @param algorithm
	 *            algorithm to test.
	 * @return if this constraint is satisfied then returns 
         *          <code>true</code>, otherwise returns <code>false</code>.
	 */
	boolean eval(Algorithm algorithm);

	/**
	 * Gets the constraint type.
	 * 
	 * @return the constraint type.
	 */
	ConstraintType getConstraintType();

	/**
	 * Sets the constraint type.
	 * 
	 * @param constraintType 
	 *            constraint type.
	 */
	void setConstraintType(ConstraintType constraintType);
}

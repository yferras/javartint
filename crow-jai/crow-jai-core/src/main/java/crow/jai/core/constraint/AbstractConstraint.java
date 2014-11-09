package crow.jai.core.constraint;

import crow.jai.core.Algorithm;
import crow.jai.core.Solution;

/**
 * Basic abstract implementation of {@link Constraint}
 *
 * @param <A> Any derived class from {@link Algorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractConstraint<A extends Algorithm<? extends Solution>>
        implements Constraint<A> {

    /**
     * Constraint type.
     */
    protected ConstraintType constraintType;

    /**
     * Constructor
     *
     * @param constraintType constrain type.
     */
    public AbstractConstraint(ConstraintType constraintType) {
        this.constraintType = constraintType;
    }

    @Override
    public ConstraintType getConstraintType() {
        return constraintType;
    }

    @Override
    public void setConstraintType(ConstraintType constraintType) {
        this.constraintType = constraintType;
    }

}

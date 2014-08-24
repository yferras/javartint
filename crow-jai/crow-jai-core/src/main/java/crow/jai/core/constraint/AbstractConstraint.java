package crow.jai.core.constraint;

/**
 * Basic abstract implementation of {@link Constraint}
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractConstraint implements Constraint {

    /**
     * Constraint type.
     */
    protected ConstraintType constraintType;

    /**
     * Constructor
     *
     * @param constraintType condition type.
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

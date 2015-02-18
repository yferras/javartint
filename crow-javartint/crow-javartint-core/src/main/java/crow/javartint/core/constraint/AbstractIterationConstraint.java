package crow.javartint.core.constraint;

/*
 * #%L
 * Crow JavArtInt Core
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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

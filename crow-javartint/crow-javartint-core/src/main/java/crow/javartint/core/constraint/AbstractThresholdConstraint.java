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

import crow.javartint.core.Solution;
import crow.javartint.core.Algorithm;

/**
 * This abstract class can be used to create constrains to algorithms based on
 * thresholds.
 *
 * @param <T> Any derived class from {@link Comparable} interface
 * @param <A> Any derived class from {@link Algorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.3
 */
public abstract class AbstractThresholdConstraint<T extends Comparable<T>, A extends Algorithm<? extends Solution>>
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

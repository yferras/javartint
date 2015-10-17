package com.github.yferras.javartint.core.constraint;

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

import com.github.yferras.javartint.core.Algorithm;
import com.github.yferras.javartint.core.Solution;

/**
 * Serves to create constraints to stop the algorithm's execution.
 *
 * @param <A> Any derived class from {@link com.github.yferras.javartint.core.Algorithm} interface.
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

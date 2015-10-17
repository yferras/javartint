package com.github.yferras.javartint.core;

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

import com.github.yferras.javartint.core.constraint.Constraint;

/**
 * This interface represents in the general way an algorithm.
 *
 * @param <S> Any class derived from {@link com.github.yferras.javartint.core.Solution} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
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
    Constraint<Algorithm<Solution>>[] getConstraints();

    /**
     * Gets the elapsed time in milliseconds.
     *
     * @return the elapsed time in milliseconds.
     */
    Long getElapsedTime();

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
     * @return the result of evaluates all constraints.
     */
    boolean testConstraint();
}

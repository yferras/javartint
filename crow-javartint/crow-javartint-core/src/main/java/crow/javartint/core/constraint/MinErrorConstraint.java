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

import crow.javartint.core.ErrorBasedAlgorithm;
import crow.javartint.core.Solution;

/**
 * This class can be used to create constrains to algorithms based on errors.
 *
 * @param <A> Any derived class from {@link ErrorBasedAlgorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class MinErrorConstraint<A extends ErrorBasedAlgorithm<? extends Solution>>
        extends AbstractThresholdConstraint<Double, A> {

    /**
     * Constructor.
     *
     * @param constraintType constrain type.
     * @param threshold      error threshold.
     */
    public MinErrorConstraint(ConstraintType constraintType, double threshold) {
        super(constraintType, threshold);
    }

    /**
     * Tests if the actual error of algorithm is less than the threshold, in
     * this case returns <code>true</code>, otherwise returns
     * <code>false</code>.
     *
     * @param algorithm instance derived from {@link ErrorBasedAlgorithm}
     * @return <code>true</code> if the actual error of algorithm is greater
     * than the threshold, otherwise returns <code>false</code>.
     */
    @Override
    public boolean evaluate(A algorithm) {
        return algorithm.getCurrentError().compareTo(getThreshold()) < 0;
    }

}

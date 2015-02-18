package crow.javartint.core.util;

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

import crow.javartint.core.Algorithm;
import crow.javartint.core.Solution;

import java.util.EventObject;

/**
 * Algorithm event object.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class AlgorithmEvent extends EventObject {

    private static final long serialVersionUID = -4552301165892831022L;
    private final Solution solution;

    /**
     * Constructor.
     *
     * @param source   The object on which the Event initially occurred.
     * @param solution The solution.
     */
    public AlgorithmEvent(Algorithm<?> source, Solution solution) {
        super(source);
        this.solution = solution;
    }

    /**
     * Gets the solution.
     *
     * @return The solution.
     */
    public Solution getSolution() {
        return solution;
    }

}

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

import com.github.yferras.javartint.core.Solution;
import com.github.yferras.javartint.core.Algorithm;

/**
 * This interface allows to create algorithms based on numeric threshold.
 *
 * @param <N> Any derived class from {@link Number} abstract class.
 * @param <A> Any derived class from {@link Algorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public interface NumericThresholdConstraint<N extends Number & Comparable<N>, A extends Algorithm<? extends Solution>>
	extends ThresholdConstraint<N, A> {

}

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
 * Basic abstract implementation of
 * {@link com.github.yferras.javartint.core.constraint.Constraint}
 *
 * @param <A>
 *            Any derived class from
 *            {@link com.github.yferras.javartint.core.Algorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractConstraint<A extends Algorithm<? extends Solution>> implements Constraint<A> {

	/**
	 * Constraint type.
	 */
	protected ConstraintType constraintType;

	/**
	 * Constructor
	 *
	 * @param constraintType
	 *            constrain type.
	 */
	public AbstractConstraint(ConstraintType constraintType) {
		this.constraintType = constraintType;
	}

	/** {@inheritDoc} */
	@Override
	public ConstraintType getConstraintType() {
		return constraintType;
	}

	/** {@inheritDoc} */
	@Override
	public void setConstraintType(ConstraintType constraintType) {
		this.constraintType = constraintType;
	}

}

package com.github.yferras.javartint.core.function;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Interface to create generic derivable functions.
 *
 * @param <R>
 *            Type of result
 * @param <P>
 *            Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.2
 * @since 1.0.1
 */
public interface DerivableFunction<R, P> extends Function<R, P> {

	/**
	 * Builder to construct instances of {@link DerivableFunction}.
	 *
	 * @param <R>
	 *            Type of result
	 * @param <P>
	 *            Type of parameters
	 * @author Eng. Ferrás Cecilio, Yeinier
	 * @version 0.1
	 * @since 1.1.0
	 */
	class Builder<R, P> implements com.github.yferras.javartint.core.util.Builder<DerivableFunction<R, P>> {

		private Function<R, P> baseFunction;
		private List<Function<R, P>> functions;

		/**
		 * Constructor for Builder.
		 */
		public Builder() {
			functions = new ArrayList<>();
		}

		/**
		 * Adds new derivative function. The order corresponds with the order of
		 * this method is invoked.
		 *
		 * @param function
		 *            derivative function.
		 * @return an instance of this builder.
		 */
		public Builder<R, P> addDerived(Function<R, P> function) {
			functions.add(function);
			return this;
		}

		/** {@inheritDoc} */
		@Override
		public DerivableFunction<R, P> build() {
			return new DerivableFunction<R, P>() {
				@Override
				public DerivableFunction<R, P> derive() {
					return buildRecursive(functions.iterator(), 1);
				}

				@Override
				public R evaluate(P params) {
					return baseFunction.evaluate(params);
				}

				@Override
				public int getOrder() {
					return 0;
				}
			};
		}

		private DerivableFunction<R, P> buildRecursive(final Iterator<Function<R, P>> it, final int order) {
			if (it.hasNext()) {
				final Function<R, P> function = it.next();
				return new DerivableFunction<R, P>() {
					@Override
					public DerivableFunction<R, P> derive() {
						return buildRecursive(it, order + 1);
					}

					@Override
					public R evaluate(P params) {
						return function.evaluate(params);
					}

					@Override
					public int getOrder() {
						return order;
					}
				};
			}
			return null;
		}

		/**
		 * Sets the base function. This method must be invoked once.
		 *
		 * @param function
		 *            base function.
		 * @return an instance of this builder.
		 */
		public Builder<R, P> setBaseFunction(Function<R, P> function) {
			baseFunction = function;
			return this;
		}
	}

	/**
	 * Gets the derived function.
	 *
	 * @return derived function. If the current function doesn't has derivative,
	 *         will return {@code null}.
	 */
	DerivableFunction<R, P> derive();

	/**
	 * Gets the level of derived function. Normally a constant integer, for
	 * example for the first derived this method must return 1, for the second
	 * derived must return 2, and so on.
	 *
	 * @return an integer that represents the level of derived function.
	 * @since 1.1.0
	 */
	int getOrder();
}

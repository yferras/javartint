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
 * Interface to create generic composite functions.
 *
 * @param <R>
 *            Type of result
 * @param <P>
 *            Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.2
 * @since 1.0.1
 */
public interface CompositeFunction<R, P> extends Function<R, P> {

	/**
	 * Builder to construct instances of {@link CompositeFunction}.
	 *
	 * @param <R>
	 *            Type of result
	 * @param <P>
	 *            Type of parameters
	 * @author Eng. Ferrás Cecilio, Yeinier
	 * @version 0.1
	 * @since 1.1.0
	 */
	class Builder<R, P> implements com.github.yferras.javartint.core.util.Builder<CompositeFunction<R, P>> {

		@SuppressWarnings("rawtypes")
		private List<Function> functions;

		/**
		 * Constructor for Builder.
		 */
		public Builder() {
			functions = new ArrayList<>();
		}

		/**
		 * Always adds to the end the function.
		 *
		 * @param function
		 *            function instance to add.
		 * @return @return an instance of this builder.
		 */
		public Builder<R, P> append(@SuppressWarnings("rawtypes") Function function) {
			functions.add(function);
			return this;
		}

		/** {@inheritDoc} */
		@Override
		public CompositeFunction<R, P> build() {
			return new CompositeFunction<R, P>() {
				@SuppressWarnings("unchecked")
				@Override
				public R evaluate(P params) {
					if (functions.isEmpty()) {
						return null;
					}
					@SuppressWarnings("rawtypes")
					final Iterator<Function> iterator = functions.iterator();
					Object result = iterator.next().evaluate(params);
					while (iterator.hasNext()) {
						result = iterator.next().evaluate(result);
					}
					return (R) result;
				}

				@SuppressWarnings("rawtypes")
				@Override
				public Function get(int index) {
					return functions.get(index);
				}

				@Override
				public int size() {
					return functions.size();
				}
			};
		}

		/**
		 * Always inserts the function to the beginning.
		 *
		 * @param function
		 *            function instance to add.
		 * @return @return an instance of this builder.
		 */
		public Builder<R, P> push(@SuppressWarnings("rawtypes") Function function) {
			functions.add(0, function);
			return this;
		}
	}

	/**
	 * <p>
	 * get.
	 * </p>
	 *
	 * @param index
	 *            a int.
	 * @return a {@link com.github.yferras.javartint.core.function.Function}
	 *         object.
	 * @since 1.1.0
	 */
	@SuppressWarnings("rawtypes")
	Function get(int index);

	/**
	 * Gets the numbers of functions.
	 *
	 * @return the numbers functions.
	 * @since 1.1.0
	 */
	int size();

}

package com.github.yferras.javartint.core.util;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <p>
 * This abstract class implements
 * {@link com.github.yferras.javartint.core.util.Builder}, and it can be used as
 * base of future builders.
 * </p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 1.0
 * @since 1.1.0
 */
public abstract class AbstractBuilder<T> implements Builder<T> {

	private final Properties properties;
	private final Set<String> requiredPropertyKeys;

	/**
	 * <p>
	 * Constructor for AbstractBuilder.
	 * </p>
	 *
	 * @param requiredPropertyKeys
	 *            required property keys.
	 */
	protected AbstractBuilder(String... requiredPropertyKeys) {
		this.properties = new Properties();
		SortedSet<String> sortedSet = new TreeSet<>(Arrays.asList(requiredPropertyKeys));
		this.requiredPropertyKeys = Collections.unmodifiableSortedSet(sortedSet);
	}

	/**
	 * <p>
	 * Gets the properties. The properties are used to store the configuration
	 * required to build the object.
	 * </p>
	 *
	 * @return a {@link java.util.Properties} object.
	 */
	protected final Properties getProperties() {
		return properties;
	}

	/**
	 * <p>
	 * Gets the set of required keys.
	 * </p>
	 *
	 * @return an unmodifiable sorted {@link java.util.Set} object.
	 */
	protected final Set<String> getRequiredPropertyKeys() {
		return requiredPropertyKeys;
	}

	/**
	 * <p>
	 * Checks that all required property keys are setting up.
	 * </p>
	 *
	 * @throws com.github.yferras.javartint.core.util.ValidationException
	 *             if any required property key is not setting up.
	 */
	protected void validate() {
		final Set<Object> keySet = getProperties().keySet();
		if (!keySet.containsAll(getRequiredPropertyKeys())) {
			Set<String> auxSet = new LinkedHashSet<>(getRequiredPropertyKeys());
			auxSet.removeAll(keySet);
			StringBuilder builder = new StringBuilder("Parameter").append(auxSet.size() == 1 ? ": " : "s: ");
			for (String s : auxSet) {
				builder.append("'").append(s).append("', ");
			}
			builder.append(auxSet.size() == 1 ? " is " : " are ").append("required.");
			throw new ValidationException(builder.toString());
		}
	}

	/**
	 * <p>
	 * This method must be implemented by subclasses to create the object.
	 * </p>
	 *
	 * @return a T object.
	 */
	protected abstract T buildObject();

	/**
	 * {@inheritDoc}
	 * <p>
	 * Performs the validation process and then builds the object.
	 * </p>
	 */
	@Override
	public T build() {
		validate();
		return buildObject();
	}
}

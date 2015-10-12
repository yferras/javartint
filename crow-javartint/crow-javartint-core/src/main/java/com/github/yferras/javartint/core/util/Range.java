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

/**
 * This class is useful to define ranges.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class Range<T extends Comparable<T>> implements Filter<T> {

    private static final int MAGIC_NUMBER = 31;

    private final T min;
    private final T max;
    private final Use use;

    /**
     * Initializes this instance.
     *
     * @param min lower bound
     * @param max upper bound
     * @param use defines the use of bounds
     * @throws com.github.yferras.javartint.core.util.ValidationException if {@code min} is greater than {@code max}
     */
    public Range(T min, T max, Use use) {
        if (min.compareTo(max) > 0) {
            throw new ValidationException("'min' is greater than 'max'");
        }
        this.min = min;
        this.max = max;
        this.use = use;
    }

    /**
     * Initializes this instance.
     *
     * @param min lower bound
     * @param max upper bound
     */
    public Range(T min, T max) {
        this(min, max, Use.BOTH);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Test if the element is inside of the range.
     */
    @Override
    public boolean accept(T element) {
        switch (use) {
            case BOTH:
                return element.compareTo(getMin()) >= 0 && element.compareTo(getMax()) <= 0;
            case NONE:
                return element.compareTo(getMin()) > 0 && element.compareTo(getMax()) < 0;
            case MIN:
                return element.compareTo(getMin()) >= 0 && element.compareTo(getMax()) < 0;
            case MAX:
                return element.compareTo(getMin()) > 0 && element.compareTo(getMax()) <= 0;
            default:
                return false;
        }
    }

    /**
     * Gets the lower bound
     *
     * @return the min value
     */
    public T getMin() {
        return min;
    }

    /**
     * Gets the upper bound
     *
     * @return the max value
     */
    public T getMax() {
        return max;
    }

    /**
     * Gets the use of bounds
     *
     * @return use value
     */
    public Use getUse() {
        return use;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Range<?> range = (Range<?>) o;

        if (min != null ? !min.equals(range.min) : range.min != null) {
            return false;
        }
        if (max != null ? !max.equals(range.max) : range.max != null) {
            return false;
        }
        return use == range.use;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = MAGIC_NUMBER * result + (max != null ? max.hashCode() : 0);
        result = MAGIC_NUMBER * result + (use != null ? use.hashCode() : 0);
        return result;
    }

    /**
     * To define the use the limits in the range.
     */
    public static enum Use {
        /**
         * Both limits are included
         */
        BOTH,
        /**
         * None are include
         */
        NONE,
        /**
         * Only min is included
         */
        MIN,
        /**
         * Only max is included
         */
        MAX
    }
}

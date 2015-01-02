package crow.javartint.core.util;

/**
 * This class is useful to define ranges.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class Range<T extends Comparable<T>> implements Filter<T> {

    /**
     * To define the use the limits in the range
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

    private final T min;
    private final T max;
    private final Use use;

    /**
     * Initializes this instance.
     *
     * @param min lower bound
     * @param max upper bound
     * @param use defines the use of bounds
     * @throws java.lang.IllegalArgumentException if {@code min} is greater than {@code max}
     */
    public Range(T min, T max, Use use) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("'min' is greater than 'max'");
        }
        this.min = min;
        this.max = max;
        this.use = use;
    }

    /**
     * Test if a input value is inside range.
     *
     * @param element element to check
     * @return {@code true} if the element is inside the bounds; otherwise returns {@code false}
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
}

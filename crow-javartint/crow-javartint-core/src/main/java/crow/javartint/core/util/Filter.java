package crow.javartint.core.util;

/**
 * This interfaces is useful to create custom filters.
 *
 * @param <T> Generic type.
 * @author Eng. Ferrás Cecilio, Yeinier Jul 18, 2011
 */
public interface Filter<T> {

    /**
     * Serves to create custom filters.
     *
     * @param element element to check
     * @return <code>true</code> if this element pass the filter, otherwise
     * returns <code>false</code>
     */
    boolean accept(T element);
}
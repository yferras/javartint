package crow.jai.core.util;

/**
 * This interfaces is useful to create custom filters.
 *
 * @author Eng. Ferrás Cecilio, Yeinier Jul 18, 2011
 * @param <T> Generic type.
 */
public interface Filter<T> {

    /**
     * Serves to accept custom filters.
     *
     * @param element element to filter
     * @return <code>true</code> if this element pass the filter, otherwise
     * returns <code>false</code>
     */
    boolean accept(T element);
}

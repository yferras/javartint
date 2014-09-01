package crow.jai.core;

/**
 * This interface allows to create algorithms based on threshold.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @param <S> Any class derived from {@link Solution} interface.
 * @param <T> Any class derived from {@link Comparable} interface.
 */
public interface ThresholdBasedAlgorithm<S extends Solution, T extends Comparable<T>> 
    extends Algorithm<S> {
    
    /**
     * Gets the current threshold.
     * @return current threshold.
     */
    T getThreshold();
}

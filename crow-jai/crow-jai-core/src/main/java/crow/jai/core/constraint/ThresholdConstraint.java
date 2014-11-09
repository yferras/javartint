package crow.jai.core.constraint;

import crow.jai.core.Solution;
import crow.jai.core.ThresholdBasedAlgorithm;

/**
 * Serves to create constraints based on thresholds.
 *
 * @param <T> Any derived class from {@link Comparable} interface.
 * @param <A> Any derived class from {@link ThresholdBasedAlgorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface ThresholdConstraint<T extends Comparable<T>, A extends ThresholdBasedAlgorithm<? extends Solution, T>>
        extends Constraint<A> {

    /**
     * Gets the threshold.
     *
     * @return the threshold.
     */
    T getThreshold();

}
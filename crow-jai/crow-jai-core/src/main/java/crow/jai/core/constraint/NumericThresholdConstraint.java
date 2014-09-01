package crow.jai.core.constraint;

import crow.jai.core.Solution;
import crow.jai.core.ThresholdBasedAlgorithm;

/**
 * This interface allows to create algorithms based on threshold.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @param <N> Any derived class from {@link Number} abstract class.
 * @param <A> Any derived class from {@link ThresholdBasedAlgorithm} interface.
 */
public interface NumericThresholdConstraint<N extends Number & Comparable<N>, A extends ThresholdBasedAlgorithm<? extends Solution, N>> 
    extends ThresholdConstraint<N, A> {
    
}

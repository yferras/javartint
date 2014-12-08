package crow.javartint.core.constraint;

import crow.javartint.core.Solution;
import crow.javartint.core.ThresholdBasedAlgorithm;

/**
 * This interface allows to create algorithms based on threshold.
 *
 * @param <N> Any derived class from {@link Number} abstract class.
 * @param <A> Any derived class from {@link ThresholdBasedAlgorithm} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface NumericThresholdConstraint<N extends Number & Comparable<N>, A extends ThresholdBasedAlgorithm<? extends Solution, N>>
        extends ThresholdConstraint<N, A> {

}

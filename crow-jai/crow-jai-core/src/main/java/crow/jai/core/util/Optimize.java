package crow.jai.core.util;

import crow.jai.core.OptimizationAlgorithm;

/**
 * This enumeration serves to tell the way to optimize the solution in the
 * classes derived from {@link OptimizationAlgorithm}.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public enum Optimize {

    /**
     * Optimize to obtain the maximum solution.
     */
    MAX,
    /**
     * Optimize to obtain the minimum solution.
     */
    MIN
}

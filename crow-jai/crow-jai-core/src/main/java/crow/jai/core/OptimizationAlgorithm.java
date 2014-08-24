package crow.jai.core;

import crow.jai.core.util.Optimize;

/**
 * This interface allows to create algorithms to optimize.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface OptimizationAlgorithm extends Algorithm {

    /**
     *Gets the optimize type.
     * @return optimize type.
     */
    public Optimize getOptimize();

    /**
     * Sets the optimize type.
     * @param optimize the optimize type.
     */
    public void setOptimize(Optimize optimize);
}

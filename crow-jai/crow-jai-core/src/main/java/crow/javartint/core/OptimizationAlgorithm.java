package crow.javartint.core;

import crow.javartint.core.util.Optimize;

/**
 * This interface allows to create algorithms to optimize.
 *
 * @param <S> Any class derived from {@link Solution} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public interface OptimizationAlgorithm<S extends Solution> extends Algorithm<S> {

    /**
     * Gets the optimize type.
     *
     * @return optimize type.
     */
    public Optimize getOptimize();

    /**
     * Sets the optimize type.
     *
     * @param optimize the optimize type.
     */
    public void setOptimize(Optimize optimize);
}
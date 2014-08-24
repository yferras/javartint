package crow.jai.core;

import crow.jai.core.util.Optimize;

/**
 * This interface allows to create algorithms to optimize.
 * 
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface OptimizationAlgorithm extends Algorithm {

	public Optimize getOptimize();

	public void setOptimize(Optimize optimize);
}
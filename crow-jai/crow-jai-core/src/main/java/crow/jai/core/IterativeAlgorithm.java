package crow.jai.core;

/**
 * This interface allows to create iterative algorithms.
 * 
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface IterativeAlgorithm extends Algorithm {

	/**
	 * Gets the iterations that the algorithm perform.
	 * 
	 * @return the iterations that the algorithm perform.
	 */
	long getIterations();

}

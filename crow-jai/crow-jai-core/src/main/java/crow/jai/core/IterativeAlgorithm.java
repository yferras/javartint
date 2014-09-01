package crow.jai.core;

/**
 * This interface allows to create iterative algorithms.
 * 
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 * @param <S> Any class derived from {@link Solution} interface.
 */
public interface IterativeAlgorithm<S extends Solution> 
    extends Algorithm<S> {

	/**
	 * Gets the iterations that the algorithm perform.
	 * 
	 * @return the iterations that the algorithm perform.
	 */
	Long getIterations();

}

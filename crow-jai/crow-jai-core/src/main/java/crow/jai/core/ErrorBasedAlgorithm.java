package crow.jai.core;

/**
 * This interface allows to create algorithms based on the error calculus.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 * @param <S> Any class derived from {@link Solution} interface.
 */
public interface ErrorBasedAlgorithm<S extends Solution> 
    extends ThresholdBasedAlgorithm<S, Double> {

    /**
     * Gets the actual error.
     *
     * @return the actual error.
     */
    Double getCurrentError();
}

package crow.jai.core;

/**
 * This interface allows to create algorithms based on the error calculus.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface BasedOnErrorAlgorithm extends Algorithm {

    /**
     * Gets the actual error.
     *
     * @return the actual error.
     */
    double getCurrentError();
}

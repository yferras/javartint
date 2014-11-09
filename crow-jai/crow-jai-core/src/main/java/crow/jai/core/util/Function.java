package crow.jai.core.util;

/**
 * Interface to create generic functions.
 *
 * @param <R> Type of result
 * @param <P> Type of parameters
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface Function<R, P> {

    /**
     * Evaluates the params and returns a result.
     *
     * @param params parameters to evaluate.
     * @return the result.
     */
    R evaluate(P... params);

}

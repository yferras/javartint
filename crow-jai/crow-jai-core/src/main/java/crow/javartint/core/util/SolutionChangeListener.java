package crow.javartint.core.util;

import java.util.EventListener;

/**
 * Listener to know when algorithm has changed the solution.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public interface SolutionChangeListener extends EventListener {

    /**
     * This method should be fired when the algorithm updates the solution.
     *
     * @param event algorithm event.
     */
    void solutionUpdated(AlgorithmEvent event);
}

package crow.jai.core.util;

import java.util.EventListener;

/**
 * Listener to know when algorithm has finished.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public interface ExcecutionEndListener extends EventListener {

    /**
     * This method should be fired when the algorithm ends its execution.
     *
     * @param event algorithm event.
     */
    void algorithmFinished(AlgorithmEvent event);
}

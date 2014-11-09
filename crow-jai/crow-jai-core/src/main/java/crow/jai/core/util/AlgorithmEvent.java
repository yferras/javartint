package crow.jai.core.util;

import crow.jai.core.Algorithm;
import crow.jai.core.Solution;

import java.util.EventObject;

/**
 * Algorithm event object.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class AlgorithmEvent extends EventObject {

    private static final long serialVersionUID = -4552301165892831022L;
    private final Solution solution;

    /**
     * Constructor.
     *
     * @param source   The object on which the Event initially occurred.
     * @param solution The solution.
     */
    public AlgorithmEvent(Algorithm<?> source, Solution solution) {
        super(source);
        this.solution = solution;
    }

    /**
     * Gets the solution.
     *
     * @return The solution.
     */
    public Solution getSolution() {
        return solution;
    }

}

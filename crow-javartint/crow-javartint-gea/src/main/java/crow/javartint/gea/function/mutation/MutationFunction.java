package crow.javartint.gea.function.mutation;

import crow.javartint.core.util.function.ProbabilisticFunction;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * Interface that represents mutation function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface MutationFunction<T extends Genome<? extends Gene<?>>>
        extends ProbabilisticFunction<T, T> {
}
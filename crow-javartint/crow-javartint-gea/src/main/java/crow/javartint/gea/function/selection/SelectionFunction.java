package crow.javartint.gea.function.selection;

import crow.javartint.core.util.function.Function;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.List;

/**
 * Interface that represents selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface SelectionFunction<T extends Genome<? extends Gene<?>>>
        extends Function<List<T>, List<T>> {
}

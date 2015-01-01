package crow.javartint.gea.function.generator;

import crow.javartint.core.util.function.Function;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * This interface is used to create a functions to generate genomes.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public interface GeneratorFunction<T extends Genome<? extends Gene<?>>> extends Function<T, Void[]> {

    /**
     * This method must be implemented to generate dynamically instances
     * of {@link crow.javartint.gea.genome.Genome}
     *
     * @param params must be empty.
     * @return a generated instance of {@link crow.javartint.gea.genome.Genome}
     */
    @Override
    T evaluate(Void... params);
}

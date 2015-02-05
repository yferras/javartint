package crow.javartint.gea.function.decoder;

import crow.javartint.core.util.function.Function;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * This interface is used to create a functions to decode genomes.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public interface DecoderFunction<T extends Genome<? extends Gene<?>>> extends Function<Double, T> {
}

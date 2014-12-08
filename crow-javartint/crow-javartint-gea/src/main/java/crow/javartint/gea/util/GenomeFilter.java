package crow.javartint.gea.util;

import crow.javartint.core.util.Filter;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * This interface serves to implements many filters to ensure that the genomes
 * are valid.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 * @param <T>
 */
public interface GenomeFilter<T extends Genome<? extends Gene<?>>>
        extends Filter<T> {

    /**
     * Allows filter the genomes.
     *
     * @param genome genome to filter.
     * @return {@code true} if this genome is valid, otherwise returns 
     * {@code false}
     */
    @Override
    boolean accept(T genome);
}

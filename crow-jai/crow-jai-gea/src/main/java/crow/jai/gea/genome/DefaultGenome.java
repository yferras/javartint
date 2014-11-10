package crow.jai.gea.genome;

import crow.jai.gea.gene.Gene;

/**
 * This class represents a default genome.
 *
 * @param <T> Any derived class from {@link Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public class DefaultGenome<T extends Gene<?>> extends AbstractGenome<T> {

    /**
     * Default constructor.
     */
    public DefaultGenome() {
        super();
    }

}

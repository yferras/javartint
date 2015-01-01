package crow.javartint.gea.genome;

import crow.javartint.gea.gene.Gene;

/**
 * This class represents a default genome.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.gene.Gene} interface.
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

package crow.javartint.gea.gene;


/**
 * Default generic gene.
 *
 * @param <T> Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class DefaultGene<T> extends AbstractGene<T> {

    /**
     * Default constructor.
     *
     * @param data value to initialize this gene.
     */
    public DefaultGene(T data) {
        super(data);
    }

}

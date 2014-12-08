package crow.javartint.gea.gene;

/**
 * Default generic gene to represent arrays of numbers.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @param <T> Any derived class from {@link Number} abstract class.
 */
public class NumberArrayGene<T extends Number>
        extends ArrayGene<T> {

    public NumberArrayGene(T[] data) {
        super(data);
    }

}

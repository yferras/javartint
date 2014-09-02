package crow.jai.gea.gene;

/**
 * This interface represents a generic gene.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @param <T> Any class
 */
public interface Gene<T> extends Cloneable {

    /**
     * Gets the data.
     * @return the data
     */
    T getData();

    /**
     * Sets the data.
     * @param data data argument to set.
     */
    void setData(T data);

}
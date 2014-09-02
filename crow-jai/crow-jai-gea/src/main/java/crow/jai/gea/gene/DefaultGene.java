package crow.jai.gea.gene;

import java.util.Objects;

/**
 * Default generic gene.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @param <T> Any class
 */
public class DefaultGene<T> implements Gene<T> {

    protected T data;

    /**
     * Default constructor.
     *
     * @param data value to initialize this gene.
     */
    public DefaultGene(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    protected DefaultGene<T> clone() throws CloneNotSupportedException {
        DefaultGene<T> copy = (DefaultGene<T>) super.clone();
        return copy;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultGene<?> other = (DefaultGene<?>) obj;
        return Objects.equals(this.data, other.data);
    }

}

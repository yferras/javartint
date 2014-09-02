package crow.jai.gea.gene;

/**
 * Default generic gene.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @param <T> Any class
 */
public class DefaultGene<T> implements Cloneable, Gene<T> {

    protected T data;

    /**
     * Default constructor.
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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }
    
    
}

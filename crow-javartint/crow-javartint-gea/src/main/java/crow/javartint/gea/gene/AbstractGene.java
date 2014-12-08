package crow.javartint.gea.gene;

import java.io.*;
import java.util.Objects;

/**
 * Abstract implementation of {@link crow.javartint.gea.gene.Gene}.
 *
 * @param <T> Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class AbstractGene<T> implements Gene<T> {

    /**
     * The data.
     */
    protected T data;

    public AbstractGene(T data) {
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

    @SuppressWarnings("unchecked")
    @Override
    public Gene<T> clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream
                    (outputStream.toByteArray());
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(inputStream);
            Gene<T> cloned =
                    (Gene<T>) objectInputStream.readObject();
            objectInputStream.close();
            return cloned;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CloneNotSupportedException();
        }
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
        return Objects.equals(other.getData(), this.data);
    }
}

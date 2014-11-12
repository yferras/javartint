package crow.jai.gea.gene;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Default generic gene to represent arrays.
 *
 * @param <T> Any class
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class ArrayGene<T> extends AbstractGene<T[]> implements
        Cloneable, Iterable<T>, Iterator<T> {

    private int iteratorIndex = 0;

    public ArrayGene(T[] data) {
        super(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArrayGene<?> other = (ArrayGene<?>) obj;
        return Arrays.deepEquals(this.getData(), other.getData());
    }

    /**
     * Get the value of data at specified index.
     *
     * @param index specified index.
     * @return the value of data at specified index
     */
    public T getAllele(int index) {
        return this.getData()[index];
    }

    public int getLength() {
        return getData().length;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Arrays.deepHashCode(this.getData());
        return hash;
    }

    @Override
    public boolean hasNext() {
        return iteratorIndex < getData().length;
    }

    @Override
    public Iterator<T> iterator() {
        iteratorIndex = 0;
        return this;
    }

    @Override
    public T next() {
        int index = iteratorIndex;
        if (index >= getData().length) {
            throw new NoSuchElementException();
        }
        iteratorIndex = index + 1;
        return getData()[index];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("SIZE OF ARRAY IS FIXED");
    }

    /**
     * Set the value of data at specified index.
     *
     * @param index   index to set de value
     * @param newData new value of data at specified index
     */
    public void setAllele(int index, T newData) {
        this.data[index] = newData;
    }
}

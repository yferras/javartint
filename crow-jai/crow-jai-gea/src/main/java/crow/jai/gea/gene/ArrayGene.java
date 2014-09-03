package crow.jai.gea.gene;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Default generic gene to represent arrays.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 * @param <T> Any class
 */
public abstract class ArrayGene<T> extends DefaultGene<T[]> implements
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
        return Arrays.deepEquals(this.data, other.data);
    }

	@Override
    public ArrayGene<T> clone() throws CloneNotSupportedException {
        ArrayGene<T> gene = (ArrayGene<T>) super.clone();
        gene.data = data.clone();
        return gene;
    }

    /**
     * Get the value of data at specified index.
     *
     * @param index specified index.
     * @return the value of data at specified index
     */
    public T getAllele(int index) {
        return this.data[index];
    }

    public int getLength() {
        return data.length;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Arrays.deepHashCode(this.data);
        return hash;
    }

    @Override
    public boolean hasNext() {
        return iteratorIndex < data.length;
    }

    @Override
    public Iterator<T> iterator() {
        iteratorIndex = 0;
        return this;
    }

    @Override
    public T next() {
        int index = iteratorIndex;
        if (index >= data.length) {
            throw new NoSuchElementException();
        }
        iteratorIndex = index + 1;
        return data[index];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("SIZE OF ARRAY IS FIXED");
    }

    /**
     * Set the value of data at specified index.
     *
     * @param index index to set de value
     * @param newData new value of data at specified index
     */
    public void setAllele(int index, T newData) {
        this.data[index] = newData;
    }
}

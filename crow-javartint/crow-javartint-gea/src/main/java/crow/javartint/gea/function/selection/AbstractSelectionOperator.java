package crow.javartint.gea.function.selection;

import crow.javartint.core.util.function.Function;
import crow.javartint.gea.function.scaling.AbstractScalingMethod;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.List;

/**
 * Abstract class that represents selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractSelectionOperator<T extends Genome<? extends Gene<?>>>
        implements Function<List<T>, List<T>> {

    private int numToSelect = 1;

    private AbstractScalingMethod<T> scalingMethod = null;

    /**
     * Constructor than initializes this instance.
     *
     * @param numToSelect   number of elements to select
     * @param scalingMethod scaling method
     */
    public AbstractSelectionOperator(int numToSelect, AbstractScalingMethod<T> scalingMethod) {
        if (numToSelect < 1) {
            throw new IllegalArgumentException("'numToSelect' can't be less than 1.");
        }
        this.numToSelect = numToSelect;
        this.scalingMethod = scalingMethod;
    }

    /**
     * Constructor than initializes this instance.
     *
     * @param numToSelect number of elements to select
     */
    public AbstractSelectionOperator(int numToSelect) {
        this(numToSelect, null);
    }

    /**
     * Gets the number elements to select by this function
     *
     * @return the number elements to select
     */
    public int getNumToSelect() {
        return numToSelect;
    }

    /**
     * Gets the scaling method
     *
     * @return the instance of scaling method
     */
    public AbstractScalingMethod<T> getScalingMethod() {
        return scalingMethod;
    }

    /**
     * Ensures that parameter are valid.
     *
     * @param params parameters to validate.
     * @throws IllegalArgumentException If {@code params} is {@code null}.
     *                                  Or, the length is less than the number of elements to select
     */
    private void validate(List<T> params) {
        if (params == null) {
            throw new IllegalArgumentException("'params' can't be null.");
        }
        if (params.size() < getNumToSelect()) {
            throw new IllegalArgumentException("'params.length' is less than getNumToSelect()");
        }
    }

    /**
     * Selects from a list the desired number of genomes and retrieves then into
     * list.
     *
     * @param genomes Source to select the number of desired genomes.
     * @return a list with the selected genomes.
     */
    protected abstract List<T> select(List<T> genomes);

    /**
     * Performs the selection process after validating the input params ({@link #validate})
     * @param params parameters to evaluate.
     * @return an array with selected elements
     */
    @Override
    public List<T> evaluate(List<T> params) {
        validate(params);
        return select(params);
    }


}

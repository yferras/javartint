package com.github.yferras.javartint.gea.function.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod;
import com.github.yferras.javartint.gea.util.MathUtil;

/**
 * Stochastic universal sampling selection function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class SusSelectionFunction<T extends Individual>
    extends AbstractSelectionFunction<T> {

    /**
     * <p>Constructor for SusSelectionFunction.</p>
     *
     * @param numToSelect   a int.
     * @param scalingMethod a {@link com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod} object.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public SusSelectionFunction(int numToSelect, AbstractScalingMethod<T> scalingMethod)  {
        super(numToSelect, scalingMethod);
    }

    /**
     * <p>Constructor for SusSelectionFunction.</p>
     *
     * @param numToSelect a int.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public SusSelectionFunction(int numToSelect)  {
        this(numToSelect, null);
    }

    /**
     * <p>Constructor for SusSelectionFunction.</p>
     */
    public SusSelectionFunction() {
        super();
    }


    /** {@inheritDoc} */
    @Override
    protected List<T> select(List<T> individuals) {
        if (getScalingMethod() != null) {
            getScalingMethod().evaluate(individuals);
        }
        double total = MathUtil.total(individuals);
        double d = total / getNumToSelect();
        double r = new Random().nextDouble();
        double start = r * d;
        double[] pointers = new double[getNumToSelect()];
        for (int i = 0; i < getNumToSelect(); i++) {
            pointers[i] = start + i * d;
        }
        List<T> selected = new ArrayList<>();
        int i = 0;
        double sum = 0.0;
        for (double pointer : pointers) {
            while (sum < pointer) {
                sum += individuals.get(i++).getFitness();
            }
            selected.add(individuals.get(i - 1));
        }
        return selected;
    }
}

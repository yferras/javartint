package com.github.yferras.javartint.gea.function.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.yferras.javartint.gea.Individual;

/**
 * Random selection function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class RandomSelectionFunction<T extends Individual>
    extends AbstractSelectionFunction<T> {

    private Random random = new Random();

    /**
     * Constructor that initializes this instance.
     *
     * @param numToSelect number of elements to select
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public RandomSelectionFunction(int numToSelect)  {
        super(numToSelect);
    }

    /**
     * Constructor that initializes this instance.
     */
    public RandomSelectionFunction() {
        super();
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Selects genomes randomly.
     */
    @Override
    protected List<T> select(List<T> individuals) {
        List<T> list = new ArrayList<>(getNumToSelect());
        int n = individuals.size();
        while (list.size() < getNumToSelect()) {
            list.add(individuals.get(random.nextInt(n)));
        }
        return list;
    }
}

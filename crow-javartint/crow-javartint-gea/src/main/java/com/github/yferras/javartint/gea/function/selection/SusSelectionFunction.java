package com.github.yferras.javartint.gea.function.selection;

/*
 * #%L
 * Crow JavArtInt GEA
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.github.yferras.javartint.core.util.ValidationException;
import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod;
import com.github.yferras.javartint.gea.util.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public SusSelectionFunction(int numToSelect, AbstractScalingMethod<T> scalingMethod) throws ValidationException {
        super(numToSelect, scalingMethod);
    }

    /**
     * <p>Constructor for SusSelectionFunction.</p>
     *
     * @param numToSelect a int.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public SusSelectionFunction(int numToSelect) throws ValidationException {
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

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

import com.github.yferras.javartint.gea.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     */
    public RandomSelectionFunction(int numToSelect) {
        super(numToSelect);
    }

    /**
     * Constructor that initializes this instance.
     */
    public RandomSelectionFunction() {
        super(2);
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

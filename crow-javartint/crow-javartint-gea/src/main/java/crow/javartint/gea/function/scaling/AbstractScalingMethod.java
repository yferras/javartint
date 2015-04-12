package crow.javartint.gea.function.scaling;

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

import crow.javartint.core.util.Optimize;
import crow.javartint.core.util.function.Function;
import crow.javartint.gea.chromosome.Chromosome;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represents scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractScalingMethod<T extends Genome<? extends Chromosome<? extends Gene<?>>>>
        implements Function<List<T>, List<T>> {

    private final Optimize optimize;

    /**
     * Constructor that initializes this instance.
     *
     * @param optimize optimization way
     */
    public AbstractScalingMethod(Optimize optimize) {
        this.optimize = optimize;
    }

    /**
     * Get the value of optimize
     *
     * @return the value of optimize
     */
    public Optimize getOptimize() {
        return optimize;
    }

    /**
     * Method to perform scaling process
     *
     * @param genomes list to scale.
     */
    protected abstract void scale(List<T> genomes);

    /**
     * Ensures that parameters are valid.
     *
     * @param params parameters to validate.
     * @throws IllegalArgumentException if {@code params} is null
     *                                  or {@code params.isEmpty()} is {@code true}.
     */
    protected void validate(List<T> params) throws IllegalArgumentException {
        if (params == null) {
            throw new IllegalArgumentException("'params' can't be null.");
        }
        if (params.isEmpty()) {
            throw new IllegalArgumentException("'params' can't be empty.");
        }
    }

    /**
     * Validates the input parameters and performs the scaling process.
     *
     * @param params parameters to evaluate.
     * @return a list with scaled genomes
     * @throws java.lang.IllegalArgumentException see {@link #validate(java.util.List)}
     */
    @Override
    public List<T> evaluate(List<T> params) {
        validate(params);
        List<T> list = new ArrayList<>(params);
        scale(list);
        return list;
    }

}

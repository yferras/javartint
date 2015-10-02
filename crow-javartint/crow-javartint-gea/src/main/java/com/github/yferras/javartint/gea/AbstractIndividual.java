package com.github.yferras.javartint.gea;

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

/**
 * This abstract class implements partially the interface {@link com.github.yferras.javartint.gea.Individual}.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public abstract class AbstractIndividual implements Individual {

    private Double fitness = 0.0;

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getFitness() {
        return fitness;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Individual o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Individual clone() throws CloneNotSupportedException {
        return (Individual) super.clone();
    }
}

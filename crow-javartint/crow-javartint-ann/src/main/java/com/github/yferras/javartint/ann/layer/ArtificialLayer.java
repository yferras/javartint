package com.github.yferras.javartint.ann.layer;

/*
 * #%L
 * Crow JavArtInt ANN
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

import com.github.yferras.javartint.ann.neuron.ArtificialNeuron;

import java.io.Serializable;

/**
 * This interface represents a generic artificial layer.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public interface ArtificialLayer<T extends ArtificialNeuron>
    extends Iterable<T>, Serializable {

    /**
     * Gets the layer size.
     *
     * @return the an instance of {@code LayerSize}.
     */
    LayerSize size();

    /**
     * Gets the neurons count for the given row.
     *
     * @param row row.
     * @return the number of neurons.
     */
    int neuronsCount(int row);

    /**
     * Gets the total neurons count for layer.
     *
     * @return the total number of neurons.
     */
    int neuronsCount();

    /**
     * Retrieves an array with neurons for the given row.
     *
     * @param row row
     * @return an array with neurons.
     */
    T[] getNeurons(int row);

    /**
     * Retrieves a neuron for the given row and column.
     *
     * @param row row
     * @param column column
     * @return an array with neurons.
     */
    T getNeuron(int row, int column);

}

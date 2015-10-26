package com.github.yferras.javartint.ann.neuron;

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

import com.github.yferras.javartint.core.function.CompositeFunction;

import java.io.Serializable;

/**
 * This interface represents a generic artificial neuron.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public interface ArtificialNeuron
    extends CompositeFunction<Double, Double[]>, Iterable<Double>, Serializable {

    /**
     * Gets weights array.
     *
     * @return an array of weights.
     */
    Double[] getWeights();

    /**
     * Gets the weight in the given index.
     *
     * @param index index
     * @return the value of weight.
     */
    Double getWeight(int index);

    /**
     * Sets the weight in the given index.
     *
     * @param index index
     * @param weight the value weight
     */
    void setWeight(int index, Double weight);

    /**
     * Get the bias of the artificial neuron.
     *
     * @return the bias value.
     */
    double getBias();

    /**
     * Sets the value of bias.
     *
     * @param bias value of bias
     */
    void setBias(double bias);

    /**
     * Gets the size of the artificial neuron. The size of the neuron will be determined by the number of inputs.
     *
     * @return the size of the neuron.
     */
    int size();

    /**
     * Sets the number of inputs of artificial neuron.
     *
     * @param size the number of inputs.
     */
    void setNumberOfInputs(int size);

    /**
     * Gets the initialization status of neuron.
     *
     * @return the status of neuron.
     */
    NeuronInitializationStatus getStatus();
}

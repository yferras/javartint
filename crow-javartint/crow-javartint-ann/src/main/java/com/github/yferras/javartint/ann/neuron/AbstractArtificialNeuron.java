package com.github.yferras.javartint.ann.neuron;

import java.util.Iterator;

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

import com.github.yferras.javartint.ann.function.activation.ActivationFunction;
import com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction;
import com.github.yferras.javartint.core.util.AbstractItemIterator;

/**
 * Class that implements partially {@link com.github.yferras.javartint.ann.neuron.ArtificialNeuron} to create
 * artificial neurons.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractArtificialNeuron implements ArtificialNeuron {

	private static final long serialVersionUID = -7024158065216020664L;
	protected PropagationRuleFunction propagationRuleFunction;
    protected ActivationFunction activationFunction;
    private Double[] weights;
    private double bias = -1.0;

    /** {@inheritDoc} */
    @Override
    public Double[] getWeights() {
        return weights;
    }

    /** {@inheritDoc} */
    @Override
    public Double getWeight(int index) {
        return weights[index];
    }

    /** {@inheritDoc} */
    @Override
    public void setWeight(int index, Double weight) {
        weights[index] = weight;
    }

    /** {@inheritDoc} */
    @Override
    public double getBias() {
        return bias;
    }

    /** {@inheritDoc} */
    @Override
    public void setBias(double bias) {
        this.bias = bias;
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return weights.length;
    }

    /** {@inheritDoc} */
    @Override
    public void setNumberOfInputs(int size) {
        weights = new Double[size];
    }

    /** {@inheritDoc} */
    @Override
    public NeuronInitializationStatus getStatus() {
        int countNull = 0;
        for (Double weight : weights) {
            if (weight == null){
                countNull++;
            }
        }
        if (countNull == size()){
            return NeuronInitializationStatus.PRISTINE;
        }
        if (countNull == 0) {
            return NeuronInitializationStatus.FULL;
        }
        return NeuronInitializationStatus.PARTIALL;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<Double> iterator() {
        return new AbstractItemIterator<Double>() {

            @Override
            public Double getItem(int index) {
                return weights[index];
            }

            @Override
            public int itemsCount() {
                return weights.length;
            }
        };
    }


}

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

import com.github.yferras.javartint.ann.function.activation.ActivationFunction;
import com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction;
import com.github.yferras.javartint.core.function.DefaultCompositeFunction;
import com.github.yferras.javartint.core.util.AbstractItemIterator;

import java.util.Iterator;

/**
 * Class that implements partially {@link com.github.yferras.javartint.ann.neuron.ArtificialNeuron} to create
 * artificial neurons.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractArtificialNeuron
	extends DefaultCompositeFunction<Double, Double[]>
	implements ArtificialNeuron {

	private Double[] weights;
	private double bias;
	protected PropagationRuleFunction propagationRuleFunction;
	protected ActivationFunction activationFunction;

	/**
	 * Constructor for AbstractArtificialNeuron.
	 *
	 * @param size the itemsCount of the neuron (number of weights).
	 * @param propagationRuleFunction a {@link com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction} object.
	 * @param activationFunction a {@link com.github.yferras.javartint.ann.function.activation.ActivationFunction} object.
	 */
	public AbstractArtificialNeuron(int size, PropagationRuleFunction propagationRuleFunction,
	                                ActivationFunction activationFunction) {
		weights = new Double[size];
		bias = -1.0;
		this.propagationRuleFunction = propagationRuleFunction;
		this.activationFunction = activationFunction;
		this.setFunctions(propagationRuleFunction, activationFunction);
	}

	/** {@inheritDoc} */
	@Override
	public Double[] getWeights() {
		return weights;
	}

	/** {@inheritDoc} */
	@Override
	public double getBias() {
		return bias;
	}

	/** {@inheritDoc} */
	@Override
	public int size() {
		return weights.length;
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

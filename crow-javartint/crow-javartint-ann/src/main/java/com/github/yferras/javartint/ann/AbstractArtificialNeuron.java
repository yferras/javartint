package com.github.yferras.javartint.ann;

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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>AbstractArtificialNeuron class.</p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class AbstractArtificialNeuron implements ArtificialNeuron {

	protected Double[] weights;
	protected double bias;
	protected PropagationRuleFunction propagationRuleFunction;
	protected ActivationFunction activationFunction;

	/**
	 * <p>Constructor for AbstractArtificialNeuron.</p>
	 *
	 * @param size a int.
	 * @param propagationRuleFunction a {@link com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction} object.
	 * @param activationFunction a {@link com.github.yferras.javartint.ann.function.activation.ActivationFunction} object.
	 */
	public AbstractArtificialNeuron(int size, PropagationRuleFunction propagationRuleFunction,
	                                ActivationFunction activationFunction) {
		weights = new Double[size];
		this.propagationRuleFunction = propagationRuleFunction;
		this.activationFunction = activationFunction;
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
		return getWeights().length;
	}

	/** {@inheritDoc} */
	@Override
	public Double evaluate(Double[] params) {
		final Double r = propagationRuleFunction.evaluate(new Double[][]{params, weights});
		return activationFunction.evaluate(r);
	}

	/** {@inheritDoc} */
	@Override
	public Iterator<Double> iterator() {
		return new ArtificialNeuronIterator();
	}

	private class ArtificialNeuronIterator implements Iterator<Double> {

		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < size();
		}

		@Override
		public Double next() {
			try {
				int i = cursor;
				Double next = weights[i];
				cursor = i + 1;
				return next;
			} catch (IndexOutOfBoundsException e) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("SIZE OF ARRAY IS FIXED");
		}
	}
}

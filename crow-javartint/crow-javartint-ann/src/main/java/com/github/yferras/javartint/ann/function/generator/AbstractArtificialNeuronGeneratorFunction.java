package com.github.yferras.javartint.ann.function.generator;

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
import com.github.yferras.javartint.ann.neuron.ArtificialNeuron;

/**
 * <p>Abstract AbstractArtificialNeuronGeneratorFunction class.</p>
 *
 * @param <T> Any derived type from {@link com.github.yferras.javartint.ann.neuron.ArtificialNeuron}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version $Id: $Id
 */
public abstract class AbstractArtificialNeuronGeneratorFunction<T extends ArtificialNeuron>
    implements ArtificialNeuronGeneratorFunction<T> {

    private final int size;
    private final Class<T> clazz;
    private final ActivationFunction activationFunction;
    private final PropagationRuleFunction propagationRuleFunction;

    /**
     * <p>Constructor for AbstractArtificialNeuronGeneratorFunction.</p>
     *
     * @param size a size of artificial neuron.
     * @param clazz a {@link java.lang.Class} object.
     * @param activationFunction a {@link com.github.yferras.javartint.ann.function.activation.ActivationFunction} object.
     * @param propagationRuleFunction a {@link com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction} object.
     */
    public AbstractArtificialNeuronGeneratorFunction(int size, Class<T> clazz,
                                                     ActivationFunction activationFunction,
                                                     PropagationRuleFunction propagationRuleFunction) {
        this.size = size;
        this.clazz = clazz;
        this.activationFunction = activationFunction;
        this.propagationRuleFunction = propagationRuleFunction;
    }

    /** {@inheritDoc} */
    @SafeVarargs
    @Override
    public final T evaluate(Void... params) {
        return generate(size, activationFunction, propagationRuleFunction);
    }

    /** {@inheritDoc} */
    @Override
    public Class<T> getItemGeneratedClass() {
        return clazz;
    }

    /**
     * <p>Generates artificial neurons.</p>
     *
     * @param size the size of neuron..
     * @return a instance of artificial neuron.
     * @param activationFunction a {@link com.github.yferras.javartint.ann.function.activation.ActivationFunction} object.
     * @param propagationRuleFunction a {@link com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction} object.
     */
    protected abstract T generate(int size, ActivationFunction activationFunction,
                                  PropagationRuleFunction propagationRuleFunction);
}

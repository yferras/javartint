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
import com.github.yferras.javartint.core.util.AlgorithmRuntimeException;

/**
 * <p>RandomArtificialNeuronGeneratorFunction class.</p>
 * 
 * @param <T> Any class derived from {@link ArtificialNeuron}
 * @author yferras
 * @version $Id: $Id
 */
public class RandomArtificialNeuronGeneratorFunction<T extends ArtificialNeuron> extends AbstractArtificialNeuronGeneratorFunction<T> {


    /**
     * <p>Constructor for RandomArtificialNeuronGeneratorFunction.</p>
     *
     * @param size a int.
     * @param clazz a {@link java.lang.Class} object.
     * @param activationFunction a {@link com.github.yferras.javartint.ann.function.activation.ActivationFunction} object.
     * @param propagationRuleFunction a {@link com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction} object.
     */
    public RandomArtificialNeuronGeneratorFunction(int size, Class<T> clazz,
                                                   ActivationFunction activationFunction,
                                                   PropagationRuleFunction propagationRuleFunction) {
        super(size, clazz, activationFunction, propagationRuleFunction);
    }

    /** {@inheritDoc} */
    @Override
    protected T generate(int size, ActivationFunction activationFunction, PropagationRuleFunction propagationRuleFunction) {
        try {
            T neuron = getItemGeneratedClass().newInstance();
            neuron.setBias(-1.0);
            neuron.setNumberOfInputs(size);
            for (int i = 0; i < size; i++) {
                neuron.setWeight(i, Math.random());
            }
            neuron.setFunctions(activationFunction, propagationRuleFunction);
            return neuron;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new AlgorithmRuntimeException(e); 
        }
        return null;
    }


}

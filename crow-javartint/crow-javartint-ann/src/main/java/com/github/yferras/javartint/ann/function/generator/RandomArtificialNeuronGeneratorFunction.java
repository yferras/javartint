package com.github.yferras.javartint.ann.function.generator;

import com.github.yferras.javartint.ann.function.activation.ActivationFunction;
import com.github.yferras.javartint.ann.function.propagationrule.PropagationRuleFunction;
import com.github.yferras.javartint.ann.neuron.ArtificialNeuron;

public class RandomArtificialNeuronGeneratorFunction<T extends ArtificialNeuron> extends AbstractArtificialNeuronGeneratorFunction<T> {


    public RandomArtificialNeuronGeneratorFunction(int size, Class<T> clazz,
                                                   ActivationFunction activationFunction,
                                                   PropagationRuleFunction propagationRuleFunction) {
        super(size, clazz, activationFunction, propagationRuleFunction);
    }

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
            e.printStackTrace();
        }
        return null;
    }


}

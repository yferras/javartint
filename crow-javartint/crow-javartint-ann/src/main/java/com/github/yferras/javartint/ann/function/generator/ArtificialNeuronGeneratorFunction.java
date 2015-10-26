package com.github.yferras.javartint.ann.function.generator;

import com.github.yferras.javartint.ann.neuron.ArtificialNeuron;
import com.github.yferras.javartint.core.function.GeneratorFunction;


public interface ArtificialNeuronGeneratorFunction<T extends ArtificialNeuron> extends GeneratorFunction<T> {

    Class<T> getItemGeneratedClass();
}

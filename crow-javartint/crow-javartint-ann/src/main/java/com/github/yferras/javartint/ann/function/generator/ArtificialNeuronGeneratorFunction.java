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

import com.github.yferras.javartint.ann.neuron.ArtificialNeuron;
import com.github.yferras.javartint.core.function.GeneratorFunction;


/**
 * <p>ArtificialNeuronGeneratorFunction interface.</p>
 *
 * @author yferras
 * @version $Id: $Id
 */
public interface ArtificialNeuronGeneratorFunction<T extends ArtificialNeuron> extends GeneratorFunction<T> {

    /**
     * <p>getItemGeneratedClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    Class<T> getItemGeneratedClass();
}
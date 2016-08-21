package com.github.yferras.javartint.ann.network;

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

import com.github.yferras.javartint.ann.layer.ArtificialLayer;
import com.github.yferras.javartint.ann.neuron.ArtificialNeuron;
import com.github.yferras.javartint.core.function.DefaultCompositeFunction;
import com.github.yferras.javartint.core.util.AbstractItemIterator;

import java.util.Iterator;

/**
 * <p>AbstractArtificialNetwork class.</p>
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class AbstractArtificialNetwork<T extends ArtificialLayer<? extends ArtificialNeuron>>
    extends DefaultCompositeFunction<Double[], Double[]> implements ArtificialNetwork<T> {

    private T[] layers;

    /**
     * <p>Constructor for AbstractArtificialNetwork.</p>
     *
     * @param layers an array of T objects.
     */
    public AbstractArtificialNetwork(T[] layers) {
    	this.layers = layers;
        super.setFunctions(layers);
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return layers.length;
    }

    /** {@inheritDoc} */
    @Override
    public T[] getLayers() {
        return layers;
    }

    /** {@inheritDoc} */
    @Override
    public T getLayer(int index) {
        return layers[index];
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> iterator() {
        return new AbstractItemIterator<T>() {
            @Override
            public T getItem(int index) {
                return getLayer(index);
            }

            @Override
            public int itemsCount() {
                return size();
            }
        };
    }
}

//package com.github.yferras.javartint.ann.layer;
//
///*
// * #%L
// * Crow JavArtInt ANN
// * %%
// * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
// * %%
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as
// * published by the Free Software Foundation, either version 3 of the
// * License, or (at your option) any later version.
// * 
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// * 
// * You should have received a copy of the GNU General Public
// * License along with this program.  If not, see
// * <http://www.gnu.org/licenses/gpl-3.0.html>.
// * #L%
// */
//
//import com.github.yferras.javartint.ann.function.activation.LineActivationFunction;
//import com.github.yferras.javartint.ann.function.generator.RandomArtificialNeuronGeneratorFunction;
//import com.github.yferras.javartint.ann.function.propagationrule.BalancedSumPropagationRuleFunction;
//import com.github.yferras.javartint.ann.neuron.DefaultArtificialNeuron;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class DefaultArtificialLayerTest {
//
//    private DefaultArtificialLayer<DefaultArtificialNeuron> getArtificialLayer() throws InstantiationException, IllegalAccessException {
//        return new DefaultArtificialLayer<>(20,
//            new RandomArtificialNeuronGeneratorFunction<>(
//                10, DefaultArtificialNeuron.class,
//                new LineActivationFunction(),
//                new BalancedSumPropagationRuleFunction()));
//    }
//
//    @Test
//    public void testConstructor() throws Exception {
//        try {
//            final DefaultArtificialLayer<DefaultArtificialNeuron> layer = getArtificialLayer();
//            assertNotNull(layer);
//        } catch (Exception e) {
//            fail();
//        }
//    }
//
//    @Test
//    public void testSize() throws Exception {
//        final DefaultArtificialLayer<DefaultArtificialNeuron> layer = getArtificialLayer();
//        assertNotNull(layer.size());
//        assertEquals(new LayerSize(20, 1), layer.size());
//    }
//
//    @Test
//    public void testNeuronsCount() throws Exception {
//        final DefaultArtificialLayer<DefaultArtificialNeuron> layer = getArtificialLayer();
//        assertEquals(20, layer.size().count());
//    }
//
//    @Test
//    public void testGetNeurons() throws Exception {
//
//    }
//
//    @Test
//    public void testGetNeuron() throws Exception {
//
//    }
//
//    @Test
//    public void testIterator() throws Exception {
//
//    }
//}
//package com.github.yferras.javartint.ann.neuron;
//
///*
// * #%L
// * Crow JavArtInt ANN
// * %%
// * Copyright (C) 2014 - 2016 Eng. Ferrás Cecilio, Yeinier
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
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class DefaultArtificialNeuronTest {
//
//    private DefaultArtificialNeuron getDefaultArtificialNeuron() {
//        final DefaultArtificialNeuron neuron = new DefaultArtificialNeuron();
//        neuron.setNumberOfInputs(10);
//        return neuron;
//    }
//
//    @Test
//    public void testGetWeights() throws Exception {
//        DefaultArtificialNeuron neuron = getDefaultArtificialNeuron();
//        assertTrue(neuron.getWeights() != null);
//    }
//
//    @Test
//    public void testGetBias() throws Exception {
//        DefaultArtificialNeuron neuron = getDefaultArtificialNeuron();
//        assertEquals(-1.0, neuron.getBias(), 0.0);
//    }
//
//    @Test
//    public void testSize() throws Exception {
//        DefaultArtificialNeuron neuron = getDefaultArtificialNeuron();
//        assertEquals(10, neuron.size());
//    }
//
//    @Test
//    public void testIterator() throws Exception {
//        DefaultArtificialNeuron neuron = getDefaultArtificialNeuron();
//        int count = 0;
//        for (Double value : neuron) {
//            count++;
//        }
//        assertEquals(neuron.size(), count);
//    }
//
//
//    @Test
//    public void testEvaluate() throws Exception {
//
//    }
//
//    @Test
//    public void testGetWeight() throws Exception {
//        DefaultArtificialNeuron neuron = getDefaultArtificialNeuron();
//        for (int i = 0; i < neuron.size(); i++) {
//            assertNull(neuron.getWeight(i));
//        }
//    }
//
//    @Test
//    public void testSetWeight() throws Exception {
//        DefaultArtificialNeuron neuron = getDefaultArtificialNeuron();
//        for (int i = 0; i < neuron.size(); i++) {
//            neuron.setWeight(i, (double)i);
//            assertNotNull(neuron.getWeight(i));
//            assertEquals((double) i, neuron.getWeight(i), 0.0);
//        }
//    }
//
//    @Test
//    public void testGetStatus() throws Exception {
//        DefaultArtificialNeuron neuron = getDefaultArtificialNeuron();
//        assertEquals(NeuronInitializationStatus.PRISTINE, neuron.getStatus());
//        neuron.setWeight(0, .1);
//        assertEquals(NeuronInitializationStatus.PARTIALL, neuron.getStatus());
//        for (int i = 0; i < neuron.size(); i++) {
//            neuron.setWeight(i, (double)i);
//        }
//        assertEquals(NeuronInitializationStatus.FULL, neuron.getStatus());
//    }
//}
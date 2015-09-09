package com.github.yferras.javartint.gea.function.scaling;

/*
* #%L
 * * Crow JavArtInt GEA
 * *
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * *
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

import com.github.yferras.javartint.gea.genome.DefaultGenome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BoltzmannScalingMethodTest {

	@Before
	public void setUp() throws Exception {
		System.out.print(BoltzmannScalingMethod.class.getName().concat("."));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvaluate() throws Exception {
		System.out.println("evaluate (performed algorithm)");
		int numCities = 10;
		double boltzmannTemperature = 2.0 * numCities;
		final double deltaTemp = 0.05;
		BoltzmannScalingMethod<DefaultGenome> scalingMethod = new BoltzmannScalingMethod<>(numCities);
		List<DefaultGenome> genomes = new ArrayList<>(100);
		double[] exp = new double[100];
		Random random = new Random();
		double sum = 0.0;
		for (int i = 0; i < 100; i++) {
			genomes.add(new DefaultGenome<>());
			double f = random.nextInt(100);
			genomes.get(i).setFitness(exp[i] = f);
			sum += f;
		}
		double mean = sum / 100.0;
		boltzmannTemperature -= deltaTemp;
		final double divider = mean / boltzmannTemperature;
		for (int i = 0; i < 100; i++) {
			exp[i] = exp[i] / divider;
		}

		List<DefaultGenome> evaluate = scalingMethod.evaluate(genomes);
		for (int i = 0; i < evaluate.size(); i++) {
			assertEquals(exp[i], evaluate.get(i).getFitness(), 0.0);
		}
	}
}

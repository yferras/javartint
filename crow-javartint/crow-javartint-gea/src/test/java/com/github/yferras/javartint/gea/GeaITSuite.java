package com.github.yferras.javartint.gea;

/*
 * #%L
 * Crow JavArtInt ANN
 * %%
 * Copyright (C) 2014 - 2016 Eng. Ferrás Cecilio, Yeinier
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * #%L
 * Crow JavArtInt GEA
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

import com.github.yferras.javartint.gea.function.generator.BinaryGenomeGenFunctionTest;
import com.github.yferras.javartint.gea.function.generator.RangeGenomeGenFunctionTest;
import com.github.yferras.javartint.gea.function.generator.TspGenomeGenFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.MutationFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.binary.BinaryMutationFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.numeric.RealValuedMutationFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.tsp.DisplacedInversionMutationFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.tsp.DisplacementMutationFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.tsp.ExchangeMutationFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.tsp.InversionMutationFunctionTest;
import com.github.yferras.javartint.gea.function.mutation.tsp.ScrambleMutationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.DiscreteRecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.MultiPointsRecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.RecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.SinglePointRecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.TowPointsRecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.tsp.OrderBasedRecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.tsp.PermutationRecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.recombination.tsp.PositionBasedRecombinationFunctionTest;
import com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethodTest;
import com.github.yferras.javartint.gea.function.scaling.BoltzmannScalingMethodTest;
import com.github.yferras.javartint.gea.function.scaling.LinearRankScalingMethodTest;
import com.github.yferras.javartint.gea.function.scaling.RankScalingMethodTest;
import com.github.yferras.javartint.gea.function.scaling.SigmaScalingMethodTest;
import com.github.yferras.javartint.gea.gene.ArrayGeneTest;
import com.github.yferras.javartint.gea.gene.DefaultGeneTest;
import com.github.yferras.javartint.gea.genome.DefaultGenomeTest;
import com.github.yferras.javartint.gea.util.GenomeFilterTest;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ DefaultGeneTest.class, GenomeFilterTest.class, ArrayGeneTest.class, DefaultGenomeTest.class,
		RecombinationFunctionTest.class, MultiPointsRecombinationFunctionTest.class,
		SinglePointRecombinationFunctionTest.class, TowPointsRecombinationFunctionTest.class,
		MutationFunctionTest.class, BinaryMutationFunctionTest.class, AbstractScalingMethodTest.class,
		LinearRankScalingMethodTest.class, RankScalingMethodTest.class, SigmaScalingMethodTest.class,
		BoltzmannScalingMethodTest.class, BinaryGenomeGenFunctionTest.class, TspGenomeGenFunctionTest.class,
		RangeGenomeGenFunctionTest.class, AbstractGeneticAlgorithmIT.class, RealValuedMutationFunctionTest.class,
		DiscreteRecombinationFunctionTest.class, OrderBasedRecombinationFunctionTest.class,
		ScrambleMutationFunctionTest.class, DisplacementMutationFunctionTest.class, InversionMutationFunctionTest.class,
		DisplacedInversionMutationFunctionTest.class, ExchangeMutationFunctionTest.class,
		PermutationRecombinationFunctionTest.class, PositionBasedRecombinationFunctionTest.class, })
public class GeaITSuite {

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
}

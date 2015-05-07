package crow.javartint.gea;

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

import crow.javartint.gea.function.generator.BinaryGenomeGenFunctionTest;
import crow.javartint.gea.function.generator.RangeGenomeGenFunctionTest;
import crow.javartint.gea.function.generator.TspGenomeGenFunctionTest;
import crow.javartint.gea.function.mutation.MutationFunctionTest;
import crow.javartint.gea.function.mutation.binary.BinaryMutationFunctionTest;
import crow.javartint.gea.function.mutation.numeric.RealValuedMutationFunctionTest;
import crow.javartint.gea.function.mutation.tsp.*;
import crow.javartint.gea.function.recombination.*;
import crow.javartint.gea.function.recombination.tsp.OrderBasedRecombinationFunctionTest;
import crow.javartint.gea.function.scaling.*;
import crow.javartint.gea.gene.ArrayGeneTest;
import crow.javartint.gea.gene.DefaultGeneTest;
import crow.javartint.gea.genome.DefaultGenomeTest;
import crow.javartint.gea.util.GenomeFilterTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
	{
		DefaultGeneTest.class,
		GenomeFilterTest.class,
		ArrayGeneTest.class,
		DefaultGenomeTest.class,
		RecombinationFunctionTest.class,
		MultiPointsRecombinationFunctionTest.class,
		SinglePointRecombinationFunctionTest.class,
		TowPointsRecombinationFunctionTest.class,
		MutationFunctionTest.class,
		BinaryMutationFunctionTest.class,
		AbstractScalingMethodTest.class,
		LinearRankScalingMethodTest.class,
		RankScalingMethodTest.class,
		SigmaScalingMethodTest.class,
		BoltzmannScalingMethodTest.class,
		BinaryGenomeGenFunctionTest.class,
		TspGenomeGenFunctionTest.class,
		RangeGenomeGenFunctionTest.class,
		AbstractGeneticAlgorithmIT.class,
		RealValuedMutationFunctionTest.class,
		DiscreteRecombinationFunctionTest.class,
		OrderBasedRecombinationFunctionTest.class,
		ScrambleMutationFunctionTest.class,
		DisplacementMutationFunctionTest.class,
		InversionMutationFunctionTest.class,
		DisplacedInversionMutationFunctionTest.class,
		ExchangeMutationFunctionTest.class,
	}
)
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

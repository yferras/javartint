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

import crow.javartint.gea.function.crossover.CrossoverFunctionIT;
import crow.javartint.gea.function.crossover.MultiPointsCrossoverFunctionIT;
import crow.javartint.gea.function.crossover.SinglePointCrossoverFunctionIT;
import crow.javartint.gea.function.crossover.TowPointsCrossoverFunctionIT;
import crow.javartint.gea.function.generator.BinaryGenomeGenFunctionTest;
import crow.javartint.gea.function.generator.RangeGenomeGenFunctionTest;
import crow.javartint.gea.function.generator.TspGenomeGenFunctionTest;
import crow.javartint.gea.function.mutattion.BinaryMutationFunctionIT;
import crow.javartint.gea.function.mutattion.MutationFunctionIT;
import crow.javartint.gea.function.scaling.*;
import crow.javartint.gea.gene.ArrayGeneIT;
import crow.javartint.gea.gene.DefaultGeneIT;
import crow.javartint.gea.genome.DefaultGenomeIT;
import crow.javartint.gea.util.GenomeFilterIT;
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
                DefaultGeneIT.class,
                GenomeFilterIT.class,
                ArrayGeneIT.class,
                DefaultGenomeIT.class,
                CrossoverFunctionIT.class,
                MultiPointsCrossoverFunctionIT.class,
                SinglePointCrossoverFunctionIT.class,
                TowPointsCrossoverFunctionIT.class,
                MutationFunctionIT.class,
                BinaryMutationFunctionIT.class,
                AbstractScalingMethodTest.class,
                LinearRankScalingMethodTest.class,
                RankScalingMethodTest.class,
                SigmaScalingMethodTest.class,
                BoltzmannScalingMethodTest.class,
                BinaryGenomeGenFunctionTest.class,
                TspGenomeGenFunctionTest.class,
                RangeGenomeGenFunctionTest.class,
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

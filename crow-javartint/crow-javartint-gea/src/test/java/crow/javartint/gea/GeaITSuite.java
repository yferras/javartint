package crow.javartint.gea;

import crow.javartint.gea.function.crossover.*;
import crow.javartint.gea.function.mutattion.BinaryMutationFunctionIT;
import crow.javartint.gea.function.mutattion.MutationFunctionIT;
import crow.javartint.gea.function.scaling.AbstractScalingMethodTest;
import crow.javartint.gea.function.scaling.LinearRankScalingMethodTest;
import crow.javartint.gea.function.scaling.RankScalingMethodTest;
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

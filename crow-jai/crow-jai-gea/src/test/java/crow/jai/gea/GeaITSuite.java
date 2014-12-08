package crow.jai.gea;

import crow.jai.gea.function.crossover.*;
import crow.jai.gea.function.mutattion.BinaryMutationFunctionIT;
import crow.jai.gea.function.mutattion.MutationFunctionIT;
import crow.jai.gea.gene.ArrayGeneIT;
import crow.jai.gea.gene.DefaultGeneIT;
import crow.jai.gea.genome.DefaultGenomeIT;
import crow.jai.gea.util.GenomeFilterIT;
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

package crow.jai.gea;

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
                crow.jai.gea.gene.DefaultGeneIT.class,
                crow.jai.gea.gene.ArrayGeneIT.class,
                crow.jai.gea.genome.DefaultGenomeIT.class,
                crow.jai.gea.function.crossing.CrossingFunctionIT.class,
                crow.jai.gea.function.crossing.SinglePointCrossingFunctionIT
                        .class,
                crow.jai.gea.function.crossing.TowPointsCrossingFunctionIT.class

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

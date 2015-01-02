package crow.javartint.core;

import crow.javartint.core.util.RangeTest;
import crow.javartint.core.util.function.ProbabilisticFunctionIT;
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
                AbstractAlgorithmIT.class,
                ProbabilisticFunctionIT.class,
                RangeTest.class,
        }
)
public class CoreITSuite {

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

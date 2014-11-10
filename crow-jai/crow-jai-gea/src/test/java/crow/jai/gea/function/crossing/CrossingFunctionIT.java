package crow.jai.gea.function.crossing;

import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.Genome;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class CrossingFunctionIT {

    public CrossingFunctionIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetCrossingProbability() {
        System.out.println("getCrossingProbability");
        final DefaultCrossingFunction function = new DefaultCrossingFunction();
        final Object result = function.getCrossingProbability();
        assertEquals(0.5, result);
    }

    private static class DefaultCrossingFunction
            extends AbstractCrossingFunction<Genome<? extends Gene<?>>> {

        private DefaultCrossingFunction() {
            super(0.5);
        }


        @Override
        public Genome<? extends Gene<?>>[] evaluate(Genome... params) {
            return null;
        }

    }

}

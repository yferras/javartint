package crow.javartint.core.util.function;

import crow.javartint.core.util.RandomGenerator;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class ProbabilisticFunctionIT {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(AbstractProbabilisticFunction.class.getName().concat
                ("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor1() {
        System.out.println("constructor " +
                "(test probability less than 0.0)");
        try {
            new DefaultProbabilisticFunction<Double>(-.5);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void testConstructor2() {
        System.out.println("constructor " +
                "(test probability greater than 1.0)");
        try {
            new DefaultProbabilisticFunction<Double>(2.0);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void testConstructor3() {
        System.out.println("constructor " +
                "(test probability between 0.0 and 1.0)");
        try {
            new DefaultProbabilisticFunction<Double>(0.5);
        } catch (IllegalArgumentException e) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    public void testConstructor4() {
        System.out.println("constructor " +
                "(test probability = 0.0)");
        try {
            new DefaultProbabilisticFunction<Double>(0.0);
        } catch (IllegalArgumentException e) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    public void testConstructor5() {
        System.out.println("constructor " +
                "(test probability = 1.0)");
        try {
            new DefaultProbabilisticFunction<Double>(1.0);
        } catch (IllegalArgumentException e) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    public void testConstructor6() {
        System.out.println("constructor " +
                "(test probability = 0.5 and randomGenerator is null)");
        try {
            new DefaultProbabilisticFunction<Double>(1.0, null);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        final DefaultProbabilisticFunction<Double> function =
                new DefaultProbabilisticFunction<>(0.5);
        assertEquals(new Double(0.5), new Double(function.getProbability()));
    }

    @Test
    public void testGetRandomGenerator1() {
        System.out.println("getRandomGenerator (not null)");
        final DefaultProbabilisticFunction<Double> function =
                new DefaultProbabilisticFunction<>(0.5);
        assertNotNull(function.getRandomGenerator());
    }

    @Test
    public void testGetRandomGenerator2() {
        System.out.println("getRandomGenerator (instance of " +
                "SystemDefaultRandomGenerator)");
        final DefaultProbabilisticFunction<Double> function =
                new DefaultProbabilisticFunction<>(0.5);
        assertTrue(function.getRandomGenerator()
                instanceof RandomGenerator.SystemDefaultRandomGenerator);
    }

    private static class DefaultProbabilisticFunction<T extends Number>
            extends AbstractProbabilisticFunction<Double, T> {

        private DefaultProbabilisticFunction(double probability,
                                             RandomGenerator randomGenerator) {
            super(probability, randomGenerator);
        }

        private DefaultProbabilisticFunction(double probability) {
            super(probability);
        }

        @Override
        public Double evaluate(T... params) {
            if (getRandomGenerator().nextDouble() > getProbability()) {
                return params[0].doubleValue() + params[1].doubleValue();
            }
            return params[0].doubleValue() - params[1].doubleValue();
        }
    }
}

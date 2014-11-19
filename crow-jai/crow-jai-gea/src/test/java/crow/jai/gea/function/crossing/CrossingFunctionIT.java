package crow.jai.gea.function.crossing;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.GenomeConstants;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.gene.IntegerArrayGene;
import crow.jai.gea.genome.DefaultGenome;
import crow.jai.gea.genome.Genome;
import org.junit.*;

import static org.junit.Assert.*;

/**
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
        System.out.print(AbstractCrossingFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        final DefaultCrossingFunction function = new DefaultCrossingFunction();
        function.setProbability(.5);
        assertEquals(new Double(.5),
                new Double(function.getProbability()));
    }

    @Test
    public void testSetProbability1() {
        System.out.println("setProbability (invalid argument)");
        final DefaultCrossingFunction function = new DefaultCrossingFunction();
        try {
            function.setProbability(-.5);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("IllegalArgumentException, not raised.");
    }

    @Test
    public void testSetProbability2() {
        System.out.println("setProbability (valid argument)");
        final DefaultCrossingFunction function = new DefaultCrossingFunction();
        function.setProbability(.1);
        final Object result = function.getProbability();
        assertEquals(0.1, result);
    }

    @Test
    public void testDefaultCrossingProbability() {
        System.out.println("DefaultCrossingFunction");
        final DefaultCrossingFunction function = new DefaultCrossingFunction();
        final Object result = function.getProbability();
        assertEquals(0.75, result);
    }

    @Test
    public void testValidate1() {
        System.out.println("validate (params is null)");
        try {
            final DefaultCrossingFunction function = new
                    DefaultCrossingFunction();
            function.evaluate(null);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testValidate2() {
        System.out.println("validate (params length is less than two)");
        try {
            final DefaultCrossingFunction function = new
                    DefaultCrossingFunction();
            function.evaluate(new DefaultGenome());
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testEvaluate1() {
        System.out.println("evaluate (to invoke crossingProcess)");
        final DefaultCrossingFunction function = new
                DefaultCrossingFunction();
        DefaultGenome<IntegerArrayGene> genome1 =
                new DefaultGenome<>();
        DefaultGenome<IntegerArrayGene> genome2 =
                new DefaultGenome<>();
        genome1.setChromosome(new IntegerArrayGene[]{
                new IntegerArrayGene(new Integer[]{
                        1, 2, 3
                })
        });
        genome2.setChromosome(new IntegerArrayGene[]{
                new IntegerArrayGene(new Integer[]{
                        4, 5, 6
                })
        });
        Genome<? extends Gene<?>>[] result = function.evaluate(
                genome1,
                genome2);
        assertNull(result);
    }

    @Test
    public void testEvaluate2() {
        System.out.println("evaluate (two params are equals)");
        final DefaultCrossingFunction function = new
                DefaultCrossingFunction();
        DefaultGenome<IntegerArrayGene> genome1 =
                new DefaultGenome<>();
        DefaultGenome<IntegerArrayGene> genome2 =
                new DefaultGenome<>();
        genome1.setChromosome(new IntegerArrayGene[]{
                new IntegerArrayGene(new Integer[]{
                        1, 2, 3
                })
        });
        genome2.setChromosome(new IntegerArrayGene[]{
                new IntegerArrayGene(new Integer[]{
                        1, 2, 3
                })
        });
        Genome<? extends Gene<?>>[] result = function.evaluate(
                genome1,
                genome2);
        assertArrayEquals(new Genome[]{
                genome1, genome2
        }, result);
    }

    @Test
    public void testEvaluate3() {
        System.out.println("evaluate (probability constrain not meet)");
        final DefaultCrossingFunction function = new
                DefaultCrossingFunction();
        function.setProbability(0.0);
        DefaultGenome<IntegerArrayGene> genome1 =
                new DefaultGenome<>();
        DefaultGenome<IntegerArrayGene> genome2 =
                new DefaultGenome<>();
        genome1.setChromosome(new IntegerArrayGene[]{
                new IntegerArrayGene(new Integer[]{
                        1, 2, 3
                })
        });
        genome2.setChromosome(new IntegerArrayGene[]{
                new IntegerArrayGene(new Integer[]{
                        4, 5, 6
                })
        });
        Genome<? extends Gene<?>>[] result = function.evaluate(
                genome1,
                genome2);
        assertArrayEquals(new Genome[]{
                genome1, genome2
        }, result);
    }

    @Test
    public void testSetRandomGenerator() {
        final DefaultCrossingFunction function = new DefaultCrossingFunction();
        function.setRandomGenerator(GenomeConstants.RANDOM_GENERATOR_1);
        assertEquals(
                GenomeConstants.RANDOM_GENERATOR_1,
                function.getRandomGenerator()
        );
    }

    private static class DefaultCrossingFunction
            extends AbstractCrossingFunction<Genome<? extends Gene<?>>> {

        private DefaultCrossingFunction() {
            super(0.75, new RandomGenerator() {
                @Override
                public int nextInt(int n) {
                    return 0;
                }

                @Override
                public double nextDouble() {
                    return 0.5;
                }
            });
        }

        @Override
        protected Genome<? extends Gene<?>>[] crossingProcess(
                Genome<? extends Gene<?>> parent1,
                Genome<? extends Gene<?>> parent2)
                throws CloneNotSupportedException {
            return null;
        }

    }

}

package crow.jai.gea.function.crossover;

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
public class CrossoverFunctionIT {

    public CrossoverFunctionIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(AbstractCrossoverFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        final DefaultCrossoverFunction function = new DefaultCrossoverFunction();
        function.setProbability(.5);
        assertEquals(new Double(.5),
                new Double(function.getProbability()));
    }

    @Test
    public void testSetProbability1() {
        System.out.println("setProbability (invalid argument)");
        final DefaultCrossoverFunction function = new DefaultCrossoverFunction();
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
        final DefaultCrossoverFunction function = new DefaultCrossoverFunction();
        function.setProbability(.1);
        final Object result = function.getProbability();
        assertEquals(0.1, result);
    }

    @Test
    public void testDefaultCrossoverProbability() {
        System.out.println("DefaultCrossoverFunction");
        final DefaultCrossoverFunction function = new DefaultCrossoverFunction();
        final Object result = function.getProbability();
        assertEquals(0.75, result);
    }

    @Test
    public void testValidate1() {
        System.out.println("validate (params is null)");
        try {
            final DefaultCrossoverFunction function = new
                    DefaultCrossoverFunction();
            function.evaluate(null);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testValidate2() {
        System.out.println("validate (params length is less than two)");
        try {
            final DefaultCrossoverFunction function = new
                    DefaultCrossoverFunction();
            function.evaluate(new DefaultGenome());
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testEvaluate1() {
        System.out.println("evaluate (to invoke CrossoverProcess)");
        final DefaultCrossoverFunction function = new
                DefaultCrossoverFunction();
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
        final DefaultCrossoverFunction function = new
                DefaultCrossoverFunction();
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
        final DefaultCrossoverFunction function = new
                DefaultCrossoverFunction();
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
        final DefaultCrossoverFunction function = new DefaultCrossoverFunction();
        function.setRandomGenerator(GenomeConstants.RANDOM_GENERATOR_1);
        assertEquals(
                GenomeConstants.RANDOM_GENERATOR_1,
                function.getRandomGenerator()
        );
    }

    private static class DefaultCrossoverFunction
            extends AbstractCrossoverFunction<Genome<? extends Gene<?>>> {

        private DefaultCrossoverFunction() {
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
        protected Genome<? extends Gene<?>>[] recombine(
                Genome<? extends Gene<?>> parent1,
                Genome<? extends Gene<?>> parent2)
                throws CloneNotSupportedException {
            return null;
        }

    }

}

package crow.jai.gea.function.mutattion;

import crow.jai.gea.GenomeConstants;
import crow.jai.gea.function.mutation.AbstractMutationFunction;
import crow.jai.gea.gene.DefaultGene;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.DefaultGenome;
import crow.jai.gea.genome.Genome;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class MutationFunctionIT {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(AbstractMutationFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        final DefaultMutationFunction function = new DefaultMutationFunction();
        function.setProbability(.5);
        assertEquals(new Double(.5),
                new Double(function.getProbability()));
    }

    @Test
    public void testSetProbability1() {
        System.out.println("setProbability (invalid argument)");
        final DefaultMutationFunction function = new DefaultMutationFunction();
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
        final DefaultMutationFunction function = new DefaultMutationFunction();
        function.setProbability(.1);
        final Object result = function.getProbability();
        assertEquals(0.1, result);
    }

    @Test
    public void testDefaultCrossoverProbability() {
        System.out.println("DefaultMutationFunction");
        final DefaultMutationFunction function = new DefaultMutationFunction();
        final Object result = function.getProbability();
        assertEquals(0.05, result);
    }

    @SuppressWarnings("NullArgumentToVariableArgMethod")
    @Test
    public void testValidate1() {
        System.out.println("validate (params is null)");
        try {
            final DefaultMutationFunction function = new
                    DefaultMutationFunction();
            function.evaluate(null);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("'IllegalArgumentException' not raised");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testValidate2() {
        System.out.println("validate (params length is 0)");
        try {
            final DefaultMutationFunction function = new
                    DefaultMutationFunction();
            function.evaluate();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("'IllegalArgumentException' not raised");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate() throws CloneNotSupportedException {
        System.out.println("evaluate (to invoke mutation process)");
        DefaultGenome<DefaultGene<Integer>> genome = new DefaultGenome<>();
        genome.setChromosome(
                new DefaultGene[]{
                        new DefaultGene(1),
                }
        );
        final Genome<DefaultGene<Integer>> clone = genome.clone();
        final DefaultMutationFunction function = new
                DefaultMutationFunction();
        function.setRandomGenerator(GenomeConstants.RANDOM_GENERATOR_4);
        final Genome<? extends Gene<?>> result = function.evaluate(genome);
        assertTrue(genome == result);
        assertEquals(genome, result);
        assertNotEquals(clone, result);
        assertNull(result.getGene(0).getData());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate2() throws CloneNotSupportedException {
        System.out.println("evaluate (probability constrain not meet)");
        final DefaultMutationFunction function = new
                DefaultMutationFunction();
        function.setProbability(0.0);
        DefaultGenome<DefaultGene<Integer>> genome = new DefaultGenome<>();
        genome.setChromosome(
                new DefaultGene[]{
                        new DefaultGene(1),
                }
        );
        final Genome<DefaultGene<Integer>> clone = genome.clone();
        final Genome<? extends Gene<?>> result = function.evaluate(genome);
        assertTrue(genome == result);
        assertEquals(genome, result);
        assertEquals(clone, result);
        assertNotNull(result.getGene(0).getData());
    }

    private static class DefaultMutationFunction extends
            AbstractMutationFunction<Genome<? extends Gene<?>>> {

        @Override
        protected void mutate(Genome<? extends Gene<?>> subject) {
            subject.getGene(0).setData(null);
        }
    }
}

package crow.jai.gea.function.crossing;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.DefaultGene;
import crow.jai.gea.genome.DefaultGenome;
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class SinglePointCrossingFunctionIT {

    private static final RandomGenerator RANDOM_GENERATOR = new RandomGenerator() {
        @Override
        public int nextInt(int n) {
            return n / 2 - 1;
        }

        @Override
        public double nextDouble() {
            return .5;
        }
    };

    private static final DefaultGenome<DefaultGene<Integer>> GENOME_1 = new DefaultGenome<>();
    private static final DefaultGenome<DefaultGene<Integer>> GENOME_2 = new DefaultGenome<>();
    private static final DefaultGenome<DefaultGene<Integer>>[] GENOMES = new DefaultGenome[2];
    private static final int CHROMOSOME_SIZE = 11;
    static {

        for (int i = 0; i < CHROMOSOME_SIZE; i++) {
            GENOME_1.addGene(new DefaultGene<>(i));
            GENOME_2.addGene(new DefaultGene<>(CHROMOSOME_SIZE - i));
        }
        GENOMES[0] = GENOME_1;
        GENOMES[1] = GENOME_2;
    }

    public SinglePointCrossingFunctionIT() {
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
    public void testEvaluate1() {
        System.out.println("evaluate1");
        SinglePointCrossingFunction<DefaultGenome<DefaultGene<Integer>>> function =
                new SinglePointCrossingFunction<>(0.0, RANDOM_GENERATOR);
        @SuppressWarnings("unchecked")
        DefaultGenome<DefaultGene<Integer>>[] result = function.evaluate(GENOMES);
        assertArrayEquals(result, GENOMES);
    }


    @Test
    public void testEvaluate2() {
        System.out.println("evaluate2");
        SinglePointCrossingFunction<DefaultGenome<DefaultGene<Integer>>> function =
                new SinglePointCrossingFunction<>(0.75, RANDOM_GENERATOR);
        @SuppressWarnings("unchecked")
        DefaultGenome<DefaultGene<Integer>>[] result = function.evaluate(GENOMES);
        @SuppressWarnings("unchecked")
        DefaultGenome<DefaultGene<Integer>>[] expResult = new DefaultGenome[2];
        expResult[0] = new DefaultGenome<>();
        expResult[1] = new DefaultGenome<>();
        for (int i = 0; i < CHROMOSOME_SIZE; i++) {
            if (i < function.getRandomGenerator().nextInt(CHROMOSOME_SIZE)) {
                expResult[0].addGene(new DefaultGene<>(CHROMOSOME_SIZE - i));
                expResult[1].addGene(new DefaultGene<>(i));
            } else {
                expResult[1].addGene(new DefaultGene<>(CHROMOSOME_SIZE - i));
                expResult[0].addGene(new DefaultGene<>(i));
            }
        }
        assertArrayEquals(result, expResult);
    }

}

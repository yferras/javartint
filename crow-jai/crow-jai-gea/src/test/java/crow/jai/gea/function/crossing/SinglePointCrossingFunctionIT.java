package crow.jai.gea.function.crossing;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.DefaultGene;
import crow.jai.gea.genome.DefaultGenome;
import crow.jai.gea.genome.Genome;
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class SinglePointCrossingFunctionIT {

    public static final RandomGenerator RANDOM_GENERATOR = new
            RandomGenerator() {
                @Override
                public int nextInt(int n) {
                    return n / 2 - 1;
                }

                @Override
                public double nextDouble() {
                    return .5;
                }
            };

    public static final DefaultGenome<DefaultGene<Integer>> GENOME_1 =
            new DefaultGenome<>();
    public static final DefaultGenome<DefaultGene<Integer>> GENOME_2 =
            new DefaultGenome<>();
    public static final DefaultGenome<DefaultGene<Integer>>[] GENOMES =
            new DefaultGenome[2];
    public static final int CHROMOSOME_SIZE = 11;

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
        System.out.print(
                SinglePointCrossingFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEvaluate2() {
        System.out.println("evaluate (performed algorithm)");
        SinglePointCrossingFunction<DefaultGenome<DefaultGene<Integer>>> function =
                new SinglePointCrossingFunction<>(0.75, RANDOM_GENERATOR);
        @SuppressWarnings("unchecked")
        Genome<DefaultGene<Integer>>[] result = function.evaluate(GENOMES);
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

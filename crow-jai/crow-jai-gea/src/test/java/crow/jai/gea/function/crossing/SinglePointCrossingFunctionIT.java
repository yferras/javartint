package crow.jai.gea.function.crossing;

import crow.jai.gea.gene.DefaultGene;
import crow.jai.gea.genome.DefaultGenome;
import crow.jai.gea.genome.Genome;
import org.junit.*;
import static crow.jai.gea.GenomeConstants.*;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class SinglePointCrossingFunctionIT {

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

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate2() {
        System.out.println("evaluate (performed algorithm)");
        SinglePointCrossingFunction<DefaultGenome<DefaultGene<Integer>>> function =
                new SinglePointCrossingFunction<>(0.75, RANDOM_GENERATOR_1);
        Genome<DefaultGene<Integer>>[] result = function.evaluate(GENOMES);
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
        assertArrayEquals(result[0].getChromosome(),
                expResult[0].getChromosome());
        assertArrayEquals(result[1].getChromosome(),
                expResult[1].getChromosome());
    }

}

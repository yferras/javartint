package crow.jai.gea.function.crossover;

import crow.jai.gea.gene.DefaultGene;
import crow.jai.gea.genome.DefaultGenome;
import crow.jai.gea.genome.Genome;
import org.junit.*;

import static crow.jai.gea.GenomeConstants.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class MultiPointsCrossoverFunctionIT {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(
                MultiPointsCrossoverFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMultiPointsCrossoverFunction1() {
        System.out.println("MultiPointsCrossoverFunction(probability)");
        final Double probability = new MultiPointsCrossoverFunction<>(1.0)
                .getProbability();
        assertEquals(new Double(1.0), probability);
    }

    @Test
    public void testMultiPointsCrossoverFunction2() {
        System.out.println("MultiPointsCrossoverFunction()");
        final Double probability = new MultiPointsCrossoverFunction<>()
                .getProbability();
        assertEquals(new Double(.75), probability);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate() {
        System.out.println("evaluate (performed algorithm)");
        RANDOM_GENERATOR_3.nextInt(CHROMOSOME_SIZE);
        MultiPointsCrossoverFunction<DefaultGenome<DefaultGene<Integer>>> function =
                new MultiPointsCrossoverFunction<>(0.75, RANDOM_GENERATOR_3);
        Genome<DefaultGene<Integer>>[] result = function.evaluate(GENOMES);
        DefaultGenome<DefaultGene<Integer>>[] expResult = new DefaultGenome[2];
        expResult[0] = new DefaultGenome<>();
        expResult[1] = new DefaultGenome<>();
        for (int i = 0; i < CHROMOSOME_SIZE; i++) {
            if (i % 2 != 0) {
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

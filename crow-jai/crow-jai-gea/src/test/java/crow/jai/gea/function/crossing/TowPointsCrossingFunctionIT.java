package crow.jai.gea.function.crossing;

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

public class TowPointsCrossingFunctionIT {

    public TowPointsCrossingFunctionIT() {
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
                TowPointsCrossingFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTowPointsCrossingFunction1() {
        System.out.println("SinglePointCrossingFunction(probability)");
        final Double probability = new TowPointsCrossingFunction<>(1.0)
                .getProbability();
        assertEquals(new Double(1.0), probability);
    }

    @Test
    public void testTowPointsCrossingFunction2() {
        System.out.println("SinglePointCrossingFunction()");
        final Double probability = new TowPointsCrossingFunction<>()
                .getProbability();
        assertEquals(new Double(.75), probability);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate() {
        System.out.println("evaluate (performed algorithm)");
        TowPointsCrossingFunction<DefaultGenome<DefaultGene<Integer>>> function =
                new TowPointsCrossingFunction<>(.75, RANDOM_GENERATOR_2);
        Genome<DefaultGene<Integer>>[] result = function.evaluate(GENOMES);
        DefaultGenome<DefaultGene<Integer>>[] expResult = new DefaultGenome[2];
        expResult[0] = new DefaultGenome<>();
        expResult[1] = new DefaultGenome<>();
        int position1 = RANDOM_GENERATOR_2.nextInt(CHROMOSOME_SIZE - 2);
        int position2 = RANDOM_GENERATOR_2.nextInt(CHROMOSOME_SIZE - 1);
        for (int i = 0; i < CHROMOSOME_SIZE; i++) {
            if (i >= position1 && i < position2) {
                expResult[0].addGene(new DefaultGene<>(CHROMOSOME_SIZE - i));
                expResult[1].addGene(new DefaultGene<>(i));
            } else {
                expResult[1].addGene(new DefaultGene<>(CHROMOSOME_SIZE - i));
                expResult[0].addGene(new DefaultGene<>(i));
            }
        }
        assertArrayEquals(expResult[0].getChromosome(),
                result[0].getChromosome());
        assertArrayEquals(expResult[1].getChromosome(),
                result[1].getChromosome());

    }


}

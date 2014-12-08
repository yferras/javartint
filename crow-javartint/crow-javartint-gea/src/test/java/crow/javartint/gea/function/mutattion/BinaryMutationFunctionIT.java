package crow.javartint.gea.function.mutattion;

import crow.javartint.gea.GenomeConstants;
import crow.javartint.gea.function.mutation.BinaryMutationFunction;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.DefaultGenome;
import crow.javartint.gea.genome.Genome;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class BinaryMutationFunctionIT {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(BinaryMutationFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate() throws CloneNotSupportedException {
        System.out.println("evaluate (performed algorithm)");
        DefaultGenome<Gene<Integer>> genome = new DefaultGenome<>();
        Gene<Integer>[] genes = new Gene[]{
                new DefaultGene(1),
                new DefaultGene(0),
                new DefaultGene(1),
                new DefaultGene(0),
                new DefaultGene(0),
                new DefaultGene(1),
                new DefaultGene(1),
                new DefaultGene(0),
                new DefaultGene(0),
                new DefaultGene(1),
        };
        genome.setChromosome(genes);

        final Genome<Gene<Integer>> clone = genome.clone();
        clone.getGene(1).setData(1);
        clone.getGene(3).setData(1);
        clone.getGene(4).setData(1);
        clone.getGene(9).setData(0);

        BinaryMutationFunction<DefaultGenome<Gene<Integer>>> function = new
                BinaryMutationFunction<>();
        function.setRandomGenerator(GenomeConstants.RANDOM_GENERATOR_4);
        function.evaluate(genome);
        assertEquals(clone, genome);
        assertArrayEquals(clone.getChromosome(), genome.getChromosome());
    }


}

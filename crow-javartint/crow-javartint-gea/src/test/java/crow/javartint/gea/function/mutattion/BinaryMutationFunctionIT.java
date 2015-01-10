package crow.javartint.gea.function.mutattion;

import crow.javartint.gea.GenomeConstants;
import crow.javartint.gea.function.mutation.BinaryMutationFunction;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.gene.IntegerArrayGene;
import crow.javartint.gea.genome.DefaultGenome;
import crow.javartint.gea.genome.Genome;
import org.junit.*;

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
        DefaultGenome<IntegerArrayGene> genome = new DefaultGenome<>();
        IntegerArrayGene[] genes = new IntegerArrayGene []{
                new IntegerArrayGene(new Integer[]{0}),
                new IntegerArrayGene(new Integer[]{1, 1, 0, 1, 0, 0}),
                new IntegerArrayGene(new Integer[]{1, 0, 0, 1}),
        };
        genome.setChromosome(genes);

        final Genome<IntegerArrayGene> clone = genome.clone();
        clone.getGene(0).setAllele(0, 1);
        clone.getGene(2).setAllele(1, 1);
        clone.getGene(2).setAllele(3, 0);

        BinaryMutationFunction<DefaultGenome<IntegerArrayGene>> function = new
                BinaryMutationFunction<>();
        function.setRandomGenerator(GenomeConstants.RANDOM_GENERATOR_5);
        genome = function.evaluate(genome);
        assertArrayEquals(clone.getChromosome(), genome.getChromosome());
    }


}

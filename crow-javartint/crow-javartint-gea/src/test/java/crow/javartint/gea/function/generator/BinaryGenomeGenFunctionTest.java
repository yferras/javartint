package crow.javartint.gea.function.generator;

import crow.javartint.gea.gene.IntegerArrayGene;
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.*;

import static org.junit.Assert.*;

public class BinaryGenomeGenFunctionTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(
                BinaryGenomeGenFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        BinaryGenomeGenFunction genFunction = new BinaryGenomeGenFunction(new int[]{1, 7, 4});
        DefaultGenome<IntegerArrayGene> genome = genFunction.evaluate();
        assertEquals(3, genome.getNumberOfGenes());
        assertEquals(1, genome.getGene(0).length());
        assertEquals(7, genome.getGene(1).length());
        assertEquals(4, genome.getGene(2).length());
    }
}
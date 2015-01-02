package crow.javartint.gea.function.generator;

import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TspGenomeGenFunctionTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(
                TspGenomeGenFunction.class.getName().concat("."));
    }

    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        TspGenomeGenFunction genFunction = new TspGenomeGenFunction(5);
        DefaultGenome<DefaultGene<Integer>> genome = genFunction.evaluate();
        assertEquals(5, genome.getNumberOfGenes());
    }
}
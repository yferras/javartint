package crow.javartint.gea.function.generator;

import crow.javartint.core.util.Range;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangeGenomeGenFunctionTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testEvaluate() throws Exception {
        Range<Double> range1 = new Range<>(-1.0, 1.0);
        Range<Double> range2 = new Range<>(400.0, 500.0);
        Range<Double> range3 =  new Range<>(-1000.0, -500.0);
        RangeGenomeGenFunction genFunction = new RangeGenomeGenFunction(3,
                range1, range2, range3);
        DefaultGenome<DefaultGene<Double>> genome = genFunction.evaluate();
        assertEquals(3, genome.size());
        assertTrue(range1.accept(genome.getGene(0).getData()));
        assertTrue(range2.accept(genome.getGene(1).getData()));
        assertTrue(range3.accept(genome.getGene(2).getData()));
    }
}
package crow.javartint.gea.function.scaling;

import crow.javartint.core.util.Optimize;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LinearRankScalingMethodTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(LinearRankScalingMethod.class.getName().concat("."));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInit() throws Exception {
        System.out.println("constructor (validate for out of bounds)");
        boolean lower = true, upper = true;
        try {
            new LinearRankScalingMethod(0.999999, Optimize.MAX);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            lower = false;
        }
        try {
            new LinearRankScalingMethod(2.000001, Optimize.MAX);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            upper = false;
        }
        if (lower) {
            fail("'IllegalArgumentException' not raised when the argument is out of the lower bound.");
        }
        if (upper) {
            fail("'IllegalArgumentException' not raised when the argument is out of the upper bound.");
        }
    }

    @Test
    public void testGetSelectivePressure() throws Exception {
        LinearRankScalingMethod scalingMethod = new LinearRankScalingMethod(1.5, Optimize.MAX);
        assertEquals(1.5, scalingMethod.getSelectivePressure(), 0.0);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        double p = 1.5;
        LinearRankScalingMethod<DefaultGenome<Gene<?>>> scalingMethod =
                new LinearRankScalingMethod(p, Optimize.MAX);
        List<DefaultGenome<Gene<?>>> genomes = new ArrayList<>(10);
        double[] exp = new double[10];
        for (int i = 0; i < 10; i++) {
            genomes.add(new DefaultGenome());
            exp[i] = 2.0 - p + (2.0 * i * (p - 1.0)) / (9.0);
        }
        List<DefaultGenome<Gene<?>>> evaluate = scalingMethod.evaluate(genomes);
        for (int i = 0; i < evaluate.size(); i++) {
            assertEquals(exp[i], evaluate.get(i).getFitness(), 0.0);
        }
    }
}
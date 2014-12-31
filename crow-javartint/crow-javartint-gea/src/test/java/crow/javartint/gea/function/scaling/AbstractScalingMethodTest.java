package crow.javartint.gea.function.scaling;

import crow.javartint.core.util.Optimize;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.DefaultGenome;
import crow.javartint.gea.genome.Genome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractScalingMethodTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(AbstractScalingMethod.class.getName().concat("."));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetOptimize() throws Exception {
        System.out.println("getOptimize");
        DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
        assertEquals(Optimize.MAX, scalingMethod.getOptimize());
    }

    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
        List<Double> list = new ArrayList<>();
        list.add(1.0);
        list.add(2.0);
        list.add(3.0);
        list.add(4.0);

        List<Double> expected = new ArrayList<>();
        for (Double val : list) {
            expected.add(val * 2.0);
        }
        @SuppressWarnings("unchecked")
        List<Double> r = scalingMethod.evaluate(list);
        assertArrayEquals(expected.toArray(), r.toArray());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluate2() throws Exception {
        System.out.println("evaluate (validate null)");
        DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
        try {
            scalingMethod.evaluate(null);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("'IllegalArgumentException' not raised for null param");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluate3() throws Exception {
        System.out.println("evaluate (validate empty)");
        DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
        try {
            scalingMethod.evaluate(new ArrayList());
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("'IllegalArgumentException' not raised for empty list param");
    }

    @Test
    public void testMean() throws Exception {
        System.out.println("mean");
        DefaultScalingMethod scalingMethod = new DefaultScalingMethod(Optimize.MAX);
        List<Genome<Gene<?>>> list = new ArrayList<>();
        double exp = 0.0;
        for (int i = 0; i < 10; i++) {
            list.add(new DefaultGenome<>());
            list.get(i).setFitness(i + 1.0);
            exp += i + 1;
        }
        exp /= 10;
        @SuppressWarnings("unchecked")
        double r = scalingMethod.mean(list);
        assertEquals(exp, r, 0.0);
    }

    private static class DefaultScalingMethod extends AbstractScalingMethod {

        /**
         * Constructor that initializes this instance.
         *
         * @param optimize optimization way
         */
        public DefaultScalingMethod(Optimize optimize) {
            super(optimize);
        }

        @Override
        protected void scale(List genomes) {
            for (int i = 0; i < genomes.size(); i++) {
                Double val = (Double) genomes.get(i);
                genomes.set(i, val * 2.0);
            }
        }
    }
}
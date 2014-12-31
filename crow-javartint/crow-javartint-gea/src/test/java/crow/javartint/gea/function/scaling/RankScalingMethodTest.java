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

public class RankScalingMethodTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(RankScalingMethod.class.getName().concat("."));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        RankScalingMethod<DefaultGenome<Gene<?>>> scalingMethod = new RankScalingMethod<>(Optimize.MAX);
        List<DefaultGenome<Gene<?>>> genomes = new ArrayList<>(10);
        double[] exp = new double[10];
        for (int i = 0; i < 10; i++) {
            genomes.add(new DefaultGenome());
            exp[i] = i + 1;
        }
        List<DefaultGenome<Gene<?>>> evaluate = scalingMethod.evaluate(genomes);
        for (int i = 0; i < evaluate.size(); i++) {
            assertEquals(exp[i], evaluate.get(i).getFitness(), 0.0);
        }
    }
}
package crow.javartint.gea.function.scaling;

import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class SigmaScalingMethodTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(SigmaScalingMethod.class.getName().concat("."));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        SigmaScalingMethod<DefaultGenome<Gene<?>>> scalingMethod = new SigmaScalingMethod<>();
                List<DefaultGenome<Gene<?>>> genomes = new ArrayList<>(10);
        double[] exp = new double[100];
        Random random = new Random();
        double sum = 0.0;
        for (int i = 0; i < 100; i++) {
            genomes.add(new DefaultGenome<>());
            double f = random.nextInt(100);
            genomes.get(i).setFitness(exp[i] = f);
            sum += f;
        }
        double mean = sum / 100.0;
        sum = 0.0;
        for (DefaultGenome<Gene<?>> genome : genomes) {
            double diff = genome.getFitness() - mean;
            sum += diff * diff;
        }
        double sigma = Math.sqrt(sum / 100.0);

        for (int i = 0; i < 100; i++) {
            exp[i] = (exp[i] - mean) / sigma;
        }

        List<DefaultGenome<Gene<?>>> evaluate = scalingMethod.evaluate(genomes);
        for (int i = 0; i < evaluate.size(); i++) {
            assertEquals(exp[i], evaluate.get(i).getFitness(), 0.0);
        }
    }
}
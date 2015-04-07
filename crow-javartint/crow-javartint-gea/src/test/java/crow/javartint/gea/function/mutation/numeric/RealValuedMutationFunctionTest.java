package crow.javartint.gea.function.mutation.numeric;

import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;
import crow.javartint.gea.genome.Genome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RealValuedMutationFunctionTest {


    private final DefaultGene<Double> doubleDefaultGene1 = new DefaultGene<>(3.9);
    private final DefaultGene<Double> doubleDefaultGene2 = new DefaultGene<>(-3.4);
    private final DefaultGene<Double> doubleDefaultGene3 = new DefaultGene<>(-.04);
    private final DefaultGene<Double> doubleDefaultGene4 = new DefaultGene<>(10.8);

    @Before
    public void setUp() throws Exception {
        System.out.print(RealValuedMutationFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() throws Exception {

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testValidate() throws Exception {
        System.out.println("validate");
        RealValuedMutationFunction mutationFunction = new RealValuedMutationFunction(5);
        DefaultGenome<DefaultGene<Double>> genome = new DefaultGenome<>();
        genome.setChromosome(new DefaultGene[] {
                doubleDefaultGene1, doubleDefaultGene2,
                doubleDefaultGene3, doubleDefaultGene4
        });
        try {
            mutationFunction.validate(genome);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate");
        DefaultGenome<DefaultGene<Double>> genome = new DefaultGenome<>();
        genome.setChromosome(new DefaultGene[] {
                doubleDefaultGene1, doubleDefaultGene2,
                doubleDefaultGene3, doubleDefaultGene4
        });
        RealValuedMutationFunction mutationFunction = new RealValuedMutationFunction(genome.size());
        mutationFunction.setRandom(new Random() {
            private final double[] values = new double[] {
                    .025, .75, .025, .5, .025, .1, .70
            };
            int index = 0;
            @Override
            public double nextDouble() {
                return values[index++];
            }
        });
        final Genome<DefaultGene<Double>> r = mutationFunction.evaluate(genome);
        for (int i = 0; i < r.size(); i++) {
            double before = genome.getGene(i).getData();
            double after = r.getGene(i).getData();
            assertEquals(before, after, mutationFunction.getMutationRange(i));
        }
    }


}
package crow.javartint.gea.function.mutation;

/*
 * #%L
 * Crow JavArtInt GEA
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import crow.javartint.gea.GenomeConstants;
import crow.javartint.gea.function.mutation.AbstractMutationFunction;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.DefaultGenome;
import crow.javartint.gea.genome.Genome;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class MutationFunctionTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(AbstractMutationFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        final DefaultMutationFunction function = new DefaultMutationFunction();
        function.setProbability(.5);
        assertEquals(new Double(.5),
                new Double(function.getProbability()));
    }

    @Test
    public void testSetProbability1() {
        System.out.println("setProbability (invalid argument)");
        final DefaultMutationFunction function = new DefaultMutationFunction();
        try {
            function.setProbability(-.5);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("IllegalArgumentException, not raised.");
    }

    @Test
    public void testSetProbability2() {
        System.out.println("setProbability (valid argument)");
        final DefaultMutationFunction function = new DefaultMutationFunction();
        function.setProbability(.1);
        final Object result = function.getProbability();
        assertEquals(0.1, result);
    }

    @Test
    public void testDefaultCrossoverProbability() {
        System.out.println("DefaultMutationFunction");
        final DefaultMutationFunction function = new DefaultMutationFunction();
        final Object result = function.getProbability();
        assertEquals(0.05, result);
    }

    @SuppressWarnings("NullArgumentToVariableArgMethod")
    @Test
    public void testValidate1() {
        System.out.println("validate (params is null)");
        try {
            final DefaultMutationFunction function = new
                    DefaultMutationFunction();
            function.evaluate(null);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("'IllegalArgumentException' not raised");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testValidate2() {
        System.out.println("validate (params length is 0)");
        try {
            final DefaultMutationFunction function = new
                    DefaultMutationFunction();
            function.evaluate(null);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("'IllegalArgumentException' not raised");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate() throws CloneNotSupportedException {
        System.out.println("evaluate (to invoke mutation process)");
        DefaultGenome<DefaultGene<Integer>> genome = new DefaultGenome<>();
        genome.setChromosome(
                new DefaultGene[]{
                        new DefaultGene(1),
                }
        );
        final DefaultMutationFunction function = new
                DefaultMutationFunction();
        function.setRandom(GenomeConstants.RANDOM_GENERATOR_4);
        final Genome<? extends Gene<?>> result = function.evaluate(genome.clone());
        assertFalse(genome == result);
        assertNull(result.getGene(0).getData());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEvaluate2() throws CloneNotSupportedException {
        System.out.println("evaluate (probability constrain not meet)");
        final DefaultMutationFunction function = new
                DefaultMutationFunction();
        function.setProbability(0.0);
        DefaultGenome<DefaultGene<Integer>> genome = new DefaultGenome<>();
        genome.setChromosome(
                new DefaultGene[]{
                        new DefaultGene(1),
                }
        );
        final Genome<? extends Gene<?>> result = function.evaluate(genome.clone());
        assertFalse(genome == result);
        assertEquals(genome, result);
        assertNotNull(result.getGene(0).getData());
    }

    private static class DefaultMutationFunction extends
            AbstractMutationFunction<Genome<? extends Gene<?>>> {

        @Override
        protected Genome<? extends Gene<?>> mutate(Genome<? extends Gene<?>> subject) {
            subject.getGene(0).setData(null);
            return subject;
        }
    }
}

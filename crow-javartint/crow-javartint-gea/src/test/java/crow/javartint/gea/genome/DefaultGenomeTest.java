package crow.javartint.gea.genome;

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

import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.gene.DoubleArrayGene;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.gene.IntegerArrayGene;
import org.junit.*;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class DefaultGenomeTest {

    private static final Gene<Integer> GENE1 = new DefaultGene<>(1);
    private static final Gene<Double[]> GENE2 = new DoubleArrayGene(new Double[]{1.0, 5.0, 9.0});
    private static final Gene<Integer[]> GENE3 = new IntegerArrayGene(new Integer[]{1, 5, 9});
    private static final Gene<Integer> GENE4 = new DefaultGene<>(4);
    private static final Gene<?>[] GENES = new Gene[]{
        GENE1, GENE2, GENE3, GENE4};

    public DefaultGenomeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(DefaultGenome.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getChromosome method, of class DefaultGenome.
     */
	@Test
    public void testGetChromosome() {
        System.out.println("getChromosome");
		DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        Object[] expResult = GENES.clone();
        Object[] result = instance.getChromosome();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getFitness method, of class DefaultGenome.
     */
    @Test
    public void testGetFitness() {
        System.out.println("getFitness");
        DefaultGenome<?> instance = new DefaultGenome<>();
        double expResult = 0.0;
        double result = instance.getFitness();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getGene method, of class DefaultGenome.
     */
	@Test
    public void testGetGene() {
        System.out.println("getGene");
        int index = 1;
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        Object expResult = GENE2;
        Object result = instance.getGene(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class DefaultGenome.
     */
	@Test
    public void testGetNumberOfGenes() {
        System.out.println("size");
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        int expResult = 4;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasNext method, of class DefaultGenome.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        boolean result = instance.hasNext();
        assertEquals(true, result);
    }

    /**
     * Test of iterator method, of class DefaultGenome.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        Iterator<?> result = instance.iterator();
        assertTrue(result != null);
    }

    /**
     * Test of next method, of class DefaultGenome.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        Object result = instance.next();
        assertTrue(result != null);
    }

    /**
     * Test of remove method, of class DefaultGenome.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        try {
            instance.remove();
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of setChromosome method, of class DefaultGenome.
     */
    @Test
    public void testSetChromosome() {
        System.out.println("setChromosome");
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        assertTrue(instance.getChromosome() != null);
    }

    /**
     * Test of setFitness method, of class DefaultGenome.
     */
    @Test
    public void testSetFitness() {
        System.out.println("setFitness");
        double fitness = 0.0;
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setFitness(fitness);
        assertTrue(fitness == instance.getFitness());
    }

    /**
     * Test of setGene method, of class DefaultGenome.
     */
    @Test
    public void testSetGene() {
        System.out.println("setGene");
        int index = 2;
        Gene<Integer> newGene = new DefaultGene<>(0);
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        instance.setGene(index, newGene);
        assertEquals(newGene, instance.getGene(index));
    }

    /**
     * Test of clone method, of class DefaultGenome.
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        DefaultGenome<Gene<?>> instance = new DefaultGenome<>();
        instance.setChromosome(GENES);
        DefaultGenome<Gene<?>> result = (DefaultGenome<Gene<?>>) instance.clone();
        assertTrue(instance.equals(result));
        assertFalse(instance.genes == result.genes);
    }

}
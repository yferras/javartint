package crow.jai.gea.genome;

import crow.jai.gea.gene.DefaultGene;
import crow.jai.gea.gene.DoubleArrayGene;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.gene.IntegerArrayGene;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class DefaultGenomeIT {

    private static final Gene GENE1 = new DefaultGene(1);
    private static final Gene GENE2 = new DoubleArrayGene(new Double[]{1.0, 5.0, 9.0});
    private static final Gene GENE3 = new IntegerArrayGene(new Integer[]{1, 5, 9});
    private static final Gene GENE4 = new DefaultGene(4);
    private static final Gene[] GENES = new Gene[]{
        GENE1, GENE2, GENE3, GENE4};

    public DefaultGenomeIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
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
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        Gene[] expResult = GENES.clone();
        Gene[] result = instance.getChromosome();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFitness method, of class DefaultGenome.
     */
    @Test
    public void testGetFitness() {
        System.out.println("getFitness");
        DefaultGenome instance = new DefaultGenome();
        double expResult = 0.0;
        double result = instance.getFitness();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getGene method, of class DefaultGenome.
     */
    @Test
    public void testGetGene() {
        System.out.println("getGene");
        int index = 1;
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        Object expResult = GENE2;
        Object result = instance.getGene(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfGenes method, of class DefaultGenome.
     */
    @Test
    public void testGetNumberOfGenes() {
        System.out.println("getNumberOfGenes");
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        int expResult = 4;
        int result = instance.getNumberOfGenes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of hasNext method, of class DefaultGenome.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        boolean expResult = true;
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class DefaultGenome.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        DefaultGenome instance = new DefaultGenome();
        Iterator result = instance.iterator();
        assertTrue(result != null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class DefaultGenome.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        Object result = instance.next();
        assertTrue(result != null);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class DefaultGenome.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        DefaultGenome instance = new DefaultGenome();
        try {
            instance.remove();
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setChromosome method, of class DefaultGenome.
     */
    @Test
    public void testSetChromosome() {
        System.out.println("setChromosome");
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        assertTrue(instance.getChromosome() != null);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setFitness method, of class DefaultGenome.
     */
    @Test
    public void testSetFitness() {
        System.out.println("setFitness");
        double fitness = 0.0;
        DefaultGenome instance = new DefaultGenome();
        instance.setFitness(fitness);
        assertTrue(fitness == instance.getFitness());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setGene method, of class DefaultGenome.
     */
    @Test
    public void testSetGene() {
        System.out.println("setGene");
        int index = 2;
        Gene newGene = new DefaultGene(0);
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        instance.setGene(index, newGene);
        assertEquals(newGene, instance.getGene(index));
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class DefaultGenome.
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        DefaultGenome instance = new DefaultGenome();
        instance.setChromosome(GENES);
        DefaultGenome result = instance.clone();
        assertEquals(result, instance);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}

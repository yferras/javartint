package crow.jai.gea.gene;

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
public class DefaultGeneIT {

    public DefaultGeneIT() {
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
     * Test of getData method, of class DefaultGene.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        DefaultGene<Double> instance = new DefaultGene<Double>(new Double(1.0));
        Object expResult = 1.0;
        Object result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class DefaultGene.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        Object data = 1.0;
        DefaultGene<Object> instance = new DefaultGene<Object>(data);
        instance.setData(data);
        Object result = instance.getData();
        assertEquals(data, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class DefaultGene.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone1() throws Exception {
        System.out.println("clone check references");
        DefaultGene<Double> instance = new DefaultGene<Double>(2.0);
        DefaultGene<Double> result = instance.clone();
        assertFalse(instance == result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class DefaultGene.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone2() throws Exception {
        System.out.println("clone check identity");
        DefaultGene<Double> instance = new DefaultGene<Double>(2.0);
        DefaultGene<Double> result = instance.clone();
        assertEquals(instance, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of hashCode method, of class DefaultGene.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DefaultGene<Double> instance = new DefaultGene<Double>(5.0);
        int expResult = new DefaultGene<Double>(5.0).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DefaultGene.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new DefaultGene<Double>(2.0);
        DefaultGene<Double> instance = new DefaultGene<Double>(2.0);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}

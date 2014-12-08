package crow.javartint.gea.gene;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        System.out.print(DefaultGene.class.getName().concat("."));
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
        DefaultGene<Double> instance = new DefaultGene<>(1.0);
        Object expResult = 1.0;
        Object result = instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Test of setData method, of class DefaultGene.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        Object data = 1.0;
        DefaultGene<Object> instance = new DefaultGene<>(data);
        instance.setData(data);
        Object result = instance.getData();
        assertEquals(data, result);
    }

    /**
     * Test of clone method, of class DefaultGene.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone1() throws Exception {
        System.out.println("clone (check references)");
        DefaultGene<Double> instance = new DefaultGene<>(2.0);
        DefaultGene<Double> result = (DefaultGene<Double>) instance.clone();
        assertFalse(instance == result);
    }

    /**
     * Test of clone method, of class DefaultGene.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone2() throws Exception {
        System.out.println("clone (check identity)");
        DefaultGene<Double> instance = new DefaultGene<>(2.0);
        DefaultGene<Double> result = (DefaultGene<Double>) instance.clone();
        assertEquals(instance, result);
    }


    /**
     * Test of hashCode method, of class DefaultGene.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DefaultGene<Double> instance = new DefaultGene<>(5.0);
        int expResult = new DefaultGene<>(5.0).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class DefaultGene.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new DefaultGene<>(2.0);
        DefaultGene<Double> instance = new DefaultGene<>(2.0);
        boolean result = instance.equals(obj);
        assertEquals(true, result);
    }

}

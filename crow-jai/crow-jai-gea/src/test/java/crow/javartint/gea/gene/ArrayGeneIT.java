package crow.javartint.gea.gene;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class ArrayGeneIT {

    public static final ArrayGene<Double> INSTANCE = new ArrayGene<>(
            new Double[]{
                    1.0, 2.0, 3.0, 4.0, 5.0
            });

    public ArrayGeneIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(ArrayGene.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getData method, of class ArrayGene.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        Object[] expResult = new Double[]{
                1.0, 2.0, 3.0, 4.0, 5.0
        };
        Object[] result = INSTANCE.getData();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setData method, of class ArrayGene.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        Double[] data = new Double[]{3.0, 1.0, 2.0, 5.0, 4.0};
        ArrayGene<Double> instance = new ArrayGene<>(null);
        instance.setData(data.clone());
        Double[] result = instance.getData();
        assertArrayEquals(data, result);
    }

    /**
     * Test of clone method, of class ArrayGene.
     *
     * @throws Exception
     */
    @Test
    public void testClone1() throws Exception {
        System.out.println("clone (check references)");
        ArrayGene<Double> result = (ArrayGene<Double>) INSTANCE.clone();
        assertFalse(INSTANCE == result);
    }

    /**
     * Test of clone method, of class ArrayGene.
     *
     * @throws Exception
     */
    @Test
    public void testClone2() throws Exception {
        System.out.println("clone (check identity)");
        ArrayGene<Double> result = (ArrayGene<Double>) INSTANCE.clone();
        assertEquals(INSTANCE, result);
    }


    /**
     * Test of hashCode method, of class ArrayGene.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = new ArrayGene<>(new Double[]{
                1.0, 2.0, 3.0, 4.0, 5.0
        }).hashCode();
        int result = INSTANCE.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ArrayGene.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new ArrayGene<>(new Double[]{
                1.0, 2.0, 3.0, 4.0, 5.0
        });
        assertEquals(true, INSTANCE.equals(obj));
    }

    /**
     * Test of length method, of class ArrayGene.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        assertEquals(5, INSTANCE.length());
    }

    /**
     * Test of getAllele method, of class ArrayGene.
     */
    @Test
    public void testGetAllele() {
        System.out.println("getAllele");
        assertEquals(new Double(5.0), INSTANCE.getAllele(4));
    }

    /**
     * Test of setAllele method, of class ArrayGene.
     */
    @Test
    public void testSetAllele() {
        System.out.println("setAllele");
        try {
            final ArrayGene<Double> gene = (ArrayGene<Double>) INSTANCE.clone();
            gene.setAllele(2, 10.0);
            assertEquals(new Double(10.0), gene.getAllele(2));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            fail("clone raised an exception");
        }
    }

}

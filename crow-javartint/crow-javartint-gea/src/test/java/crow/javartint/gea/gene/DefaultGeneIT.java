package crow.javartint.gea.gene;

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

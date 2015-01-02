package crow.javartint.core.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangeTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(Range.class.getName().concat
                ("."));
    }

    @Test
    public void testConstructor3() {
        System.out.println("constructor " +
                "(raise exception)");
        try {
            new Range<>(0.5, 0.0, Range.Use.BOTH);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            return;
        }
        fail("'IllegalArgumentException' not raised");
    }
    @Test
    public void testAccept() throws Exception {
        Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.BOTH);
        assertTrue(range.accept(-1.0));
        assertTrue(range.accept(1.0));
        assertTrue(range.accept(0.0));
        assertFalse(range.accept(-1.1));
        assertFalse(range.accept(1.05));
        range = new Range<>(-1.0, 1.0, Range.Use.NONE);
        assertFalse(range.accept(-1.0));
        assertFalse(range.accept(1.0));
        assertTrue(range.accept(0.0));
        assertFalse(range.accept(-1.1));
        assertFalse(range.accept(1.05));
        range = new Range<>(-1.0, 1.0, Range.Use.MAX);
        assertFalse(range.accept(-1.0));
        assertTrue(range.accept(1.0));
        assertTrue(range.accept(0.0));
        assertFalse(range.accept(-1.1));
        assertFalse(range.accept(1.05));
        range = new Range<>(-1.0, 1.0, Range.Use.MIN);
        assertTrue(range.accept(-1.0));
        assertFalse(range.accept(1.0));
        assertTrue(range.accept(0.0));
        assertFalse(range.accept(-1.1));
        assertFalse(range.accept(1.05));
    }

    @Test
    public void testGetMin() throws Exception {
        Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.BOTH);
        assertEquals(-1.0, range.getMin(), 0.0);
    }

    @Test
    public void testGetMax() throws Exception {
        Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.BOTH);
        assertEquals(1.0, range.getMax(), 0.0);
    }

    @Test
    public void testGetUse() throws Exception {
        Range<Double> range = new Range<>(-1.0, 1.0, Range.Use.NONE);
        assertEquals(Range.Use.NONE, range.getUse());
    }
}
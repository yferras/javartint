package crow.javartint.gea.util;

import crow.javartint.gea.genome.DefaultGenome;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenomeFilterIT {

    @BeforeClass
    public static void setUpClass() {
    }

    ;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.print(GenomeFilter.class.getName().concat("."));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAccept1() {
        System.out.println("accept (true)");
        assertTrue(new DefaultFilter().accept(new DefaultGenome<>()));
    }

    @Test
    public void testAccept2() {
        System.out.println("accept (false)");
        assertFalse(new DefaultFilter().accept(null));
    }

    @SuppressWarnings("unchecked")
    final class DefaultFilter implements GenomeFilter<DefaultGenome<?>> {

        /**
         * Returns {@code true} if genome is not {@code null},
         * otherwise returns {@code false}.
         *
         * @param genome genome to filter.
         * @return {@code true} if the passed genome is accepted.
         */
        @Override
        public boolean accept(DefaultGenome<?> genome) {
            return genome != null;
        }
    }

}
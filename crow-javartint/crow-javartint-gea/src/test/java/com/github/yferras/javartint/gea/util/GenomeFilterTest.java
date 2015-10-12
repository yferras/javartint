package com.github.yferras.javartint.gea.util;

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

import com.github.yferras.javartint.gea.genome.DefaultGenome;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenomeFilterTest {

    @BeforeClass
    public static void setUpClass() {
    }

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
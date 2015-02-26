package crow.javartint.gea.function.generator;

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
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TspGenomeGenFunctionTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(
                TspGenomeGenFunction.class.getName().concat("."));
    }

    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        TspGenomeGenFunction genFunction = new TspGenomeGenFunction(5);
        DefaultGenome<DefaultGene<Integer>> genome = genFunction.evaluate();
        assertEquals(5, genome.size());
    }
}
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

import crow.javartint.gea.gene.IntegerArrayGene;
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryGenomeGenFunctionTest {

    @Before
    public void setUp() throws Exception {
        System.out.print(
                BinaryGenomeGenFunction.class.getName().concat("."));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate (performed algorithm)");
        BinaryGenomeGenFunction genFunction = new BinaryGenomeGenFunction(new int[]{1, 7, 4});
        DefaultGenome<IntegerArrayGene> genome = genFunction.evaluate();
        assertEquals(3, genome.size());
        assertEquals(1, genome.getGene(0).length());
        assertEquals(7, genome.getGene(1).length());
        assertEquals(4, genome.getGene(2).length());
    }
}
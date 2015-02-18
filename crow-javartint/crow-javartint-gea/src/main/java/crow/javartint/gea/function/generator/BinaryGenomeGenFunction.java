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

import java.util.Random;

/**
 *
 */
public class BinaryGenomeGenFunction
        extends AbstractGenomeGeneratorFunction<DefaultGenome<IntegerArrayGene>> {

    public BinaryGenomeGenFunction(int numberOfGenes, int lengthOfGene) {
        super(numberOfGenes, lengthOfGene);
    }

    public BinaryGenomeGenFunction(int[] lengthsOfGenes) {
        super(lengthsOfGenes);
    }

    @Override
    protected DefaultGenome<IntegerArrayGene> generate(int[] lengthsOfGenes) {
        Random random = new Random();
        IntegerArrayGene[] genes = new IntegerArrayGene[lengthsOfGenes.length];
        for (int i = 0; i < lengthsOfGenes.length; i++) {
            Integer[] data = new Integer[lengthsOfGenes[i]];
            for (int j = 0; j < data.length; j++) {
                data[j] = random.nextInt(2);
            }
            genes[i] = new IntegerArrayGene(data);
        }
        DefaultGenome<IntegerArrayGene> genome = new DefaultGenome<>();
        genome.setChromosome(genes);
        return genome;
    }

}

package crow.javartint.gea.function.mutation;

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
import crow.javartint.gea.genome.Genome;

/**
 * Class that represents an binary mutation function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class BinaryMutationFunction<T extends Genome<IntegerArrayGene>>
        extends AbstractMutationFunction<T> {

    @Override
    protected T mutate(T subject) {
        boolean muted = false;
        while (!muted) {
            for (IntegerArrayGene gene : subject) {
                for (int i = 0; i < gene.length(); i++) {
                    if (getRandomGenerator().nextDouble() < getProbability()){
                        int val = gene.getAllele(i);
                        gene.setAllele(i, val == 0 ? 1 : 0);
                        muted = true;
                    }
                }
            }
        }
        return subject;
    }
}

package crow.javartint.gea.function.decoder;

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

import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * This class implements the interface {@link crow.javartint.gea.function.decoder.DecoderFunction}.
 * This class can be derived to create a functions to decode genomes.
 *
 * @param <D> Type of decoded result
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.2
 */
public abstract class AbstractDecoderFunction<D, T extends Genome<? extends Gene<?>>> implements DecoderFunction<D, T> {

    /**
     * Validates the input params.
     *
     * @param genome genome to validate.
     * @throws java.lang.IllegalArgumentException if argument is {@code null}
     */
    protected void validate(T genome) {
        if (genome == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method must be implemented to decode the genome.
     *
     * @param genome genome to decode
     * @return decoded value
     */
    protected abstract D decode(T genome);

    /**
     * This method validate and performs the decode process.
     *
     * @param genome see {@link #decode(crow.javartint.gea.genome.Genome)}
     * @return see {@link #decode(crow.javartint.gea.genome.Genome)}
     */
    @Override
    public D evaluate(T genome) {
        validate(genome);
        return decode(genome);
    }
}

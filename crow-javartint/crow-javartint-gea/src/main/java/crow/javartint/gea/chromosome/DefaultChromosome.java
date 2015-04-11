package crow.javartint.gea.chromosome;

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

/**
 * This class represents a default chromosome.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.gene.Gene} interface.
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class DefaultChromosome<T extends Gene<?>> extends AbstractChromosome<T> {

    /**
     * Default constructor.
     */
    public DefaultChromosome() {
        super();
    }

}

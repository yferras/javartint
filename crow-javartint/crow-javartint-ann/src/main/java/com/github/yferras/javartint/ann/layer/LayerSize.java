package com.github.yferras.javartint.ann.layer;

/*
 * #%L
 * Crow JavArtInt ANN
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

import java.io.Serializable;

/**
 * This class represents the size of a layer.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class LayerSize implements Serializable{
    private static final int HASH_CODE_CONST = 31;
    private final int height;
    private final int width;

    /**
     * <p>Constructor for LayerSize.</p>
     *
     * @param height
     *            the value of height
     * @param width
     *            the value of width
     */
    public LayerSize(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Gets the value of height
     *
     * @return the value of height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the value of width
     *
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the total items.
     *
     * @return the total items.
     */
    public int count() {
        return getWidth() * getHeight();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LayerSize layerSize = (LayerSize) o;

        return height == layerSize.height && width == layerSize.width;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return HASH_CODE_CONST * height + width;
    }
}

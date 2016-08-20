/*
 * Copyright (C) 2015  Eng. Ferrás Cecilio, Yeinier. <https://www.linkedin.com/in/yeinierferras>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.github.yferras.javartint.ann.function.activation;

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

import com.github.yferras.javartint.core.function.DerivableFunction;

/**
 * Class that represents a line activation function.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class LineActivationFunction implements DerivableFunction<Double, Double>, ActivationFunction {

	private static final long serialVersionUID = -241622991375510051L;

	/**
     * <p>Constructor for LineActivationFunction.</p>
     */
    public LineActivationFunction() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    public DerivableFunction<Double, Double> derive() {
        return new DerivableFunction<Double, Double>() {
            @Override
            public DerivableFunction<Double, Double> derive() {
                return null;
            }

            @Override
            public Double evaluate(Double params) {
                return 1.0;
            }

			@Override
			public int getOrder() {
				return 1;
			}
        };
    }

    /**
     * {@inheritDoc}
     * <p/>
     * f(x) = x
     */
    @Override
    public Double evaluate(Double x) {
        return x;
    }

	@Override
	public int getOrder() {
		return 1;
	}
}

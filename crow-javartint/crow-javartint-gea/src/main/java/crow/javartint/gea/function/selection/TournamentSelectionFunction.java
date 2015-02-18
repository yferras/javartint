package crow.javartint.gea.function.selection;

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

import crow.javartint.gea.function.scaling.AbstractScalingMethod;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tournament selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class TournamentSelectionFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractSelectionFunction<T> {

    public TournamentSelectionFunction() {
        this(2);
    }

    public TournamentSelectionFunction(int selectedGenomes) {
        this(selectedGenomes, null);
    }

    public TournamentSelectionFunction(int selectedGenomes,
                                       AbstractScalingMethod<T> scalingMethod)
            throws IllegalArgumentException {
        super(selectedGenomes, scalingMethod);
    }

    @Override
    protected List<T> select(List<T> genomes) {
        if (getScalingMethod() != null) {
            getScalingMethod().evaluate(genomes);
        }
        List<T> selected = new ArrayList<>(getNumToSelect());
        while (selected.size() < getNumToSelect()) {
            selected.add(tournamentSelection(genomes));
        }
        return selected;
    }

    private T tournamentSelection(List<T> genomes) {
        double bestFitnessSoFar = 0;
        int chosenOne = 0;
        Random rand = new Random();
        for (int i = 0; i < getNumToSelect(); i++) {
            int thisTry = rand.nextInt(genomes.size());
            if (genomes.get(thisTry).getFitness() > bestFitnessSoFar) {
                chosenOne = thisTry;
                bestFitnessSoFar = genomes.get(thisTry).getFitness();
            }
        }
        return genomes.get(chosenOne);
    }
}

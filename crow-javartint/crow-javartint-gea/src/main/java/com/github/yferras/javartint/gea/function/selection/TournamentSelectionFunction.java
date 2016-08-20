package com.github.yferras.javartint.gea.function.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.yferras.javartint.gea.Individual;
import com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod;

/**
 * Tournament selection function.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.Individual}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public class TournamentSelectionFunction<T extends Individual>
    extends AbstractSelectionFunction<T> {

    /**
     * <p>Constructor for TournamentSelectionFunction.</p>
     */
    public TournamentSelectionFunction() {
        super();
    }

    /**
     * <p>Constructor for TournamentSelectionFunction.</p>
     *
     * @param selectedGenomes a int.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public TournamentSelectionFunction(int selectedGenomes)  {
        this(selectedGenomes, null);
    }

    /**
     * <p>Constructor for TournamentSelectionFunction.</p>
     *
     * @param selectedGenomes a int.
     * @param scalingMethod   a {@link com.github.yferras.javartint.gea.function.scaling.AbstractScalingMethod} object.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public TournamentSelectionFunction(int selectedGenomes,
                                       AbstractScalingMethod<T> scalingMethod)  {
        super(selectedGenomes, scalingMethod);
    }

    /** {@inheritDoc} */
    @Override
    protected List<T> select(List<T> individuals) {
        if (getScalingMethod() != null) {
            getScalingMethod().evaluate(individuals);
        }
        List<T> selected = new ArrayList<>(getNumToSelect());
        while (selected.size() < getNumToSelect()) {
            selected.add(tournamentSelection(individuals));
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

package crow.javartint.gea.function.selection;

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
public class TournamentSelectionOperator<T extends Genome<? extends Gene<?>>>
        extends AbstractSelectionOperator<T>  {

    public TournamentSelectionOperator() {
        this(2);
    }

    public TournamentSelectionOperator(int selectedGenomes) {
        this(selectedGenomes, null);
    }

    public TournamentSelectionOperator(int selectedGenomes,
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
        int chosenone = 0;
        Random rand = new Random();
        for (int i = 0; i < getNumToSelect(); i++) {
            int thisTry = rand.nextInt(genomes.size());
            if (genomes.get(thisTry).getFitness() > bestFitnessSoFar) {
                chosenone = thisTry;
                bestFitnessSoFar = genomes.get(thisTry).getFitness();
            }
        }
        return genomes.get(chosenone);
    }
}

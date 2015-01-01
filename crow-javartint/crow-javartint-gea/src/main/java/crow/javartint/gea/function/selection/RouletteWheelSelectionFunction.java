package crow.javartint.gea.function.selection;

import crow.javartint.gea.function.scaling.AbstractScalingMethod;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Roulette wheel selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class RouletteWheelSelectionFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractSelectionFunction<T> {


    public RouletteWheelSelectionFunction(int numToSelect, AbstractScalingMethod<T> scalingMethod) {
        super(numToSelect, scalingMethod);
    }

    public RouletteWheelSelectionFunction(int numToSelect) {
        this(numToSelect, null);
    }

    @Override
    protected List<T> select(List<T> genomes) {
        double totalFitnessScore = 0.0;
        if (getScalingMethod() != null) {
            getScalingMethod().evaluate(genomes);
        }
        for (T genome : genomes) {
            totalFitnessScore += Math.abs(genome.getFitness());
        }
        Random rand = new Random();

        List<T> selected = new ArrayList<>(getNumToSelect());
        for (int i = 0; i < getNumToSelect(); i++) {
            double sumAux = 0.0;
            double randNumb = rand.nextDouble() * totalFitnessScore;
            for (T genome : genomes) {
                if ((sumAux += Math.abs(genome.getFitness())) >= randNumb) {
                    selected.add(i, genome);
                    break;
                }
            }
        }
        return selected;
    }
}

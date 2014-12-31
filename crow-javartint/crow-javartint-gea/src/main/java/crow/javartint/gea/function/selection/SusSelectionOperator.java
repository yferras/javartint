package crow.javartint.gea.function.selection;

import crow.javartint.gea.function.scaling.AbstractScalingMethod;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.*;

/**
 * Stochastic universal sampling selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class SusSelectionOperator<T extends Genome<? extends Gene<?>>>
        extends AbstractSelectionOperator<T> {

    public SusSelectionOperator(int numToSelect, AbstractScalingMethod<T> scalingMethod) {
        super(numToSelect, scalingMethod);
    }

    public SusSelectionOperator(int numToSelect) {
        this(numToSelect, null);
    }

    public SusSelectionOperator() {
        this(2);
    }


    @Override
    protected List<T> select(List<T> genomes) {
        if (getScalingMethod() != null) {
            getScalingMethod().evaluate(genomes);
        }
        Collections.sort(genomes, new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                double diff = o1.getFitness() - o2.getFitness();
                return (diff == 0) ? 0 : (diff > 0) ? -1 : 1;
            }
        });
        double worstFitness = genomes.get(genomes.size() - 1).getFitness();
        if (worstFitness < 0) {
            double absValue = Math.abs(worstFitness);
            for (Genome genome : genomes) {
                genome.setFitness(genome.getFitness() + absValue);
            }
        }
        double sum = 0.0;
        for (T genome : genomes) {
            sum += genome.getFitness();
        }
        for (T genome : genomes) {
            genome.setFitness(genome.getFitness() / sum);
        }
        double inc = 1.0 / (double)getNumToSelect();
        Random rand = new Random();
        double mark = rand.nextDouble() * inc;
        sum = 0.0;
        List<T> selected = new ArrayList<>(getNumToSelect());
        Collections.shuffle(genomes);
        for (T genome : genomes) {
            sum += genome.getFitness();
            if (sum > mark) {
                selected.add(genome);
                if (selected.size() == getNumToSelect()) {
                    break;
                }
                mark += inc;
            }
        }
        return selected;
    }
}

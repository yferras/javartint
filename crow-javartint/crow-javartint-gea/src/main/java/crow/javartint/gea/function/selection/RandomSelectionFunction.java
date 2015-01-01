package crow.javartint.gea.function.selection;

import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Random selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class RandomSelectionFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractSelectionFunction<T> {

    private Random random = new Random();

    /**
     * Constructor that initializes this instance.
     *
     * @param numToSelect number of elements to select
     */
    public RandomSelectionFunction(int numToSelect) {
        super(numToSelect);
    }

    /**
     * Constructor that initializes this instance.
     */
    public RandomSelectionFunction() {
        super(2);
    }

    /**
     * Selects genomes randomly.
     *
     * @param genomes Source to select the number of desired genomes.
     * @return selected genomes
     */
    @Override
    protected List<T> select(List<T> genomes) {
        List<T> list = new ArrayList<>(getNumToSelect());
        int n = genomes.size();
        while (list.size() < getNumToSelect()){
            list.add(genomes.get(random.nextInt(n)));
        }
        return list;
    }
}

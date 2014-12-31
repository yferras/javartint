package crow.javartint.gea.function.selection;

import crow.javartint.core.util.Optimize;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;
import crow.javartint.gea.util.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Elitism selection function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class ElitismSelectionOperator<T extends Genome<? extends Gene<?>>>
        extends AbstractSelectionOperator<T> {

    private final Optimize optimize;

    /**
     * Constructor that initializes this instance.
     *
     * @param numToSelect number of elements to select
     * @param optimize    the optimization way
     */
    public ElitismSelectionOperator(int numToSelect, Optimize optimize) {
        super(numToSelect);
        this.optimize = optimize;
    }


    /**
     * Gets the optimization way.
     *
     * @return optimization way value.
     */
    public Optimize getOptimize() {
        return optimize;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected List<T> select(List<T> genomes) {
        List<T> list = new ArrayList<>(genomes);
        Collections.sort(list, Helper.getComparator(getOptimize()));
        if (optimize == Optimize.MIN) return list.subList(0, getNumToSelect());
        else return list.subList(list.size() - getNumToSelect(), list.size());
    }
}

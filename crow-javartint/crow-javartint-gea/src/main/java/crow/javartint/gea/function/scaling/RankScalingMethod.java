package crow.javartint.gea.function.scaling;

import crow.javartint.core.util.Optimize;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;
import crow.javartint.gea.util.Helper;

import java.util.Collections;
import java.util.List;

/**
 * Rank scaling method function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public final class RankScalingMethod<T extends Genome<? extends Gene<?>>>
        extends AbstractScalingMethod<T>  {

    /**
     * Constructor that initializes this instance.
     *
     * @param optimize optimization way
     */
    public RankScalingMethod(Optimize optimize) {
        super(optimize);
    }

    @Override
    protected void scale(List<T> genomes) {
        Collections.sort(genomes, Helper.getComparator(getOptimize()));
        for (int i = 0; i < genomes.size(); i++) {
            genomes.get(i).setFitness(i + 1);
        }
    }
}

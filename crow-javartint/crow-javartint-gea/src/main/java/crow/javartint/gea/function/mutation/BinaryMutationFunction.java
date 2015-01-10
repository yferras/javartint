package crow.javartint.gea.function.mutation;

import crow.javartint.gea.gene.IntegerArrayGene;
import crow.javartint.gea.genome.Genome;

/**
 * Class that represents an binary mutation function.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class BinaryMutationFunction<T extends Genome<IntegerArrayGene>>
        extends AbstractMutationFunction<T> {

    @Override
    protected T mutate(T subject) {
        boolean muted = false;
        while (!muted) {
            for (IntegerArrayGene gene : subject) {
                for (int i = 0; i < gene.length(); i++) {
                    if (getRandomGenerator().nextDouble() < getProbability()){
                        int val = gene.getAllele(i);
                        gene.setAllele(i, val == 0 ? 1 : 0);
                        muted = true;
                    }
                }
            }
        }
        return subject;
    }
}

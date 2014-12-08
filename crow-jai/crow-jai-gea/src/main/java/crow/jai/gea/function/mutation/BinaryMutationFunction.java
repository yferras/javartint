package crow.jai.gea.function.mutation;

import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.Genome;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an binary mutation function.
 *
 * @param <T> Any derived class from {@link crow.jai.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class BinaryMutationFunction<T extends Genome<? extends Gene<Integer>>>
        extends AbstractMutationFunction<T> {

    @Override
    protected void mutate(T subject) {
        int n = subject.getNumberOfGenes(); // Gets total of genes
        int mutate = getRandomGenerator().nextInt(n); // generate number of
        // genes to mutate
        while (mutate == 0) {
            // generate different of zero
            mutate = getRandomGenerator().nextInt(n);
        }
        List<Integer> old = new ArrayList<>(mutate);
        for (int i = 0; i < mutate; i++) {
            int index = getRandomGenerator().nextInt(n);
            while (old.contains(index)) {
                index = getRandomGenerator().nextInt(n);
            }
            final Gene<Integer> gene = subject.getGene(index);
            gene.setData(gene.getData() == 0 ? 1 : 0);
            old.add(index);
        }
    }
}

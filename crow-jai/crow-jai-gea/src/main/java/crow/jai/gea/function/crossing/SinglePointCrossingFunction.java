package crow.jai.gea.function.crossing;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.AbstractGenome;
import crow.jai.gea.genome.Genome;

/**
 * Specific crossing function, that performs the crossing process in a single
 * random point.
 *
 * <p>
 * The example shows two genomes, each one has six genes, and the raised random
 * position is 2.<br />
 * <code>
 * [GA0,GA1,GA2,GA3,GA4,GA5] //Parent A<br />
 * [GB0,GB1,GB2,GB3,GB4,GB5] //Parent B<br />
 * [   ,   ,___,___,___,___] //Underline represents the position that will be exchanged<br />
 * [GA0,GA1,GB2,GB3,GB4,GB5] //Child A after crossing<br />
 * [GB0,GB1,GA2,GA3,GA4,GA5] //Child B after crossing<br />
 * </code>
 * </p>
 *
 * @param <T> any derived type from {@link crow.jai.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public class SinglePointCrossingFunction<T extends Genome<? extends Gene<?>>>
        extends AbstractCrossingFunction<T> {

    public SinglePointCrossingFunction(double probability, RandomGenerator randomGenerator) {
        super(probability, randomGenerator);
    }

    public SinglePointCrossingFunction(double probability) {
        super(probability);
    }

    public SinglePointCrossingFunction() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T[] crossingProcess(T parent1, T parent2) throws CloneNotSupportedException {
        int numberOfGenes = parent1.getNumberOfGenes();
        Genome[] offspring = new Genome[2];
        offspring[0] = ((AbstractGenome)parent1).clone();
        offspring[1] = ((AbstractGenome)parent2).clone();
        int position = getRandomGenerator().nextInt(numberOfGenes);
        for (int i = 0; i < position; i++) {
            Gene aux = offspring[0].getGene(i);
            offspring[0].setGene(i, offspring[1].getGene(i));
            offspring[1].setGene(i, aux);
        }
        return (T[]) offspring;
    }
}

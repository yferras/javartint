package crow.jai.gea.function.crossing;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.Gene;
import crow.jai.gea.genome.DefaultGenome;
import crow.jai.gea.genome.Genome;

/**
 * Specific crossing function, that performs the crossing process in a single
 * random point.
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

    /**
     * {@inheritDoc }
     * <p>
     * Exchanges the genes through random position.
     * </p>
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
     * @param params {@inheritDoc }
     * @return {@inheritDoc }
     * @throws IllegalArgumentException if {@code params} is null
     *                                  or {@code params.length} is less than 2.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T[] evaluate(T... params) {
        if (params == null) {
            throw new IllegalArgumentException("'params' can't be null.");
        }
        if (params.length < 2) {
            throw new IllegalArgumentException("'params' must contain at less two elements");
        }
        int numberOfGenes = params[0].getNumberOfGenes();
        Genome[] offspring = new DefaultGenome[2];
        try {
            offspring[0] = ((DefaultGenome) params[0]).clone();
            offspring[1] = ((DefaultGenome) params[1]).clone();
            int position = getRandomGenerator().nextInt(numberOfGenes);
            if (getRandomGenerator().nextDouble() > getCrossingProbability() ||
                    params[0].equals(params[1]) ||
                    position == 0) {
                return (T[]) offspring;
            }
            for (int i = 0; i < position; i++) {
                Gene aux = offspring[0].getGene(i);
                offspring[0].setGene(i, offspring[1].getGene(i));
                offspring[1].setGene(i, aux);
            }
            return (T[]) offspring;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

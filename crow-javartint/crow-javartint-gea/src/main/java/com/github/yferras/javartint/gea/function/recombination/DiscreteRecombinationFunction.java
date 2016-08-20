package com.github.yferras.javartint.gea.function.recombination;

import java.util.Random;

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;

/**
 * <p>
 * Specific recombination function, that performs an exchange of variable values between the individuals.
 * For each position the parent who contributes its variable to the offspring is chosen randomly with equal probability.
 * This function can be applied to any type of genome.
 * </p>
 * <p>
 * Example shows two genomes, each one has six genes.<br />
 * <code>
 * [GA0,GA1,GA2,GA3,GA4,GA5] // Parent A<br />
 * [GB0,GB1,GB2,GB3,GB4,GB5] // Parent B<br />
 * [ A , B , B , B , A , A ] // first random contribution<br />
 * [ A , A , A , B , B , A ] // second random contribution<br />
 * [GA0,GB1,GB2,GB3,GA4,GA5] // Child A after recombination process<br />
 * [GA0,GB1,GA2,GB3,GB4,GA5] // Child B after recombination process<br />
 * </code>
 * </p>
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class DiscreteRecombinationFunction<T extends Genome<? extends Chromosome<? extends Gene<?>>>>
    extends AbstractRecombinationFunction<T> {

    private static final int MAX_INDEX = 2;

    /**
     * <p>Constructor for DiscreteRecombinationFunction.</p>
     *
     * @param probability a double.
     * @param random      a {@link java.util.Random} object.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public DiscreteRecombinationFunction(double probability, Random random)  {
        super(probability, random);
    }

    /**
     * <p>Constructor for DiscreteRecombinationFunction.</p>
     *
     * @param probability a double.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public DiscreteRecombinationFunction(double probability)  {
        super(probability);
    }

    /**
     * <p>Constructor for DiscreteRecombinationFunction.</p>
     */
    public DiscreteRecombinationFunction() {
        super();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
        Genome[] offspring = new Genome[]{
            ((Genome) parent1).clone(),
            ((Genome) parent2).clone()
        };
        int numberOfChromosomes = parent1.size();
        for (int i = 0; i < numberOfChromosomes; i++) {
            int numberOfGenes = offspring[0].getChromosome(i).size();
            for (int j = 0; j < numberOfGenes; j++) {
                Gene<?> aux0 = offspring[getRandom().nextInt(MAX_INDEX)].getChromosome(i).getGene(j);
                Gene<?> aux1 = offspring[getRandom().nextInt(MAX_INDEX)].getChromosome(i).getGene(j);
                offspring[0].getChromosome(i).setGene(j, aux0.clone());
                offspring[1].getChromosome(i).setGene(j, aux1.clone());
            }
        }
        return (T[]) offspring;
    }
}

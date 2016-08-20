package com.github.yferras.javartint.gea.function.recombination.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.yferras.javartint.gea.function.recombination.AbstractRecombinationFunction;
import com.github.yferras.javartint.gea.gene.DefaultGene;
import com.github.yferras.javartint.gea.genome.TspGenome;

/**
 * <p/>
 * Specific recombination function in TSP.
 * <p/>
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.TspGenome}
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class OrderBasedRecombinationFunction<T extends TspGenome>
    extends AbstractRecombinationFunction<T> {


    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of recombination
     * @param random      random instance
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public OrderBasedRecombinationFunction(double probability, Random random)  {
        super(probability, random);
    }

    /**
     * Constructor, initializes instances with probability of recombination
     * specified by {@code probability} parameter and random is an
     * instance of {@link java.util.Random}.
     *
     * @param probability probability of recombination
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public OrderBasedRecombinationFunction(double probability)  {
        this(probability, new Random());
    }

    /**
     * Default constructor, initializes instances with probability of recombination
     * equals to {@code .75} and random generator is an instance of {@link java.util.Random}.
     */
    public OrderBasedRecombinationFunction() {
        super();
    }


    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    protected T[] recombine(T parent1, T parent2)
        throws CloneNotSupportedException {
        TspGenome[] offspring = new TspGenome[]{
            parent1.clone(),
            parent2.clone()
        };

        List<DefaultGene<Integer>>[] genes = new List[]{
            new ArrayList<>(),
            new ArrayList<>()
        };

        int n = parent1.getChromosome().size();
        int pos = getRandom().nextInt(n - 1);
        while (pos < n) {
            genes[0].add(offspring[0].getChromosome().getGene(pos));
            genes[1].add(offspring[1].getChromosome().getGene(pos));
            pos += getRandom().nextInt(n - pos) + 1;
        }

        int auxPos0 = 0;
        int auxPos1 = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < genes[0].size(); k++) {
                if (offspring[1].getChromosome().getGene(j).equals(genes[0].get(k))) {
                    offspring[1].getChromosome().setGene(j, genes[0].get(auxPos0++));
                    break;
                }
            }
            for (int k = 0; k < genes[1].size(); k++) {
                if (offspring[0].getChromosome().getGene(j).equals(genes[1].get(k))) {
                    offspring[0].getChromosome().setGene(j, genes[1].get(auxPos1++));
                    break;
                }
            }
        }
        return (T[]) offspring;
    }
}

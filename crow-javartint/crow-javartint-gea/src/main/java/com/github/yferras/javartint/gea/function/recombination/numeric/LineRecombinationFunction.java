package com.github.yferras.javartint.gea.function.recombination.numeric;

import java.util.Random;

import com.github.yferras.javartint.gea.chromosome.Chromosome;
import com.github.yferras.javartint.gea.gene.Gene;
import com.github.yferras.javartint.gea.genome.Genome;


/**
 * Specific recombination function to real valued genomes.
 *
 * @param <T> Any derived class from {@link com.github.yferras.javartint.gea.genome.Genome}<code>&lt;? extends </code>
 *            {@link com.github.yferras.javartint.gea.gene.Gene}<code>&lt;Double&gt;&gt;</code>
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class LineRecombinationFunction<T extends Genome<? extends Chromosome<? extends Gene<Double>>>>
    extends IntermediateRecombinationFunction<T> {

    private static final double A = 1.0;
    private static final double B = 2.0;

    /**
     * Constructor, initializes instances with the given parameters.
     *
     * @param probability probability of mutation
     * @param random      random instance
     * @param distance    defines the length of the extra-segment for possible offspring.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public LineRecombinationFunction(double probability, Random random, double distance)  {
        super(probability, random, distance);
    }

    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random}.
     *
     * @param probability probability of mutation
     * @param distance    defines the length of the extra-segment for possible offspring.
     * @throws com.github.yferras.javartint.core.util.ValidationException if any.
     */
    public LineRecombinationFunction(double probability, double distance)  {
        super(probability, distance);
    }


    /**
     * Constructor, initializes instances with the given parameters.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * and <code>probability = 0.75</code>.
     *
     * @param distance defines the length of the extra-segment for possible offspring.
     */
    public LineRecombinationFunction(double distance) {
        super(distance);
    }

    /**
     * Default constructor.
     * By default it uses as random generator an instance of {@link java.util.Random},
     * <code>probability = 0.75</code> and <code>distance = 0.25</code>.
     */
    public LineRecombinationFunction() {
        this(DEFAULT_DISTANCE);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    protected T[] recombine(T parent1, T parent2) throws CloneNotSupportedException {
        int numberOfGenes = parent1.size();
        Genome<? extends Chromosome<? extends Gene<Double>>>[] offspring = new Genome[]{
            ((Genome) parent1).clone(),
            ((Genome) parent2).clone()
        };
        double a = getRandom().nextDouble() * (A + B * getDistance()) - getDistance();
        Chromosome<? extends Gene<Double>> chromosome0 = offspring[0].getChromosome(0);
        Chromosome<? extends Gene<Double>> chromosome1 = offspring[1].getChromosome(0);
        for (int i = 0; i < numberOfGenes; i++) {
            double value0 = chromosome0.getGene(i).getData();
            double value1 = chromosome1.getGene(i).getData();
            double newValue0 = value0 * a + value1 * (1.0 - a);
            double newValue1 = value1 * a + value0 * (1.0 - a);
            chromosome0.getGene(i).setData(newValue0);
            chromosome1.getGene(i).setData(newValue1);
        }
        return (T[]) offspring;
    }
}

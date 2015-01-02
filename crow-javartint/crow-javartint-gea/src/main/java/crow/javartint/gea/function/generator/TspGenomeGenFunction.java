package crow.javartint.gea.function.generator;

import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.gene.IntegerArrayGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.*;

/**
 *
 */
public class TspGenomeGenFunction
        extends AbstractGenomeGeneratorFunction<DefaultGenome<DefaultGene<Integer>>> {

    public TspGenomeGenFunction(int numberOfGenes) {
        super(numberOfGenes, 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected DefaultGenome<DefaultGene<Integer>> generate(int[] lengthsOfGenes) {
        List<DefaultGene<Integer>> cities = new ArrayList<>(lengthsOfGenes.length);
        for (int i = 0; i < lengthsOfGenes.length; i++) {
            cities.add(new DefaultGene<>(i));
        }
        Collections.shuffle(cities);
        DefaultGenome<DefaultGene<Integer>> genome = new DefaultGenome<>();
        genome.setChromosome(cities.toArray(new DefaultGene[lengthsOfGenes.length]));
        return genome;
    }

}

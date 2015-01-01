package crow.javartint.gea.function.generator;

import crow.javartint.gea.gene.IntegerArrayGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Random;

/**
 *
 */
public class BinaryGenomeGenFunction
        extends AbstractGenomeGeneratorFunction<DefaultGenome<IntegerArrayGene>> {

    public BinaryGenomeGenFunction(int numberOfGenes, int lengthOfGene) {
        super(numberOfGenes, lengthOfGene);
    }

    public BinaryGenomeGenFunction(int[] lengthsOfGenes) {
        super(lengthsOfGenes);
    }

    @Override
    protected DefaultGenome<IntegerArrayGene> generate(int[] lengthsOfGenes) {
        Random random = new Random();
        IntegerArrayGene[] genes = new IntegerArrayGene[lengthsOfGenes.length];
        for (int i = 0; i < lengthsOfGenes.length; i++) {
            Integer[] data = new Integer[lengthsOfGenes[i]];
            for (int j = 0; j < data.length; j++) {
                data[j] = random.nextInt(2);
            }
            genes[i] = new IntegerArrayGene(data);
        }
        DefaultGenome<IntegerArrayGene> genome = new DefaultGenome<>();
        genome.setChromosome(genes);
        return genome;
    }

}

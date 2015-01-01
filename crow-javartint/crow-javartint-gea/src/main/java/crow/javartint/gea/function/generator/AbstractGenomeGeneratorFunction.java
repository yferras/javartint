package crow.javartint.gea.function.generator;


import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * This class implements the interface {@link crow.javartint.gea.function.generator.GeneratorFunction}.
 * This class can be derived to create a functions to generate genomes.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public abstract class AbstractGenomeGeneratorFunction<T extends Genome<? extends Gene<?>>>
        implements GeneratorFunction<T> {

    private final int[] lengthsOfGenes;

    /**
     * Initializes the number of genes and the length of each gene.
     *
     * @param numberOfGenes the number of genes.
     * @param lengthOfGene  the length of each gene.
     */
    public AbstractGenomeGeneratorFunction(int numberOfGenes, int lengthOfGene) {
        this.lengthsOfGenes = new int[numberOfGenes];
        for (int i = 0; i < lengthsOfGenes.length; i++) {
            lengthsOfGenes[i] = lengthOfGene;
        }
    }

    /**
     * Initializes the lengths of genes. The {@code lengthsOfGenes.length}
     * if the number of genes and the value in each position is the size of each gene.
     *
     * @param lengthsOfGenes array that contains the length of genes.
     */
    public AbstractGenomeGeneratorFunction(int[] lengthsOfGenes) {
        validate(lengthsOfGenes);
        this.lengthsOfGenes = lengthsOfGenes;
    }

    /**
     * Validates the input params.
     *
     * @param params params to validate.
     * @throws java.lang.IllegalArgumentException if argument is {@code null}
     */
    protected void validate(int[] params) {
        if (params == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method must be implemented to generate a genome.
     *
     * @param lengthsOfGenes the array that contains the length of each gene.
     * @return an instance of {@link crow.javartint.gea.genome.Genome}
     */
    protected abstract T generate(final int[] lengthsOfGenes);

    @Override
    public T evaluate(Void... params) {
        return generate(lengthsOfGenes);
    }

}

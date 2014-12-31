package crow.javartint.gea;

import crow.javartint.core.util.RandomGenerator;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class GenomeConstants {
    public static final RandomGenerator RANDOM_GENERATOR_1 = new
            RandomGenerator() {
                @Override
                public int nextInt(int n) {
                    return n / 2 - 1;
                }

                @Override
                public double nextDouble() {
                    return .5;
                }
            };

    public static final RandomGenerator RANDOM_GENERATOR_2 = new
            RandomGenerator() {
                private int aux = 0;

                @Override
                public int nextInt(int n) {
                    if (aux == 0) {
                        aux = 1;
                        return n / 3;
                    } else {
                        aux = 0;
                        return 2 * n / 3;
                    }
                }

                @Override
                public double nextDouble() {
                    return .5;
                }
            };

    /**
     * Generates alternates constant probabilities
     */
    public static final RandomGenerator RANDOM_GENERATOR_3 = new
            RandomGenerator() {
                private int aux = -1;
                private Integer n;

                /**
                 * In this case the method is tricked to set a
                 * different behaviour from the original one
                 * @param n the bound on the random number to be returned.
                 *          Must be positive.
                 * @return 0
                 */
                @Override
                public int nextInt(int n) {
                    if (this.n == null) {
                        this.n = n;
                    }
                    return 0;
                }

                /**
                 * Generates alternates constant probabilities alternates
                 * between 0.5 and 0.95 values, begins by 0.5
                 * @return the probability
                 */
                @Override
                public double nextDouble() {
                    if (aux > n) {
                        aux = -1;
                        n = null;
                    } else {
                        aux++;
                    }
                    return (aux % 2 == 0) ? .5 : .95;
                }

            };

    /**
     * Used in BinaryMutationFunctionIT
     */
    public static final RandomGenerator RANDOM_GENERATOR_4 = new
            RandomGenerator() {

                private int index = 0;
                private final int[] pos = { 0, 4, 1, 1, 3, 4, 9 };

                @Override
                public int nextInt(int n) {
                    if (index >= pos.length)
                        index = 0;
                    return pos[index++];
                }

                @Override
                public double nextDouble() {
                    return 0.025;
                }
            };

    public static final DefaultGenome<DefaultGene<Integer>> GENOME_1 =
            new DefaultGenome<>();
    public static final DefaultGenome<DefaultGene<Integer>> GENOME_2 =
            new DefaultGenome<>();
    public static final DefaultGenome[] GENOMES =
            new DefaultGenome[2];
    public static final int CHROMOSOME_SIZE = 11;

    static {

        for (int i = 0; i < CHROMOSOME_SIZE; i++) {
            GENOME_1.addGene(new DefaultGene<>(i));
            GENOME_2.addGene(new DefaultGene<>(CHROMOSOME_SIZE - i));
        }
        GENOMES[0] = GENOME_1;
        GENOMES[1] = GENOME_2;
    }
}

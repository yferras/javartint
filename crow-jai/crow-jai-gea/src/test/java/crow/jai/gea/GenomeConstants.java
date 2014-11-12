package crow.jai.gea;

import crow.jai.core.util.RandomGenerator;
import crow.jai.gea.gene.DefaultGene;
import crow.jai.gea.genome.DefaultGenome;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
 */
public class GenomeConstants {
    public static final RandomGenerator RANDOM_GENERATOR = new
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

    public static final DefaultGenome<DefaultGene<Integer>> GENOME_1 =
            new DefaultGenome<>();
    public static final DefaultGenome<DefaultGene<Integer>> GENOME_2 =
            new DefaultGenome<>();
    public static final DefaultGenome<DefaultGene<Integer>>[] GENOMES =
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

package crow.javartint.gea.function.decoder;

import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

/**
 * This class implements the interface {@link crow.javartint.gea.function.decoder.DecoderFunction}.
 * This class can be derived to create a functions to decode genomes.
 *
 * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public abstract class AbstractDecoderFunction<T extends Genome<? extends Gene<?>>> implements DecoderFunction<T> {

    /**
     * Validates the input params.
     *
     * @param genome genome to validate.
     * @throws java.lang.IllegalArgumentException if argument is {@code null}
     */
    protected void validate(T genome) {
        if (genome == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method must be implemented to decode the genome.
     *
     * @param genome genome to decode
     * @return decoded value
     */
    protected abstract Double decode(T genome);

    /**
     * This method validate and performs the decode process.
     *
     * @param genome see {@link #decode(crow.javartint.gea.genome.Genome)}
     * @return see {@link #decode(crow.javartint.gea.genome.Genome)}
     */
    @Override
    public Double evaluate(T genome) {
        validate(genome);
        return decode(genome);
    }
}

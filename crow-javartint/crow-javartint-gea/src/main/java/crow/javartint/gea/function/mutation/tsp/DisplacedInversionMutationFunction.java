package crow.javartint.gea.function.mutation.tsp;

import crow.javartint.core.util.RandomGenerator;
import crow.javartint.core.util.Range;
import crow.javartint.gea.function.mutation.AbstractMutationFunction;
import crow.javartint.gea.gene.DefaultGene;
import crow.javartint.gea.genome.DefaultGenome;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *     Specific mutation function in TSP.
 * </p>
 * <p>
 *     Takes the original genome and generates a random section based on genome's size. After that, the genes inside
 *     this section are stored in a list and removed from the source. The genes list reverses the order of their
 *     elements, then is reinserted on a random position in the source.
 * </p>
 * <p>
 *     Example, given the following setting: <code>minSpanSize = 4</code> genome:<br/>
 * </p>
 * <pre>
 *     TSPGenome:
 *     [ 8, 5, 2, 1, 6, 3, 7, 0, 9, 4 ]
 *     Generating section:
 *     start = rand(10 - 4), start = 3
 *     end = 3 + 4 = 7
 *     [ 8, 5, 2,(1, 6, 3, 7), 0, 9, 4 ]
 *     section = (1, 6, 3, 7)
 *     Removing section from source:
 *     [ 8, 5, 2, 0, 9, 4 ]
 *     Reversing the order of section:
 *     section = (7, 3, 6, 1)
 *     Generating random position to insert section:
 *     pos = rand(6), pos = 4
 *     [ 8, 5, 2, 0, (7, 3, 6, 1), 9, 4 ]
 *     Result:
 *     [ 8, 5, 2, 0, 7, 3, 6, 1, 9, 4 ]
 * </pre>
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public class DisplacedInversionMutationFunction extends AbstractMutationFunction<DefaultGenome<DefaultGene<Integer>>> {


    private int minSpanSize;

    public DisplacedInversionMutationFunction(double mutationRate) {
        this(mutationRate, 2);
    }

    public DisplacedInversionMutationFunction(double mutationRate,
                                              int minSpanSize) {
        super(mutationRate);
        this.minSpanSize = minSpanSize;
    }

    public int getMinSpanSize() {
        return minSpanSize;
    }

    @Override
    protected void validate(DefaultGenome<DefaultGene<Integer>> param) throws IllegalArgumentException {
        super.validate(param);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected DefaultGenome<DefaultGene<Integer>> mutate(DefaultGenome<DefaultGene<Integer>> subject)
            throws CloneNotSupportedException {
        RandomGenerator randomGenerator = new RandomGenerator.SystemDefaultRandomGenerator();
        int start = randomGenerator.nextInt(subject.size() - getMinSpanSize());
        int end = start + getMinSpanSize();
        List genes = Arrays.asList(subject.getChromosome());
        List section = genes.subList(start, end);
        genes.removeAll(section);
        int curPos = randomGenerator.nextInt(genes.size());
        Collections.reverse(section);
        genes.addAll(curPos, section);
        subject.setChromosome((DefaultGene<Integer>[]) genes.toArray());
        return subject;
    }
}

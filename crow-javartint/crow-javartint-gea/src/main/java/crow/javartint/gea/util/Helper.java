package crow.javartint.gea.util;

import crow.javartint.core.util.Optimize;
import crow.javartint.gea.gene.Gene;
import crow.javartint.gea.genome.Genome;

import java.util.Comparator;

/**
 * This class serves meany useful methods.
 *
 * @author Eng. Ferrás Cecilio, Yeinier.
 * @version 0.0.1
 */
public final class Helper {
    private Helper() {
    }

    /**
     * Gets an instance of {@link java.util.Comparator} depending of the value of optimize.
     *
     * @param optimize the optimization way
     * @param <T> Any derived class from {@link crow.javartint.gea.genome.Genome}
     * @return an instance of {@link java.util.Comparator}
     */
    public static <T extends Genome<? extends Gene<?>>> Comparator<T> getComparator(final Optimize optimize) {

        switch (optimize) {
            case MAX:
                return new Comparator<T>() {

                    @Override
                    public int compare(T o1, T o2) {
                        return (int) (o1.getFitness() - o2.getFitness());
                    }
                };
            case MIN:
                return new Comparator<T>() {

                    @Override
                    public int compare(T o1, T o2) {
                        return (int) (o2.getFitness() - o1.getFitness());
                    }
                };
            default:
                throw new AssertionError();
        }

    }
}

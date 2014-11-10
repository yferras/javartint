package crow.jai.core.util;

import java.util.Random;

/**
 * This interface serves to decouple the logic of generate random numbers.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public interface RandomGenerator {

    /**
     * @see java.util.Random#nextInt(int)
     * @param n the bound on the random number to be returned.  Must be
     *        positive.
     * @return the next pseudorandom, uniformly distributed {@code int}
     *         value between {@code 0} (inclusive) and {@code n} (exclusive)
     *         from this random number generator's sequence
     */
    int nextInt(int n);

    /**
     * @see java.util.Random#nextDouble()
     * @return the next pseudorandom, uniformly distributed {@code double}
     *         value between {@code 0.0} and {@code 1.0} from this
     *         random number generator's sequence
     */
    public double nextDouble();

    /**
     * This de default random generator provided by the system.
     *
     * @author Eng. Ferrás Cecilio, Yeinier
     * @version 0.0.1
     */
    public static final class SystemDefaultRandomGenerator implements RandomGenerator {

        private Random random;

        /**
         * @see java.util.Random#Random(long)
         *
         * @param seed the initial seed
         */
        public SystemDefaultRandomGenerator(long seed) {
            this.random = new Random(seed);
        }

        /**
         * Default constructor.
         */
        public SystemDefaultRandomGenerator() {
            random = new Random();
        }

        /**
         * {@inheritDoc}
         * @param n the bound on the random number to be returned.  Must be
         *        positive.
         * @return {@inheritDoc}
         */
        @Override
        public int nextInt(int n) {
            return random.nextInt(n);
        }

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         */
        @Override
        public double nextDouble() {
            return random.nextDouble();
        }
    }
}

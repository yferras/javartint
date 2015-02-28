package crow.javartint.gea;

/*
 * #%L
 * Crow JavArtInt GEA
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import crow.javartint.core.ErrorBasedAlgorithm;
import crow.javartint.core.constraint.ConstraintType;
import crow.javartint.core.constraint.MaxIterationsConstraint;
import crow.javartint.core.constraint.MinErrorConstraint;
import crow.javartint.core.util.AlgorithmEvent;
import crow.javartint.core.util.ExecutionEndListener;
import crow.javartint.core.util.Optimize;
import crow.javartint.core.util.SolutionChangeListener;
import crow.javartint.gea.function.crossover.SinglePointCrossoverFunction;
import crow.javartint.gea.function.decoder.DecoderFunction;
import crow.javartint.gea.function.generator.BinaryGenomeGenFunction;
import crow.javartint.gea.function.mutation.BinaryMutationFunction;
import crow.javartint.gea.function.selection.ElitismSelectionFunction;
import crow.javartint.gea.gene.IntegerArrayGene;
import crow.javartint.gea.genome.DefaultGenome;
import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;

import static org.junit.Assert.assertTrue;

public class AbstractGeneticAlgorithmIT {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAccept2() {
        final DecoderFunction<DefaultGenome<IntegerArrayGene>> genomeDecoderFunction =
                new DecoderFunction<DefaultGenome<IntegerArrayGene>>() {
                    /**
                     * Function to decode the binary genome to decimal number.
                     * y(x) = x
                     *
                     * @param params genome to decode.
                     * @return a decimal number between range (-64.00, 100.00)
                     */
                    @Override
                    public Double evaluate(DefaultGenome<IntegerArrayGene> params) {

                        int sign = params.getGene(1).getAllele(0) == 0 ? -1 : 1;
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Integer integer : params.getGene(1)) {
                            stringBuilder.append(integer);
                        }
                        int a = Integer.valueOf(stringBuilder.toString(), 2);
                        stringBuilder = new StringBuilder();
                        for (Integer integer : params.getGene(2)) {
                            stringBuilder.append(integer);
                        }
                        int b = Integer.valueOf(stringBuilder.toString(), 2);
                        return sign * (a % 100 + b % 100 / 100.0);
                    }
                };
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(genomeDecoderFunction);

        geneticAlgorithm.addConstraint(new MaxIterationsConstraint<>(ConstraintType.OPTIONAL, 1000L));
        geneticAlgorithm.addConstraint(new MinErrorConstraint<GeneticAlgorithm>(ConstraintType.OPTIONAL, .1));

        geneticAlgorithm.addSolutionChangeListener(new SolutionChangeListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void solutionUpdated(AlgorithmEvent event) {
                GeneticAlgorithm ga = (GeneticAlgorithm) event.getSource();
                String r = MessageFormat.format("{2,number,integer} ms\tGeneration:\t{0}\tSolution:\t{1}",
                        ga.getIterations(),
                        genomeDecoderFunction.evaluate((DefaultGenome<IntegerArrayGene>) event.getSolution()),
                        ga.getElapsedTime());
                System.out.println(r);
            }
        });
        geneticAlgorithm.addExecutionEndListener(new ExecutionEndListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void algorithmFinished(AlgorithmEvent event) {
                GeneticAlgorithm ga = (GeneticAlgorithm) event.getSource();
                DefaultGenome<IntegerArrayGene> genome = ga.getSolution();
                String r = MessageFormat.format("Best solution:\t{1}\nTime:\t{0,number,integer}\nIterations:\t{2}",
                        ga.getElapsedTime(),
                        genome.getFitness(),
                        ga.getIterations());
                System.out.println(r);
                assertTrue(true);
            }
        });
        Thread thread = new Thread(geneticAlgorithm);
        thread.run();
    }


    private static class GeneticAlgorithm extends AbstractGeneticAlgorithm<DefaultGenome<IntegerArrayGene>>
            implements ErrorBasedAlgorithm<DefaultGenome<IntegerArrayGene>> {
        /**
         * Initializes this class.
         *
         * @param decoder function to decode the genome
         */
        public GeneticAlgorithm(DecoderFunction<DefaultGenome<IntegerArrayGene>> decoder) {
            super(500, Optimize.MAX, decoder,
                    new BinaryGenomeGenFunction(new int[]{1, 7, 7}),
                    new SinglePointCrossoverFunction<DefaultGenome<IntegerArrayGene>>(),
                    new BinaryMutationFunction<DefaultGenome<IntegerArrayGene>>(),
                    new ElitismSelectionFunction<DefaultGenome<IntegerArrayGene>>(10, Optimize.MAX));
        }

        @Override
        public Double getCurrentError() {
            return 100.0 - getBestFitnessScore();
        }

        @Override
        public Double getThreshold() {
            return null;
        }
    }
}
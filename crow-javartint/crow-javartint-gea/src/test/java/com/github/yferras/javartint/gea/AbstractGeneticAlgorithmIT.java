package com.github.yferras.javartint.gea;

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

import com.github.yferras.javartint.gea.function.generator.BinaryGenomeGenFunction;
import com.github.yferras.javartint.gea.function.mutation.binary.BinaryMutationFunction;
import com.github.yferras.javartint.gea.function.recombination.SinglePointRecombinationFunction;
import com.github.yferras.javartint.gea.function.selection.ElitismSelectionFunction;
import com.github.yferras.javartint.gea.genome.DefaultGenome;
import com.github.yferras.javartint.core.ErrorBasedAlgorithm;
import com.github.yferras.javartint.core.constraint.ConstraintType;
import com.github.yferras.javartint.core.constraint.MaxIterationsConstraint;
import com.github.yferras.javartint.core.constraint.MinErrorConstraint;
import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.AlgorithmEvent;
import com.github.yferras.javartint.core.util.ExecutionEndListener;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.core.util.SolutionChangeListener;
import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.function.decoder.DecoderFunction;
import com.github.yferras.javartint.gea.gene.ByteArrayGene;
import com.github.yferras.javartint.gea.genome.BinaryGenome;
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
		final DecoderFunction<Double, BinaryGenome> genomeDecoderFunction =
			new DecoderFunction<Double, BinaryGenome>() {
				/**
				 * Function to decode the binary genome to decimal number.
				 * y(x) = x
				 *
				 * @param params genome to decode.
				 * @return a decimal number between range (-64.00, 100.00)
				 */
				@Override
				public Double evaluate(BinaryGenome params) {

					int sign = params.getChromosome(0).getGene(0).getAllele(0) == 0 ? -1 : 1;
					StringBuilder stringBuilder = new StringBuilder();
					for (Byte b : params.getChromosome(0).getGene(1)) {
						stringBuilder.append(b);
					}
					int a = Integer.valueOf(stringBuilder.toString(), 2);
					stringBuilder = new StringBuilder();
					for (Byte b : params.getChromosome(0).getGene(2)) {
						stringBuilder.append(b);
					}
					int b = Integer.valueOf(stringBuilder.toString(), 2);
					return sign * (a % 100 + b % 1000 / 1000.0);
				}
			};

		final Function<Double, Double> targetFunction = new Function<Double, Double>() {
			@Override
			public Double evaluate(Double x) {
				return -x * x / 100.0 + x / 10.0 + 5.0;
			}
		};

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(genomeDecoderFunction, targetFunction);

		geneticAlgorithm.addConstraint(new MaxIterationsConstraint<>(ConstraintType.OPTIONAL, 5000L));
		geneticAlgorithm.addConstraint(new MinErrorConstraint<GeneticAlgorithm>(ConstraintType.OPTIONAL, .0005));

		geneticAlgorithm.addSolutionChangeListener(new SolutionChangeListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void solutionUpdated(AlgorithmEvent event) {
				GeneticAlgorithm ga = (GeneticAlgorithm) event.getSource();
				BinaryGenome genome = ga.getSolution();
				Double decodedValue = genomeDecoderFunction.evaluate(genome);
				Double result = targetFunction.evaluate(decodedValue);
				String r = MessageFormat.format(
					"{0,number,0000}\t\t{1}\t\t\t\t{2,number,#.000}\t\t{3,number,#.0000}\t\t{4,number,#.0000}",
					ga.getElapsedTime(),
					ga.getIterations(),
					decodedValue,
					result,
					ga.getCurrentError()
				);
				System.out.println(r);
			}
		});
		geneticAlgorithm.addExecutionEndListener(new ExecutionEndListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void algorithmFinished(AlgorithmEvent event) {
				GeneticAlgorithm ga = (GeneticAlgorithm) event.getSource();
				DefaultGenome<DefaultChromosome<ByteArrayGene>> genome = ga.getSolution();
				String r = MessageFormat.format("Best solution:\t{1}\nTime:\t{0,number,integer}\nIterations:\t{2}",
					ga.getElapsedTime(),
					genome.getFitness(),
					ga.getIterations());
				System.out.println(r);
				assertTrue(true);
			}
		});
		Thread thread = new Thread(geneticAlgorithm);
		System.out.println("Time (ms)\tGeneration\t\tX\t\t\tf(x)\t\tE");
		thread.run();
	}


	private static class GeneticAlgorithm extends AbstractGeneticAlgorithm<BinaryGenome, Double>
		implements ErrorBasedAlgorithm<BinaryGenome> {
		/**
		 * Initializes this class.
		 *
		 * @param decoder function to decode the genome
		 */
		public GeneticAlgorithm(DecoderFunction<Double, BinaryGenome> decoder,
		                        Function<Double, Double> targetFunction) {
			super(50, Optimize.MAX, decoder, targetFunction,
				new BinaryGenomeGenFunction(new int[]{1, 7, 10}),
				new SinglePointRecombinationFunction<BinaryGenome>(),
				new BinaryMutationFunction<>(),
				new ElitismSelectionFunction<BinaryGenome>(2, Optimize.MAX));
		}

		@Override
		public Double getCurrentError() {
			return Math.abs(getBestFitnessScore() - 5.25);
		}

	}
}
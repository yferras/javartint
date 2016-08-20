package com.github.yferras.javartint.gea;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.MessageFormat;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

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

import com.github.yferras.javartint.core.ErrorBasedAlgorithm;
import com.github.yferras.javartint.core.constraint.ConstraintType;
import com.github.yferras.javartint.core.constraint.MaxIterationsConstraint;
import com.github.yferras.javartint.core.constraint.MinErrorConstraint;
import com.github.yferras.javartint.core.function.Function;
import com.github.yferras.javartint.core.util.AlgorithmEvent;
import com.github.yferras.javartint.core.util.ExecutionEndListener;
import com.github.yferras.javartint.core.util.Optimize;
import com.github.yferras.javartint.core.util.SolutionChangeListener;
import com.github.yferras.javartint.core.util.ValidationException;
import com.github.yferras.javartint.gea.chromosome.DefaultChromosome;
import com.github.yferras.javartint.gea.function.decoder.DecoderFunction;
import com.github.yferras.javartint.gea.function.generator.BinaryGenomeGenFunction;
import com.github.yferras.javartint.gea.function.mutation.binary.BinaryMutationFunction;
import com.github.yferras.javartint.gea.function.recombination.SinglePointRecombinationFunction;
import com.github.yferras.javartint.gea.function.selection.ElitismSelectionFunction;
import com.github.yferras.javartint.gea.gene.ByteArrayGene;
import com.github.yferras.javartint.gea.genome.BinaryGenome;
import com.github.yferras.javartint.gea.genome.DefaultGenome;

public class AbstractGeneticAlgorithmIT {

	private static class GeneticAlgorithm extends AbstractGeneticAlgorithm<BinaryGenome, Double>
			implements ErrorBasedAlgorithm<BinaryGenome> {

		public static final class Builder
				extends AbstractGeneticAlgorithm.Builder<GeneticAlgorithm, BinaryGenome, Double> {
			@Override
			protected GeneticAlgorithm buildObject() {
				return new GeneticAlgorithm(getProperties());
			}
		}

		private GeneticAlgorithm(Properties properties) {
			super(properties);
		}

		@Override
		public Double getCurrentError() {
			return Math.abs(getBestFitnessScore() - 5.25);
		}
	}

	private final BinaryGenomeGenFunction binaryGenomeGenFunction = new BinaryGenomeGenFunction(new int[] { 1, 7, 10 });

	private final BinaryMutationFunction binaryMutationFunction = new BinaryMutationFunction<>();

	private final ElitismSelectionFunction elitismSelectionFunction = new ElitismSelectionFunction<>(2, Optimize.MAX);

	private final DecoderFunction<Double, BinaryGenome> genomeDecoderFunction = new DecoderFunction<Double, BinaryGenome>() {
		/**
		 * Function to decode the binary genome to decimal number. y(x) = x
		 *
		 * @param params
		 *            genome to decode.
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

	private final SinglePointRecombinationFunction singlePointRecombinationFunction = new SinglePointRecombinationFunction<>();

	private final Function<Double, Double> targetFunction = new Function<Double, Double>() {
		@Override
		public Double evaluate(Double x) {
			return -x * x / 100.0 + x / 10.0 + 5.0;
		}
	};

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testAccept2() throws Exception {

		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		builder.setMutationFunction(binaryMutationFunction).setRecombinationFunction(singlePointRecombinationFunction)
				.setSelectionFunction(elitismSelectionFunction).setGeneratorFunction(binaryGenomeGenFunction)
				.setPopulationSize(50).setOptimize(Optimize.MAX).setTargetFunction(targetFunction)
				.setDecoder(genomeDecoderFunction);
		GeneticAlgorithm geneticAlgorithm = builder.build();

		geneticAlgorithm.addConstraint(new MaxIterationsConstraint<>(ConstraintType.OPTIONAL, 5000L));
		geneticAlgorithm.addConstraint(new MinErrorConstraint<GeneticAlgorithm>(ConstraintType.OPTIONAL, .0005));

		geneticAlgorithm.addSolutionChangeListener(new SolutionChangeListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void solutionUpdated(AlgorithmEvent event) {
				try {
					GeneticAlgorithm ga = (GeneticAlgorithm) event.getSource();
					BinaryGenome genome = ga.getSolution();
					Double decodedValue = genomeDecoderFunction.evaluate(genome);
					Double result = targetFunction.evaluate(decodedValue);
					String r = MessageFormat.format(
							"{0,number,0000}\t\t{1}\t\t\t\t{2,number,#.000}\t\t{3,number,#.0000}\t\t{4,number,#.0000}",
							ga.getElapsedTime(), ga.getIterations(), decodedValue, result, ga.getCurrentError());
					System.out.println(r);
				} catch (ValidationException e) {
					fail(e.getMessage());
				}
			}
		});
		geneticAlgorithm.addExecutionEndListener(new ExecutionEndListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void algorithmFinished(AlgorithmEvent event) {
				GeneticAlgorithm ga = (GeneticAlgorithm) event.getSource();
				DefaultGenome<DefaultChromosome<ByteArrayGene>> genome = ga.getSolution();
				String r = MessageFormat.format("Best solution:\t{1}\nTime:\t{0,number,integer}\nIterations:\t{2}",
						ga.getElapsedTime(), genome.getFitness(), ga.getIterations());
				System.out.println(r);
				assertTrue(true);
			}
		});
		Thread thread = new Thread(geneticAlgorithm);
		System.out.println("Time (ms)\tGeneration\t\tX\t\t\tf(x)\t\tE");
		thread.run();
	}

	@Test
	public void testThrowException1() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException2() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setRecombinationFunction(singlePointRecombinationFunction)
					.setSelectionFunction(elitismSelectionFunction).setGeneratorFunction(binaryGenomeGenFunction)
					.setPopulationSize(50).setOptimize(Optimize.MAX).setTargetFunction(targetFunction)
					.setDecoder(genomeDecoderFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException3() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setMutationFunction(binaryMutationFunction).setSelectionFunction(elitismSelectionFunction)
					.setGeneratorFunction(binaryGenomeGenFunction).setPopulationSize(50).setOptimize(Optimize.MAX)
					.setTargetFunction(targetFunction).setDecoder(genomeDecoderFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException4() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setMutationFunction(binaryMutationFunction)
					.setRecombinationFunction(singlePointRecombinationFunction)
					.setGeneratorFunction(binaryGenomeGenFunction).setPopulationSize(50).setOptimize(Optimize.MAX)
					.setTargetFunction(targetFunction).setDecoder(genomeDecoderFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException5() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setMutationFunction(binaryMutationFunction)
					.setRecombinationFunction(singlePointRecombinationFunction)
					.setSelectionFunction(elitismSelectionFunction).setPopulationSize(50).setOptimize(Optimize.MAX)
					.setTargetFunction(targetFunction).setDecoder(genomeDecoderFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException6() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setMutationFunction(binaryMutationFunction)
					.setRecombinationFunction(singlePointRecombinationFunction)
					.setSelectionFunction(elitismSelectionFunction).setGeneratorFunction(binaryGenomeGenFunction)
					.setOptimize(Optimize.MAX).setTargetFunction(targetFunction).setDecoder(genomeDecoderFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException7() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setMutationFunction(binaryMutationFunction)
					.setRecombinationFunction(singlePointRecombinationFunction)
					.setSelectionFunction(elitismSelectionFunction).setGeneratorFunction(binaryGenomeGenFunction)
					.setPopulationSize(50).setTargetFunction(targetFunction).setDecoder(genomeDecoderFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException8() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setMutationFunction(binaryMutationFunction)
					.setRecombinationFunction(singlePointRecombinationFunction)
					.setSelectionFunction(elitismSelectionFunction).setGeneratorFunction(binaryGenomeGenFunction)
					.setPopulationSize(50).setOptimize(Optimize.MAX).setDecoder(genomeDecoderFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}

	@Test
	public void testThrowException9() throws Exception {
		GeneticAlgorithm.Builder builder = new GeneticAlgorithm.Builder();
		try {
			builder.setMutationFunction(binaryMutationFunction)
					.setRecombinationFunction(singlePointRecombinationFunction)
					.setSelectionFunction(elitismSelectionFunction).setGeneratorFunction(binaryGenomeGenFunction)
					.setPopulationSize(50).setOptimize(Optimize.MAX).setTargetFunction(targetFunction);
			builder.build();
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				assertTrue(true);
				return;
			}
		}
		fail();
	}
}
# javartint
**Jav**a library for **Art**ificial **Int**elligence

[ES]

Este es un proyecto personal, que persigue como objetivo la creación de varias bibliotecas de clases para el desarrollo de aplicaciones que requieran el uso de IA.

[EN]

This is a personal project, which pursues as a goal the creation of various class libraries for developing applications that require the use of AI.

## Core

At the core are the classes and interfaces of high level. There are also utility classes. 

### Main concepts

#### Algorithm

*Algorithm*, is an abstraction of the concept and the basis of all the algorithms implemented in derived libraries.

#### Solution

This is the result that should return an algorithm at the end of its execution.

#### Constraint

A condition that is imposed upon the algorithm to stop or continue its execution. For example, an algorithm's execution can be restricted by the execution time, number of iterations or any other parameter.

#### Function

*Function (Math)*: a relationship or expression involving one or more variables.  

## Genetic and Evolutionary Algorithms (GEA)

### Main concepts

#### Gen

Is the basic unit that stores information.

#### Genome

Biological specialization of the solution; the information represented by this solution is somehow encoded in a set of genes.

#### Recombination

The process in which two or more genomes provide your information to be combined and get new individuals (offspring) with new features.

#### Mutation

The process by which the information of an individual (genome) is altered to modify its features.

#### Selection

The process by which certain individuals (genomes) are selected to fulfill any role.

### How to use GEA

You need first a function to optimize. In this example it will use the [Rosenbrock function](https://en.wikipedia.org/wiki/Rosenbrock_function).

RosenbrockFunction.java

```java

    package demo;
    
    import crow.javartint.core.function.Function;
    
    public class RosenbrockFunction implements Function<Double, Double[]>{
    
        private static final double A = 1.0;
        private static final double B = 100.0;
        
        @Override
        public Double evaluate(Double... params) {
            double sum = 0.0;
            for (int i = 0; i < params.length - 1; i++) {
                double x = params[i];
                double y = params[i + 1];
                sum += (Math.pow(A - x, 2.0) + B * Math.pow(y - x * x, 2.0));
            }
            return sum;
        }
    }

```

After that, you need some points to analyze:

 * Type of genome (Binary or Real).
    * How to structure the genome.
    * How to decode the genome (if the genome is binary).
 * Appropriate functions (Selection, Recombination and Mutation functions).
 * Condition or conditions to stop the algorithm.
 * Population size.
 
The Rosenbrock function, it can be evaluated with a n-elements vector, each element belongs to the real-numbers domain.

This example it'll use a binary genome (BinaryGenome). But, how to represents a real value with binary genome? 
A real number can be separated in parts: integer part (a), decimal part (b) and its sign (c). 
The decimal part and the sign are also integer numbers. See next example:
    
    Real number is R = -3.1416
    
    Decomposed number is: a = 3, b = 1416 and c = -1
    
    Constructing real number again: R = c * ( a + b / 10000 ) = -3.1416

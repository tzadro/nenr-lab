package hr.fer.tzadro.nenr.lab7;

import java.util.Random;
import java.util.stream.IntStream;

public class CrossoverOperators {

    public static Individual randomCrossover(Individual parent1, Individual parent2) {
        Random random = new Random();
        if (random.nextDouble() < 1./3) {
            return meanCrossover(parent1, parent2);
        } else if (random.nextDouble() < 1./2) {
            return uniformCrossover(parent1, parent2);
        } else {
            return gaussianCrossover(parent1, parent2);
        }
    }

    public static Individual meanCrossover(Individual parent1, Individual parent2) {
        if (parent1.numParams != parent2.numParams)
            throw new IllegalArgumentException("Parents do not have the same number of parameters.");

        double[] params = IntStream.range(0, parent1.numParams)
                                   .mapToDouble(i -> (parent1.params[i] + parent2.params[i]) / 2)
                                   .toArray();
        return new Individual(params);
    }

    public static Individual uniformCrossover(Individual parent1, Individual parent2) {
        if (parent1.numParams != parent2.numParams)
            throw new IllegalArgumentException("Parents do not have the same number of parameters.");

        Random random = new Random();
        double[] params = IntStream.range(0, parent1.numParams)
                                   .mapToDouble(i -> (parent1.params[i] + parent2.params[i]) / 2 + random.nextDouble() * Math.abs(parent1.params[i] - parent2.params[i]) - Math.abs(parent1.params[i] - parent2.params[i]) / 2)
                                   .toArray();
        return new Individual(params);
    }

    public static Individual gaussianCrossover(Individual parent1, Individual parent2) {
        if (parent1.numParams != parent2.numParams)
            throw new IllegalArgumentException("Parents do not have the same number of parameters.");

        Random random = new Random();
        double[] params = IntStream.range(0, parent1.numParams)
                                   .mapToDouble(i -> (parent1.params[i] + parent2.params[i]) / 2 + random.nextGaussian() * Math.abs(parent1.params[i] - parent2.params[i]) / 2)
                                   .toArray();
        return new Individual(params);
    }
}

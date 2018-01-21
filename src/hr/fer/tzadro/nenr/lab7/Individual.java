package hr.fer.tzadro.nenr.lab7;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Individual implements IIndividual, Comparable<Individual> {
    public double[] params;
    public double fitness;
    public int numParams;

    public Individual(int numParams) {
        this.numParams = numParams;

        Random random = new Random();
        params = Stream.generate(random::nextGaussian)
                       .limit(numParams)
                       .mapToDouble(e -> e)
                       .toArray();
    }

    public Individual(double[] params) {
        this.params = params;
        this.numParams = params.length;
    }

    public void mutate(double mutationProbability, double sigma) {
        Random random = new Random();
        IntStream.range(0, numParams).forEach(i -> params[i] += (random.nextDouble() < mutationProbability) ? random.nextGaussian() * sigma : 0);
    }

    @Override
    public int compareTo(Individual o) {
        if (fitness > o.fitness)
            return 1;
        else if (fitness == o.fitness)
            return 0;
        else
            return -1;
    }

    @Override
    public double getFitness() {
        return fitness;
    }
}

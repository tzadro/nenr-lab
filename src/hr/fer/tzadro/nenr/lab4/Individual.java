package hr.fer.tzadro.nenr.lab4;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Individual implements Comparable<Individual> {
    private static final int numOfElements = 5;
    private static final double lowerBound = -4;
    private static final double upperBound = 4;

    private double[] gene;
    private double fitness;

    public Individual(double[] gene) {
        if (gene.length != numOfElements)
            throw new IllegalArgumentException("Gene size incorrect.");

        this.gene = gene;
    }

    public Individual() {
        this(new Random().doubles(numOfElements, lowerBound, upperBound).toArray());
    }

    public double calculateFitness(List<Measurement> measurements) {
        fitness = measurements.stream()
                              .mapToDouble(m -> Math.pow(m.f - CharacteristicFunction.valueFor(m.x, m.y, gene), 2))
                              .average()
                              .orElseThrow(() -> new IllegalArgumentException("No measurements."));

        return getFitness();
    }

    public double[] getGene() {
        return gene;
    }

    public double getFitness() {
        return fitness;
    }

    public static Individual crossover(Individual parent1, Individual parent2) {
        double[] gene1 = parent1.getGene();
        double[] gene2 = parent2.getGene();

        double[] newGene = IntStream.range(0, numOfElements)
                                    .mapToDouble(i -> (gene1[i] + gene2[i]) / 2)
                                    .toArray();
        return new Individual(newGene);
    }

    public void mutate(double mutationProbability) {
        IntStream.range(0, gene.length)
                 .forEach(i -> {
                     if (Math.random() <= mutationProbability)
                         gene[i] = lowerBound + Math.random() * (upperBound - lowerBound);
                 });
    }

    @Override
    public int compareTo(Individual o) {
        if (fitness > o.getFitness())
            return 1;
        else if (fitness == o.getFitness())
            return 0;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "(" + Arrays.stream(gene)
                           .mapToObj(e -> String.format(Locale.US, "%.2f", e))
                           .collect(Collectors.joining(", ")) + ")";
    }
}

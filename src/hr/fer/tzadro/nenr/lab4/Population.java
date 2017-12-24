package hr.fer.tzadro.nenr.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {
    private int populationSize;
    private List<Individual> individuals;
    private Individual bestIndividual;

    public Population(int populationSize) {
        this.populationSize = populationSize;

        individuals = Stream.generate(Individual::new)
                            .limit(populationSize)
                            .collect(Collectors.toList());
    }

    public Individual calculateFitness(List<Measurement> measurements) {
        bestIndividual = individuals.get(0);

        for (Individual individual : individuals) {
            individual.calculateFitness(measurements);

            if (individual.getFitness() < bestIndividual.getFitness())
                bestIndividual = individual;
        }

        return bestIndividual;
    }

    public Individual selectIndividual(boolean preserveBest) {
        List<Individual> source = new ArrayList<>(individuals);

        if (preserveBest)
            source.remove(bestIndividual);

        return source.get(new Random().nextInt(populationSize - (preserveBest ? 1 : 0)));
    }

    public List<Individual> selectIndividuals(int n, boolean preserveBest) {
        List<Individual> source = new ArrayList<>(individuals);

        if (preserveBest)
            source.remove(bestIndividual);

        Collections.shuffle(source);
        return source.stream()
                     .limit(n)
                     .collect(Collectors.toList());
    }

    public void replace(Individual oldIndividual, Individual newIndividual) {
        individuals.remove(oldIndividual);
        individuals.add(newIndividual);
    }

    public Individual getBestIndividual() {
        return bestIndividual;
    }
}

package hr.fer.tzadro.nenr.lab4;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {
    int populationSize;

    List<Individual> individuals;
    Individual bestIndividual;

    public Population(int populationSize) {
        this.populationSize = populationSize;

        individuals = Stream.generate(Individual::new).limit(populationSize).collect(Collectors.toList());
    }

    public Individual calculateFitness(List<Measurement> measurements) {
        bestIndividual = individuals.get(0);

        for (Individual individual : individuals) {
            individual.calculateFitness(measurements);

            if (individual.getFitness() > bestIndividual.getFitness())
                bestIndividual = individual;
        }

        return bestIndividual;
    }

    public Individual selectIndividual(boolean preserveBest) {
        if (preserveBest) {
            Individual selected = individuals.get(new Random().nextInt(populationSize));

            while (selected == bestIndividual) {
                selected = individuals.get(new Random().nextInt(populationSize));
            }

            return selected;
        } else {
            return individuals.get(new Random().nextInt(populationSize));
        }
    }

    public List<Individual> selectIndividuals(int n, boolean preserveBest) {
        if (preserveBest) {
            Collections.shuffle(individuals);
            List<Individual> selection = individuals.stream().limit(n).collect(Collectors.toList());

            while (selection.contains(bestIndividual)) {

            }

            return selection;
        } else {
            Collections.shuffle(individuals);
            return individuals.stream().limit(n).collect(Collectors.toList());
        }
    }

    public void replace(Individual oldIndividual, Individual newIndividual) {
        individuals.remove(oldIndividual);
        individuals.add(newIndividual);
    }

    public Individual getBestIndividual() {
        return bestIndividual;
    }
}

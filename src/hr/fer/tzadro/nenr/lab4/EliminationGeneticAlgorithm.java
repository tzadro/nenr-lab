package hr.fer.tzadro.nenr.lab4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EliminationGeneticAlgorithm implements IGeneticAlgorithm {
    private int populationSize;
    private double mutationProbability;

    public EliminationGeneticAlgorithm(int populationSize, double mutationProbability) {
        this.populationSize = populationSize;
        this.mutationProbability = mutationProbability;
    }

    @Override
    public Individual run(List<Measurement> measurements, int numOfIterations, boolean preserveBest) {
        List<Individual> selection;
        Individual selected, selectionWorstIndividual, newIndividual;

        Population population = new Population(populationSize);
        Individual bestIndividual = population.selectIndividual(false);

        for (int i = 1; i <= numOfIterations; i++) {
            bestIndividual = calculateFitness(i, measurements, population, bestIndividual);

            selection = population.selectIndividuals(3, preserveBest);
            Collections.sort(selection);
            selectionWorstIndividual = selection.get(2);
            newIndividual = Individual.crossover(selection.get(0), selection.get(1));
            population.replace(selectionWorstIndividual, newIndividual);

            selected = population.selectIndividual(preserveBest);
            selected.mutate(mutationProbability);
        }

        bestIndividual = calculateFitness(numOfIterations, measurements, population, bestIndividual);
        return bestIndividual;
    }

    private Individual calculateFitness(int generation, List<Measurement> measurements, Population population, Individual bestIndividual) {
        Individual generationBestIndividual = population.calculateFitness(measurements);

        if (generationBestIndividual.getFitness() < bestIndividual.getFitness()) {
            bestIndividual = generationBestIndividual;

            System.out.println(String.format(Locale.US, "Generation: %d, new best fitness: %.2f, with params: %s", generation, bestIndividual.getFitness(), bestIndividual.toString()));
        }

        return bestIndividual;
    }
}

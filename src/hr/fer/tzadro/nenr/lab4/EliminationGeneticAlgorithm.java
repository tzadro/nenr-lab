package hr.fer.tzadro.nenr.lab4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EliminationGeneticAlgorithm implements IGeneticAlgorithm {
    private int populationSize;
    private double mutationProbability;

    private Population population;

    public EliminationGeneticAlgorithm(int populationSize, double mutationProbability) {
        this.populationSize = populationSize;
        this.mutationProbability = mutationProbability;
    }

    @Override
    public Individual run(List<Measurement> measurements, int numOfIterations, boolean preserveBest) {
        List<Individual> selection;
        Individual generationBestIndividual, bestIndividual, selected, selectionWorstIndividual, newIndividual;

        population = new Population(populationSize);
        bestIndividual = population.selectIndividual(false);

        for (int i = 1; i <= numOfIterations; i++) {
            generationBestIndividual = population.calculateFitness(measurements);
            if (generationBestIndividual.getFitness() > bestIndividual.getFitness()) {
                bestIndividual = generationBestIndividual;

                System.out.println("Generation: " + i + ", new best fitness: " + bestIndividual.getFitness() + ", params: " + Arrays.toString(bestIndividual.getGene()));
            }

            selection = population.selectIndividuals(3, preserveBest);
            Collections.sort(selection);
            selectionWorstIndividual = selection.get(2);
            newIndividual = Individual.crossover(selection.get(0), selection.get(1));
            population.replace(selectionWorstIndividual, newIndividual);

            selected = population.selectIndividual(preserveBest);
            selected.mutate(mutationProbability);
        }

        generationBestIndividual = population.calculateFitness(measurements);
        if (generationBestIndividual.getFitness() > bestIndividual.getFitness()) {
            bestIndividual = generationBestIndividual;

            System.out.println("Generation: " + numOfIterations + ", new best fitness: " + bestIndividual.getFitness() + ", params: " + Arrays.toString(bestIndividual.getGene()));
        }

        return population.getBestIndividual();
    }
}

package hr.fer.tzadro.nenr.lab4.algorithm;

import hr.fer.tzadro.nenr.lab4.Individual;
import hr.fer.tzadro.nenr.lab4.Measurement;
import hr.fer.tzadro.nenr.lab4.Population;

import java.util.List;
import java.util.Locale;

public abstract class GeneticAlgorithm {
    protected int populationSize;
    protected double mutationProbability;

    protected GeneticAlgorithm(int populationSize, double mutationProbability) {
        this.populationSize = populationSize;
        this.mutationProbability = mutationProbability;
    }

    public abstract Individual run(List<Measurement> measurements, int numOfIterations, boolean preserveBest);

    protected Individual calculateGenerationFitness(int generation, List<Measurement> measurements, Population population, Individual bestIndividual) {
        Individual generationBestIndividual = population.calculateFitness(measurements);

        if (bestIndividual == null || generationBestIndividual.getFitness() < bestIndividual.getFitness()) {
            bestIndividual = generationBestIndividual;

            System.out.println(String.format(Locale.US, "Generation: %d, new best fitness: %.2f, with params: %s", generation, bestIndividual.getFitness(), bestIndividual.toString()));
        }

        return bestIndividual;
    }
}

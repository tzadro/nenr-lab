package hr.fer.tzadro.nenr.lab7;

import hr.fer.tzadro.nenr.lab4.selection.SelectionOperators;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GeneticAlgorithm {
    private Population population;
    private int popSize;
    private NeuralNetwork neuralNetwork;
    private Individual populationBest;

    public GeneticAlgorithm(int popSize, NeuralNetwork neuralNetwork) {
        population = new Population(popSize, neuralNetwork.getNumParams(), SelectionOperators.<Individual>randomSelectionOperator());
        this.popSize = popSize;
        this.neuralNetwork = neuralNetwork;
    }

    public double[] run(List<Example> data, int numIter, boolean verbose, double v1, double sigma1, double sigma2, double mutationProb) {
        evaluateFitness(data, 0, verbose);

        double min = Math.pow(10, -7);
        for (int i = 0; i < numIter || populationBest.fitness < min; i++) {
            List<Individual> selection = population.selectIndividuals(3);
            Collections.sort(selection);
            Individual toRemove = selection.get(2);
            Individual toAdd = CrossoverOperators.randomCrossover(selection.get(0), selection.get(1));
            population.replace(toRemove, toAdd);

            Individual toMutate = population.selectIndividual();
            double sigma = (Math.random() < v1) ? sigma1 : sigma2;
            toMutate.mutate(mutationProb, sigma);

            evaluateFitness(data, i / popSize, verbose);
        }

        return populationBest.params;
    }

    private void evaluateFitness(List<Example> data, int generation, boolean verbose) {
        population.individuals.stream()
                              .forEach(individual -> individual.fitness = neuralNetwork.calcError(data, individual.params));

        Individual generationBest = population.individuals.stream()
                                                          .min(Individual::compareTo)
                                                          .orElseThrow(() -> new IllegalArgumentException("Problem finding generation best individual."));

        if (populationBest == null || generationBest.fitness < populationBest.fitness) {
            populationBest = generationBest;
            if (verbose)
                System.out.println(String.format(Locale.US, "Generation: %d, new best fitness: %.4f", generation, populationBest.fitness));
        }
    }
}

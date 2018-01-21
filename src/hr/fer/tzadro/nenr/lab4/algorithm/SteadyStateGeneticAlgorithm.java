package hr.fer.tzadro.nenr.lab4.algorithm;

import hr.fer.tzadro.nenr.lab4.Individual;
import hr.fer.tzadro.nenr.lab4.Measurement;
import hr.fer.tzadro.nenr.lab4.Population;
import hr.fer.tzadro.nenr.lab4.selection.SelectionOperators;

import java.util.Collections;
import java.util.List;

public class SteadyStateGeneticAlgorithm extends GeneticAlgorithm {

    public SteadyStateGeneticAlgorithm(int populationSize, double mutationProbability) {
        super(populationSize, mutationProbability);
    }

    @Override
    public Individual run(List<Measurement> measurements, int numOfIterations, boolean preserveBest) {
        Population population = new Population(populationSize, SelectionOperators.<Individual>randomSelectionOperator());
        Individual bestIndividual = calculateGenerationFitness(0, measurements, population, null);

        for (int i = 1; i <= numOfIterations; i++) {
            List<Individual> selection = population.selectIndividuals(3, preserveBest);
            Collections.sort(selection);
            Individual selectionWorstIndividual = selection.get(2);
            Individual newIndividual = Individual.crossover(selection.get(0), selection.get(1));
            population.replace(selectionWorstIndividual, newIndividual);

            Individual selected = population.selectIndividual(preserveBest);
            selected.mutate(mutationProbability);

            bestIndividual = calculateGenerationFitness(i, measurements, population, bestIndividual);
        }

        return bestIndividual;
    }
}

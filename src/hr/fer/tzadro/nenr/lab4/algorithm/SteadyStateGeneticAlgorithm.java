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
        List<Individual> selection;
        Individual selected, selectionWorstIndividual, newIndividual;

        Population population = new Population(populationSize, SelectionOperators.randomSelectionOperator());
        Individual bestIndividual = calculateGenerationFitness(0, measurements, population, null);

        for (int i = 1; i <= numOfIterations; i++) {
            selection = population.selectIndividuals(3, preserveBest);
            Collections.sort(selection);
            selectionWorstIndividual = selection.get(2);
            newIndividual = Individual.crossover(selection.get(0), selection.get(1));
            population.replace(selectionWorstIndividual, newIndividual);

            selected = population.selectIndividual(preserveBest);
            selected.mutate(mutationProbability);

            bestIndividual = calculateGenerationFitness(i, measurements, population, bestIndividual);
        }

        return bestIndividual;
    }
}

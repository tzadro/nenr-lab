package hr.fer.tzadro.nenr.lab4.algorithm;

import hr.fer.tzadro.nenr.lab4.Individual;
import hr.fer.tzadro.nenr.lab4.Measurement;
import hr.fer.tzadro.nenr.lab4.Population;
import hr.fer.tzadro.nenr.lab4.selection.SelectionOperators;

import java.util.List;

public class GenerationalGeneticAlgorithm extends GeneticAlgorithm {

    public GenerationalGeneticAlgorithm(int populationSize, double mutationProbability) {
        super(populationSize, mutationProbability);
    }

    @Override
    public Individual run(List<Measurement> measurements, int numOfIterations, boolean preserveBest) {
        List<Individual> selection;
        Individual newIndividual;
        Population newPopulation;

        Population population = new Population(populationSize, SelectionOperators.rouletteWheelSelectionOperator());
        Individual bestIndividual = calculateGenerationFitness(0, measurements, population, null);

        for (int i = 1; i <= numOfIterations; i++) {
            newPopulation = new Population(SelectionOperators.rouletteWheelSelectionOperator());

            if (preserveBest)
                newPopulation.add(bestIndividual);

            for (int j = 0; j < populationSize - (preserveBest ? 1 : 0); j++) {
                selection = population.selectIndividuals(2, false);
                newIndividual = Individual.crossover(selection.get(0), selection.get(1));
                newIndividual.mutate(mutationProbability);
                newPopulation.add(newIndividual);
            }

            population = newPopulation;
            bestIndividual = calculateGenerationFitness(i, measurements, population, bestIndividual);
        }

        return bestIndividual;
    }
}

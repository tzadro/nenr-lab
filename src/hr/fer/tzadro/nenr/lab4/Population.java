package hr.fer.tzadro.nenr.lab4;

import hr.fer.tzadro.nenr.lab4.selection.ISelectionOperator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {
    private ISelectionOperator selector;
    private List<Individual> individuals;
    private Individual bestIndividual;

    public Population(ISelectionOperator selector) {
        this(0, selector);
    }

    public Population(int populationSize, ISelectionOperator selector) {
        this.selector = selector;
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
        return (Individual)selector.selectIndividual(individuals, preserveBest, bestIndividual);
    }

    public List<Individual> selectIndividuals(int n, boolean preserveBest) {
        return selector.selectIndividuals(individuals, n, preserveBest, bestIndividual);
    }

    public void replace(Individual oldIndividual, Individual newIndividual) {
        individuals.remove(oldIndividual);
        individuals.add(newIndividual);
    }

    public void add(Individual newIndividual) {
        individuals.add(newIndividual);
    }
}

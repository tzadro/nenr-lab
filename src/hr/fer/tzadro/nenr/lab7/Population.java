package hr.fer.tzadro.nenr.lab7;

import hr.fer.tzadro.nenr.lab4.selection.ISelectionOperator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {
    private ISelectionOperator selector;
    public List<Individual> individuals;

    public Population(int popSize, int numParams, ISelectionOperator selector) {
        this.selector = selector;
        individuals = Stream.generate(() -> new Individual(numParams))
                            .limit(popSize)
                            .collect(Collectors.toList());
    }

    public Individual selectIndividual() {
        return (Individual) selector.selectIndividual(individuals, false, null);
    }

    public List<Individual> selectIndividuals(int n) {
        return selector.selectIndividuals(individuals, n, false, null);
    }

    public void replace(Individual toRemove, Individual toAdd) {
        individuals.remove(toRemove);
        individuals.add(toAdd);
    }
}

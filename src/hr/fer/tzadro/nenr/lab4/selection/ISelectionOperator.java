package hr.fer.tzadro.nenr.lab4.selection;

import hr.fer.tzadro.nenr.lab4.Individual;

import java.util.List;

public interface ISelectionOperator {
    Individual selectIndividual(List<Individual> individuals, boolean preserveBest, Individual bestIndividual);

    List<Individual> selectIndividuals(List<Individual> individuals, int n, boolean preserveBest, Individual bestIndividual);
}

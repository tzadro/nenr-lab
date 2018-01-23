package hr.fer.tzadro.nenr.lab4.selection;

import hr.fer.tzadro.nenr.lab7.IIndividual;

import java.util.List;

public interface ISelectionOperator<T extends IIndividual> {
    T selectIndividual(List<T> individuals, boolean preserveBest, T bestIndividual);

    List<T> selectIndividuals(List<T> individuals, int n, boolean preserveBest, T bestIndividual);
}

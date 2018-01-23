package hr.fer.tzadro.nenr.lab4.selection;

import hr.fer.tzadro.nenr.lab7.IIndividual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SelectionOperators {

    public static <T extends IIndividual> ISelectionOperator randomSelectionOperator() {
        return new ISelectionOperator<T>() {

            @Override
            public T selectIndividual(List<T> individuals, boolean preserveBest, T bestIndividual) {
                List<T> source = new ArrayList<>(individuals);

                if (preserveBest)
                    source.remove(bestIndividual);

                return source.get(new Random().nextInt(individuals.size() - (preserveBest ? 1 : 0)));
            }

            @Override
            public List<T> selectIndividuals(List<T> individuals, int n, boolean preserveBest, T bestIndividual) {
                if (n > individuals.size() - (preserveBest ? 1 : 0))
                    throw new IllegalArgumentException("Population too small.");

                List<T> source = new ArrayList<>(individuals);

                if (preserveBest)
                    source.remove(bestIndividual);

                Collections.shuffle(source);
                return source.stream()
                             .limit(n)
                             .collect(Collectors.toList());
            }
        };
    }

    public static <T extends IIndividual> ISelectionOperator rouletteWheelSelectionOperator() {
        return new ISelectionOperator<T>() {

            @Override
            public T selectIndividual(List<T> individuals, boolean preserveBest, T bestIndividual) {
                List<T> source = new ArrayList<>(individuals);

                if (preserveBest)
                    source.remove(bestIndividual);

                double selection = Math.random();
                double max = source.stream()
                                   .mapToDouble(e -> e.getFitness())
                                   .max()
                                   .orElseThrow(() -> new IllegalArgumentException("Couldn't find max value."));
                double sum = source.stream()
                                   .mapToDouble(e -> max - e.getFitness())
                                   .sum();
                double top = 0;

                if (sum == 0) {
                    System.out.println("ROULETTE GOING RANDOM");
                    return source.get(new Random().nextInt(individuals.size() - (preserveBest ? 1 : 0)));
                } else {
                    for (T individual : individuals) {
                        top += (max - individual.getFitness()) / sum;

                        if (selection < top) {
                            return individual;
                        }
                    }
                    throw new IllegalArgumentException("Roulette wheel error.");
                }
            }

            @Override
            public List<T> selectIndividuals(List<T> individuals, int n, boolean preserveBest, T bestIndividual) {
                if (n > individuals.size() - (preserveBest ? 1 : 0))
                    throw new IllegalArgumentException("Population too small.");

                List<T> source = new ArrayList<>(individuals);
                List<T> result = new ArrayList<>();

                if (preserveBest)
                    source.remove(bestIndividual);

                for (int i = 0; i < n; i++) {
                    T selected = selectIndividual(individuals, preserveBest, bestIndividual);
                    result.add(selected);
                    source.remove(selected);
                }

                return result;
            }
        };
    }
}

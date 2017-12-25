package hr.fer.tzadro.nenr.lab4.selection;

import hr.fer.tzadro.nenr.lab4.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SelectionOperators {

    public static ISelectionOperator randomSelectionOperator() {
        return new ISelectionOperator() {

            @Override
            public Individual selectIndividual(List<Individual> individuals, boolean preserveBest, Individual bestIndividual) {
                List<Individual> source = new ArrayList<>(individuals);

                if (preserveBest)
                    source.remove(bestIndividual);

                return source.get(new Random().nextInt(individuals.size() - (preserveBest ? 1 : 0)));
            }

            @Override
            public List<Individual> selectIndividuals(List<Individual> individuals, int n, boolean preserveBest, Individual bestIndividual) {
                if (n > individuals.size() - (preserveBest ? 1 : 0))
                    throw new IllegalArgumentException("Population too small.");

                List<Individual> source = new ArrayList<>(individuals);

                if (preserveBest)
                    source.remove(bestIndividual);

                Collections.shuffle(source);
                return source.stream()
                             .limit(n)
                             .collect(Collectors.toList());
            }
        };
    }

    public static ISelectionOperator rouletteWheelSelectionOperator() {
        return new ISelectionOperator() {

            @Override
            public Individual selectIndividual(List<Individual> individuals, boolean preserveBest, Individual bestIndividual) {
                List<Individual> source = new ArrayList<>(individuals);
                double selection, max, sum, top;

                if (preserveBest)
                    source.remove(bestIndividual);

                selection = Math.random();
                max = source.stream()
                            .mapToDouble(e -> e.getFitness())
                            .max()
                            .orElseThrow(() -> new IllegalArgumentException("Couldn't find max value."));
                sum = source.stream()
                            .mapToDouble(e -> max - e.getFitness())
                            .sum();
                top = 0;

                for (Individual individual : individuals) {
                    top += (max - individual.getFitness()) / sum;

                    if (selection < top) {
                        return individual;
                    }
                }
                throw new IllegalArgumentException("Roulette wheel error.");
            }

            @Override
            public List<Individual> selectIndividuals(List<Individual> individuals, int n, boolean preserveBest, Individual bestIndividual) {
                if (n > individuals.size() - (preserveBest ? 1 : 0))
                    throw new IllegalArgumentException("Population too small.");

                List<Individual> source = new ArrayList<>(individuals);

                if (preserveBest)
                    source.remove(bestIndividual);

                Collections.shuffle(source);
                return source.stream()
                             .limit(n)
                             .collect(Collectors.toList());
            }
        };
    }
}

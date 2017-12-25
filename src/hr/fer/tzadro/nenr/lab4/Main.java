package hr.fer.tzadro.nenr.lab4;

import hr.fer.tzadro.nenr.lab4.algorithm.SteadyStateGeneticAlgorithm;
import hr.fer.tzadro.nenr.lab4.algorithm.GeneticAlgorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String DATASET_PATH = "./materijali/zad4-datasets/zad4-dataset1.txt";
        int POPULATION_SIZE = 200, NUM_OF_ITERATIONS = 10000;
        double MUTATION_PROBABILITY = 0.2;
        boolean PRESERVE_BEST = true;

        List<Measurement> measurements;
        try (Stream<String> stream = Files.lines(Paths.get(DATASET_PATH))) {
            measurements = stream.map(Measurement::new).collect(Collectors.toList());
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not load file.");
        }

        GeneticAlgorithm algorithm = new SteadyStateGeneticAlgorithm(POPULATION_SIZE, MUTATION_PROBABILITY);
        Individual result = algorithm.run(measurements, NUM_OF_ITERATIONS, PRESERVE_BEST);
        System.out.println(String.format(Locale.US, "Best fitness: %.2f, with params: %s", result.getFitness(), result.toString()));
    }
}

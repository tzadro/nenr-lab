package hr.fer.tzadro.nenr.lab4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Measurement> measurements;

        String DATASET_PATH = "./materijali/zad3-datasets/zad4-dataset1.txt";
        int POPULATION_SIZE = 10;
        int NUM_OF_ITERATIONS = 100;
        double MUTATION_PROBABILITY = 0.2;
        boolean PRESERVE_BEST = false;

        try (Stream<String> stream = Files.lines(Paths.get(DATASET_PATH))) {
            measurements = stream.map(Measurement::new).collect(Collectors.toList());
        } catch (IOException ioe) {
            System.out.println("Could not load file.");
            return;
        }

        IGeneticAlgorithm algorithm = new EliminationGeneticAlgorithm(POPULATION_SIZE, MUTATION_PROBABILITY);
        Individual result = algorithm.run(measurements, NUM_OF_ITERATIONS, PRESERVE_BEST);

        System.out.println("Best fitness: " + result.getFitness() + ", best params: " + Arrays.toString(result.getGene()));
    }
}
package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;
import hr.fer.tzadro.nenr.lab6.algorithm.NeuroFuzzyAlgorithm;
import hr.fer.tzadro.nenr.lab6.data.Data;
import hr.fer.tzadro.nenr.lab6.data.Example;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        String MEMBERSHIP_FUNCTIONS_OUTPUT = "./materijali/zad6-outputs/membership-functions.txt";
        String TEST_ERROR_OUTPUT = "./materijali/zad6-outputs/test-error.txt";
        String TRAIN_ERRORS_OUTPUT = "./materijali/zad6-outputs/train-errors.txt";
        int NUM_RULES = 8, NUM_ITERATIONS = 10000;
        double LEARNING_RATE = 0.015;
        IBinaryFunction tNorm = Operations.product();

        NeuroFuzzyAlgorithm algorithm = new NeuroFuzzyAlgorithm(NUM_RULES, tNorm);
        List<Example> data = Data.getTrainingData();
        List<Double> trainErrors = algorithm.train(data, NUM_ITERATIONS, LEARNING_RATE);

        writeMembershipFunctions(MEMBERSHIP_FUNCTIONS_OUTPUT, algorithm);
        writeTestError(TEST_ERROR_OUTPUT, algorithm, data);
        writeTrainErrors(TRAIN_ERRORS_OUTPUT, trainErrors, NUM_ITERATIONS);
    }

    private static void writeMembershipFunctions(String path, NeuroFuzzyAlgorithm algorithm) {
        try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            algorithm.writeMembershipFunctions(writer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write file.");
        }
    }

    private static void writeTestError(String path, NeuroFuzzyAlgorithm algorithm, List<Example> data) {
        try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            writer.println(data.stream()
                               .map(e -> Double.toString(e.x))
                               .collect(Collectors.joining(",")));
            writer.println(data.stream()
                               .map(e -> Double.toString(e.y))
                               .collect(Collectors.joining(",")));
            writer.println(data.stream()
                               .map(e -> Double.toString(algorithm.forward(e.x, e.y) - e.z))
                               .collect(Collectors.joining(",")));
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write file.");
        }
    }

    private static void writeTrainErrors(String path, List<Double> errors, int numIterations) {
        try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            writer.println(IntStream.range(0, numIterations)
                                    .mapToObj(i -> Integer.toString(i))
                                    .collect(Collectors.joining(",")));
            writer.println(errors.stream()
                                 .map(e -> Double.toString(e))
                                 .collect(Collectors.joining(",")));
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write file.");
        }
    }
}

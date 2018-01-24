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
        String OUTPUT_FOLDER = "./materijali/zad6-outputs/";
        int NUM_RULES = 8, NUM_ITERATIONS = 10000;
        double LEARNING_RATE = 0.015;
        IBinaryFunction tNorm = Operations.product();

        NeuroFuzzyAlgorithm algorithm;
        List<Example> data = Data.getTrainingData();
        List<Double> trainErrors;

        algorithm = new NeuroFuzzyAlgorithm(NUM_RULES, tNorm);
        trainErrors = algorithm.train(data, NUM_ITERATIONS, LEARNING_RATE, true);
        writeMembershipFunctions(OUTPUT_FOLDER + "batch-membership-functions.txt", algorithm);
        writeTestError(OUTPUT_FOLDER + "batch-test-error.txt", algorithm, data);
        writeTrainErrors(OUTPUT_FOLDER + "batch-train-errors.txt", trainErrors, NUM_ITERATIONS);

        algorithm = new NeuroFuzzyAlgorithm(NUM_RULES, tNorm);
        trainErrors = algorithm.train(data, NUM_ITERATIONS, LEARNING_RATE, false);
        writeMembershipFunctions(OUTPUT_FOLDER + "online-membership-functions.txt", algorithm);
        writeTestError(OUTPUT_FOLDER + "online-test-error.txt", algorithm, data);
        writeTrainErrors(OUTPUT_FOLDER + "online-train-errors.txt", trainErrors, NUM_ITERATIONS);

        double lowLearningRate = 0.001, midLearningRate = 0.02, highLearningRate = 0.2;
        trainBothFor(NUM_RULES, tNorm, data, NUM_ITERATIONS, lowLearningRate, "low", OUTPUT_FOLDER);
        trainBothFor(NUM_RULES, tNorm, data, NUM_ITERATIONS, midLearningRate, "mid", OUTPUT_FOLDER);
        trainBothFor(NUM_RULES, tNorm, data, NUM_ITERATIONS, highLearningRate, "high", OUTPUT_FOLDER);
    }

    private static void trainBothFor(int numRules, IBinaryFunction tNorm, List<Example> data, int numIterations, double learningRate, String prefix, String outputFolder) {
        NeuroFuzzyAlgorithm algorithm;
        List<Double> trainErrors;

        algorithm = new NeuroFuzzyAlgorithm(numRules, tNorm);
        trainErrors = algorithm.train(data, numIterations, learningRate, true);
        writeTrainErrors(outputFolder + prefix + "-batch-train-errors.txt", trainErrors, numIterations);

        algorithm = new NeuroFuzzyAlgorithm(numRules, tNorm);
        trainErrors = algorithm.train(data, numIterations, learningRate, false);
        writeTrainErrors(outputFolder + prefix + "-online-train-errors.txt", trainErrors, numIterations);
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

package hr.fer.tzadro.nenr.lab7;

import hr.fer.tzadro.nenr.lab5.utility.Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String datasetPath = "./materijali/zad7-datasets/zad7-dataset.txt";
        int numInputs = 2;
        int numOutputs = 3;
        List<Example> data = loadDataset(datasetPath, numInputs, numOutputs);

        int[] layers = new int[]{numInputs, 8, 4, numOutputs};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layers);

        int popSize = 10;
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(popSize, neuralNetwork);

        int numIter = 10000;
        boolean verbose = false;
        double v1 = 0.2;
        double sigma1 = 1.5;
        double sigma2 = 0.5;
        double mutationProb = 0.05;
        double[] params = geneticAlgorithm.run(data, numIter, verbose, v1, sigma1, sigma2, mutationProb);

        int correct = 0;
        int incorrect = 0;
        for (Example example : data) {
            double[] output = neuralNetwork.calcOutput(example.inputs, params);
            int y = Utility.argmax(output);
            int y_ = Utility.argmax(example.outputs);

            if (y == y_)
                correct++;
            else
                incorrect++;

            System.out.println(y + " " + y_ + " " + (y == y_ ? "true" : "false"));
        }
        System.out.println("Correct: " + correct + ", incorrect: " + incorrect);
    }

    private static List<Example> loadDataset(String path, int numInputs, int numOutputs) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.map(line -> new Example(line, numInputs, numOutputs))
                         .collect(Collectors.toList());
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not load file. Path must be: " + path);
        }
    }
}

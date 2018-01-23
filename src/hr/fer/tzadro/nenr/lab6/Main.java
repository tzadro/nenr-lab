package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;
import hr.fer.tzadro.nenr.lab6.algorithm.NeuroFuzzyAlgorithm;
import hr.fer.tzadro.nenr.lab6.data.Data;
import hr.fer.tzadro.nenr.lab6.data.Example;

import java.io.PrintWriter;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String OUTPUT_PATH = "./materijali/zad6-outputs/membership-functions.txt";
        int NUM_RULES = 8, NUM_ITERATIONS = 10000;
        double LEARNING_RATE = 0.015;
        IBinaryFunction tNorm = Operations.product();

        NeuroFuzzyAlgorithm algorithm = new NeuroFuzzyAlgorithm(NUM_RULES, tNorm);
        List<Example> data = Data.getTrainingData();
        algorithm.train(data, NUM_ITERATIONS, LEARNING_RATE);

        try (PrintWriter writer = new PrintWriter(OUTPUT_PATH, "UTF-8")) {
            algorithm.writeMembershipFunctions(writer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Could not write file.");
        }
    }
}

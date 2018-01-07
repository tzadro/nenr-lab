package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;
import hr.fer.tzadro.nenr.lab6.data.Data;
import hr.fer.tzadro.nenr.lab6.data.Example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int NUM_RULES = 8, NUM_ITERATIONS = 10000;
        double LEARNING_RATE = 0.015;
        IBinaryFunction tNorm = Operations.product();

        NeuroFuzzyAlgorithm algorithm = new NeuroFuzzyAlgorithm(NUM_RULES, tNorm);
        List<Example> data = Data.getTrainingData();
        algorithm.train(data, NUM_ITERATIONS, LEARNING_RATE);
    }
}

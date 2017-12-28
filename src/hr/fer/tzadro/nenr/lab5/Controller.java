package hr.fer.tzadro.nenr.lab5;

import hr.fer.tzadro.nenr.lab5.algorithm.NeuralNetworkAlgorithm;
import hr.fer.tzadro.nenr.lab5.data.Coordinate;
import hr.fer.tzadro.nenr.lab5.data.DataProcessor;
import hr.fer.tzadro.nenr.lab5.data.Example;

import java.util.List;

public class Controller {
    private NeuralNetworkAlgorithm algorithm;
    private DataProcessor data;

    public Controller(int M, double learningRate, int numIterations, boolean minibatch, Integer minibatchSize, int[] hiddenLayers, String datasetPath) {
        data = new DataProcessor(M);
        algorithm = new NeuralNetworkAlgorithm(M, hiddenLayers);
        List<Example> dataset = data.loadDataset(datasetPath);
        algorithm.train(dataset, learningRate, numIterations, minibatch, minibatchSize);
    }

    public void newPoint(double X, double Y) {
        data.addToPath(X, Y);
    }

    public void mouseReleased() {
        List<Coordinate> features = data.getFeatures();

        int result = algorithm.predict(features);

        System.out.println(result);

        data.clearPath();
    }
}

package hr.fer.tzadro.nenr.lab5;

import hr.fer.tzadro.nenr.lab5.algorithm.NeuralNetworkAlgorithm;
import hr.fer.tzadro.nenr.lab5.data.Coordinate;
import hr.fer.tzadro.nenr.lab5.data.DataProcessor;

import java.util.List;

public class Controller {
    private NeuralNetworkAlgorithm algorithm;
    private DataProcessor data;

    public Controller(int M, double learningRate, int[] hiddenLayers, String datasetPath) {
        data = new DataProcessor(M);
        algorithm = new NeuralNetworkAlgorithm(M, learningRate, hiddenLayers);
        algorithm.train(data.loadDataset(datasetPath));
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

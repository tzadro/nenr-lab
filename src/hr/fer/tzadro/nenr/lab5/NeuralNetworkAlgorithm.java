package hr.fer.tzadro.nenr.lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NeuralNetworkAlgorithm {
    private int M;
    private List<Layer> layers;

    public NeuralNetworkAlgorithm(int M, int[] hiddenLayers) {
        this.M = M;
        layers = new ArrayList<>();

        int out = 2 * M;
        for (int layer : hiddenLayers) {
            layers.add(new Layer(out, layer));
            out = layer;
        }
        layers.add(new Layer(hiddenLayers[hiddenLayers.length - 1], 5));
    }

    public void train(List<Example> dataset) {
        // todo: implement
    }

    // todo: too ugly
    public int predict(List<Coordinate> gesture) {
        double[][] out = new double[1][M * 2];

        List<Double> xs = gesture.stream().map(e -> e.x).collect(Collectors.toList());
        List<Double> ys = gesture.stream().map(e -> e.y).collect(Collectors.toList());
        xs.addAll(ys);

        out[0] = xs.stream().mapToDouble(e -> e).toArray();

        for (Layer layer : layers) {
            out = layer.forward(out);
        }

        int maxIndex = 5;
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < out[0].length; i++) {
            System.out.println(out[0][i]);
            if (out[0][i] > maxValue) {
                maxValue = out[0][i];
                maxIndex = i;
            }
        }

        if (maxIndex == 5)
            throw new IllegalArgumentException("Something went really wrong..");

        return maxIndex;
    }
}

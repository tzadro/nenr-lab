package hr.fer.tzadro.nenr.lab5;

import java.util.ArrayList;
import java.util.List;

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
        this.layers.add(new Layer(hiddenLayers[hiddenLayers.length - 1], 5));
    }

    public void train(List<Example> dataset) {
        // todo: implement
    }
}

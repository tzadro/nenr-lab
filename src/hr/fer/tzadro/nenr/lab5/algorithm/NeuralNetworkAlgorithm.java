package hr.fer.tzadro.nenr.lab5.algorithm;

import hr.fer.tzadro.nenr.lab5.utility.Utility;
import hr.fer.tzadro.nenr.lab5.data.Coordinate;
import hr.fer.tzadro.nenr.lab5.data.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeuralNetworkAlgorithm {
    private int M;
    private List<Layer> layers;

    public NeuralNetworkAlgorithm(int M, int[] hiddenLayers) {
        this.M = M;
        layers = new ArrayList<>();

        int out = 2 * M;
        for (int numNodes : hiddenLayers) {
            layers.add(new Layer(out, numNodes));
            out = numNodes;
        }
        layers.add(new Layer(hiddenLayers[hiddenLayers.length - 1], 5));
    }

    public void train(List<Example> dataset, double learningRate, int numIterations, boolean minibatch, Integer minibatchSize) {
        int row = dataset.size(), minibatchStart = 0;
        double[][] X = new double[row][M * 2];
        double[][] Y_ = new double[row][5];
        IntStream.range(0, row).forEach(i -> {
            Example example = dataset.get(i);

            X[i] = example.X.stream()
                            .flatMapToDouble(e -> e.toStream())
                            .toArray();
            Y_[i] = example.Yoh_.stream()
                                .mapToDouble(e -> e)
                                .toArray();
        });

        for (int i = 0; i < numIterations; i++) {
            double[][] X_batch, Y_batch;
            if (minibatch) {
                if (minibatchStart + minibatchSize > X.length)
                    minibatchStart = 0;

                X_batch = Arrays.copyOfRange(X, minibatchStart, minibatchStart + minibatchSize);
                Y_batch = Arrays.copyOfRange(Y_, minibatchStart, minibatchStart + minibatchSize);

                minibatchStart = (minibatchStart + minibatchSize);
            } else {
                X_batch = X.clone();
                Y_batch = Y_.clone();
            }

            double[][] out = X_batch;
            for (Layer layer : layers) {
                out = layer.forward(out);
            }

            double[][] error = Utility.mul(0.5, Utility.square(Utility.diff(Y_batch, out)));
            System.out.println(String.format("%.4f", Arrays.stream(error[0]).sum()) + " out: (" + Arrays.stream(out[0]).mapToObj(e -> String.format(Locale.US, "%.4f", e)).collect(Collectors.joining(",")) + ")");

            out = Utility.div(Utility.diff(out, Y_batch), out.length);
            for (int j = layers.size() - 1; j >= 0; j--) {
                out = layers.get(j).backward(out, learningRate); // todo: wrong?
            }
        }
    }

    public int predict(List<Coordinate> gesture) {
        double[][] out = new double[1][M * 2];

        out[0] = gesture.stream().flatMapToDouble(e -> e.toStream()).toArray();

        for (Layer layer : layers) {
            out = layer.forward(out);
        }
        System.out.println("out: (" + Arrays.stream(out[0]).mapToObj(e -> String.format(Locale.US, "%.4f", e)).collect(Collectors.joining(",")) + ")");

        return Utility.argmax(out[0]);
    }
}

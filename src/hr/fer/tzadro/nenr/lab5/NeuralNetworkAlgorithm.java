package hr.fer.tzadro.nenr.lab5;

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
        for (int layer : hiddenLayers) {
            layers.add(new Layer(out, layer));
            out = layer;
        }
        layers.add(new Layer(hiddenLayers[hiddenLayers.length - 1], 5));
    }

    public void train(List<Example> dataset) {
        int row = dataset.size();
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

        for (int i = 0; i < 1000; i++) {
            double[][] out = X.clone();

            for (Layer layer : layers) {
                out = layer.forward(out);
            }

            double[][] error = Utility.mul(0.5, Utility.square(Utility.diff(Y_, Utility.mul(Y_, out))));

            System.out.println("Y_: (" + Arrays.stream(Y_[0]).mapToObj(e -> String.format(Locale.US, "%.4f", e)).collect(Collectors.joining(",")) + ")");
            System.out.println("error: (" + Arrays.stream(error[0]).mapToObj(e -> String.format(Locale.US, "%.4f", e)).collect(Collectors.joining(",")) + ")");

            System.out.println("out: (" + Arrays.stream(out[0]).mapToObj(e -> String.format(Locale.US, "%.4f", e)).collect(Collectors.joining(",")) + ")");
            out = Utility.div(Utility.diff(out, Y_), out.length);
            System.out.println("out grad: (" + Arrays.stream(out[0]).mapToObj(e -> String.format(Locale.US, "%.4f", e)).collect(Collectors.joining(",")) + ")");
            for (int j = layers.size() - 1; j >= 0; j--) {
                out = layers.get(j).backward(out);
            }
        }
    }

    public int predict(List<Coordinate> gesture) {
        double[][] out = new double[1][M * 2];

        out[0] = gesture.stream().flatMapToDouble(e -> e.toStream()).toArray();

        for (Layer layer : layers) {
            out = layer.forward(out);
        }

        return maxIndex(out[0]);
    }

    public int maxIndex(double[] array) {
        int maxIndex = 5;

        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                maxIndex = i;
            }
        }

        if (maxIndex == 5)
            throw new IllegalArgumentException("Something went really wrong..");

        return maxIndex;
    }
}

package hr.fer.tzadro.nenr.lab7;

import hr.fer.tzadro.nenr.lab5.utility.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NeuralNetwork {
    private int[] layers;
    private int numNeurons;
    private int numParams;
    private int numTypeOneParams;
    private int numTypeTwoParams;
    private double[][] valNeurons;

    public NeuralNetwork(int[] layers) {
        this.layers = layers;

        numNeurons = Arrays.stream(layers)
                           .sum();
        numTypeOneParams = layers[0] * layers[1] * 2;
        numTypeTwoParams = IntStream.range(2, layers.length)
                                    .map(i -> layers[i - 1] * (layers[i] + 1))
                                    .sum();
        numParams = numTypeOneParams + numTypeTwoParams;

        valNeurons = IntStream.range(1, layers.length)
                              .mapToObj(i -> new double[layers[i]])
                              .toArray(double[][]::new);
    }

    public int getNumParams() {
        return numParams;
    }

    public double[] calcOutput(double[] inputs, double[] params) {
        double[][][] typeOneParams = IntStream.range(0, layers[1])
                                              .mapToObj(i -> new double[][]{
                                                      Arrays.copyOfRange(
                                                              params,
                                                              i * layers[0] * 2,
                                                              i * layers[0] * 2 + layers[0]
                                                      ),
                                                      Arrays.copyOfRange(
                                                              params,
                                                              i * layers[0] * 2 + layers[0],
                                                              i * layers[0] * 2 + layers[0] * 2
                                                      )
                                              }).toArray(double[][][]::new);

        valNeurons[0] = IntStream.range(0, layers[1])
                                 .mapToDouble(i -> typeOneNeuron(inputs, typeOneParams[i][0], typeOneParams[i][1]))
                                 .toArray();

        double[][][][] typeTwoParams = IntStream.range(2, layers.length)
                                                .mapToObj(i -> new double[][][]{
                                                        IntStream.range(0, layers[i - 1])
                                                                 .mapToObj(j -> Arrays.copyOfRange(
                                                                         params,
                                                                         numTypeOneParams + (i - 2) * j * layers[i],
                                                                         numTypeOneParams + (i - 2) * j * layers[i] + layers[i]
                                                                 )).toArray(double[][]::new),
                                                        new double[][]{
                                                                Arrays.copyOfRange(
                                                                        params,
                                                                        numTypeOneParams + (i - 2) * layers[i - 1] * layers[i],
                                                                        numTypeOneParams + (i - 2) * layers[i - 1] * layers[i] + layers[i]
                                                                )
                                                        }
                                                }).toArray(double[][][][]::new);

        IntStream.range(1, valNeurons.length).forEach(i -> valNeurons[i] = typeTwoNeuron(valNeurons[i - 1], typeTwoParams[i - 1][0], typeTwoParams[i - 1][1][0]));

        return valNeurons[valNeurons.length - 1];
    }

    public double calcError(List<Example> data, double[] params) {
        return data.stream()
                   .mapToDouble(
                           example -> Arrays.stream(Utility.square(Utility.diff(example.outputs, calcOutput(example.inputs, params))))
                                            .sum()
                   ).sum() / data.size();
    }

    private double typeOneNeuron(double[] x, double[] w, double[] s) {
        return 1 / (1 + Arrays.stream(Utility.div(Utility.abs(Utility.diff(x, w)), Utility.abs(s))).sum());
    }

    private double[] typeTwoNeuron(double[] x, double[][] w, double[] b) {
        return Utility.sigmoid(Utility.sum(Utility.matmul(x, w), b))[0];
    }
}

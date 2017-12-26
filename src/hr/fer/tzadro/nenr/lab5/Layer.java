package hr.fer.tzadro.nenr.lab5;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Layer {
    int size;
    double[][] weights, inputs;
    double[] biases;

    public Layer(int numIn, int size) {
        this.size = size;
        Random rand = new Random();

        // init weights between -1 and 1
        weights = new double[numIn][size];
        IntStream.range(0, weights.length)
                 .forEach(i -> IntStream.range(0, weights[i].length)
                                        .forEach(j -> weights[i][j] = rand.nextDouble() * 2 - 1));

        // init biases to 0
        biases = new double[size];
    }

    public double[][] forward(double[][] inputs) {
        this.inputs = inputs;
        double[][] result = Utility.matmul(inputs, weights);

        Arrays.stream(result)
              .forEach(row -> IntStream.range(0, size)
                                       .forEach(i -> row[i] = Utility.sigmoid(row[i] + biases[i]))
              );

        return result;
    }

    public double[][] backward(double[][] grads) {
        int n = grads.length;

        double[][] grad_weights = Utility.matmul(Utility.transpose(grads), inputs);
        double[] grad_bias = Arrays.stream(Utility.transpose(grads))
                                   .mapToDouble(row -> Arrays.stream(row)
                                                             .sum() / n
                                   ).toArray();

        weights = Utility.sum(weights, Utility.mul(-0.01, Utility.transpose(grad_weights)));
        biases = Utility.sum(biases, Utility.mul(-0.01, grad_bias));

        grads = Utility.matmul(grads, Utility.transpose(weights));
        return grads;
    }
}

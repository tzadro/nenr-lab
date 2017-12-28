package hr.fer.tzadro.nenr.lab5.algorithm;

import hr.fer.tzadro.nenr.lab5.utility.Utility;

import java.util.Arrays;

public class Layer {
    private int numNodes;
    private double[][] weights, inputsCache, outputsCache;
    private double[] biases;

    public Layer(int numIn, int numNodes) {
        this.numNodes = numNodes;

        weights = Utility.random(numIn, numNodes);
        biases = new double[numNodes];
    }

    public double[][] forward(double[][] inputs) {
        inputsCache = inputs;
        outputsCache = Utility.sigmoid(Utility.sum(Utility.matmul(inputs, weights), biases));
        return outputsCache;
    }

    public double[][] backward(double[][] grads) {
        int n = grads.length;

        grads = Utility.mul(grads, Utility.mul(outputsCache, Utility.diff(1, outputsCache)));

        double[][] grad_weights = Utility.matmul(Utility.transpose(grads), inputsCache);
        double[] grad_bias = Arrays.stream(Utility.transpose(grads))
                                   .mapToDouble(row -> Arrays.stream(row)
                                                             .sum() / n
                                   ).toArray();

        weights = Utility.diff(weights, Utility.mul(0.4, Utility.transpose(grad_weights)));
        biases = Utility.diff(biases, Utility.mul(0.4, grad_bias));

        grads = Utility.matmul(grads, Utility.transpose(weights));
        return grads;
    }
}

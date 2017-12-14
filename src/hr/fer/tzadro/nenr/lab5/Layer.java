package hr.fer.tzadro.nenr.lab5;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Layer {
    int size;
    double[][] weights;
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

    public double[][] forward(double[][] in) {
        double[][] result = matmul(in, weights);
        Arrays.stream(result)
                .forEach(row -> IntStream.range(0, size)
                        .forEach(i -> row[i] = sigmoid(row[i] + biases[i])));
        return result;
    }

    // https://stackoverflow.com/questions/34774384/multiply-2-double-matrices-using-streams
    private double[][] matmul(double[][] m1, double[][] m2) {
        return Arrays.stream(m1)
                .map(r -> IntStream.range(0, m2[0].length)
                        .mapToDouble(i -> IntStream.range(0, m2.length)
                                .mapToDouble(j -> r[j] * m2[j][i])
                                .sum())
                        .toArray())
                .toArray(double[][]::new);
    }

    // http://chronicles.blog.ryanrampersad.com/2009/02/sigmoid-function-in-java/
    public double sigmoid(double x) {
        return (1 / ( 1 + Math.pow(Math.E, -x)));
    }
}

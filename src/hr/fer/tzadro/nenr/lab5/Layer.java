package hr.fer.tzadro.nenr.lab5;

import java.util.List;
import java.util.Random;

public class Layer {
    double[][] weights;
    double[] biases;

    public Layer(int numIn, int size) {
        weights = new Random().doubles(numIn * size, -1, 1).toArray();
        weights = new double[numIn][]
        biases = new double[size];
    }

    public List<Double> forward(List<Double> in) {
        // todo: implement
        return null;
    }
}

package hr.fer.tzadro.nenr.lab6;

import java.util.stream.IntStream;

public class WeightedAverage {
    private double[] numerator;
    private double[] denominator;

    public double[] output() {
        return IntStream.range(0, numerator.length).mapToDouble(i -> numerator[i] / denominator[i]).toArray();
    }

    public double[] weightSum() {
        return denominator;
    }

    public void accept(IWeightedValue[] r) {
        if (numerator == null) {
            numerator = new double[r.length];
            denominator = new double[r.length];
        }

        IntStream.range(0, r.length).forEach(i -> {
            numerator[i] += r[i].getWeight() * r[i].getValue();
            denominator[i] += r[i].getWeight();
        });
    }

    public void combine(WeightedAverage other) {
        if (numerator == null) {
            numerator = new double[other.numerator.length];
            denominator = new double[other.numerator.length];
        }

        IntStream.range(0, other.numerator.length).forEach(i -> {
            numerator[i] += other.numerator[i];
            denominator[i] += other.denominator[i];
        });
    }
}

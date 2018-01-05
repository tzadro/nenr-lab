package hr.fer.tzadro.nenr.lab6;

public class WeightedAverage {
    private double numerator = 0;
    private double denominator = 0;

    public double output() {
        return numerator / denominator;
    }

    public void accept(IWeightedValue r) {
        numerator += r.getWeight() * r.getValue();
        denominator += r.getWeight();
    }

    public void combine(WeightedAverage other) {
        numerator += other.numerator;
        denominator += other.denominator;
    }
}

package hr.fer.tzadro.nenr.lab6;

public class RuleResult implements IWeightedValue {
    public double pi;
    public double z;

    public RuleResult(double pi, double z) {
        this.pi = pi;
        this.z = z;
    }

    @Override
    public double getWeight() {
        return pi;
    }

    @Override
    public double getValue() {
        return z;
    }
}

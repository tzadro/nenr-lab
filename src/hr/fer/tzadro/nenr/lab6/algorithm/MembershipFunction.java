package hr.fer.tzadro.nenr.lab6.algorithm;

public class MembershipFunction {

    public static double valueFor(double x, double a, double b) {
        return 1 / (1 + Math.exp(b * (x - a)));
    }
}

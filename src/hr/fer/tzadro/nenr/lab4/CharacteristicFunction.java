package hr.fer.tzadro.nenr.lab4;

public class CharacteristicFunction {

    public static double valueFor(double x, double y, double[] b) {
        return Math.sin(b[0] + b[1] * x) + b[2] * Math.cos(x * (b[3] + y)) / (1 + Math.exp(Math.pow(x - b[4], 2)));
    }
}

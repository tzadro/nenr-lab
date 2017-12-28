package hr.fer.tzadro.nenr.lab5.data;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.DoubleStream;

public class Coordinate {
    public double x;
    public double y;

    public Coordinate() {
        this(Double.NaN, Double.NaN);
    }

    public Coordinate(String line) {
        String[] elements = line.split(" ");

        x = Double.parseDouble(elements[0]);
        y = Double.parseDouble(elements[1]);
    }

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "(%.2f, %.2f)", x, y);
    }

    public DoubleStream toStream() {
        return Arrays.stream(new double[]{x, y});
    }
}

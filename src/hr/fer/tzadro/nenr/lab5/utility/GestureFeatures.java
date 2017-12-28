package hr.fer.tzadro.nenr.lab5.utility;

import hr.fer.tzadro.nenr.lab5.data.Coordinate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GestureFeatures {
    private Coordinate first;
    private double D;
    private int M;
    private List<Coordinate> features;
    private List<Double> distances;

    public GestureFeatures(Coordinate first, double D, int M) {
        this.first = first;
        this.D = D;
        this.M = M;
        features = Stream.generate(Coordinate::new).limit(M).collect(Collectors.toList());
        distances = Stream.generate(() -> Double.POSITIVE_INFINITY).limit(M).collect(Collectors.toList());
    }

    public List<Coordinate> features() {
        return features;
    }

    public void accept(Coordinate c) {
        IntStream.range(0, M).forEach(i -> {
            double distance = Math.abs(i * D / (M - 1) - Math.sqrt(Math.pow(first.x - c.x, 2) + Math.pow(first.y - c.y, 2)));

            if (distance < distances.get(i)) {
                distances.set(i, distance);
                features.set(i, c);
            }
        });
    }

    public void combine(GestureFeatures other) {
        IntStream.range(0, M).forEach(i -> {
            if (other.distances.get(i) < distances.get(i)) {
                distances.set(i, other.distances.get(i));
                features.set(i, other.features.get(i));
            }
        });
    }
}

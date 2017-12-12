package hr.fer.tzadro.nenr.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeuralNetworkAlgorithm {
    private int M;
    private String datasetPath;
    private List<Coordinate> gesture;

    public NeuralNetworkAlgorithm(int M, String datasetPath) {
        this.M = M;
        this.datasetPath = datasetPath;
        gesture = new ArrayList<>();
    }

    public void addToPath(double x, double y) {
        gesture.add(new Coordinate(x, y));
    }

    public void predict() {
        List<Coordinate> features = getFeatures();

        // todo: predict

        clearPath();
    }

    private List<Coordinate> getFeatures() {
        normalize();

        double D = getPathLength();

        return gesture.stream()
                .collect(() -> new GestureFeatures(gesture.get(0), D, M), GestureFeatures::accept, GestureFeatures::combine)
                .features();
    }

    private void normalize() {
        Coordinate Tc = gesture.stream()
                .collect(CoordinateAverager::new, CoordinateAverager::accept, CoordinateAverager::combine)
                .average();

        gesture.forEach(c -> {
            c.x -= Tc.x;
            c.y -= Tc.y;
        });

        double m = gesture.stream()
                .collect(CoordinateMaximum::new, CoordinateMaximum::accept, CoordinateMaximum::combine)
                .maximum();

        gesture.forEach(c -> {
            c.x /= m;
            c.y /= m;
        });
    }

    private double getPathLength() {
        return IntStream.range(0, gesture.size() - 1)
                .mapToDouble(i -> Math.sqrt(Math.pow(gesture.get(i).x - gesture.get(i + 1).x, 2) + Math.pow(gesture.get(i).y - gesture.get(i + 1).y, 2)))
                .sum();
    }

    private void print(List<Coordinate> coordinates) {
        System.out.println(coordinates.stream().map(e -> e.x + " " + e.y).collect(Collectors.joining(","))); // + "\t1,0,0,0,0"
    }

    private void clearPath() {
        gesture.clear();
    }
}

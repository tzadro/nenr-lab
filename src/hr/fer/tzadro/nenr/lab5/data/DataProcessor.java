package hr.fer.tzadro.nenr.lab5.data;

import hr.fer.tzadro.nenr.lab5.utility.CoordinateAverager;
import hr.fer.tzadro.nenr.lab5.utility.CoordinateMaximum;
import hr.fer.tzadro.nenr.lab5.utility.GestureFeatures;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataProcessor {
    private int M;
    private List<Coordinate> gesture;

    public DataProcessor(int M) {
        this.M = M;
        gesture = new ArrayList<>();
    }

    public List<Example> loadDataset(String datasetPath) {
        try (Stream<String> stream = Files.lines(Paths.get(datasetPath))) {
            return stream.map(Example::new).collect(Collectors.toList());
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not load file.");
        }
    }

    public void addToPath(double x, double y) {
        gesture.add(new Coordinate(x, y));
    }

    public List<Coordinate> getFeatures() {
        normalize();

        double D = getPathLength();

        return gesture.stream()
                      .collect(() -> new GestureFeatures(gesture.get(0), D, M), GestureFeatures::accept, GestureFeatures::combine)
                      .features();
    }

    public void print(List<Coordinate> coordinates) {
        System.out.println(coordinates.stream().map(e -> e.x + " " + e.y).collect(Collectors.joining(","))); // + "\t1,0,0,0,0"
    }

    public void clearPath() {
        gesture.clear();
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
}

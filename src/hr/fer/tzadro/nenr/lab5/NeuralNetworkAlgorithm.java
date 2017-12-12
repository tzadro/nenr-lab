package hr.fer.tzadro.nenr.lab5;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkAlgorithm {
    private List<Coordinate> gesture;

    public NeuralNetworkAlgorithm() {
        gesture = new ArrayList<>();
    }

    public void addToPath(double x, double y) {
        gesture.add(new Coordinate(x, y));
    }

    public void predict() {
        normalize();

        clearPath();
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

        System.out.println(Tc.x + " " + Tc.y);
        System.out.println(m);
    }

    private void clearPath() {
        gesture.clear();
    }
}

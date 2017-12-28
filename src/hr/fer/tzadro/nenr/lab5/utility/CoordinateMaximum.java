package hr.fer.tzadro.nenr.lab5.utility;

import hr.fer.tzadro.nenr.lab5.data.Coordinate;

public class CoordinateMaximum {
    private double maxx = 0;
    private double maxy = 0;

    public double maximum() {
        return Math.max(maxx, maxy);
    }

    public void accept(Coordinate c) {
        if (Math.abs(c.x) > maxx)
            maxx = Math.abs(c.x);

        if (Math.abs(c.y) > maxx)
            maxx = Math.abs(c.y);
    }

    public void combine(CoordinateMaximum other) {
        if (other.maxx > maxx)
            maxx = other.maxx;

        if (other.maxy > maxy)
            maxy = other.maxy;
    }
}

package hr.fer.tzadro.nenr.lab5.utility;

import hr.fer.tzadro.nenr.lab5.data.Coordinate;

public class CoordinateAverager {
    private double sumx = 0;
    private double sumy = 0;
    private int count = 0;

    public Coordinate average() {
        if (count == 0)
            throw new IllegalArgumentException("No coordinates given.");

        double avgx = sumx / count;
        double avgy = sumy / count;
        return new Coordinate(avgx, avgy);
    }

    public void accept(Coordinate c) {
        sumx += c.x;
        sumy += c.y;
        count++;
    }

    public void combine(CoordinateAverager other) {
        sumx += other.sumx;
        sumy += other.sumy;
        count += other.count;
    }
}

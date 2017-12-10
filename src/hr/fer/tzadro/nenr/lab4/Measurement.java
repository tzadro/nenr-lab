package hr.fer.tzadro.nenr.lab4;

public class Measurement {
    public double x;
    public double y;
    public double f;

    public Measurement(String line) {
        String[] elements = line.split("\\t");

        x = Double.parseDouble(elements[0]);
        y = Double.parseDouble(elements[1]);
        f = Double.parseDouble(elements[2]);
    }
}

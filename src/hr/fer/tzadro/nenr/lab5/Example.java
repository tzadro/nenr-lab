package hr.fer.tzadro.nenr.lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example {
    public List<Coordinate> X;
    public List<Integer> Yoh_;

    public Example(String line) {
        String[] elements = line.split("\\t");

        X = Arrays.stream(elements[0].split(","))
                  .map(Coordinate::new)
                  .collect(Collectors.toList());

        Yoh_ = Arrays.stream(elements[1].split(","))
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "X: " + X.toString() + " Yoh_: " + Yoh_.toString();
    }
}

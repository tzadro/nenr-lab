package hr.fer.tzadro.nenr.lab4;

import java.util.List;

public interface IGeneticAlgorithm {
    Individual run(List<Measurement> measurements, int numOfIterations, boolean preserveBest);
}

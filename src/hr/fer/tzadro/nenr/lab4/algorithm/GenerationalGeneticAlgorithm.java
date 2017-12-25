package hr.fer.tzadro.nenr.lab4.algorithm;

import hr.fer.tzadro.nenr.lab4.Individual;
import hr.fer.tzadro.nenr.lab4.Measurement;

import java.util.List;

public class GenerationalGeneticAlgorithm extends GeneticAlgorithm {

    protected GenerationalGeneticAlgorithm(int populationSize, double mutationProbability) {
        super(populationSize, mutationProbability);
    }

    @Override
    public Individual run(List<Measurement> measurements, int numOfIterations, boolean preserveBest) {
        // todo: implement
        return null;
    }
}

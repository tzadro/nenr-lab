package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab5.data.Example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeuroFuzzyAlgorithm {
    List<Rule> rules;

    public NeuroFuzzyAlgorithm(int numRules, IBinaryFunction tNorm) {
        rules = Stream.generate(() -> new Rule(tNorm))
                      .limit(numRules)
                      .collect(Collectors.toList());
    }

    public void train(List<Example> data, int numIterations) {
        // todo: data

        for (int i = 0; i < numIterations; i++) {
            // todo: implement
        }
    }

    public double forward(double x, double y) {
        return rules.stream()
                    .map(rule -> rule.forward(x, y))
                    .collect(WeightedAverage::new, WeightedAverage::accept, WeightedAverage::combine)
                    .output();
    }
}

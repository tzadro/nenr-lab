package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab5.utility.Utility;
import hr.fer.tzadro.nenr.lab6.data.Example;

import java.util.Arrays;
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
        double[] X = data.stream().mapToDouble(e -> e.x).toArray();
        double[] Y = data.stream().mapToDouble(e -> e.y).toArray();
        double[] Z_ = data.stream().mapToDouble(e -> e.z).toArray();

        for (int i = 0; i < numIterations; i++) {
            double[] out = rules.stream()
                                .map(rule -> rule.forward(X, Y))
                                .collect(WeightedAverage::new, WeightedAverage::accept, WeightedAverage::combine)
                                .output();

            double[] error = Utility.mul(0.5, Utility.square(Utility.diff(Z_, out)));
            System.out.println(Arrays.stream(error).average());

            double[] gradError = Utility.div(Utility.diff(out, Z_), out.length); // todo: wrong?
            rules.stream()
                 .forEach(rule -> rule.backward(gradError));
        }
    }

    public double forward(double x, double y) {
        double[] X = new double[]{x};
        double[] Y = new double[]{y};

        return rules.stream()
                    .map(rule -> rule.forward(X, Y))
                    .collect(WeightedAverage::new, WeightedAverage::accept, WeightedAverage::combine)
                    .output()[0];
    }
}

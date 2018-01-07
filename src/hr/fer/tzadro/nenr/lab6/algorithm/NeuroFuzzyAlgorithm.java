package hr.fer.tzadro.nenr.lab6.algorithm;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab5.utility.Utility;
import hr.fer.tzadro.nenr.lab6.data.Example;
import hr.fer.tzadro.nenr.lab6.utility.WeightedAverage;

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

    public void train(List<Example> data, int numIterations, double learningRate) {
        double[] X = data.stream().mapToDouble(e -> e.x).toArray();
        double[] Y = data.stream().mapToDouble(e -> e.y).toArray();
        double[] Z_ = data.stream().mapToDouble(e -> e.z).toArray();

        for (int i = 1; i <= numIterations; i++) {
            WeightedAverage combiner = rules.stream()
                                            .map(rule -> rule.forward(X, Y))
                                            .collect(WeightedAverage::new, WeightedAverage::accept, WeightedAverage::combine);

            double[] out = combiner.output();
            double[] weightSum = combiner.weightSum();

            double[] error = Utility.mul(0.5, Utility.square(Utility.diff(Z_, out)));
            if (i % 500 == 0 || i == 1) {
                System.out.println(i + ": " + Arrays.stream(error)
                                                    .average()
                                                    .orElseThrow(() -> new IllegalArgumentException("Error average error.")));
            }

            double[] errorGradOut = Utility.diff(out, Z_);
            rules.stream()
                 .forEach(rule -> {
                     double[] outGradPi = new double[X.length];

                     for (Rule otherRule : rules) {
                         outGradPi = Utility.sum(outGradPi, Utility.mul(otherRule.pi, Utility.diff(rule.z, otherRule.z)));
                     }

                     outGradPi = Utility.div(outGradPi, Utility.square(weightSum));

                     double[] outGradZ = Utility.div(rule.pi, weightSum);

                     double[] errorGradPi = Utility.mul(errorGradOut, outGradPi);
                     double[] errorGradZ = Utility.mul(errorGradOut, outGradZ);

                     rule.backward(errorGradPi, errorGradZ, learningRate);
                 });
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

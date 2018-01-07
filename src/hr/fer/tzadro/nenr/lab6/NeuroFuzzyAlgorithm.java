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

    public void train(List<Example> data, int numIterations, double learningRate) {
        double[] X = data.stream().mapToDouble(e -> e.x).toArray();
        double[] Y = data.stream().mapToDouble(e -> e.y).toArray();
        double[] Z_ = data.stream().mapToDouble(e -> e.z).toArray();

        for (int i = 0; i < numIterations; i++) {
            WeightedAverage combiner = rules.stream()
                                            .map(rule -> rule.forward(X, Y))
                                            .collect(WeightedAverage::new, WeightedAverage::accept, WeightedAverage::combine);

            double[] out = combiner.output();
            //System.out.println("out: " + out[0]);
            double[] weightSum = combiner.weightSum();

            double[] error = Utility.mul(0.5, Utility.square(Utility.diff(Z_, out)));
            System.out.println(Arrays.stream(error)
                                     .average()
                                     .orElseThrow(() -> new IllegalArgumentException("Error average error.")));

            double[] gradError = Utility.diff(out, Z_); // todo: wrong?
            //System.out.println("Z_: " + Z_[0]);
            //System.out.println("gradError: " + gradError[0]);
            rules.stream()
                 .forEach(rule -> {
                     double[] gradPi = new double[X.length];

                     for (Rule otherRule : rules) {
                         gradPi = Utility.sum(gradPi, Utility.mul(otherRule.pi, Utility.diff(rule.z, otherRule.z)));
                     }

                     gradPi = Utility.div(gradPi, Utility.square(weightSum));

                     double[] gradZ = Utility.div(rule.pi, weightSum);

                     //System.out.println("gradPi: " + gradPi[0]);
                     //System.out.println("gradZ: " + gradZ[0]);
                     rule.backward(Utility.mul(gradError, gradPi), Utility.mul(gradError, gradZ), learningRate);
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

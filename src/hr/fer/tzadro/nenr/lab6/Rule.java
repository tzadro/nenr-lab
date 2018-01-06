package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Rule {
    private double a, b, c, d, z, p, q, r;
    private IBinaryFunction tNorm;

    public Rule(IBinaryFunction tNorm) {
        a = Math.random();
        b = Math.random();
        c = Math.random();
        d = Math.random();
        p = Math.random();
        q = Math.random();
        r = Math.random();
    }

    public RuleResult[] forward(double[] x, double[] y) {
        double[] alpha = Arrays.stream(x)
                               .map(e -> MembershipFunction.valueFor(e, a, b))
                               .toArray();
        double[] beta = Arrays.stream(y)
                              .map(e -> MembershipFunction.valueFor(e, c, d))
                              .toArray();
        double[] pi = IntStream.range(0, alpha.length)
                               .mapToDouble(i -> tNorm.valueAt(alpha[i], beta[i]))
                               .toArray();
        double[] z = IntStream.range(0, alpha.length)
                              .mapToDouble(i -> p * x[i] + q * y[i] + r)
                              .toArray();
        return IntStream.range(0, pi.length)
                        .mapToObj(i -> new RuleResult(pi[i], z[i]))
                        .toArray(RuleResult[]::new);
    }

    public void backward(double[] gradsError) {
        // todo: implement
    }
}

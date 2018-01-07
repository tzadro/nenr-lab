package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab5.utility.Utility;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Rule {
    private double a, b, c, d, p, q, r;
    private IBinaryFunction tNorm;

    public double[] xCache;
    public double[] yCache;
    public double[] alpha;
    public double[] beta;
    public double[] pi;
    public double[] z;

    public Rule(IBinaryFunction tNorm) {
        this.tNorm = tNorm;
        a = Math.random();
        b = Math.random();
        c = Math.random();
        d = Math.random();
        p = Math.random();
        q = Math.random();
        r = Math.random();
    }

    public RuleResult[] forward(double[] x, double[] y) {
        xCache = x;
        yCache = y;

        alpha = Arrays.stream(x)
                      .map(e -> MembershipFunction.valueFor(e, a, b))
                      .toArray();
        beta = Arrays.stream(y)
                     .map(e -> MembershipFunction.valueFor(e, c, d))
                     .toArray();
        pi = IntStream.range(0, alpha.length)
                      .mapToDouble(i -> tNorm.valueAt(alpha[i], beta[i]))
                      .toArray();
        z = IntStream.range(0, alpha.length)
                     .mapToDouble(i -> p * x[i] + q * y[i] + r)
                     .toArray();

        return IntStream.range(0, pi.length)
                        .mapToObj(i -> new RuleResult(pi[i], z[i]))
                        .toArray(RuleResult[]::new);
    }

    public void backward(double[] gradPi, double[] gradZ, double learningRate) {
        double[] gradA = Utility.mul(gradPi, beta);
        double[] gradB = Utility.mul(gradPi, alpha);

        double[] grada = Utility.mul(gradA, Utility.mul(b, Utility.mul(alpha, Utility.diff(1, alpha))));
        a -= learningRate * Arrays.stream(grada).average().orElseThrow(() -> new IllegalArgumentException("Backprop grad a error."));

        double[] gradb = Utility.mul(gradA, Utility.mul(Utility.diff(xCache, a), Utility.mul(alpha, Utility.diff(1, alpha))));
        b -= learningRate * Arrays.stream(gradb).average().orElseThrow(() -> new IllegalArgumentException("Backprop grad b error."));

        double[] gradc = Utility.mul(gradB, Utility.mul(d, Utility.mul(beta, Utility.diff(1, beta))));
        c -= learningRate * Arrays.stream(gradc).average().orElseThrow(() -> new IllegalArgumentException("Backprop grad c error."));

        double[] gradd = Utility.mul(gradB, Utility.mul(Utility.diff(xCache, c), Utility.mul(beta, Utility.diff(1, beta))));
        d -= learningRate * Arrays.stream(gradd).average().orElseThrow(() -> new IllegalArgumentException("Backprop grad d error."));

        double[] gradp = Utility.mul(gradZ, xCache);
        p -= learningRate * Arrays.stream(gradp).average().orElseThrow(() -> new IllegalArgumentException("Backprop grad p error."));

        double[] gradq = Utility.mul(gradZ, yCache);
        q -= learningRate * Arrays.stream(gradq).average().orElseThrow(() -> new IllegalArgumentException("Backprop grad q error."));

        double[] gradr = gradZ;
        r -= learningRate * Arrays.stream(gradr).average().orElseThrow(() -> new IllegalArgumentException("Backprop grad r error."));
    }
}

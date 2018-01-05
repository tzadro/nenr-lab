package hr.fer.tzadro.nenr.lab6;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;

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

    public RuleResult forward(double x, double y) {
        double alpha = MembershipFunction.valueFor(x, a, b);
        double beta = MembershipFunction.valueFor(y, a, b);
        double pi = tNorm.valueAt(alpha, beta);
        double z = p * x + q * y + r;
        return new RuleResult(pi, z);
    }
}

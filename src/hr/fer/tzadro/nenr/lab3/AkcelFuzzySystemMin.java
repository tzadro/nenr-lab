package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad1.SimpleDomain;
import hr.fer.tzadro.nenr.lab1.zad2.CalculatedFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.StandardFuzzySets;

public class AkcelFuzzySystemMin implements IFuzzySystem {
    private IDefuzzifier def;
    private IFuzzySet[] rules;

    public AkcelFuzzySystemMin(IDefuzzifier def) {
        this.def = def;

        IFuzzySet antecedent, consequent;

        // domene
        IDomain offsetDomain = new SimpleDomain(-10, 11);

        // jezicne varijable
        IFuzzySet offsetNB = new CalculatedFuzzySet(
                offsetDomain,
                StandardFuzzySets.LFunction(-9, -5)
        );
        IFuzzySet offsetNM = new CalculatedFuzzySet(
                offsetDomain,
                StandardFuzzySets.lambdaFunction(-9, -5, 0)
        );
        IFuzzySet offsetZE = new CalculatedFuzzySet(
                offsetDomain,
                StandardFuzzySets.lambdaFunction(-5, 0, 5)
        );
        IFuzzySet offsetPM = new CalculatedFuzzySet(
                offsetDomain,
                StandardFuzzySets.lambdaFunction(0, 5, 9)
        );
        IFuzzySet offsetPB = new CalculatedFuzzySet(
                offsetDomain,
                StandardFuzzySets.gammaFunction(5, 9)
        );

        // pravila
        // AKO
    }

    @Override
    public int infer(int L, int D, int LK, int DK, int V, int S) {
        // preformuliranje ulaza
        int positionOffset = (int) ((2. * (L - D) / (L + D)) / 0.1); // [-10, 10]
        int angleOffset = (int) ((2. * (LK - DK) / (LK + DK)) / 0.1); // [-10, 10]

        return 5;
    }
}

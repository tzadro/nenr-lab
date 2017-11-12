package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad1.Debug;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad1.SimpleDomain;
import hr.fer.tzadro.nenr.lab1.zad2.CalculatedFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.StandardFuzzySets;
import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KormiloFuzzySystemMin implements IFuzzySystem {
    private IDefuzzifier def;
    private IBinaryFunction tNorm;

    private ArrayList<IFuzzySet> rules;
    private IDomain offsetDomain;
    private IDomain angleDomain;

    public KormiloFuzzySystemMin(IDefuzzifier def, IBinaryFunction tNorm) {
        this.def = def;
        this.tNorm = tNorm;

        // domene
        offsetDomain = new SimpleDomain(-10, 11);
        angleDomain = new SimpleDomain(-90, 91);

        // jezicne varijable
        IFuzzySet offsetNB = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.LFunction(-7, -3));
        IFuzzySet offsetNM = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(-7, -3, 0));
        IFuzzySet offsetZE = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(-3, 0, 3));
        IFuzzySet offsetPM = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(0, 3, 7));
        IFuzzySet offsetPB = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.gammaFunction(3, 7));

        IFuzzySet angleNB = new CalculatedFuzzySet(angleDomain, StandardFuzzySets.LFunction(-90, -60));
        IFuzzySet angleNM = new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lambdaFunction(-90, -60, -30));
        IFuzzySet angleNS = new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lambdaFunction(-60, -30, 0));
        IFuzzySet angleZE = new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lambdaFunction(-30, 0, 30));
        IFuzzySet anglePS = new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lambdaFunction(0, 30, 60));
        IFuzzySet anglePM = new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lambdaFunction(30, 60, 90));
        IFuzzySet anglePB = new CalculatedFuzzySet(angleDomain, StandardFuzzySets.gammaFunction(60, 90));

        // pravila
        rules = new ArrayList<>();

        rules.add(FuzzyLogic.imply(offsetNB, offsetNB, angleNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetNM, angleNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetZE, angleNS, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPM, angleNS, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPB, angleZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNB, angleNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNM, angleNS, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetZE, angleNS, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPM, angleZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPB, anglePS, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNB, angleNS, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNM, angleNS, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetZE, angleZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPM, anglePS, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPB, anglePS, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNB, angleNS, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNM, angleZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetZE, anglePS, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPM, anglePS, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPB, anglePM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNB, angleZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNM, anglePS, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetZE, anglePS, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPM, anglePM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPB, anglePB, tNorm));
    }

    public IFuzzySet getRule(int index) {
        return rules.get(index);
    }

    public IDomain getAngleDomain() {
        return angleDomain;
    }

    @Override
    public int infer(int positionOffset, int trailOffset, int V, int S, boolean verbose) {
        DomainElement input = DomainElement.of(positionOffset, trailOffset);

        IFuzzySet conclusion = FuzzyLogic.conclude(rules, input, angleDomain);

        if (verbose)
            Debug.print(conclusion, "Angle:");

        return (int) def.defuzzify(conclusion);
    }
}


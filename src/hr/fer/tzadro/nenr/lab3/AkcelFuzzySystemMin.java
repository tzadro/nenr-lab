package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad1.Debug;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad1.SimpleDomain;
import hr.fer.tzadro.nenr.lab1.zad2.CalculatedFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.StandardFuzzySets;
import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;

import java.util.ArrayList;

public class AkcelFuzzySystemMin implements IFuzzySystem {
    private IDefuzzifier def;
    private IBinaryFunction tNorm;

    private ArrayList<IFuzzySet> rules;
    private IDomain offsetDomain;
    private IDomain speedDomain;
    private IDomain accelerationDomain;

    public AkcelFuzzySystemMin(IDefuzzifier def, IBinaryFunction tNorm) {
        this.def = def;
        this.tNorm = tNorm;

        // domene
        offsetDomain = new SimpleDomain(-10, 11);
        speedDomain = new SimpleDomain(0, 11);
        accelerationDomain = new SimpleDomain(-6, 7);

        // jezicne varijable
        IFuzzySet offsetNB = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.LFunction(-7, -3));
        IFuzzySet offsetNM = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(-7, -3, 0));
        IFuzzySet offsetZE = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(-3, 0, 3));
        IFuzzySet offsetPM = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(0, 3, 7));
        IFuzzySet offsetPB = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.gammaFunction(3, 7));

        IFuzzySet speedZE = new CalculatedFuzzySet(speedDomain, StandardFuzzySets.LFunction(0, 10));
        IFuzzySet speedPM = new CalculatedFuzzySet(speedDomain, StandardFuzzySets.lambdaFunction(0, 10, 25));
        IFuzzySet speedPB = new CalculatedFuzzySet(speedDomain, StandardFuzzySets.gammaFunction(10, 25));

        IFuzzySet accelerationNB = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.LFunction(-6, -3));
        IFuzzySet accelerationNM = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lambdaFunction(-6, -3, 0));
        IFuzzySet accelerationZE = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lambdaFunction(-1, 0, 3));
        IFuzzySet accelerationPM = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lambdaFunction(0, 3, 6));
        IFuzzySet accelerationPB = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.gammaFunction(1, 6));

        // pravila
        rules = new ArrayList<>();

        rules.add(FuzzyLogic.imply(offsetNB, offsetNB, speedZE, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetNM, speedZE, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetZE, speedZE, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPM, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPB, speedZE, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNB, speedZE, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNM, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetZE, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPM, speedZE, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPB, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNB, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNM, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetZE, speedZE, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPM, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPB, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNB, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNM, speedZE, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetZE, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPM, speedZE, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPB, speedZE, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNB, speedZE, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNM, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetZE, speedZE, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPM, speedZE, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPB, speedZE, accelerationNM, tNorm));

        rules.add(FuzzyLogic.imply(offsetNB, offsetNB, speedPM, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetNM, speedPM, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetZE, speedPM, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPM, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPB, speedPM, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNB, speedPM, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNM, speedPM, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetZE, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPM, speedPM, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPB, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNB, speedPM, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNM, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetZE, speedPM, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPM, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPB, speedPM, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNB, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNM, speedPM, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetZE, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPM, speedPM, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPB, speedPM, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNB, speedPM, accelerationPB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNM, speedPM, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetZE, speedPM, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPM, speedPM, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPB, speedPM, accelerationNB, tNorm));

        rules.add(FuzzyLogic.imply(offsetNB, offsetNB, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetNM, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetZE, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPM, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNB, offsetPB, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNB, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetNM, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetZE, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPM, speedPB, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetNM, offsetPB, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNB, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetNM, speedPB, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetZE, speedPB, accelerationPM, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPM, speedPB, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetZE, offsetPB, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNB, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetNM, speedPB, accelerationZE, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetZE, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPM, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPM, offsetPB, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNB, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetNM, speedPB, accelerationNM, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetZE, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPM, speedPB, accelerationNB, tNorm));
        rules.add(FuzzyLogic.imply(offsetPB, offsetPB, speedPB, accelerationNB, tNorm));
    }

    public IFuzzySet getRule(int index) {
        return rules.get(index);
    }

    public IDomain getAccelerationDomain() {
        return accelerationDomain;
    }

    @Override
    public int infer(int positionOffset, int trailOffset, int V, int S, boolean verbose) {
        DomainElement input = DomainElement.of(new int[]{positionOffset, trailOffset, V});

        IFuzzySet conclusion = FuzzyLogic.conclude(rules, input, accelerationDomain);

        if (verbose)
            Debug.print(conclusion, "Acceleration:");

        return (int) def.defuzzify(conclusion);
    }
}

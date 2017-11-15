package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad1.Debug;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;

import java.util.ArrayList;
import java.util.List;

public class Zad1 {

    public static void main(String[] args) {
        int L = 50, D = 150, LK = 50, DK = 60, V = 10, S = 0, rule = 8;
        int positionOffset, trailOffset;

        IDefuzzifier def = new COADefuzzifier();
        IBinaryFunction tNorm = Operations.minimum();
        AkcelFuzzySystemMin fsAkcel = new AkcelFuzzySystemMin(def, tNorm);
        KormiloFuzzySystemMin fsKormilo = new KormiloFuzzySystemMin(def, tNorm);

        positionOffset = (int) (-10 + (1. * L) / (L + D) * 20); // [-10, 10]
        trailOffset = (int) (-10 + (1. * LK) / (LK + DK) * 20); // [-10, 10]

        List<IFuzzySet> accelerationRule = new ArrayList<>();
        accelerationRule.add(fsAkcel.getRule(rule));
        DomainElement accelerationInput = DomainElement.of(new int[]{positionOffset, trailOffset, V});
        IDomain accelerationDomain = fsAkcel.getAccelerationDomain();
        IFuzzySet accelerationConclusion = FuzzyLogic.conclude(accelerationRule, accelerationInput, accelerationDomain);
        Debug.print(accelerationConclusion, "Acceleration:");

        List<IFuzzySet> angleRule = new ArrayList<>();
        angleRule.add(fsKormilo.getRule(rule));
        DomainElement angleInput = DomainElement.of(positionOffset, trailOffset);
        IDomain angleDomain = fsKormilo.getAngleDomain();
        IFuzzySet angleConclusion = FuzzyLogic.conclude(angleRule, angleInput, angleDomain);
        Debug.print(angleConclusion, "Angle:");
    }
}

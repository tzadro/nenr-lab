package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad1.SimpleDomain;
import hr.fer.tzadro.nenr.lab1.zad2.CalculatedFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.StandardFuzzySets;
import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KormiloFuzzySystemMin implements IFuzzySystem {
    private IDefuzzifier def;
    private ArrayList<IFuzzySet> rules;
    private IBinaryFunction tNorm = Operations.minimum();

    private IDomain offsetDomain;
    private IDomain angleDomain;

    public KormiloFuzzySystemMin(IDefuzzifier def) {
        this.def = def;
        rules = new ArrayList<>();

        IFuzzySet antecedent, consequent;

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
        rules.add(createRule(offsetNB, offsetNB, angleNB));
        rules.add(createRule(offsetNB, offsetNM, angleNM));
        rules.add(createRule(offsetNB, offsetZE, angleNS));
        rules.add(createRule(offsetNB, offsetPM, angleNS));
        rules.add(createRule(offsetNB, offsetPB, angleZE));
        rules.add(createRule(offsetNM, offsetNB, angleNM));
        rules.add(createRule(offsetNM, offsetNM, angleNS));
        rules.add(createRule(offsetNM, offsetZE, angleNS));
        rules.add(createRule(offsetNM, offsetPM, angleZE));
        rules.add(createRule(offsetNM, offsetPB, anglePS));
        rules.add(createRule(offsetZE, offsetNB, angleNS));
        rules.add(createRule(offsetZE, offsetNM, angleNS));
        rules.add(createRule(offsetZE, offsetZE, angleZE));
        rules.add(createRule(offsetZE, offsetPM, anglePS));
        rules.add(createRule(offsetZE, offsetPB, anglePS));
        rules.add(createRule(offsetPM, offsetNB, angleNS));
        rules.add(createRule(offsetPM, offsetNM, angleZE));
        rules.add(createRule(offsetPM, offsetZE, anglePS));
        rules.add(createRule(offsetPM, offsetPM, anglePS));
        rules.add(createRule(offsetPM, offsetPB, anglePM));
        rules.add(createRule(offsetPB, offsetNB, angleZE));
        rules.add(createRule(offsetPB, offsetNM, anglePS));
        rules.add(createRule(offsetPB, offsetZE, anglePS));
        rules.add(createRule(offsetPB, offsetPM, anglePM));
        rules.add(createRule(offsetPB, offsetPB, anglePB));
    }

    @Override
    public int infer(int positionOffset, int trailOffset, int V, int S, PrintWriter writer) throws IOException {
        DomainElement input = DomainElement.of(positionOffset, trailOffset);

        IFuzzySet result = new MutableFuzzySet(angleDomain);

        DomainElement temp;
        for (IFuzzySet rule : rules) {
            MutableFuzzySet implication = new MutableFuzzySet(angleDomain);

            for (DomainElement angleElement : angleDomain) {
                temp = DomainElement.combine(input, angleElement);
                try {
                    implication.set(angleElement, rule.getValueAt(temp));
                } catch(Exception e) {
                    writer.println(e);
                    writer.flush();
                }
            }

            result = Operations.binaryOperation(result, implication, Operations.maximum());
        }

        return (int) def.defuzzify(result);
    }

    private IFuzzySet createRule(IFuzzySet premise1, IFuzzySet premise2, IFuzzySet consequent) {
        IFuzzySet antecedent = Operations.cartesianProduct(premise1, premise2, tNorm);
        return Operations.cartesianProduct(antecedent, consequent, tNorm);
    }
}


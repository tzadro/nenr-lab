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

public class AkcelFuzzySystemMin implements IFuzzySystem {

    private IDefuzzifier def;
    private ArrayList<IFuzzySet> rules;
    private IBinaryFunction tNorm = Operations.minimum();

    private IDomain offsetDomain;
    private IDomain speedDomain;
    private IDomain accelerationDomain;

    public AkcelFuzzySystemMin(IDefuzzifier def) {
        this.def = def;
        rules = new ArrayList<>();

        IFuzzySet antecedent, consequent;

        // domene
        offsetDomain = new SimpleDomain(-10, 11);
        speedDomain = new SimpleDomain(0, 51);
        accelerationDomain = new SimpleDomain(-6, 7);

        // jezicne varijable
        IFuzzySet offsetNB = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.LFunction(-7, -3));
        IFuzzySet offsetNM = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(-7, -3, 0));
        IFuzzySet offsetZE = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(-3, 0, 3));
        IFuzzySet offsetPM = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(0, 3, 7));
        IFuzzySet offsetPB = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.gammaFunction(3, 7));

        IFuzzySet speedZE = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.LFunction(0,  10));
        IFuzzySet speedPM = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.lambdaFunction(0, 10, 25));
        IFuzzySet speedPB = new CalculatedFuzzySet(offsetDomain, StandardFuzzySets.gammaFunction(10, 25));

        IFuzzySet accelerationNB = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.LFunction(-6, -3));
        IFuzzySet accelerationNM = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lambdaFunction(-6, -3, 0));
        IFuzzySet accelerationZE = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lambdaFunction(-1, 0, 3));
        IFuzzySet accelerationPM = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lambdaFunction(0, 3, 6));
        IFuzzySet accelerationPB = new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.gammaFunction(1, 6));

        rules.add(createRule(offsetNB, offsetNB, speedZE, accelerationNM));
        rules.add(createRule(offsetNB, offsetNM, speedZE, accelerationZE));
        rules.add(createRule(offsetNB, offsetZE, speedZE, accelerationZE));
        rules.add(createRule(offsetNB, offsetPM, speedZE, accelerationPM));
        rules.add(createRule(offsetNB, offsetPB, speedZE, accelerationPB));
        rules.add(createRule(offsetNM, offsetNB, speedZE, accelerationZE));
        rules.add(createRule(offsetNM, offsetNM, speedZE, accelerationPM));
        rules.add(createRule(offsetNM, offsetZE, speedZE, accelerationPM));
        rules.add(createRule(offsetNM, offsetPM, speedZE, accelerationPB));
        rules.add(createRule(offsetNM, offsetPB, speedZE, accelerationPM));
        rules.add(createRule(offsetZE, offsetNB, speedZE, accelerationPM));
        rules.add(createRule(offsetZE, offsetNM, speedZE, accelerationPM));
        rules.add(createRule(offsetZE, offsetZE, speedZE, accelerationPB));
        rules.add(createRule(offsetZE, offsetPM, speedZE, accelerationPM));
        rules.add(createRule(offsetZE, offsetPB, speedZE, accelerationPM));
        rules.add(createRule(offsetPM, offsetNB, speedZE, accelerationPM));
        rules.add(createRule(offsetPM, offsetNM, speedZE, accelerationPB));
        rules.add(createRule(offsetPM, offsetZE, speedZE, accelerationPM));
        rules.add(createRule(offsetPM, offsetPM, speedZE, accelerationZE));
        rules.add(createRule(offsetPM, offsetPB, speedZE, accelerationZE));
        rules.add(createRule(offsetPB, offsetNB, speedZE, accelerationPB));
        rules.add(createRule(offsetPB, offsetNM, speedZE, accelerationPM));
        rules.add(createRule(offsetPB, offsetZE, speedZE, accelerationPM));
        rules.add(createRule(offsetPB, offsetPM, speedZE, accelerationZE));
        rules.add(createRule(offsetPB, offsetPB, speedZE, accelerationNM));

        rules.add(createRule(offsetNB, offsetNB, speedPM, accelerationNB));
        rules.add(createRule(offsetNB, offsetNM, speedPM, accelerationNM));
        rules.add(createRule(offsetNB, offsetZE, speedPM, accelerationZE));
        rules.add(createRule(offsetNB, offsetPM, speedPM, accelerationPM));
        rules.add(createRule(offsetNB, offsetPB, speedPM, accelerationPB));
        rules.add(createRule(offsetNM, offsetNB, speedPM, accelerationNM));
        rules.add(createRule(offsetNM, offsetNM, speedPM, accelerationZE));
        rules.add(createRule(offsetNM, offsetZE, speedPM, accelerationPM));
        rules.add(createRule(offsetNM, offsetPM, speedPM, accelerationPB));
        rules.add(createRule(offsetNM, offsetPB, speedPM, accelerationPM));
        rules.add(createRule(offsetZE, offsetNB, speedPM, accelerationZE));
        rules.add(createRule(offsetZE, offsetNM, speedPM, accelerationPM));
        rules.add(createRule(offsetZE, offsetZE, speedPM, accelerationPB));
        rules.add(createRule(offsetZE, offsetPM, speedPM, accelerationPM));
        rules.add(createRule(offsetZE, offsetPB, speedPM, accelerationZE));
        rules.add(createRule(offsetPM, offsetNB, speedPM, accelerationPM));
        rules.add(createRule(offsetPM, offsetNM, speedPM, accelerationPB));
        rules.add(createRule(offsetPM, offsetZE, speedPM, accelerationPM));
        rules.add(createRule(offsetPM, offsetPM, speedPM, accelerationZE));
        rules.add(createRule(offsetPM, offsetPB, speedPM, accelerationNM));
        rules.add(createRule(offsetPB, offsetNB, speedPM, accelerationPB));
        rules.add(createRule(offsetPB, offsetNM, speedPM, accelerationPM));
        rules.add(createRule(offsetPB, offsetZE, speedPM, accelerationZE));
        rules.add(createRule(offsetPB, offsetPM, speedPM, accelerationNM));
        rules.add(createRule(offsetPB, offsetPB, speedPM, accelerationNB));

        rules.add(createRule(offsetNB, offsetNB, speedPB, accelerationNB));
        rules.add(createRule(offsetNB, offsetNM, speedPB, accelerationNB));
        rules.add(createRule(offsetNB, offsetZE, speedPB, accelerationNB));
        rules.add(createRule(offsetNB, offsetPM, speedPB, accelerationNM));
        rules.add(createRule(offsetNB, offsetPB, speedPB, accelerationNM));
        rules.add(createRule(offsetNM, offsetNB, speedPB, accelerationNB));
        rules.add(createRule(offsetNM, offsetNM, speedPB, accelerationNB));
        rules.add(createRule(offsetNM, offsetZE, speedPB, accelerationNM));
        rules.add(createRule(offsetNM, offsetPM, speedPB, accelerationZE));
        rules.add(createRule(offsetNM, offsetPB, speedPB, accelerationNM));
        rules.add(createRule(offsetZE, offsetNB, speedPB, accelerationNB));
        rules.add(createRule(offsetZE, offsetNM, speedPB, accelerationZE));
        rules.add(createRule(offsetZE, offsetZE, speedPB, accelerationPM));
        rules.add(createRule(offsetZE, offsetPM, speedPB, accelerationZE));
        rules.add(createRule(offsetZE, offsetPB, speedPB, accelerationNB));
        rules.add(createRule(offsetPM, offsetNB, speedPB, accelerationNM));
        rules.add(createRule(offsetPM, offsetNM, speedPB, accelerationZE));
        rules.add(createRule(offsetPM, offsetZE, speedPB, accelerationNM));
        rules.add(createRule(offsetPM, offsetPM, speedPB, accelerationNB));
        rules.add(createRule(offsetPM, offsetPB, speedPB, accelerationNB));
        rules.add(createRule(offsetPB, offsetNB, speedPB, accelerationNM));
        rules.add(createRule(offsetPB, offsetNM, speedPB, accelerationNM));
        rules.add(createRule(offsetPB, offsetZE, speedPB, accelerationNB));
        rules.add(createRule(offsetPB, offsetPM, speedPB, accelerationNB));
        rules.add(createRule(offsetPB, offsetPB, speedPB, accelerationNB));
    }

    @Override
    public int infer(int positionOffset, int trailOffset, int V, int S, PrintWriter writer) throws IOException {
        DomainElement input = DomainElement.of(new int[]{positionOffset, trailOffset, V});

        IFuzzySet result = new MutableFuzzySet(accelerationDomain);

        DomainElement temp;
        for (IFuzzySet rule : rules) {
            MutableFuzzySet implication = new MutableFuzzySet(accelerationDomain);

            for (DomainElement angleElement : accelerationDomain) {
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

    private IFuzzySet createRule(IFuzzySet premise1, IFuzzySet premise2, IFuzzySet premise3, IFuzzySet consequent) {
        IFuzzySet antecedent = Operations.cartesianProduct(Operations.cartesianProduct(premise1, premise2, tNorm), premise3, tNorm);
        return Operations.cartesianProduct(antecedent, consequent, tNorm);
    }
}

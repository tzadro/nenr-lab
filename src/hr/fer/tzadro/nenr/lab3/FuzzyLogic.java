package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;

import java.util.List;

public class FuzzyLogic {

    public static IFuzzySet conclude(List<IFuzzySet> rules, DomainElement input, IDomain resultDomain) {
        IFuzzySet result = new MutableFuzzySet(resultDomain);
        DomainElement temp;

        for (IFuzzySet rule : rules) {
            MutableFuzzySet implication = new MutableFuzzySet(resultDomain);

            for (DomainElement angleElement : resultDomain) {
                temp = DomainElement.combine(input, angleElement);
                implication.set(angleElement, rule.getValueAt(temp));
            }

            result = Operations.binaryOperation(result, implication, Operations.maximum());
        }

        return result;
    }

    public static IFuzzySet imply(IFuzzySet premise1, IFuzzySet premise2, IFuzzySet consequent, IBinaryFunction function) {
        IFuzzySet antecedent = Operations.cartesianProduct(premise1, premise2, function);
        return Operations.cartesianProduct(antecedent, consequent, Operations.minimum());
    }

    public static IFuzzySet imply(IFuzzySet premise1, IFuzzySet premise2, IFuzzySet premise3, IFuzzySet consequent, IBinaryFunction function) {
        return imply(Operations.cartesianProduct(premise1, premise2, function), premise3, consequent, Operations.minimum());
    }
}

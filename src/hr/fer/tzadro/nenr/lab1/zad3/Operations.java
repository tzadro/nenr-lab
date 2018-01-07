package hr.fer.tzadro.nenr.lab1.zad3;

import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;

public class Operations {

    public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction function) {
        MutableFuzzySet newSet = new MutableFuzzySet(set.getDomain());

        for (DomainElement element : set.getDomain()) {
            double value = set.getValueAt(element);
            newSet.set(element, function.valueAt(value));
        }

        return newSet;
    }

    public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, IBinaryFunction function) {
        if (!set1.getDomain().toString().equals(set2.getDomain().toString()))
            throw new IllegalArgumentException("Only sets with same domains supported.");

        MutableFuzzySet newSet = new MutableFuzzySet(set1.getDomain());

        for (DomainElement element : set1.getDomain()) {
            double value1 = set1.getValueAt(element);
            double value2 = set2.getValueAt(element);
            newSet.set(element, function.valueAt(value1, value2));
        }

        return newSet;
    }

    public static IFuzzySet cartesianProduct(IFuzzySet set1, IFuzzySet set2, IBinaryFunction function) {
        IDomain domain = Domain.combine(set1.getDomain(), set2.getDomain());
        MutableFuzzySet set = new MutableFuzzySet(domain);

        DomainElement element;
        double mu;
        for (DomainElement left : set1.getDomain()) {
            for (DomainElement right : set2.getDomain()) {
                element = DomainElement.combine(left, right);
                mu = function.valueAt(set1.getValueAt(left), set2.getValueAt(right));
                set.set(element, mu);
            }
        }

        return set;
    }

    public static IUnaryFunction zadehNot() {
        return new IUnaryFunction() {

            @Override
            public double valueAt(double x) {
                return 1 - x;
            }
        };
    }

    public static IBinaryFunction zadehAnd() {
        return minimum();
    }

    public static IBinaryFunction zadehOr() {
        return maximum();
    }

    public static IBinaryFunction hamacherTNorm(double v) {
        return new IBinaryFunction() {

            @Override
            public double valueAt(double x1, double x2) {
                return (x1 * x2) / (v + (1 - v) * (x1 + x2 - x1 * x2));
            }
        };
    }

    public static IBinaryFunction hamacherSNorm(double v) {
        return new IBinaryFunction() {

            @Override
            public double valueAt(double x1, double x2) {
                return (x1 + x2 - (2 - v) * x1 * x2) / (1 - (1 - v) * x1 * x2);
            }
        };
    }

    public static IBinaryFunction minimum() {
        return new IBinaryFunction() {

            @Override
            public double valueAt(double x1, double x2) {
                return Math.min(x1, x2);
            }
        };
    }

    public static IBinaryFunction maximum() {
        return new IBinaryFunction() {

            @Override
            public double valueAt(double x1, double x2) {
                return Math.max(x1, x2);
            }
        };
    }

    public static IBinaryFunction product() {
        return new IBinaryFunction() {

            @Override
            public double valueAt(double x1, double x2) {
                return x1 * x2;
            }
        };
    }
}

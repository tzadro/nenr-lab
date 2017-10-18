package hr.fer.tzadro.nenr.lab1.zad3;

import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
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
        // todo: only sets with same domains supported

        MutableFuzzySet newSet = new MutableFuzzySet(set1.getDomain());

        for (DomainElement element : set1.getDomain()) {
            double value1 = set1.getValueAt(element);
            double value2 = set2.getValueAt(element);
            newSet.set(element, function.valueAt(value1, value2));
        }

        return newSet;
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
        return new IBinaryFunction() {

            @Override
            public double valueAt(double x1, double x2) {
                return Math.min(x1, x2);
            }
        };
    }

    public static IBinaryFunction zadehOr() {
        return new IBinaryFunction() {

            @Override
            public double valueAt(double x1, double x2) {
                return Math.max(x1, x2);
            }
        };
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
}

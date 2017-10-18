package hr.fer.tzadro.nenr.lab1.zad3;

import hr.fer.tzadro.nenr.lab1.zad1.Debug;
import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;

public class Main {

    public static void main(String[] args) {
        IDomain d = Domain.intRange(0, 11);

        IFuzzySet set1 = new MutableFuzzySet(d).set(DomainElement.of(0), 1.0)
                                               .set(DomainElement.of(1), 0.8)
                                               .set(DomainElement.of(2), 0.6)
                                               .set(DomainElement.of(3), 0.4)
                                               .set(DomainElement.of(4), 0.2);
        Debug.print(set1, "Set1:");

        IFuzzySet notSet1 = Operations.unaryOperation(set1, Operations.zadehNot());
        Debug.print(notSet1, "notSet1:");

        IFuzzySet union = Operations.binaryOperation(set1, notSet1, Operations.zadehOr());
        Debug.print(union, "Set1 union notSet1:");

        IFuzzySet hinters = Operations.binaryOperation(set1, notSet1, Operations.hamacherTNorm(1.0));
        Debug.print(hinters, "Set1 intersection with notSet1 using parameterised Hamacher T norm with parameter 1.0:");
    }
}

/*
Set1:
d(0)=1.000000
d(1)=0.800000
d(2)=0.600000
d(3)=0.400000
d(4)=0.200000
d(5)=0.000000
d(6)=0.000000
d(7)=0.000000
d(8)=0.000000
d(9)=0.000000
d(10)=0.000000

notSet1:
d(0)=0.000000
d(1)=0.200000
d(2)=0.400000
d(3)=0.600000
d(4)=0.800000
d(5)=1.000000
d(6)=1.000000
d(7)=1.000000
d(8)=1.000000
d(9)=1.000000
d(10)=1.000000

Set1 union notSet1:
d(0)=1.000000
d(1)=0.800000
d(2)=0.600000
d(3)=0.600000
d(4)=0.800000
d(5)=1.000000
d(6)=1.000000
d(7)=1.000000
d(8)=1.000000
d(9)=1.000000
d(10)=1.000000

Set1 intersection with notSet1 using parameterised Hamacher T norm with parameter 1.0:
d(0)=0.000000
d(1)=0.160000
d(2)=0.240000
d(3)=0.240000
d(4)=0.160000
d(5)=0.000000
d(6)=0.000000
d(7)=0.000000
d(8)=0.000000
d(9)=0.000000
d(10)=0.000000
*/

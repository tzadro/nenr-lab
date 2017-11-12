package hr.fer.tzadro.nenr.lab1.zad2;

import hr.fer.tzadro.nenr.lab1.zad1.Debug;
import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;

public class Main {

    public static void main(String[] args) {
        IDomain d = Domain.intRange(0, 11); // {0,1,...,10}

        IFuzzySet set1 = new MutableFuzzySet(d)
                .set(DomainElement.of(0), 1.0)
                .set(DomainElement.of(1), 0.8)
                .set(DomainElement.of(2), 0.6)
                .set(DomainElement.of(3), 0.4)
                .set(DomainElement.of(4), 0.2);
        Debug.print(set1, "Set1:");

        IDomain d2 = Domain.intRange(-5, 6); // {-5,-4,...,4,5}
        IFuzzySet set2 = new CalculatedFuzzySet(
                d2,
                StandardFuzzySets.lambdaFunction(-4, 0, 4)
        );
        Debug.print(set2, "Set2:");
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

Set2:
d(-5)=0.000000
d(-4)=0.000000
d(-3)=0.250000
d(-2)=0.500000
d(-1)=0.750000
d(0)=1.000000
d(1)=0.750000
d(2)=0.500000
d(3)=0.250000
d(4)=0.000000
d(5)=0.000000
*/

package hr.fer.tzadro.nenr.lab2.zad2;

import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;
import hr.fer.tzadro.nenr.lab2.zad1.Relations;

public class Main {

    public static void main(String[] args) {
        IDomain u1 = Domain.intRange(1, 5); // {1,2,3,4}
        IDomain u2 = Domain.intRange(1, 4); // {1,2,3}
        IDomain u3 = Domain.intRange(1, 5); // {1,2,3,4}

        IFuzzySet r1 = new MutableFuzzySet(Domain.combine(u1, u2))
                .set(DomainElement.of(1,1), 0.3)
                .set(DomainElement.of(1,2), 1)
                .set(DomainElement.of(3,3), 0.5)
                .set(DomainElement.of(4,3), 0.5);

        IFuzzySet r2 = new MutableFuzzySet(Domain.combine(u2, u3))
                .set(DomainElement.of(1,1), 1)
                .set(DomainElement.of(2,1), 0.5)
                .set(DomainElement.of(2,2), 0.7)
                .set(DomainElement.of(3,3), 1)
                .set(DomainElement.of(3,4), 0.4);

        IFuzzySet r1r2 = Relations.compositionOfBinaryRelations(r1, r2);

        for(DomainElement e : r1r2.getDomain()) {
            System.out.println("mu(" + e + ")=" + r1r2.getValueAt(e));
        }
    }
}

/*
mu((1,1))=0.5
mu((1,2))=0.7
mu((1,3))=0.0
mu((1,4))=0.0
mu((2,1))=0.0
mu((2,2))=0.0
mu((2,3))=0.0
mu((2,4))=0.0
mu((3,1))=0.0
mu((3,2))=0.0
mu((3,3))=0.5
mu((3,4))=0.4
mu((4,1))=0.0
mu((4,2))=0.0
mu((4,3))=0.5
mu((4,4))=0.4
*/

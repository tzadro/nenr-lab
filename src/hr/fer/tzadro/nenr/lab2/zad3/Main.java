package hr.fer.tzadro.nenr.lab2.zad3;

import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;
import hr.fer.tzadro.nenr.lab2.zad1.Relations;

public class Main {

    public static void main(String[] args) {
        IDomain u = Domain.intRange(1, 5); // {1,2,3,4}

        IFuzzySet r = new MutableFuzzySet(Domain.combine(u, u))
                .set(DomainElement.of(1, 1), 1)
                .set(DomainElement.of(2, 2), 1)
                .set(DomainElement.of(3, 3), 1)
                .set(DomainElement.of(4, 4), 1)
                .set(DomainElement.of(1, 2), 0.3)
                .set(DomainElement.of(2, 1), 0.3)
                .set(DomainElement.of(2, 3), 0.5)
                .set(DomainElement.of(3, 2), 0.5)
                .set(DomainElement.of(3, 4), 0.2)
                .set(DomainElement.of(4, 3), 0.2);
        IFuzzySet r2 = r;

        System.out.println("Početna relacija je neizrazita relacija ekvivalencije? " + Relations.isFuzzyEquivalence(r2));
        System.out.println();

        for (int i = 1; i <= 3; i++) {
            r2 = Relations.compositionOfBinaryRelations(r2, r);

            System.out.println("Broj odrađenih kompozicija: " + i + ". Relacija je:");

            for (DomainElement e : r2.getDomain()) {
                System.out.println("mu(" + e + ")=" + r2.getValueAt(e));
            }

            System.out.println("Ova relacija je neizrazita relacija ekvivalencije? " + Relations.isFuzzyEquivalence(r2));
            System.out.println();
        }
    }
}

/*
Početna relacija je neizrazita relacija ekvivalencije? false

Broj odrađenih kompozicija: 1. Relacija je:
mu((1,1))=1.0
mu((1,2))=0.3
mu((1,3))=0.3
mu((1,4))=0.0
mu((2,1))=0.3
mu((2,2))=1.0
mu((2,3))=0.5
mu((2,4))=0.2
mu((3,1))=0.3
mu((3,2))=0.5
mu((3,3))=1.0
mu((3,4))=0.2
mu((4,1))=0.0
mu((4,2))=0.2
mu((4,3))=0.2
mu((4,4))=1.0
Ova relacija je neizrazita relacija ekvivalencije? false

Broj odrađenih kompozicija: 2. Relacija je:
mu((1,1))=1.0
mu((1,2))=0.3
mu((1,3))=0.3
mu((1,4))=0.2
mu((2,1))=0.3
mu((2,2))=1.0
mu((2,3))=0.5
mu((2,4))=0.2
mu((3,1))=0.3
mu((3,2))=0.5
mu((3,3))=1.0
mu((3,4))=0.2
mu((4,1))=0.2
mu((4,2))=0.2
mu((4,3))=0.2
mu((4,4))=1.0
Ova relacija je neizrazita relacija ekvivalencije? true

Broj odrađenih kompozicija: 3. Relacija je:
mu((1,1))=1.0
mu((1,2))=0.3
mu((1,3))=0.3
mu((1,4))=0.2
mu((2,1))=0.3
mu((2,2))=1.0
mu((2,3))=0.5
mu((2,4))=0.2
mu((3,1))=0.3
mu((3,2))=0.5
mu((3,3))=1.0
mu((3,4))=0.2
mu((4,1))=0.2
mu((4,2))=0.2
mu((4,3))=0.2
mu((4,4))=1.0
Ova relacija je neizrazita relacija ekvivalencije? true
*/

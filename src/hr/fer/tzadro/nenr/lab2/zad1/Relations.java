package hr.fer.tzadro.nenr.lab2.zad1;

import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;

import static hr.fer.tzadro.nenr.lab1.zad3.Operations.binaryOperation;

public class Relations {

    public static boolean isUtimesURelation(IFuzzySet relation) {
        assert2D(relation);

        IDomain domain = relation.getDomain();
        IDomain[] components = domain.getComponents();

        return components[0].toString().equals(components[1].toString());
    }

    public static boolean isSymmetric(IFuzzySet relation) {
        assertUtimeURelation(relation);
        int i, j;

        for (DomainElement element : relation.getDomain()) {
            i = element.getComponentValue(0);
            j = element.getComponentValue(1);

            if (i != j) {
                DomainElement symmetricElement = DomainElement.of(j, i);

                if (relation.getValueAt(element) != relation.getValueAt(symmetricElement)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isReflexive(IFuzzySet relation) {
        assertUtimeURelation(relation);
        int i, j;

        for (DomainElement element : relation.getDomain()) {
            i = element.getComponentValue(0);
            j = element.getComponentValue(1);

            if (i == j && relation.getValueAt(element) != 1.) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMaxMinTransitive(IFuzzySet relation) {
        assertUtimeURelation(relation);

        IDomain domain = relation.getDomain();
        IDomain component = domain.getComponent(0);

        DomainElement leftElement, rightElement;
        double temp, mu, muA, muB;
        for (DomainElement domainElement : domain) {
            mu = 0;

            for (DomainElement componentElement : component) {
                leftElement = DomainElement.of(domainElement.getComponentValue(0), componentElement.getComponentValue(0));
                rightElement = DomainElement.of(componentElement.getComponentValue(0), domainElement.getComponentValue(1));

                muA = relation.getValueAt(leftElement);
                muB= relation.getValueAt(rightElement);
                if (muA > muB)
                    temp = muB;
                else
                    temp = muA;

                if (temp > mu)
                    mu = temp;
            }

            if (relation.getValueAt(domainElement) < mu)
                return false;
        }
        return true;
    }

    public static IFuzzySet compositionOfBinaryRelations(IFuzzySet relation1, IFuzzySet relation2) {
        assert2D(relation1);
        assert2D(relation2);
        assertMiddle(relation1, relation2);

        IDomain domain1 = relation1.getDomain();
        IDomain domain2 = relation2.getDomain();

        IDomain left = domain1.getComponent(0);
        IDomain middle = domain1.getComponent(1);
        IDomain right = domain2.getComponent(1);

        IDomain compositeDomain = Domain.combine(left, right);
        MutableFuzzySet composite = new MutableFuzzySet(compositeDomain);

        DomainElement leftElement, rightElement;
        double mu, muA, muB, temp;
        for (DomainElement compositeElement : compositeDomain) {
            mu = 0;

            for (DomainElement middleElement : middle) {
                leftElement = DomainElement.of(compositeElement.getComponentValue(0), middleElement.getComponentValue(0));
                rightElement = DomainElement.of(middleElement.getComponentValue(0), compositeElement.getComponentValue(1));

                // temp = relation1.getValueAt(leftElement) * relation2.getValueAt(rightElement);
                muA = relation1.getValueAt(leftElement);
                muB= relation2.getValueAt(rightElement);
                if (muA > muB)
                    temp = muB;
                else
                    temp = muA;

                if (temp > mu)
                    mu = temp;
            }

            composite.set(compositeElement, mu);
        }

        return composite;
    }

    public static boolean isFuzzyEquivalence(IFuzzySet relation) {
        return isReflexive(relation)
                && isSymmetric(relation)
                && isMaxMinTransitive(relation);
    }

    private static void assertUtimeURelation(IFuzzySet relation) {
        if (!isUtimesURelation(relation))
            throw new IllegalArgumentException("Must be UxU relation.");
    }

    private static void assert2D(IFuzzySet relation) {
        if (relation.getDomain().getNumberOfComponents() != 2)
            throw new IllegalArgumentException("Must be 2D.");
    }

    private static void assertMiddle(IFuzzySet relation1, IFuzzySet relation2) {
        if (!relation1.getDomain().getComponent(1).toString().equals(relation2.getDomain().getComponent(0).toString()))
            throw new IllegalArgumentException("Dimensions not compatible.");
    }
}

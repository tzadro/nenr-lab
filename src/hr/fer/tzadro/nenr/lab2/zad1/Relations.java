package hr.fer.tzadro.nenr.lab2.zad1;

import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;

public class Relations {

    public static boolean isUTimesURelation(IFuzzySet relation) {
        assert2D(relation);

        IDomain domain = relation.getDomain();
        IDomain[] components = domain.getComponents();

        return components[0].toString().equals(components[1].toString());
    }

    public static boolean isSymmetric(IFuzzySet relation) {
        assertUTimesURelation(relation);

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
        assertUTimesURelation(relation);
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
        assertUTimesURelation(relation);

        IDomain domain = relation.getDomain();
        IDomain component = domain.getComponent(0);

        double mu;
        for (DomainElement element : domain) {
            mu = findMaxMin(element, component, relation, relation);

            if (relation.getValueAt(element) < mu)
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

        IDomain leftComponent = domain1.getComponent(0);
        IDomain middleComponent = domain1.getComponent(1);
        IDomain rightComponent = domain2.getComponent(1);

        IDomain compositeDomain = Domain.combine(leftComponent, rightComponent);
        MutableFuzzySet composite = new MutableFuzzySet(compositeDomain);

        double mu;
        for (DomainElement compositeElement : compositeDomain) {
            mu = findMaxMin(compositeElement, middleComponent, relation1, relation2);
            composite.set(compositeElement, mu);
        }

        return composite;
    }

    public static boolean isFuzzyEquivalence(IFuzzySet relation) {
        return isReflexive(relation)
                && isSymmetric(relation)
                && isMaxMinTransitive(relation);
    }

    private static double findMaxMin(DomainElement compositeElement, IDomain middleComponent, IFuzzySet relation1, IFuzzySet relation2) {
        DomainElement leftElement, rightElement;
        double mu = 0, mu1, mu2;

        for (DomainElement componentElement : middleComponent) {
            leftElement = DomainElement.of(compositeElement.getComponentValue(0), componentElement.getComponentValue(0));
            rightElement = DomainElement.of(componentElement.getComponentValue(0), compositeElement.getComponentValue(1));

            mu1 = relation1.getValueAt(leftElement);
            mu2 = relation2.getValueAt(rightElement);

            mu = Math.max(Math.min(mu1, mu2), mu);
        }

        return mu;
    }

    private static void assertUTimesURelation(IFuzzySet relation) {
        if (!isUTimesURelation(relation))
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

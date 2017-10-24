package hr.fer.tzadro.nenr.lab2.zad1;

import hr.fer.tzadro.nenr.lab1.zad1.Domain;
import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad2.MutableFuzzySet;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;

import static hr.fer.tzadro.nenr.lab1.zad3.Operations.binaryOperation;

public class Relations {

    public static boolean isUtimesURelation(IFuzzySet relation) {
        IDomain domain = relation.getDomain();

        if (domain.getNumberOfComponents() != 2)
            throw new IllegalArgumentException("Domain not 2D.");

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

        return true; // todo: implement
    }

    public static IFuzzySet compositionOfBinaryRelations(IFuzzySet relation1, IFuzzySet relation2) {
        assert2D(relation1);
        assert2D(relation2);

        IDomain domain1 = relation1.getDomain();
        IDomain domain2 = relation2.getDomain();

        if (!domain1.getComponent(1).toString().equals(domain2.getComponent(0).toString()))
            throw new IllegalArgumentException("Dimensions not compatible.");

        IDomain compositeDomain = Domain.combine(domain1.getComponent(0), domain2.getComponent(1))
        IFuzzySet composite = new MutableFuzzySet(compositeDomain);

        // todo: implement
    }

    private static void assertUtimeURelation(IFuzzySet relation) {
        if (!isUtimesURelation(relation))
            throw new IllegalArgumentException("Must be UxU relation.");
    }

    private static void assert2D(IFuzzySet relation) {
        if (relation.getDomain().getNumberOfComponents() != 2)
            throw new IllegalArgumentException("Must be 2D.");
    }
}

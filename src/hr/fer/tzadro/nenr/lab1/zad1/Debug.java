package hr.fer.tzadro.nenr.lab1.zad1;

import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;

public class Debug {

    public static void print(IDomain domain, String headingText) {
        if (headingText != null) {
            System.out.println(headingText);
        }

        for (DomainElement e : domain) {
            System.out.println("Element domene: " + e);
        }

        System.out.println("Kardinalitet domene je: " + domain.getCardinality());
        System.out.println();
    }

    public static void print(IFuzzySet fuzzySet, String headingText) {
        if (headingText != null) {
            System.out.println(headingText);
        }

        for (DomainElement e : fuzzySet.getDomain()) {
            System.out.println("d" + e.toString() + "=" + fuzzySet.getValueAt(e));
        }

        System.out.println();
    }
}
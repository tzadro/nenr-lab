package hr.fer.tzadro.nenr.lab1;

import java.util.Iterator;

public abstract class Domain extends IDomain {

    public Domain() { }

    public static IDomain intRange(int first, int last) {
        return new SimpleDomain(first, last);
    }

    public static Domain combine(IDomain, IDomain) {

    }
}
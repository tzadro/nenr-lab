package hr.fer.tzadro.nenr.lab1;

import java.util.Arrays;

public class DomainElement {
    private int[] values;

    public DomainElement(int[] values) {
        this.values = values;
    }

    public int getNumberOfComponents() {
        return values.length;
    }

    public int getComponentValue(int component) {
        return values[component];
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // TODO:
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // TODO:
    }

    @Override
    public String toString() {
        return Arrays.toString(values); // TODO: [ into (
    }

    public static DomainElement of(int[] values) {
        return new DomainElement(values);
    }
}
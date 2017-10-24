package hr.fer.tzadro.nenr.lab1.zad1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }

    @Override
    public String toString() {
        return "(" + IntStream.range(0, getNumberOfComponents())
                              .mapToObj(i -> Integer.toString(getComponentValue(i)))
                              .collect(Collectors.joining(", ")) + ")";
    }

    public static DomainElement of(int[] values) {
        return new DomainElement(values);
    }

    public static DomainElement of(int value) {
        return of(new int[]{value});
    }


    public static DomainElement of(int value1, int value2) {
        return of(new int[]{value1, value2});
    }
}
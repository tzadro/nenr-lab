package hr.fer.tzadro.nenr.lab1.zad1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompositeDomain extends Domain {
    private SimpleDomain[] components;

    public CompositeDomain(SimpleDomain[] components) {
        this.components = components;
    }

    @Override
    public int indexOfElement(DomainElement element) {
        if (element.getNumberOfComponents() != getNumberOfComponents())
            throw new IllegalArgumentException("Number of components not same.");

        return IntStream.range(0, getNumberOfComponents())
                        .map(i -> components[i].indexOfElement(DomainElement.of(element.getComponentValue(i))) * recursiveSize(i + 1))
                        .sum();
    }

    @Override
    public DomainElement elementForIndex(int index) {

        int numOfComponents = getNumberOfComponents();
        int[] values = new int[numOfComponents];

        for (int i = 0; i < numOfComponents; i++) {
            int element = (index / recursiveSize(i + 1)) % components[i].getCardinality();
            values[i] = components[i].elementForIndex(element).getComponentValue(0);
        }

        return new DomainElement(values);
    }

    @Override
    public Iterator<DomainElement> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < getCardinality();
            }

            @Override
            public DomainElement next() {
                return elementForIndex(currentIndex++);
            }
        };
    }

    @Override
    public int getCardinality() {
        return Arrays.stream(components)
                     .mapToInt(e -> e.getCardinality())
                     .reduce(1, (a, b) -> a * b);
    }

    @Override
    public IDomain getComponent(int component) {
        if (component < 0 || component >= components.length)
            throw new IllegalArgumentException("Out of range.");

        return components[component];
    }

    @Override
    public SimpleDomain[] getComponents() {
        return components;
    }

    @Override
    public int getNumberOfComponents() {
        return components.length;
    }

    @Override
    public String toString() {
        return "{" + IntStream.range(0, getCardinality())
                .mapToObj(i -> elementForIndex(i).toString())
                .collect(Collectors.joining(", ")) + "}";
    }

    private int recursiveSize(int from) {
        if (from >= components.length)
            return 1;

        return components[from].getCardinality() * recursiveSize(from + 1);
    }
}
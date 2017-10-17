package hr.fer.tzadro.nenr.lab1;

import java.util.Iterator;
import java.util.stream.IntStream;

public class SimpleDomain extends Domain {
    private int first;
    private int last;

    public SimpleDomain(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public int indexOfElement(DomainElement element) {
        if (element.getNumberOfComponents() != getNumberOfComponents())
            throw new IllegalArgumentException("Number of components not same.");

        if (element.getComponentValue(0) < first && element.getComponentValue(0) >= last)
            throw new IllegalArgumentException("Out of bounds.");

        return element.getComponentValue(0) - first;
    }

    @Override
    public DomainElement elementForIndex(int index) {
        return new DomainElement(new int[]{first + index});
    }

    @Override
    public int getCardinality() {
        return last - first;
    }

    @Override
    public IDomain getComponent(int component) {
        if (component != 0)
            throw new IllegalArgumentException("Out of range.");

        return this;
    }

    @Override
    public IDomain[] getComponents() {
        return new IDomain[]{this};
    }

    @Override
    public int getNumberOfComponents() {
        return 1;
    }

    @Override
    public Iterator<DomainElement> iterator() {
        return IntStream.range(first, last)
                        .mapToObj(e -> new DomainElement(new int[]{e}))
                        .iterator();
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }
}
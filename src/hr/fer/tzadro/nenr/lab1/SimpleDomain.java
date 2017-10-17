package hr.fer.tzadro.nenr.lab1;

import com.sun.javaws.exceptions.InvalidArgumentException;

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
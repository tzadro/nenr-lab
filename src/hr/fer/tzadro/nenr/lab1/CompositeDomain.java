package hr.fer.tzadro.nenr.lab1;

import java.util.Iterator;

public class CompositeDomain extends Domain {
    private SimpleDomain[] components;

    public CompositeDomain(SimpleDomain[] components) {
        this.components = components;
    }

    @Override
    public Iterator<DomainElement> iterator() {
        return null; // TODO:
    }

    @Override
    public int getCardinality() {
        return 0; // TODO:
    }

    @Override
    public IDomain getComponent(int component) {
        if (component < 0 || component >= components.length)
            throw new IllegalArgumentException("Out of range.");

        return components[component];
    }

    @Override
    public int getNumberOfComponents() {
        return components.length;
    }
}
package hr.fer.tzadro.nenr.lab1.zad2;

import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;

public class MutableFuzzySet implements IFuzzySet {
    private IDomain domain;
    private double[] memberships;

    public MutableFuzzySet(IDomain domain) {
        this.domain = domain;
        memberships = new double[domain.getCardinality()];
    }

    @Override
    public IDomain getDomain() {
        return domain;
    }

    @Override
    public double getValueAt(DomainElement element) {
        return memberships[domain.indexOfElement(element)];
    }

    public MutableFuzzySet set(DomainElement element, double mu) {
        memberships[domain.indexOfElement(element)] = mu;
        return this;
    }
}

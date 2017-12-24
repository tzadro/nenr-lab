package hr.fer.tzadro.nenr.lab1.zad2;

import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad1.IDomain;

public interface IFuzzySet {
    IDomain getDomain();

    double getValueAt(DomainElement element);
}

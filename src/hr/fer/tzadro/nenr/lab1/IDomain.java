package hr.fer.tzadro.nenr.lab1;

public interface IDomain extends Iterable<DomainElement> {
    int getCardinality();
    IDomain getComponent(int component);
    IDomain[] getComponents();
    int getNumberOfComponents();
    int indexOfElement(DomainElement element);
    DomainElement elementForIndex(int index);
}
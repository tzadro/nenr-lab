package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad1.DomainElement;
import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;

public class COADefuzzifier implements IDefuzzifier {

    public COADefuzzifier() { }

    @Override
    public double defuzzify(IFuzzySet set) {
        assert1D(set);

        double numerator = 0, denominator = 0;

        for (DomainElement element : set.getDomain()) {
            numerator += element.getComponentValue(0) * set.getValueAt(element);
            denominator += set.getValueAt(element);
        }

        if (denominator == 0)
            return 0;

        return numerator / denominator;
    }

    private void assert1D(IFuzzySet set) {
        if (set.getDomain().getNumberOfComponents() != 1)
            throw new IllegalArgumentException("Must be 1D.");
    }
}

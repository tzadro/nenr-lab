package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad2.IFuzzySet;

public interface IDefuzzifier {
    double defuzzify(IFuzzySet set);
}
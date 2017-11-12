package hr.fer.tzadro.nenr.lab3;

import hr.fer.tzadro.nenr.lab1.zad3.IBinaryFunction;
import hr.fer.tzadro.nenr.lab1.zad3.Operations;

public class Zad2 {

    public static void main(String[] args) {
        int L = 50, D = 150, LK = 50, DK = 60, V = 20, S = 0;
        int akcel, kormilo, positionOffset, trailOffset;

        IDefuzzifier def = new COADefuzzifier();
        IBinaryFunction tNorm = Operations.minimum();
        IFuzzySystem fsAkcel = new AkcelFuzzySystemMin(def, tNorm);
        IFuzzySystem fsKormilo = new KormiloFuzzySystemMin(def, tNorm);

        positionOffset = (int) (-10 + (1. * L) / (L + D) * 20); // [-10, 10]
        trailOffset = (int) (-10 + (1. * LK) / (LK + DK) * 20); // [-10, 10]

        akcel = fsAkcel.infer(positionOffset, trailOffset, V, S, true);
        System.out.println(akcel + "\n");
        kormilo = fsKormilo.infer(positionOffset, trailOffset, V, S, true);
        System.out.println(kormilo);
    }
}

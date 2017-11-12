package hr.fer.tzadro.nenr.lab3;

public class KormiloFuzzySystemMin implements IFuzzySystem {
    private IDefuzzifier def;

    public KormiloFuzzySystemMin(IDefuzzifier def) {
        this.def = def;
    }

    @Override
    public int infer(int L, int D, int LK, int DK, int V, int S) {

        return 0;
    }
}


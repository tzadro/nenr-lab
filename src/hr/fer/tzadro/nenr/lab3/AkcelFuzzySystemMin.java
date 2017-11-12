package hr.fer.tzadro.nenr.lab3;

public class AkcelFuzzySystemMin implements IFuzzySystem {
    private IDefuzzifier def;

    public AkcelFuzzySystemMin(IDefuzzifier def) {
        this.def = def;
    }

    @Override
    public double infer(int L, int D, int LK, int DK, int V, int S) {

        // todo:

        return 0;
    }
}

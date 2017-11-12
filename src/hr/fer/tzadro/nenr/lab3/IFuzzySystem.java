package hr.fer.tzadro.nenr.lab3;

import java.io.IOException;
import java.io.PrintWriter;

public interface IFuzzySystem {
    int infer(int positionOffset, int trailOffset, int V, int S, PrintWriter writer) throws IOException;
}

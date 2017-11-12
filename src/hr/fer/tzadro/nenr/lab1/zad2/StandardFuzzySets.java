package hr.fer.tzadro.nenr.lab1.zad2;

public class StandardFuzzySets {

    public static IIntUnaryFunction gammaFunction(int alpha, int beta) {
        return new IIntUnaryFunction() {

            @Override
            public double valueAt(int x) {
                if (x < alpha)
                    return 0;
                else if (x >= alpha && x < beta)
                    return 1. * (x - alpha) / (beta - alpha);
                else
                    return 1;
            }
        };
    }

    public static IIntUnaryFunction lambdaFunction(int alpha, int beta, int gamma) {
        return new IIntUnaryFunction() {

            @Override
            public double valueAt(int x) {
                if (x >= alpha && x < beta)
                    return 1. * (x - alpha) / (beta - alpha);
                else if (x >= beta && x < gamma)
                    return 1. * (gamma - x) / (gamma - beta);
                else
                    return 0;
            }
        };
    }

    public static IIntUnaryFunction LFunction(int alpha, int beta) {
        return new IIntUnaryFunction() {

            @Override
            public double valueAt(int x) {
                if (x < alpha)
                    return 1;
                else if (x >= alpha && x < beta)
                    return 1. * (beta - x) / (beta - alpha);
                else
                    return 0;
            }
        };
    }

}

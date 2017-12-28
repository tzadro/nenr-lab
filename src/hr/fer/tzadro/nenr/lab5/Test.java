package hr.fer.tzadro.nenr.lab5;

import hr.fer.tzadro.nenr.lab5.utility.Utility;

public class Test {

    public static void main(String[] args) {
        double[][] a = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] b = new double[][]{{1, 5, 7}, {2, 4, 6}, {1, 2, 3}};
        print(Utility.matmul(a, b));

        System.out.println(Utility.sigmoid(0.5) + " " + Utility.sigmoid(1.7));

        System.out.println(Integer.MIN_VALUE < -Integer.MAX_VALUE);
        System.out.println(-Double.MAX_VALUE < -12345);

        double[] c = new double[]{-0.1231, 0.3232, -10.5};
        System.out.println(Utility.argmax(c));

        double[][] d = Utility.random(3, 3);
        print(d);

        double[] e = new double[]{1, 2, 3};
        print(Utility.sum(a, e));

        print(Utility.transpose(a));
    }

    private static void print(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(String.format("%.2f ", m[i][j]));
            }
            System.out.println();
        }
    }
}

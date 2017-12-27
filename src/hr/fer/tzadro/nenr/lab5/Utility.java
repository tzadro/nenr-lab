package hr.fer.tzadro.nenr.lab5;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Utility {

    // https://stackoverflow.com/questions/34774384/multiply-2-double-matrices-using-streams
    public static double[][] matmul(double[][] m1, double[][] m2) {
        if (m1[0].length != m2.length)
            throw new IllegalArgumentException("Matrix dimensions don't match.");

        return Arrays.stream(m1)
                     .map(r -> IntStream.range(0, m2[0].length)
                                        .mapToDouble(i -> IntStream.range(0, m2.length)
                                                                   .mapToDouble(j -> r[j] * m2[j][i])
                                                                   .sum()
                                        )
                                        .toArray()
                     ).toArray(double[][]::new);
    }

    // http://chronicles.blog.ryanrampersad.com/2009/02/sigmoid-function-in-java/
    public static double sigmoid(double x) {
        return (1 / (1 + Math.pow(Math.E, -x)));
    }

    // https://stackoverflow.com/questions/34861469/compact-stream-expression-for-transposing-double-matrix
    public static double[][] transpose(double[][] m) {
        return IntStream.range(0, m[0].length)
                        .mapToObj(r -> IntStream.range(0, m.length)
                                                .mapToDouble(c -> m[c][r])
                                                .toArray()
                        ).toArray(double[][]::new);
    }

    public static double[][] sum(double[][] m1, double[] m2) {
        return sum(m1, expand(m2, m1[0].length));
    }

    public static double[] sum(double[] m1, double[] m2) {
        if (m1.length != m2.length)
            throw new IllegalArgumentException("Matrix dimensions don't match.");

        int row = m1.length;
        double[] result = new double[row];

        IntStream.range(0, row)
                 .forEach(i -> result[i] = m1[i] + m2[i]);

        return result;
    }

    public static double[][] sum(double[][] m1, double[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length)
            throw new IllegalArgumentException("Matrix dimensions don't match.");

        int row = m1.length;
        int col = m1[0].length;
        double[][] result = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> result[i][j] = m1[i][j] + m2[i][j])
                 );

        return result;
    }

    public static double[][] diff(double[][] m1, double[][] m2) {
        double[][] m2negative = m2.clone();
        IntStream.range(0, m2negative.length).forEach(i -> IntStream.range(0, m2negative[0].length).forEach(j -> m2negative[i][j] = -m2negative[i][j]));
        return sum(m1, m2negative);
    }

    public static double[][] mul(double k, double[][] m) {
        int row = m.length;
        int col = m[0].length;
        double[][] result = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> result[i][j] = k * m[i][j])
                 );

        return result;
    }

    public static double[][] mul(double[][] m1, double[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length)
            throw new IllegalArgumentException("Matrix dimensions don't match.");

        int row = m1.length;
        int col = m1[0].length;
        double[][] result = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> result[i][j] = m1[i][j] * m2[i][j])
                 );

        return result;
    }

    public static double[] mul(double[] m1, double[] m2) {
        if (m1.length != m2.length)
            throw new IllegalArgumentException("Matrix dimensions don't match.");

        int row = m1.length;
        double[] result = new double[row];

        IntStream.range(0, row)
                 .forEach(i -> result[i] = m1[i] * m2[i]);

        return result;
    }

    public static double[] mul(double k, double[] m) {
        int row = m.length;
        double[] result = new double[row];

        IntStream.range(0, row)
                 .forEach(i -> result[i] = k * m[i]);

        return result;
    }

    public static double[][] div(double[][] m, double k) {
        int row = m.length;
        int col = m[0].length;
        double[][] result = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> result[i][j] = m[i][j] / k)
                 );

        return result;
    }

    private static double[][] expand(double[] m, int col) {
        int row = m.length;
        double[][] expanded = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> expanded[i][j] = m[i])
                 );

        return expanded;
    }

    public static double[][] square(double[][] m) {
        return IntStream.range(0, m.length)
                        .mapToObj(i -> IntStream.range(0, m[0].length)
                                                .mapToDouble(j -> m[i][j] * m[i][j])
                                                .toArray()
                        ).toArray(double[][]::new);
    }
}

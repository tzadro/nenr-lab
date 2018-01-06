package hr.fer.tzadro.nenr.lab5.utility;

import java.util.Arrays;
import java.util.Random;
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

    public static double[][] sigmoid(double[][] m) {
        int row = m.length;
        int col = m[0].length;
        double[][] result = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> result[i][j] = sigmoid(m[i][j]))
                 );

        return result;
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
        return sum(m1, expand(m2, m1.length));
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
        if (m1.length != m2.length || m1[0].length != m2[0].length)
            throw new IllegalArgumentException("Matrix dimensions don't match.");

        int row = m1.length;
        int col = m1[0].length;
        double[][] result = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> result[i][j] = m1[i][j] - m2[i][j])
                 );

        return result;
    }

    public static double[] diff(double[] m1, double[] m2) {
        if (m1.length != m2.length)
            throw new IllegalArgumentException("Matrix dimensions don't match.");

        int row = m1.length;
        double[] result = new double[row];

        IntStream.range(0, row)
                 .forEach(i -> result[i] = m1[i] - m2[i]);

        return result;
    }

    public static double[][] diff(double k, double[][] m) {
        int row = m.length;
        int col = m[0].length;
        double[][] result = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> result[i][j] = k - m[i][j])
                 );

        return result;
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

    public static double[] div(double[] m, double k) {
        return IntStream.range(0, m.length)
                        .mapToDouble(i -> m[i] / k)
                        .toArray();
    }

    private static double[][] expand(double[] m, int row) {
        int col = m.length;
        double[][] expanded = new double[row][col];

        IntStream.range(0, row)
                 .forEach(i -> IntStream.range(0, col)
                                        .forEach(j -> expanded[i][j] = m[j])
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

    public static double[] square(double[] m) {
        return IntStream.range(0, m.length)
                        .mapToDouble(i -> m[i] * m[i])
                        .toArray();
    }

    public static int argmax(double[] array) {
        int maxIndex = Integer.MIN_VALUE;
        double maxValue = -Double.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                maxIndex = i;
            }
        }

        if (maxIndex == 5)
            throw new IllegalArgumentException("Something went really wrong..");

        return maxIndex;
    }

    public static double[][] random(int row, int col) {
        double[][] result = new double[row][col];

        IntStream.range(0, result.length)
                 .forEach(i -> IntStream.range(0, result[i].length)
                                        .forEach(j -> result[i][j] = Math.random() * 2 - 1));

        return result;
    }
}

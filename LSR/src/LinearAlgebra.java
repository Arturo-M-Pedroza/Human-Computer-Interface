import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

public class LinearAlgebra {

    // matrix * vector
    public double[] toMultiply(double[][] a, double[] b) {

        int rowsA = a.length;
        int colsA = a[0].length;

        if (colsA != b.length) {
            throw new IllegalArgumentException(
                    "Matrix dimensions must match for multiplication. " +
                            "Columns of A: " + colsA +
                            ", rows of B: " + b.length);
        }

        double[] result = new double[rowsA];

        for (int i = 0; i < rowsA; i++) {
            for (int k = 0; k < colsA; k++) {
                result[i] += a[i][k] * b[k];
            }
        }

        return result;
    }

    // matrix * matrix
    public double[][] toMultiplyMatrix(double[][] a, double[][] b) {

        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException(
                    "Matrix dimensions must match for multiplication. " +
                            "Columns of A: " + colsA +
                            ", rows of B: " + rowsB);
        }

        double[][] result = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    // transpose of matrix
    public double[][] transpose(double[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] transpose = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }

        return transpose;
    }

    // inverse of matrix using Apache Commons Math
    public double[][] matrixInverse(double[][] matrix) {

        RealMatrix original = new Array2DRowRealMatrix(matrix);
        if (!new LUDecomposition(original).getSolver().isNonSingular()) {
            throw new RuntimeException("Matrix is singular, cannot invert.");
        } else {
            RealMatrix inverse = new LUDecomposition(original)
                    .getSolver()
                    .getInverse();

            return inverse.getData();
        }

    }

    public double[][] buildPolynomialMatrix(double[] x, int degree) {

        int n = x.length;

        double[][] matrix = new double[n][degree + 1];

        for (int i = 0; i < n; i++) {

            matrix[i][0] = 1; // intercept

            for (int j = 1; j <= degree; j++) {
                matrix[i][j] = Math.pow(x[i], j);
            }
        }

        return matrix;
    }

    //multiple x variables
    public double[][] buildMultipleMatrix(double[][] x) {

        int n = x.length;
        int cols = x[0].length;

        double[][] matrix = new double[n][cols + 1];

        for (int i = 0; i < n; i++) {

            matrix[i][0] = 1; // intercept

            for (int j = 0; j < cols; j++) {
                matrix[i][j + 1] = x[i][j];
            }
        }

        return matrix;
    }


}


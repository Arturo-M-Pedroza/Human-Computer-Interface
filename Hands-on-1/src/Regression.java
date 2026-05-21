public interface Regression {

    //to compute betas by LSR (least square regression) that uses a matrix desing of the data given
    //x is at least a nx2 matrix of the values of x given in the dataset and the n is just the power of n bethas required
    //y is a matrix (vector) of the values of y given in the dataset
    double[] toComputeBeta(double[] x, double[] y);

    //print the betas calculated in the toComputeBeta function
    void toPrintRegression(double[] betas);

    //predicts the output y for a given input of x
    double toPredict(double x, double[] beta);


}

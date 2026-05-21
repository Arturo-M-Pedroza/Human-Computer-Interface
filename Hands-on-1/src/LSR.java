public class LSR implements Regression{

    //instance of linearAlgebra class to use its functions
    private LinearAlgebra linearAlgebra = new LinearAlgebra();

    //attributes of my LSR
    //onyl for one variable
    private double[] x;
    private double[] y;
    private double[] betas;
    private int degree;
    private double xPredict;
    private double yPredict;

    //needed for to variables
    private double[][] XMulti;

    //MADE BEFORE FOR ONLY LSR WITH 1 VARIABLE :(((((
    public LSR(int degree, double[] x,double[] y) {

        this.degree = degree;
        this.x = x;
        this.y = y;
    }

    public double[] getBetas() {
        return betas;
    }

    public void setxPredict(double xPredict) {
        this.xPredict = xPredict;
    }

    @Override
    public double[] toComputeBeta(double[] x, double[] y) {


        //add the columns of 1's
        double[][] xMatrix = linearAlgebra.buildPolynomialMatrix(this.x, this.degree);

        //all this are for getting the (Xt * X)-1
        double[][] xTranspose = linearAlgebra.transpose(xMatrix); //get trnaspose of x
        double[][] xTimesXTranspose = linearAlgebra.toMultiplyMatrix(xTranspose, xMatrix); //(Xt * X)
        double[][] inverseOfXTransTimesX = linearAlgebra.matrixInverse(xTimesXTranspose);

        //to get the other part of the equation (Xt * Y)
        double[] xTransposeY = linearAlgebra.toMultiply(xTranspose, this.y);

        //get y hat (Xt * X)-1 (Xt * Y)
        double[] yHat = linearAlgebra.toMultiply(inverseOfXTransTimesX, xTransposeY);

        return yHat;
    }

    @Override
    public void toPrintRegression(double[] betas) {
        System.out.print("y = " + betas[0]);

        for(int i = 1; i < betas.length; i++){
            System.out.print(" + " + betas[i] + "x^" + i);

        }
        System.out.println();
    }

    @Override
    public double toPredict(double x, double[] beta) {

        double result = beta[0];

        for(int i = 1; i < beta.length; i++){
            result += beta[i] * Math.pow(x, i);
        }

        return result;
    }

    public void magic() {
        // compute betas
        this.betas = toComputeBeta(this.x, this.y);

        // print regression equation
        toPrintRegression(this.betas);
    }

    public void prediction() {
        this.yPredict = toPredict(this.xPredict, this.betas);

        System.out.println("Prediction for x = " + this.xPredict + " , y =  " + this.yPredict);
    }

    //--------------------------------------------
    //IMPLEMENTED TO CALCULATE LSR WITH MULTIPLE VARIABLES
    //--------------------------------------------------
    public LSR(double[][] XMulti, double[] y) {
        this.XMulti = XMulti;
        this.y = y;
    }

    public double[] toComputeBetaMultiple(double[][] X, double[] y) {

        double[][] Xdesign = linearAlgebra.buildMultipleMatrix(X);

        double[][] Xt = linearAlgebra.transpose(Xdesign);
        double[][] XtX = linearAlgebra.toMultiplyMatrix(Xt, Xdesign);
        double[][] XtX_inv = linearAlgebra.matrixInverse(XtX);

        double[] XtY = linearAlgebra.toMultiply(Xt, y);

        return linearAlgebra.toMultiply(XtX_inv, XtY);
    }

    public void magicMultiple() {
        this.betas = toComputeBetaMultiple(this.XMulti, this.y);
        toPrintRegressionMultiple(this.betas);
    }

    public void toPrintRegressionMultiple(double[] betas) {
        System.out.print("y = " + betas[0]);

        for (int i = 1; i < betas.length; i++) {
            System.out.print(" + " + betas[i] + "x" + i);
        }

        System.out.println();
    }

    public double toPredictMultiple(double[] xValues, double[] beta) {

        double result = beta[0];

        for (int i = 0; i < xValues.length; i++) {
            result += beta[i + 1] * xValues[i];
        }

        return result;
    }

    public void predictionMultiple(double[] xValues) {
        double result = toPredictMultiple(xValues, this.betas);

        System.out.println("Prediction: " + result);
    }

}

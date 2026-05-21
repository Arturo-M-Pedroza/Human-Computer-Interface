public class Main {

    public static void main(String[] args) {

        // datasets

        //linear ex
//        double[] x = {0,1,2,3,4,5};
//        double[] y = {3,5,7,9,11,13};


        //cuadratic
//        double[] x = {0,1,2,3,4};
//        double[] y = {1,4,9,16,25};

        //cubic
//        double[] x = {0,1,2,3};
//        double[] y = {2,5,16,41};

        //4 power
//        double[] x = {0,1,2,3,4};
//        double[] y = {1,5,31,121,341};

        //real case
//        double[] x = {1,2,3,4,5,6,7};
//        double[] y = {1,2,3,4,5,6,7};

        //old implementation for LSR with one variable polynomical solutions
        // create regression object
//        LSR regression = new LSR(4, new double[]{0,1,2,3,4}, new double[]{1,5,31,121,341});
//
//        //calculte the betas and show the results
//        regression.magic();
//
//        // predict a new value
//        //wrapped the class polymorphism
//        regression.setxPredict(6);
//        regression.prediction();

        double[][] X = {
                {41.9,29.1},
                {43.4,29.3},
                {43.9,29.5},
                {44.5,29.7},
                {47.3,29.9},
                {47.5,30.3},
                {47.9,30.5},
                {50.2,30.7},
                {52.8,30.8},
                {53.2,30.9},
                {56.7,31.5},
                {57.0,31.7},
                {63.5,31.9},
                {65.3,32.0},
                {71.1,32.1},
                {77.0,32.5},
                {77.8,32.9}
        };

        double[] y = {
                251.3,251.3,248.3,267.5,273.0,276.5,270.3,
                274.9,285.0,290.0,297.0,302.5,304.5,309.3,
                321.7,330.7,349.0
        };

        LSR regression = new LSR(X, y);

        regression.magicMultiple();

        // ejemplo predicción
        regression.predictionMultiple(new double[]{50, 30});

        //simulacion de cinco experimentos
        double[][] experiemtos = {
                {45.0, 29.5},
                {50.0, 30.0},
                {55.0, 31.0},
                {60.0, 31.5},
                {70.0, 32.5}
        };

        for (int i = 0; i < experiemtos.length; i++) {

            double x1 = experiemtos[i]
                    [0];
            double x2 = experiemtos[i][1];

            double pred = regression.toPredictMultiple(new double[]{x1, x2}, regression.getBetas());

            System.out.println(
                    "Experimento " + (i+1) +
                            " = x1: " + x1 +
                            ", x2: " + x2 +
                            ", Y hat: " + pred
            );
        }
    }
}
package calculations;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class HodgkinHuxleyEquations implements FirstOrderDifferentialEquations {

    private static final double C = 1.0;
    private static final double E_NA = 115.0;
    private static final double E_K = -12.0;
    private static final double E_L = 10.6;
    private static final double G_NA = 120.0;
    private static final double G_K = 36.0;
    private static final double G_L = 0.3;
    private static final double I_MIN = 0;

    private double iMax;

    private double I;
    private double time;

    private double alphaM;
    private double alphaN;
    private double alphaH;
    private double betaM;
    private double betaN;
    private double betaH;


    public HodgkinHuxleyEquations(double iMax, double time) {
        this.iMax = iMax;
        this.time = time;
    }

    @Override
    public int getDimension() {
        return 4;
    }

    @Override
    public void computeDerivatives(double t, double[] x, double[] dxdt) throws MaxCountExceededException, DimensionMismatchException {

        if (t < time * 0.15) {
            I = I_MIN;
        } else {
            I = iMax;
        }

        alphaM = (0.1 * (25 - x[3])) / (Math.exp((25 - x[3]) / 10) - 1);
        betaM = 4 * Math.exp(-x[3] / 18);
        alphaN = (0.01 * (10 - x[3])) / (Math.exp((10 - x[3]) / 10) - 1);
        betaN = 0.125 * Math.exp(-x[3] / 80);
        alphaH = 0.07 * Math.exp(-x[3] / 20);
        betaH = 1 / (Math.exp((30 - x[3]) / 10) + 1);

        dxdt[0] = alphaM * (1 - x[0]) - betaM * x[0];
        dxdt[1] = alphaN * (1 - x[1]) - betaN * x[1];
        dxdt[2] = alphaH * (1 - x[2]) - betaH * x[2];
        dxdt[3] = (-(G_NA * Math.pow(x[0], 3) * x[2] * (x[3] - E_NA) + G_K * Math.pow(x[1], 4) * (x[3] - E_K) + G_L * (x[3] - E_L)) + I) / C;
    }
}

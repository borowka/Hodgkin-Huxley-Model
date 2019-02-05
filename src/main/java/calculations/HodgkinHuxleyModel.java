package calculations;

import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import model.GatingParameters;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;

public class HodgkinHuxleyModel {

    private static final double ALPHA_M = (2.5) / (Math.exp(2.5) - 1);
    private static final double BETA_M = 0;
    private static final double ALPHA_N = (0.1) / (Math.exp(1) - 1);
    private static final double BETA_N = 0.125;
    private static final double ALPHA_H = 0.07;
    private static final double BETA_H = 1 / (Math.exp(3) + 1);

    private GatingParameters gatingParameters;
    private double step;
    private double time;
    private double iMax;
    private double c;
    private double eNa;
    private double eK;
    private double eL;
    private double gNa;
    private double gK;
    private double gL;


    public HodgkinHuxleyModel(double step, double time, double iMax, double c, double eNa, double eK, double eL,
                              double gNa, double gK, double gL) {
        this.gatingParameters = new GatingParameters();
        this.step = step;
        this.time = time;
        this.iMax = iMax;
        this.c = c;
        this.eNa = eNa;
        this.eK = eK;
        this.eL = eL;
        this.gNa = gNa;
        this.gK = gK;
        this.gL = gL;
    }

    public void calculateHodgkinHuxleyModel() {
        FirstOrderDifferentialEquations equations = new HodgkinHuxleyEquations(iMax, time);
        ((HodgkinHuxleyEquations) equations).setC(c);
        ((HodgkinHuxleyEquations) equations).setE_K(eK);
        ((HodgkinHuxleyEquations) equations).setE_L(eL);
        ((HodgkinHuxleyEquations) equations).setE_NA(eNa);
        ((HodgkinHuxleyEquations) equations).setG_K(gK);
        ((HodgkinHuxleyEquations) equations).setG_L(gL);
        ((HodgkinHuxleyEquations) equations).setG_NA(gNa);
        HodgkinHuxleyPath hodgkinHuxleyPath = new HodgkinHuxleyPath(gatingParameters);
        FirstOrderIntegrator integrator = new ClassicalRungeKuttaIntegrator(step);
        integrator.addStepHandler(hodgkinHuxleyPath);

        double m0 = ALPHA_M / (ALPHA_M + BETA_M);
        double n0 = ALPHA_N / (ALPHA_N + BETA_N);
        double h0 = ALPHA_H / (ALPHA_H + BETA_H);
        double u0 = 0;

        double[] Start = {m0, n0, h0, u0};
        double[] Stop = {0, 0, 0, 0};

        integrator.integrate(equations, 0, Start, time, Stop);
    }

    public GatingParameters getGatingParameters() {
        return gatingParameters;
    }


}

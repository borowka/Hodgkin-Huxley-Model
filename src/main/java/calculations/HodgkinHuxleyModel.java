package calculations;

import model.Parameters;
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

    private Parameters parameters;

    public HodgkinHuxleyModel() {
        this.parameters = new Parameters();
    }

    public void calculateHodgkinHuxleyModel() {
        FirstOrderDifferentialEquations equations = new HodgkinHuxleyEquations(50, 100);
        HodgkinHuxleyPath hodgkinHuxleyPath = new HodgkinHuxleyPath(parameters);
        FirstOrderIntegrator integrator = new ClassicalRungeKuttaIntegrator(0.01);
        integrator.addStepHandler(hodgkinHuxleyPath);

        double m0 = ALPHA_M / (ALPHA_M + BETA_M);
        double n0 = ALPHA_N / (ALPHA_N + BETA_N);
        double h0 = ALPHA_H / (ALPHA_H + BETA_H);
        double u0 = 0;

        double[] Start = {m0, n0, h0, u0};
        double[] Stop = {0, 0, 0, 0};

        integrator.integrate(equations, 0, Start, 100, Stop);
    }

    public Parameters getParameters() {
        return parameters;
    }
}

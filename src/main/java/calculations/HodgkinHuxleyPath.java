package calculations;

import model.Parameters;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

public class HodgkinHuxleyPath implements StepHandler {

    private Parameters parameters;

    public HodgkinHuxleyPath(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public void init(double v, double[] doubles, double v1) {
    }

    @Override
    public void handleStep(StepInterpolator stepInterpolator, boolean b) throws MaxCountExceededException {

        double time = stepInterpolator.getCurrentTime();
        double[] x = stepInterpolator.getInterpolatedState();
        parameters.getTimeValues().add(time);
        parameters.getmValues().add(x[0]);
        parameters.getnValues().add(x[1]);
        parameters.gethValues().add(x[2]);
        parameters.getuValues().add(x[3]);

        System.out.println("t: " + time + ", m: " + x[0] + ", n: " + x[1] + ", h: " + x[2] + ", u: " + x[3]);
    }

    public Parameters getParameters() {
        return parameters;
    }
}

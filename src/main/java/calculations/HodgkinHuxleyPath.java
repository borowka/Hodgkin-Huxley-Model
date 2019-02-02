package calculations;

import model.GatingParameters;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

public class HodgkinHuxleyPath implements StepHandler {

    private GatingParameters gatingParameters;

    public HodgkinHuxleyPath(GatingParameters gatingParameters) {
        this.gatingParameters = gatingParameters;
    }

    @Override
    public void init(double v, double[] doubles, double v1) {
    }

    @Override
    public void handleStep(StepInterpolator stepInterpolator, boolean b) throws MaxCountExceededException {

        double time = stepInterpolator.getCurrentTime();
        double[] x = stepInterpolator.getInterpolatedState();
        gatingParameters.getTimeValues().add(time);
        gatingParameters.getmValues().add(x[0]);
        gatingParameters.getnValues().add(x[1]);
        gatingParameters.gethValues().add(x[2]);
        gatingParameters.getuValues().add(x[3]);

        System.out.println("t: " + time + ", m: " + x[0] + ", n: " + x[1] + ", h: " + x[2] + ", u: " + x[3]);
    }

    public GatingParameters getGatingParameters() {
        return gatingParameters;
    }
}

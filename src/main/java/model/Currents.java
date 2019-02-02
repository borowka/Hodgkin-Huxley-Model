package model;

import java.util.ArrayList;
import java.util.List;

public class Currents {

    private static final double E_NA = 115.0;
    private static final double E_K = -12.0;
    private static final double E_L = 10.6;
    private static final double G_NA = 120.0;
    private static final double G_K = 36.0;
    private static final double G_L = 0.3;

    private Parameters parameters;

    private List<Double> sodiumCurrent;
    private List<Double> potassiumCurrent;
    private List<Double> leakageCurrent;
    private List<Double> potassiumConductance;
    private List<Double> sodiumConductance;

    public Currents(Parameters parameters) {
        this.parameters = parameters;
        this.sodiumCurrent = new ArrayList<>();
        this.potassiumCurrent = new ArrayList<>();
        this.leakageCurrent = new ArrayList<>();
        this.potassiumConductance = new ArrayList<>();
        this.sodiumConductance = new ArrayList<>();
    }

    public void calculateCurrent() {

        for (int i = 0; i < parameters.getTimeValues().size(); i++) {
            double iNa = Math.pow(parameters.getmValues().get(i), 3) * G_NA * parameters.gethValues().get(i) * (parameters.getuValues().get(i) - E_NA);
            double iK = Math.pow(parameters.getnValues().get(i), 4) * G_K * (parameters.getuValues().get(i) - E_K);
            double iL = G_L * (parameters.getuValues().get(i) - E_L);
            double cNa = G_NA * Math.pow(parameters.getmValues().get(i), 3) * parameters.gethValues().get(i);
            double cK = G_K * Math.pow(parameters.getnValues().get(i), 4);

            sodiumCurrent.add(iNa);
            potassiumCurrent.add(iK);
            leakageCurrent.add(iL);
            potassiumConductance.add(cK);
            sodiumConductance.add(cNa);
        }

    }

    public List<Double> getSodiumCurrent() {
        return sodiumCurrent;
    }

    public List<Double> getPotassiumCurrent() {
        return potassiumCurrent;
    }

    public List<Double> getLeakageCurrent() {
        return leakageCurrent;
    }

    public List<Double> getPotassiumConductance() {
        return potassiumConductance;
    }

    public List<Double> getSodiumConductance() {
        return sodiumConductance;
    }

}

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

    private List<Double> sodiumCurrent;
    private List<Double> potassiumCurrent;
    private List<Double> leakageCurrent;
    private List<Double> potassiumConductance;
    private List<Double> sodiumConductance;

    public Currents() {
        this.sodiumCurrent = new ArrayList<>();
        this.potassiumCurrent = new ArrayList<>();
        this.leakageCurrent = new ArrayList<>();
        this.potassiumConductance = new ArrayList<>();
        this.sodiumConductance = new ArrayList<>();
    }

    public static Currents calculateCurrent(GatingParameters gatingParameters) {
        Currents currents = new Currents();

        for (int i = 0; i < gatingParameters.getTimeValues().size(); i++) {
            double iNa = Math.pow(gatingParameters.getmValues().get(i), 3) * G_NA * gatingParameters.gethValues().get(i) * (gatingParameters.getuValues().get(i) - E_NA);
            double iK = Math.pow(gatingParameters.getnValues().get(i), 4) * G_K * (gatingParameters.getuValues().get(i) - E_K);
            double iL = G_L * (gatingParameters.getuValues().get(i) - E_L);
            double cNa = G_NA * Math.pow(gatingParameters.getmValues().get(i), 3) * gatingParameters.gethValues().get(i);
            double cK = G_K * Math.pow(gatingParameters.getnValues().get(i), 4);

            currents.getSodiumCurrent().add(iNa);
            currents.getPotassiumCurrent().add(iK);
            currents.getLeakageCurrent().add(iL);
            currents.getPotassiumConductance().add(cK);
            currents.getSodiumConductance().add(cNa);
        }
        return currents;
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

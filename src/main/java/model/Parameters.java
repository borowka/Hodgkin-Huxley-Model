package model;

import java.util.ArrayList;
import java.util.List;

public class Parameters {

    private List<Double> timeValues;
    private List<Double> mValues;
    private List<Double> nValues;
    private List<Double> hValues;
    private List<Double> uValues;

    public Parameters() {
        this.timeValues = new ArrayList<>();
        this.mValues = new ArrayList<>();
        this.nValues = new ArrayList<>();
        this.hValues = new ArrayList<>();
        this.uValues = new ArrayList<>();

    }

    public List<Double> getTimeValues() {
        return timeValues;
    }

    public List<Double> getmValues() {
        return mValues;
    }

    public List<Double> getnValues() {
        return nValues;
    }

    public List<Double> gethValues() {
        return hValues;
    }

    public List<Double> getuValues() {
        return uValues;
    }
}

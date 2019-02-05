package calculations;

import java.util.ArrayList;
import java.util.List;

public class DynamicParameters {

    private List<Double> pulsesList = new ArrayList<>();
    private List<Double> list;
    private double max;
    private double avg;
    private double time;
    private double standDeviation;
    private double frequency;

    public DynamicParameters(List<Double> list, double time) {
        this.list = list;
        this.time = time;
        find();
    }

    public void find(){
        double max1 = list.get(0);
        for (int i = 1; i < list.size()-1; i++) {
            if (list.get(i) > list.get(i - 1)) {
                max1 = list.get(i);
                if (list.get(i + 1) < list.get(i))
                    pulsesList.add(max1);
            }
        }

        max = pulsesList.get(0);
        for (int i = 1; i < pulsesList.size(); i++) {
            if (pulsesList.get(i) > max)
                max = pulsesList.get(i);
        }

        double suma = 0;

        for (int i = 0; i < pulsesList.size(); i++){
            suma += pulsesList.get(i);
        }

        avg = suma/pulsesList.size();

        frequency = pulsesList.size()/time;

        double suma2 = 0;

        for (int i = 0; i < pulsesList.size(); i++){
            suma2 += Math.pow((pulsesList.get(i) - avg), 2);
        }

        standDeviation = Math.sqrt(suma2/(pulsesList.size()-1));
        System.out.println(" Amount of pulses: "  + pulsesList.size());
    }

    public List<Double> getPulsesList() {
        return pulsesList;
    }

    public double getMax() {
        return max;
    }

    public double getAvg() {
        return avg;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getStandDeviation() {
        return standDeviation;
    }
}

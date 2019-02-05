package calculations;

import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class ParametersChart {

    List<Double> listU = new ArrayList<>();
    List<Double> listM = new ArrayList<>();
    List<Double> listN = new ArrayList<>();
    List<Double> listH = new ArrayList<>();

    private ScatterChart chart;

    public ParametersChart(ScatterChart chart) {
        this.chart = chart;
    }

    public void draw(){

        for (int i = -50; i < 150; i+= 5) {
            double u = i;
            double alphaM = (0.1 * (25 - u)) / (Math.exp((25 - u) / 10) - 1);
            double betaM = 4 * Math.exp(-u / 18);
            double alphaN = (0.01 * (10 - u)) / (Math.exp((10 - u) / 10) - 1);
            double betaN = 0.125 * Math.exp(-u / 80);
            double alphaH = 0.07 * Math.exp(-u / 20);
            double betaH = 1 / (Math.exp((30 - u) / 10) + 1);
            listU.add(u);
            double m = alphaM / (alphaM + betaM);
            double n = alphaN / (alphaN + betaN);
            double h = alphaH / (alphaH  + betaH);
            listM.add(m);
            listN.add(n);
            listH.add(h);
            System.out.println("u:" + u + ", m: " + m + ", n: " + n + ", h: " + h);
        }

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        for (int i = 0; i < listU.size(); i++) {
            series1.getData().add(new XYChart.Data(listU.get(i), listM.get(i)));
            series2.getData().add(new XYChart.Data(listU.get(i), listN.get(i)));
            series3.getData().add(new XYChart.Data(listU.get(i), listH.get(i)));
        }
        chart.getData().addAll(series1);
        chart.getData().addAll(series2);
        chart.getData().addAll(series3);
    }
}

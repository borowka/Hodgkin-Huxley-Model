package gui;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartDrawer {


    public void drawChart(ScatterChart chart, List xValues, List yValues, NumberAxis x, NumberAxis y) {
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < xValues.size(); i++) {
            series.getData().add(new XYChart.Data(xValues.get(i), yValues.get(i)));
        }
        chart.getData().addAll(series);
        y.setAutoRanging(true);
        x.setAutoRanging(true);
    }

    public void drawChart(ScatterChart chart, List x1Values, List y1Values, List y2Values, List y3Values, NumberAxis x, NumberAxis y) {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        for (int i = 0; i < x1Values.size(); i++) {
            series1.getData().add(new XYChart.Data(x1Values.get(i), y1Values.get(i)));
            series2.getData().add(new XYChart.Data(x1Values.get(i), y2Values.get(i)));
            series3.getData().add(new XYChart.Data(x1Values.get(i), y3Values.get(i)));
        }
        chart.getData().addAll(series1);
        chart.getData().addAll(series2);
        chart.getData().addAll(series3);
        x.setAutoRanging(true);
        y.setAutoRanging(true);

    }

}

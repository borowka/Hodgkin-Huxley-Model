package gui;

import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartDrawer {


    public void drawChart(ScatterChart chart, List xValues, List yValues) {
        XYChart.Series series = new XYChart.Series();
        //String name = "super";
        //series.setName(name);                          //set its name (display in legend)
        //series.getNode().getStyleClass().add("series " + name);
        for (int i = 0; i < xValues.size(); i++) {
            series.getData().add(new XYChart.Data(xValues.get(i), yValues.get(i)));
        }
        chart.getData().addAll(series);
    }

}

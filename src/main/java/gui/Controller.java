package gui;

import calculations.HodgkinHuxleyModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import model.Currents;
import model.Parameters;

public class Controller {


    @FXML
    private ScatterChart<Number, Number> mInVoltageChart;

    @FXML
    private ScatterChart<Number, Number> uInTimeChart;

    @FXML
    private ScatterChart<Number, Number> hInVoltageChart;

    @FXML
    private ScatterChart<Number, Number> nInVoltageChart;

    @FXML
    private ScatterChart<Number, Number> sodiumInTimeChart;

    @FXML
    private ScatterChart<Number, Number> potassiumInTimeChart;

    @FXML
    private ScatterChart<Number, Number> leakageInTimeChart;

    @FXML
    private ScatterChart<Number, Number> mInTimeChart;

    @FXML
    private ScatterChart<Number, Number> nInTimeChart;

    @FXML
    private ScatterChart<Number, Number> hInTimeChart;

    @FXML
    private Button calculateButton;

    @FXML
    void onClickCalculate(ActionEvent event) {
        HodgkinHuxleyModel hodgkinHuxleyModel =  new HodgkinHuxleyModel();
        hodgkinHuxleyModel.calculateHodgkinHuxleyModel();
        Parameters parameters = hodgkinHuxleyModel.getParameters();
        Currents currents = new Currents(parameters);
        currents.calculateCurrent();
        ChartDrawer chartDrawer = new ChartDrawer();
        chartDrawer.drawChart(mInVoltageChart,parameters.getuValues(), parameters.getmValues());
        chartDrawer.drawChart(nInVoltageChart, parameters.getuValues(), parameters.getnValues());
        chartDrawer.drawChart(hInVoltageChart, parameters.getuValues(), parameters.gethValues());
        chartDrawer.drawChart(uInTimeChart, parameters.getTimeValues(), parameters.getuValues());
        chartDrawer.drawChart(mInTimeChart, parameters.getTimeValues(), parameters.getmValues());
        chartDrawer.drawChart(nInTimeChart, parameters.getTimeValues(), parameters.getnValues());
        chartDrawer.drawChart(hInTimeChart, parameters.getTimeValues(), parameters.gethValues());
        chartDrawer.drawChart(sodiumInTimeChart, parameters.getTimeValues(), currents.getSodiumCurrent());
        chartDrawer.drawChart(potassiumInTimeChart, parameters.getTimeValues(), currents.getPotassiumCurrent());
        chartDrawer.drawChart(leakageInTimeChart, parameters.getTimeValues(),currents.getLeakageCurrent());
    }
}

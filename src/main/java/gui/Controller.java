package gui;

import calculations.HodgkinHuxleyModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.Currents;
import model.GatingParameters;

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
    private ScatterChart<Number, Number> sodiumConductanceInTimeChart;

    @FXML
    private ScatterChart<Number, Number> potassiumConductanceInTimeChart;

    @FXML
    private TextField timeText;

    @FXML
    private TextField stepText;

    @FXML
    private TextField iMaxStep;

    @FXML
    private Button calculateButton;

    @FXML
    void onClickCalculate(ActionEvent event) {
        double step = 0;
        double time = 0;
        double iMax = 0;
        try {
            step = Double.parseDouble(stepText.getText());
            time = Double.parseDouble(timeText.getText());
            iMax = Double.parseDouble(iMaxStep.getText());
        }catch (Exception e){
            System.out.println("Not a number");
        }
        HodgkinHuxleyModel hodgkinHuxleyModel =  new HodgkinHuxleyModel(step, time, iMax);
        hodgkinHuxleyModel.calculateHodgkinHuxleyModel();
        GatingParameters gatingParameters = hodgkinHuxleyModel.getGatingParameters();
        Currents currents = Currents.calculateCurrent(gatingParameters);
        ChartDrawer chartDrawer = new ChartDrawer();
        chartDrawer.drawChart(mInVoltageChart, gatingParameters.getuValues(), gatingParameters.getmValues());
        chartDrawer.drawChart(nInVoltageChart, gatingParameters.getuValues(), gatingParameters.getnValues());
        chartDrawer.drawChart(hInVoltageChart, gatingParameters.getuValues(), gatingParameters.gethValues());
        chartDrawer.drawChart(uInTimeChart, gatingParameters.getTimeValues(), gatingParameters.getuValues());
        chartDrawer.drawChart(mInTimeChart, gatingParameters.getTimeValues(), gatingParameters.getmValues());
        chartDrawer.drawChart(nInTimeChart, gatingParameters.getTimeValues(), gatingParameters.getnValues());
        chartDrawer.drawChart(hInTimeChart, gatingParameters.getTimeValues(), gatingParameters.gethValues());
        chartDrawer.drawChart(sodiumInTimeChart, gatingParameters.getTimeValues(), currents.getSodiumCurrent());
        chartDrawer.drawChart(potassiumInTimeChart, gatingParameters.getTimeValues(), currents.getPotassiumCurrent());
        chartDrawer.drawChart(leakageInTimeChart, gatingParameters.getTimeValues(),currents.getLeakageCurrent());
        chartDrawer.drawChart(sodiumConductanceInTimeChart, gatingParameters.getTimeValues(), currents.getSodiumConductance());
        chartDrawer.drawChart(potassiumConductanceInTimeChart, gatingParameters.getTimeValues(), currents.getPotassiumConductance());

    }
}

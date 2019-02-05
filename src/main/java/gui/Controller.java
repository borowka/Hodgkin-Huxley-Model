package gui;

import calculations.DynamicParameters;
import calculations.HodgkinHuxleyModel;
import calculations.ParametersChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Currents;
import model.GatingParameters;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private ScatterChart<Number, Number> mInVoltageChart;

    @FXML
    private NumberAxis MNHxAxis;

    @FXML
    private NumberAxis MNHyAxis;

    @FXML
    private ScatterChart<Number, Number> uInTimeChart;

    @FXML
    private NumberAxis uTxAxis;

    @FXML
    private NumberAxis uTyAxis;

    @FXML
    private ScatterChart<Number, Number> sodiumInTimeChart;

    @FXML
    private NumberAxis naCurrXAxis;

    @FXML
    private NumberAxis naCurrYAxis;

    @FXML
    private ScatterChart<Number, Number> potassiumInTimeChart;

    @FXML
    private NumberAxis kCurrXAxis;

    @FXML
    private NumberAxis kCurrYAxis;

    @FXML
    private ScatterChart<Number, Number> leakageInTimeChart;

    @FXML
    private NumberAxis leakCurrXAxis;

    @FXML
    private NumberAxis leakCurrYAxis;

    @FXML
    private ScatterChart<Number, Number> mInTimeChart;

    @FXML
    private NumberAxis mTxAxis;

    @FXML
    private NumberAxis mTyAxis;

    @FXML
    private ScatterChart<Number, Number> sodiumConductanceInTimeChart;

    @FXML
    private NumberAxis naCondxAxis;

    @FXML
    private NumberAxis naCondYAxis;

    @FXML
    private ScatterChart<Number, Number> potassiumConductanceInTimeChart;

    @FXML
    private NumberAxis kCondXAxis;

    @FXML
    private NumberAxis kCondyAxis;

    @FXML
    private TextField timeText;

    @FXML
    private TextField stepText;

    @FXML
    private TextField iMaxStep;

    @FXML
    private Label deviationLabel;

    @FXML
    private Label pulsesFrequencyLabel;

    @FXML
    private Label maxPulseLabel;

    @FXML
    private Label avgPulseLabel;

    @FXML
    private TextField cText;

    @FXML
    private TextField eNaText;

    @FXML
    private TextField eKText;

    @FXML
    private TextField eLText;

    @FXML
    private TextField gNaText;

    @FXML
    private TextField gKText;

    @FXML
    private TextField gLText;

    @FXML
    private ScatterChart<Number, Number> chart;

    @FXML
    void onClickCalculate(ActionEvent event) {
        double step = 0;
        double time = 0;
        double iMax = 0;
        try {
            step = Double.parseDouble(stepText.getText());
            time = Double.parseDouble(timeText.getText());
            iMax = Double.parseDouble(iMaxStep.getText());
        } catch (Exception e) {
            System.out.println("Not a number");
        }

        double c = Double.parseDouble(cText.getText());
        double eK = Double.parseDouble(eKText.getText());
        double eNa = Double.parseDouble(eNaText.getText());
        double eL = Double.parseDouble(eLText.getText());
        double gK = Double.parseDouble(gKText.getText());
        double gNa = Double.parseDouble(gNaText.getText());
        double gL = Double.parseDouble(gLText.getText());
        HodgkinHuxleyModel hodgkinHuxleyModel = new HodgkinHuxleyModel(step, time, iMax, c, eNa, eK, eL, gNa, gK, gL);
        hodgkinHuxleyModel.calculateHodgkinHuxleyModel();
        GatingParameters gatingParameters = hodgkinHuxleyModel.getGatingParameters();
        Currents currents = Currents.calculateCurrent(gatingParameters);
        ChartDrawer chartDrawer = new ChartDrawer();
        chartDrawer.drawChart(mInVoltageChart, gatingParameters.getuValues(), gatingParameters.getmValues(), gatingParameters.getnValues(), gatingParameters.gethValues(), MNHxAxis, MNHyAxis);
        chartDrawer.drawChart(uInTimeChart, gatingParameters.getTimeValues(), gatingParameters.getuValues(),uTxAxis, uTyAxis);
        chartDrawer.drawChart(mInTimeChart, gatingParameters.getTimeValues(), gatingParameters.getmValues(), gatingParameters.getnValues(),gatingParameters.gethValues(), mTxAxis, mTyAxis);
        chartDrawer.drawChart(sodiumInTimeChart, gatingParameters.getTimeValues(), currents.getSodiumCurrent(), naCurrXAxis, naCurrYAxis);
        chartDrawer.drawChart(potassiumInTimeChart, gatingParameters.getTimeValues(), currents.getPotassiumCurrent(),kCurrXAxis, kCurrYAxis);
        chartDrawer.drawChart(leakageInTimeChart, gatingParameters.getTimeValues(), currents.getLeakageCurrent(), leakCurrXAxis, leakCurrYAxis);
        chartDrawer.drawChart(sodiumConductanceInTimeChart, gatingParameters.getTimeValues(), currents.getSodiumConductance(), naCondxAxis, naCondYAxis);
        chartDrawer.drawChart(potassiumConductanceInTimeChart, gatingParameters.getTimeValues(), currents.getPotassiumConductance(), kCondXAxis, kCondyAxis);

        DynamicParameters dynamicParameters = new DynamicParameters(gatingParameters.getuValues(), time);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        pulsesFrequencyLabel.setText(String.valueOf(formatter.format(dynamicParameters.getFrequency())));
        maxPulseLabel.setText(String.valueOf(formatter.format(dynamicParameters.getMax())));
        avgPulseLabel.setText(String.valueOf(formatter.format(dynamicParameters.getAvg())));
        deviationLabel.setText(String.valueOf(formatter.format(dynamicParameters.getStandDeviation())));
        Double.parseDouble(cText.getText());

        ParametersChart parametersChart = new ParametersChart(chart);
        parametersChart.draw();

    }

    @FXML
    void onClickClear(ActionEvent event) {
        mInVoltageChart.getData().clear();
        uInTimeChart.getData().clear();
        mInTimeChart.getData().clear();
        sodiumInTimeChart.getData().clear();
        potassiumInTimeChart.getData().clear();
        sodiumInTimeChart.getData().clear();
        leakageInTimeChart.getData().clear();
        sodiumConductanceInTimeChart.getData().clear();
        potassiumConductanceInTimeChart.getData().clear();
    }



}

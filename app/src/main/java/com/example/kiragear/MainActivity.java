package com.example.kiragear;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Tire;
import model.Tuning;
import model.Gear;

public class MainActivity extends AppCompatActivity {

    LineChart lineChart;
    ArrayList<Entry> lineEntries = new ArrayList<Entry>();
    ArrayList<Entry> progressiveLineEntries = new ArrayList<Entry>();

    ArrayList<Entry> lineEntries1 = new ArrayList<Entry>();
    ArrayList<Entry> progressiveLineEntries1 = new ArrayList<Entry>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ActivityMainBinding binding =
        // ActivityMainBinding.inflate(getLayoutInflater());
        // View view = binding.getRoot();
        // setContentView(view);

        // LinearLayout li = findViewById(R.id.main_layout);

        // setBackgroundColor()
        // li.setBackgroundColor(Color.parseColor("#rrggbb"));

        // yourView.setBackgroundColor(Color.TRANSPARENT);

        // li.setBackgroundColor(Color.YELLOW);
        //
        // Color mColor = new Color();
        // mColor.red(225);
        // mColor.green(11);
        // mColor.blue(11);
        //// li.setBackgroundColor(mColor);
        //
        // li.setBackgroundColor(Color.parseColor("#ffffff"));

        // li.setBackgroundColor(Color.rgb(226, 11, 11));

        setContentView(R.layout.activity_main);
        lineChart = findViewById(R.id.line_chart);

        generateGraph();

        // barChart = binding.barChart;
        // pieChart = binding.pieChart;

        // barChart.setData(new float[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        // pieChart.setData(new float[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });

        // for (int i = 1; i < 11; i++) {
        // // float value = (float) (Math.random() * 100);
        // float value = (float) 100;
        // barEntries.add(new BarEntry(i, value));
        // lineEntries.add(new Entry(i, value));
        // }

        // lineEntries.add(new Entry(0, 0));
        // lineEntries.add(new Entry(38, 4000));
        // lineEntries.add(new Entry(48, 5000));
        // lineEntries.add(new Entry(57, 6000));
        // lineEntries.add(new Entry(67, 7000));
        // lineEntries.add(new Entry(77, 8000));

        lineChart.setScaleEnabled(false);

        LineDataSet progressivelineDataSet = new LineDataSet(progressiveLineEntries, "Progressive Line");
        progressivelineDataSet.enableDashedLine(3, 20, 0);
        progressivelineDataSet.setLineWidth(2f);
        progressivelineDataSet.setColor(Color.BLUE);

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Line Data");
        // lineDataSet.enableDashedLine(3, 20, 0);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setColor(Color.BLUE);

        LineDataSet progressivelineDataSet1 = new LineDataSet(progressiveLineEntries1, "Progressive Line 1");
        progressivelineDataSet.enableDashedLine(3, 20, 0);
        progressivelineDataSet.setLineWidth(2f);
        progressivelineDataSet1.setColor(Color.RED);

        LineDataSet lineDataSet1 = new LineDataSet(lineEntries1, "Line Data 2");
        lineDataSet1.setLineWidth(2f);
        lineDataSet1.setColors(Color.RED);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet1);
        dataSets.add(progressivelineDataSet);
        dataSets.add(progressivelineDataSet1);

        // lineChart.setData(new LineData(lineDataSet));
        lineChart.setData(new LineData(dataSets));

        lineChart.getDescription().setText("Line Chart");
        lineChart.getDescription().setTextColor(Color.BLACK);
        lineChart.getAxisLeft().setTextColor(Color.BLACK);
        lineChart.getXAxis().setTextColor(Color.BLACK);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        // lineChart.getXAxis().setDrawGridLines(false);

        lineChart.getAxisRight().setEnabled(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);

        // LimitLine ll = new LimitLine(140f, "Critical Blood Pressure");
        // ll.setLineColor(Color.RED);
        // ll.setLineWidth(4f);
        // ll.setTextColor(Color.BLACK);
        // ll.setTextSize(12f);
        // // .. and more styling options
        //
        // leftAxis.addLimitLine(ll);

        lineChart.getXAxis().setLabelCount(5, /* force: */true);
        // lineChart.getXAxis().mAxisMinimum = 0;
        lineChart.getXAxis().setAxisMinimum(0);
        lineChart.getXAxis().setAxisMaximum(250);
//        lineChart.getXAxis().setAxisMaximum(gears.get(4).getrRpmSpeeds().get(8000).floatValue() + 10);

        // lineChart.getXAxis().mAxisMaximum = 200;

        // ArrayList<String> xAxisLables = new ArrayList();
        // xAxisLables.add("0");
        // xAxisLables.add("25");
        // xAxisLables.add("50");
        // xAxisLables.add("75");
        // xAxisLables.add("100");
        // xAxisLables.add("125");
        // // xAxisLables.add("150");
        // // xAxisLables.add("175");
        // // xAxisLables.add("200");
        //
        // // XAxis xAxis = lineChart.getXAxis();
        // // xAxis.setValueFormatter(new ValueFormatter() {
        // // @Override
        // // public String getFormattedValue(float value, AxisBase axis) {
        // // return xAxisLabels.get((int) value);
        // //
        // // }
        // // });
        //
        // lineChart.getXAxis().setValueFormatter(new
        // IndexAxisValueFormatter(xAxisLables));
    }

    public void generateGraph() {
        calculateGear();
        calculateGear1();
    }

    public void calculateGear() {
        Tire tire = new Tire();
        Gear gear = new Gear();
        Tuning tuning = new Tuning();

        tire.setWidth(185);
        tire.setAspectRatio(50);
        tire.setDiameter(17);

        ArrayList<Double> progressivePointRPM = new ArrayList<>();
        ArrayList<Double> progressivePointSpeed = new ArrayList<>();

        double tireCircumference = tire.getCircumference();

        double[] ratio = { 2.976190476, 2.105263158, 1.615508885, 1.277139208, 1.030927835 };

        List<Gear> gears = new ArrayList<Gear>();

        for (int i = 0; i < ratio.length; i++) {
            gear = new Gear();
            gear.setRatio(ratio[i]);
            gears.add(gear);
        }

        double finalDrive = 4.0816;

        tuning.setGears(gears);

        tuning.setTire(tire);

        tuning.calculateSpeed(7000, finalDrive);
        tuning.calculateSpeeds(finalDrive);

        gears = tuning.getGears();

        for (int i = 0; i < gears.size(); i++) {
            if (i != 4) {
                List<Double> speed = gears.get(i).getSpeeds();

                // get last element of array
                double last = speed.get(speed.size() - 1);

                double rpm = calculateRPM(last, gears.get(i + 1).getRatio(), tireCircumference, finalDrive);

                progressivePointRPM.add(rpm);
                progressivePointSpeed.add(last);
            }
        }
        lineEntries = constData(gears, progressivePointRPM, progressivePointSpeed);
        progressiveLineEntries = constructProgressiveLine(progressivePointRPM, progressivePointSpeed);
//         constructData(lineEntries, progressiveLineEntries, gears, progressivePointRPM, progressivePointSpeed);
    }

    public void calculateGear1() {
        Tire tire = new Tire();
        Gear gear = new Gear();
        Tuning tuning = new Tuning();

        tire.setWidth(185);
        tire.setAspectRatio(50);
        tire.setDiameter(17);

        ArrayList<Double> progressivePointRPM = new ArrayList<>();
        ArrayList<Double> progressivePointSpeed = new ArrayList<>();

        double tireCircumference = tire.getCircumference();

        double[] ratio = { 2.976190476, 2.105263158, 1.615508885, 1.277139208, 1.030927835 };

        List<Gear> gears = new ArrayList<Gear>();

        for (int i = 0; i < ratio.length; i++) {
            gear = new Gear();
            gear.setRatio(ratio[i]);
            gears.add(gear);
        }

        double finalDrive = 5.0;

        tuning.setGears(gears);

        tuning.setTire(tire);

        tuning.calculateSpeed(7000, finalDrive);
        tuning.calculateSpeeds(finalDrive);

        gears = tuning.getGears();

        for (int i = 0; i < gears.size(); i++) {
            if (i != 4) {
                List<Double> speed = gears.get(i).getSpeeds();

                // get last element of array
                double last = speed.get(speed.size() - 1);

                double rpm = calculateRPM(last, gears.get(i + 1).getRatio(), tireCircumference, finalDrive);
                progressivePointRPM.add(rpm);
                progressivePointSpeed.add(last);
            }
        }

        lineEntries1 = constData(gears, progressivePointRPM, progressivePointSpeed);

        progressiveLineEntries1 = constructProgressiveLine(progressivePointRPM, progressivePointSpeed);

//         constructData(lineEntries1, progressiveLineEntries1, gears, progressivePointRPM, progressivePointSpeed);
    }

    public void constructData(ArrayList<Entry> lineEntries, ArrayList<Entry> progressiveLineEntries, List<Gear> gears,
            ArrayList<Double> progressivePointRPM,
            ArrayList<Double> progressivePointSpeed) {

        HashMap<Integer, Double> rpmSpeed1 = gears.get(0).getrRpmSpeeds();
        HashMap<Integer, Double> rpmSpeed5 = gears.get(4).getrRpmSpeeds();

        lineEntries.add(new Entry(0f, 0f));
        lineEntries.add(new Entry(rpmSpeed1.get(8000).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(0).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(0).floatValue(), progressivePointRPM.get(0).floatValue()));
        lineEntries.add(new Entry(progressivePointSpeed.get(1).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(1).floatValue(), progressivePointRPM.get(1).floatValue()));
        lineEntries.add(new Entry(progressivePointSpeed.get(2).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(2).floatValue(), progressivePointRPM.get(2).floatValue()));
        lineEntries.add(new Entry(progressivePointSpeed.get(3).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(3).floatValue(), progressivePointRPM.get(3).floatValue()));
        lineEntries.add(new Entry(rpmSpeed5.get(8000).floatValue(), 8000f));

        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(0).floatValue(), progressivePointRPM.get(0).floatValue()));
        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(1).floatValue(), progressivePointRPM.get(1).floatValue()));
        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(2).floatValue(), progressivePointRPM.get(2).floatValue()));
        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(3).floatValue(), progressivePointRPM.get(3).floatValue()));
    }

    public void constructData1(ArrayList<Entry> progressiveLineEntries, List<Gear> gears,
            ArrayList<Double> progressivePointRPM,
            ArrayList<Double> progressivePointSpeed) {

        HashMap<Integer, Double> rpmSpeed1 = gears.get(0).getrRpmSpeeds();
        HashMap<Integer, Double> rpmSpeed5 = gears.get(4).getrRpmSpeeds();

        lineEntries1.add(new Entry(0f, 0f));
        lineEntries1.add(new Entry(rpmSpeed1.get(8000).floatValue(), 8000f));
        lineEntries1.add(new Entry(progressivePointSpeed.get(0).floatValue(), 8000f));
        lineEntries1.add(new Entry(progressivePointSpeed.get(0).floatValue(), progressivePointRPM.get(0).floatValue()));
        lineEntries1.add(new Entry(progressivePointSpeed.get(1).floatValue(), 8000f));
        lineEntries1.add(new Entry(progressivePointSpeed.get(1).floatValue(), progressivePointRPM.get(1).floatValue()));
        lineEntries1.add(new Entry(progressivePointSpeed.get(2).floatValue(), 8000f));
        lineEntries1.add(new Entry(progressivePointSpeed.get(2).floatValue(), progressivePointRPM.get(2).floatValue()));
        lineEntries1.add(new Entry(progressivePointSpeed.get(3).floatValue(), 8000f));
        lineEntries1.add(new Entry(progressivePointSpeed.get(3).floatValue(), progressivePointRPM.get(3).floatValue()));
        lineEntries1.add(new Entry(rpmSpeed5.get(8000).floatValue(), 8000f));

        progressiveLineEntries1
                .add(new Entry(progressivePointSpeed.get(0).floatValue(), progressivePointRPM.get(0).floatValue()));
        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(1).floatValue(), progressivePointRPM.get(1).floatValue()));
        progressiveLineEntries1
                .add(new Entry(progressivePointSpeed.get(2).floatValue(), progressivePointRPM.get(2).floatValue()));
        progressiveLineEntries1
                .add(new Entry(progressivePointSpeed.get(3).floatValue(), progressivePointRPM.get(3).floatValue()));
    }

    public ArrayList<Entry> constructProgressiveLine(ArrayList<Double> progressivePointRPM, ArrayList<Double> progressivePointSpeed) {
        ArrayList<Entry> progressiveLineEntries = new ArrayList<Entry>();

        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(0).floatValue(), progressivePointRPM.get(0).floatValue()));
        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(1).floatValue(), progressivePointRPM.get(1).floatValue()));
        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(2).floatValue(), progressivePointRPM.get(2).floatValue()));
        progressiveLineEntries
                .add(new Entry(progressivePointSpeed.get(3).floatValue(), progressivePointRPM.get(3).floatValue()));

        return progressiveLineEntries;
    }

    public double calculateRPM(double speed, double ratio, double tireCircumference, double finalDrive) {
        return (speed * ratio * finalDrive) / (tireCircumference * 0.001 * 60);
    }

    public ArrayList<Entry> constData(List<Gear> gears, ArrayList<Double> progressivePointRPM,
            ArrayList<Double> progressivePointSpeed) {

        ArrayList<Entry> lineEntries = new ArrayList<Entry>();

        HashMap<Integer, Double> rpmSpeed1 = gears.get(0).getrRpmSpeeds();
        HashMap<Integer, Double> rpmSpeed5 = gears.get(4).getrRpmSpeeds();

        lineEntries.add(new Entry(0f, 0f));
        lineEntries.add(new Entry(rpmSpeed1.get(8000).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(0).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(0).floatValue(), progressivePointRPM.get(0).floatValue()));
        lineEntries.add(new Entry(progressivePointSpeed.get(1).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(1).floatValue(), progressivePointRPM.get(1).floatValue()));
        lineEntries.add(new Entry(progressivePointSpeed.get(2).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(2).floatValue(), progressivePointRPM.get(2).floatValue()));
        lineEntries.add(new Entry(progressivePointSpeed.get(3).floatValue(), 8000f));
        lineEntries.add(new Entry(progressivePointSpeed.get(3).floatValue(), progressivePointRPM.get(3).floatValue()));
        lineEntries.add(new Entry(rpmSpeed5.get(8000).floatValue(), 8000f));

        // progressiveLineEntries
        // .add(new Entry(progressivePointSpeed.get(0).floatValue(),
        // progressivePointRPM.get(0).floatValue()));
        // progressiveLineEntries
        // .add(new Entry(progressivePointSpeed.get(1).floatValue(),
        // progressivePointRPM.get(1).floatValue()));
        // progressiveLineEntries
        // .add(new Entry(progressivePointSpeed.get(2).floatValue(),
        // progressivePointRPM.get(2).floatValue()));
        // progressiveLineEntries
        // .add(new Entry(progressivePointSpeed.get(3).floatValue(),
        // progressivePointRPM.get(3).floatValue()));

        return lineEntries;
    }
}
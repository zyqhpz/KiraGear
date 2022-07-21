package com.example.kiragear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Gear;
import model.Tire;
import model.Tuning;

public class GeneratedGraphViewer extends AppCompatActivity {

    LineChart lineChart;
    ArrayList<Entry> lineEntries = new ArrayList<Entry>();
    ArrayList<Entry> progressiveLineEntries = new ArrayList<Entry>();

    ArrayList<Entry> lineEntries1 = new ArrayList<Entry>();
    ArrayList<Entry> progressiveLineEntries1 = new ArrayList<Entry>();

    Tire tire = new Tire();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_graph_viewer);

        Intent intent = new Intent();
//        tire = (Tire) intent.getParcelableExtra("tire");
        Bundle extras = getIntent().getExtras();

//        tire = extras.getParcelable("tire");

        tire.setWidth(extras.getInt("width"));
        tire.setAspectRatio(extras.getInt("aspectRatio"));
        tire.setDiameter(extras.getInt("diameter"));

//        tire.setWidth(getIntent().getParcelableExtra("tire"));

        lineChart = findViewById(R.id.line_chart);

        generateGraph();

        lineChart.setScaleEnabled(false);

        LineDataSet progressivelineDataSet = new LineDataSet(progressiveLineEntries, "Progressive Line");
        progressivelineDataSet.enableDashedLine(5, 10, 0);
        progressivelineDataSet.setLineWidth(2f);
        progressivelineDataSet.setColor(Color.BLUE);
        progressivelineDataSet.setDrawValues(false);
        progressivelineDataSet.setDrawCircles(false);

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Line Data");
        // lineDataSet.enableDashedLine(3, 20, 0);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(false);

        LineDataSet progressivelineDataSet1 = new LineDataSet(progressiveLineEntries1, "Progressive Line 1");
        progressivelineDataSet1.enableDashedLine(5, 10, 0);
        progressivelineDataSet1.setLineWidth(2f);
        progressivelineDataSet1.setColor(Color.RED);
        progressivelineDataSet1.setDrawValues(false);
        progressivelineDataSet1.setDrawCircles(false);

        LineDataSet lineDataSet1 = new LineDataSet(lineEntries1, "Line Data 2");
        lineDataSet1.setLineWidth(2f);
        lineDataSet1.setColors(Color.RED);
        lineDataSet1.setDrawValues(false);
        lineDataSet1.setDrawCircles(false);

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

        lineChart.getAxisRight().setEnabled(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);

//        lineChart.getXAxis().setLabelCount(5, /* force: */true);
        lineChart.getXAxis().setAxisMinimum(0);
        lineChart.getXAxis().setAxisMaximum(250);
    }

    public void generateGraph() {
        calculateGear();
        calculateGear1();
    }

    public void openNextPage() {
        Intent intent = new Intent(this, GearInputActivity.class);
        startActivity(intent);
    }

    public void calculateGear() {
        Gear gear = new Gear();
        Tuning tuning = new Tuning();

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
        lineEntries = constructData(gears, progressivePointRPM, progressivePointSpeed);
        progressiveLineEntries = constructProgressiveLine(progressivePointRPM, progressivePointSpeed);
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

        lineEntries1 = constructData(gears, progressivePointRPM, progressivePointSpeed);

        progressiveLineEntries1 = constructProgressiveLine(progressivePointRPM, progressivePointSpeed);
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

    public ArrayList<Entry> constructData(List<Gear> gears, ArrayList<Double> progressivePointRPM,
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


        return lineEntries;
    }
}
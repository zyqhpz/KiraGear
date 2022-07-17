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

    BarChart barChart;
    PieChart pieChart;
    LineChart lineChart;

    ArrayList<BarEntry> barEntries = new ArrayList<>();
    ArrayList<Entry> lineEntries = new ArrayList<Entry>();
    ArrayList<Entry> progressiveLineEntries = new ArrayList<Entry>();

    ArrayList<Double> progressivePointRPM = new ArrayList<>();
    ArrayList<Double> progressivePointSpeed = new ArrayList<>();

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

        barChart = findViewById(R.id.bar_chart);
        pieChart = findViewById(R.id.pie_chart);
        lineChart = findViewById(R.id.line_chart);

        calculateGear();

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

        BarDataSet barDataSet = new BarDataSet(barEntries, "Bar Data");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barDataSet.setDrawValues(false);

        barChart.setData(new BarData(barDataSet));

        barChart.animateY(1000);

        barChart.getDescription().setText("Bar Chart");
        barChart.getDescription().setTextColor(Color.WHITE);
        barChart.getAxisLeft().setTextColor(Color.WHITE);

        lineChart.setScaleEnabled(false);

        LineDataSet progressivelineDataSet = new LineDataSet(progressiveLineEntries, "Progressive Line");
        progressivelineDataSet.enableDashedLine(3, 20, 0);
        progressivelineDataSet.setLineWidth(2f);

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Line Data");
        // lineDataSet.enableDashedLine(3, 20, 0);
        lineDataSet.setLineWidth(3f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(progressivelineDataSet);

        // lineChart.setData(new LineData(lineDataSet));
        lineChart.setData(new LineData(dataSets));

        lineChart.getDescription().setText("Line Chart");
        lineChart.getDescription().setTextColor(Color.WHITE);
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

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

    public void calculateGear() {
        Tire tire = new Tire();
        Gear gear = new Gear();
        Tuning tuning = new Tuning();

        tire.setWidth(185);
        tire.setAspectRatio(50);
        tire.setDiameter(17);

        double tireCircumference = tire.getCircumference();

        // System.out.println(tireCircumference);

        List<Gear> gears = new ArrayList<Gear>();

        double[] ratio = { 2.976190476, 2.105263158, 1.615508885, 1.277139208, 1.030927835 };

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

        // for (int i = 0; i < gears.size(); i++) {
        // // barEntries.add(new BarEntry(i, (float) gears.get(i).getSpeed()));
        // lineEntries.add(new Entry(i, (float) gears.get(i).getSpeed()));
        // }

        for (int i = 0; i < gears.size(); i++) {
            if (i != 4) {
                List<Double> speed = gears.get(i).getSpeeds();

                // get last element of array
                double last = speed.get(speed.size() - 1);

                double rpm = calculateRPM(last, gears.get(i + 1).getRatio(), tireCircumference, finalDrive);

                System.out.println(last);
                System.out.println(rpm);
                progressivePointRPM.add(rpm);
                progressivePointSpeed.add(last);
            }

            // System.out.println(gears.get(i).getSpeed());
        }

        constructData(gears);

        // for (Gear g : gears) {
        // System.out.print(g.getRatio() + ": ");
        // for (Double d : g.getSpeeds()) {
        // System.out.print(d + " ");
        // }
        // System.out.println();
        // // System.out.print(g.getSpeeds());
        // }

    }

    public void constructData(List<Gear> gears) {

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

    public double calculateRPM(double speed, double ratio, double tireCircumference, double finalDrive) {
        return (speed * ratio * finalDrive) / (tireCircumference * 0.001 * 60);
    }
}
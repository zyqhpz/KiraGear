package com.example.kiragear;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    float highestX = 0;

    double[] ratio = new double[5];

    double[] tuning1Ratio = new double[5];
    double[] tuning2Ratio = new double[5];

    double finalDriveOne;
    double finalDriveTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_generated_graph_viewer);

        Bundle extras = getIntent().getExtras();
        tire.setWidth(extras.getInt("width"));
        tire.setAspectRatio(extras.getInt("aspectRatio"));
        tire.setDiameter(extras.getInt("diameter"));

        // get the final drive values
        finalDriveOne = extras.getDouble("finalDriveOne");
        finalDriveTwo = extras.getDouble("finalDriveTwo");

        boolean isSame = extras.getBoolean("isSame");

        if (isSame) {
            double ratio1 = extras.getDouble("gearOne");
            double ratio2 = extras.getDouble("gearTwo");
            double ratio3 = extras.getDouble("gearThree");
            double ratio4 = extras.getDouble("gearFour");
            double ratio5 = extras.getDouble("gearFive");

            // insert all ratio values into an array
            tuning1Ratio[0] = ratio1;
            tuning1Ratio[1] = ratio2;
            tuning1Ratio[2] = ratio3;
            tuning1Ratio[3] = ratio4;
            tuning1Ratio[4] = ratio5;

            generateGraph(true);
        } else {
            double tuningOneRatio1 = extras.getDouble("tuningOneGearOne");
            double tuningOneRatio2 = extras.getDouble("tuningOneGearTwo");
            double tuningOneRatio3 = extras.getDouble("tuningOneGearThree");
            double tuningOneRatio4 = extras.getDouble("tuningOneGearFour");
            double tuningOneRatio5 = extras.getDouble("tuningOneGearFive");

            double tuningTwoRatio1 = extras.getDouble("tuningTwoGearOne");
            double tuningTwoRatio2 = extras.getDouble("tuningTwoGearTwo");
            double tuningTwoRatio3 = extras.getDouble("tuningTwoGearThree");
            double tuningTwoRatio4 = extras.getDouble("tuningTwoGearFour");
            double tuningTwoRatio5 = extras.getDouble("tuningTwoGearFive");

            // insert all ratio values into an array
            tuning1Ratio[0] = tuningOneRatio1;
            tuning1Ratio[1] = tuningOneRatio2;
            tuning1Ratio[2] = tuningOneRatio3;
            tuning1Ratio[3] = tuningOneRatio4;
            tuning1Ratio[4] = tuningOneRatio5;

            tuning2Ratio[0] = tuningTwoRatio1;
            tuning2Ratio[1] = tuningTwoRatio2;
            tuning2Ratio[2] = tuningTwoRatio3;
            tuning2Ratio[3] = tuningTwoRatio4;
            tuning2Ratio[4] = tuningTwoRatio5;

            generateGraph(false);
        }

        Button saveGallery;
        saveGallery = findViewById(R.id.buttonSaveToGallery);

        lineChart = findViewById(R.id.line_chart);

        lineChart.setScaleEnabled(false);

        LineDataSet progressiveLineDataSet = new LineDataSet(progressiveLineEntries, "Progressive Line Tuning 1");
        progressiveLineDataSet.enableDashedLine(5, 10, 0);
        progressiveLineDataSet.setLineWidth(2f);
        progressiveLineDataSet.setColor(Color.parseColor("#0492c2"));
        progressiveLineDataSet.setDrawValues(false);
        progressiveLineDataSet.setDrawCircles(false);
        progressiveLineDataSet.setFormLineWidth(1f);
        progressiveLineDataSet.setForm(Legend.LegendForm.LINE);
        progressiveLineDataSet.setFormLineDashEffect(new DashPathEffect(new float[] { 10f, 5f }, 0f));
        progressiveLineDataSet.setFormSize(15.f);

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Tuning 1");
        lineDataSet.setLineWidth(2f);
        // #63c6da
        lineDataSet.setColor(Color.parseColor("#0492c2"));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setForm(Legend.LegendForm.LINE);

        LineDataSet progressiveLineDataSet1 = new LineDataSet(progressiveLineEntries1, "Progressive Line Tuning 2");
        progressiveLineDataSet1.enableDashedLine(5, 10, 0);
        progressiveLineDataSet1.setLineWidth(2f);
        progressiveLineDataSet1.setColor(Color.RED);
        progressiveLineDataSet1.setDrawValues(false);
        progressiveLineDataSet1.setDrawCircles(false);
        progressiveLineDataSet1.setFormLineWidth(1f);
        progressiveLineDataSet1.setForm(Legend.LegendForm.LINE);
        progressiveLineDataSet1.setFormLineDashEffect(new DashPathEffect(new float[] { 10f, 5f }, 0f));
        progressiveLineDataSet1.setFormSize(15.f);

        LineDataSet lineDataSet1 = new LineDataSet(lineEntries1, "Tuning 2");
        lineDataSet1.setLineWidth(2f);
        lineDataSet1.setColors(Color.RED);
        lineDataSet1.setDrawValues(false);
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setFormLineWidth(1f);
        lineDataSet1.setForm(Legend.LegendForm.LINE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet1);
        dataSets.add(progressiveLineDataSet);
        dataSets.add(progressiveLineDataSet1);

        lineChart.setData(new LineData(dataSets));

        lineChart.getDescription().setText("KiraGear");
        lineChart.getDescription().setTextColor(Color.BLACK);
        lineChart.getDescription().setTextSize(10f);
        lineChart.getAxisLeft().setTextColor(Color.BLACK);
        lineChart.getXAxis().setTextColor(Color.BLACK);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        ValueFormatter xAxisFormatter = new SpeedAxisValueFormatter(lineChart);
        lineChart.getXAxis().setValueFormatter(xAxisFormatter);

        ValueFormatter yAxisFormatter = new RpmAxisValueFormatter(lineChart);
        lineChart.getAxisLeft().setValueFormatter(yAxisFormatter);

        lineChart.getAxisRight().setEnabled(false);

        lineChart.fitScreen();

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);

        lineChart.getXAxis().setAxisMinimum(0);
        lineChart.getXAxis().setAxisMaximum(highestX + 20);

        lineChart.animateXY(1500, 500);

        // Legend
        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setWordWrapEnabled(true);
        // legend.setDrawInside(false);

        // // get current timestamp
        // Calendar c = Calendar.getInstance();
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String formattedDate = df.format(c.getTime());

        saveGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get current timestamp
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // separate date and time
                String[] dateTime = df.format(c.getTime()).split(" ");
                String date = dateTime[0];
                String time = dateTime[1];
                String formattedDate = df.format(c.getTime());

                String fileName = date + "_" + time;

//                Bitmap.CompressFormat
                // lineChart.saveToGallery("LineChart" + date + "_" + time + ".jpg", "KiraGear", "KiraGear", Bitmap.CompressFormat.JPEG  , 100)

                if (lineChart.saveToGallery("LineChart" + date + "_" + time + ".jpg", 95)) {
                    Toast.makeText(getBaseContext(), "Chart has been saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Saving failed", Toast.LENGTH_SHORT).show();

                }
//                lineChart.saveTo
//                lineChart.saveToPath(fileName + ".jpg", "/Internal storage/Download/");
            }
        });

    }

    public void generateGraph(boolean isSame) {
        calculateGear();
        calculateGear1(isSame);
    }

    public void calculateGear() {
        Tuning tuning = new Tuning();

        ArrayList<Double> progressivePointRPM = new ArrayList<>();
        ArrayList<Double> progressivePointSpeed = new ArrayList<>();

        double tireCircumference = tire.getCircumference();

        List<Gear> gears = new ArrayList<Gear>();

        for (int i = 0; i < tuning1Ratio.length; i++) {
            Gear gear = new Gear();
            gear.setRatio(tuning1Ratio[i]);
            gears.add(gear);
        }

        // double finalDrive = 4.0816;
        double finalDrive = finalDriveOne;

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

    public void calculateGear1(boolean isSame) {
        Gear gear = new Gear();
        Tuning tuning = new Tuning();

        ArrayList<Double> progressivePointRPM = new ArrayList<>();
        ArrayList<Double> progressivePointSpeed = new ArrayList<>();

        double tireCircumference = tire.getCircumference();

        List<Gear> gears = new ArrayList<Gear>();

        if (isSame) {
            for (int i = 0; i < tuning1Ratio.length; i++) {
                gear = new Gear();
                gear.setRatio(tuning1Ratio[i]);
                gears.add(gear);
            }
        } else {
            for (int i = 0; i < tuning2Ratio.length; i++) {
                gear = new Gear();
                gear.setRatio(tuning2Ratio[i]);
                gears.add(gear);
            }
        }

        double finalDrive = finalDriveTwo;

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

    public ArrayList<Entry> constructProgressiveLine(ArrayList<Double> progressivePointRPM,
            ArrayList<Double> progressivePointSpeed) {
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

        if (rpmSpeed5.get(8000).floatValue() > highestX) {
            highestX = rpmSpeed5.get(8000).floatValue();
        }

        return lineEntries;
    }

    public class SpeedAxisValueFormatter extends ValueFormatter {
        // private final BarLineChartBase<?> chart;
        private final LineChart chart;

        public SpeedAxisValueFormatter(LineChart chart) {
            this.chart = chart;
        }

        @Override
        public String getFormattedValue(float value) {
            return (int) value + "km/h";
        }
    }

    public class RpmAxisValueFormatter extends ValueFormatter {
        private final LineChart chart;

        public RpmAxisValueFormatter(LineChart chart) {
            this.chart = chart;
        }

        @Override
        public String getFormattedValue(float value) {
            // String number = "1000500000.574";
            // Str
            double amount = Double.parseDouble(String.valueOf(value));
            DecimalFormat formatter = new DecimalFormat("#,###");

            // System.out.println(formatter.format(amount));
            return formatter.format(amount) + "RPM";
        }
    }
}
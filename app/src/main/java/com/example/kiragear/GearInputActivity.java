package com.example.kiragear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class GearInputActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gear_input);

        TextView tireWidthLabel = (TextView) findViewById(R.id.tireWidthText);
        tireWidthLabel.setTextColor(Color.BLACK);

        TextView tireAspectRatioLabel = (TextView) findViewById(R.id.tireAspectRatioText);
        tireAspectRatioLabel.setTextColor(Color.BLACK);

        TextView tireDiameterLabel = (TextView) findViewById(R.id.tireDiameterText);
        tireDiameterLabel.setTextColor(Color.BLACK);

        int[] tireWidthData = { 145, 155, 160, 165, 175, 180, 185, 195, 205, 215, 225, 235, 245 };
        int[] tireAspectRatioData = { 60, 65, 70, 75 };

        ArrayList<Integer> tireWidthList = new ArrayList<>();
        ArrayList<Integer> tireAspectRatioList = new ArrayList<>();
        ArrayList<Integer> tireDiameterList = new ArrayList<>();

        for (int i = 0; i < tireWidthData.length; i++) {
            tireWidthList.add(tireWidthData[i]);
        }

        for (int i = 0; i < tireAspectRatioData.length; i++) {
            tireAspectRatioList.add(tireAspectRatioData[i]);
        }

        for (int i = 12; i < 25; i++) {
            tireDiameterList.add(i);
        }

        Spinner spinnerWidth = (Spinner) findViewById(R.id.spinnerTireWidth);
        spinnerWidth.setBackgroundColor(Color.BLACK);

        Spinner spinnerAspectRatio = (Spinner) findViewById(R.id.spinnerTireAspectRatio);
        spinnerAspectRatio.setBackgroundColor(Color.BLACK);

        Spinner spinnerDiameter = (Spinner) findViewById(R.id.spinnerTireDiameter);
        spinnerDiameter.setBackgroundColor(Color.BLACK);

        ArrayAdapter<Integer> adapterWidth = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                tireWidthList);
        adapterWidth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWidth.setAdapter(adapterWidth);

        ArrayAdapter<Integer> adapterAspectRatio = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                tireAspectRatioList);
        adapterAspectRatio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAspectRatio.setAdapter(adapterAspectRatio);

        ArrayAdapter<Integer> adapterDiameter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                tireDiameterList);
        adapterDiameter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiameter.setAdapter(adapterDiameter);

        final int[] tireWidth = { 0 };
        final int[] tireAspectRatio = { 0 };
        final int[] tireDiameter = { 0 };

        spinnerWidth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            int item;

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                tireWidth[0] = tireWidthList.get(position).intValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        spinnerAspectRatio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                tireAspectRatio[0] = tireAspectRatioList.get(position).intValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        spinnerDiameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                tireDiameter[0] = tireDiameterList.get(position).intValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        button = (Button) findViewById(R.id.btnGenerateGraph);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText gear1 = findViewById(R.id.gearOneRatioInput);
                double gearOneRatio = Double.parseDouble(gear1.getText().toString());
                ArrayList<Double> gearRatios = new ArrayList<Double>();
                gearRatios.add(gearOneRatio);
                EditText gear2 = findViewById(R.id.gearTwoRatioInput);
                gearRatios.add(Double.parseDouble(gear2.getText().toString()));
                EditText gear3 = findViewById(R.id.gearThreeRatioInput);
                gearRatios.add(Double.parseDouble(gear3.getText().toString()));
                EditText gear4 = findViewById(R.id.gearFourRatioInput);
                gearRatios.add(Double.parseDouble(gear4.getText().toString()));
                EditText gear5 = findViewById(R.id.gearFiveRatioInput);
                gearRatios.add(Double.parseDouble(gear5.getText().toString()));

                ArrayList<Double> finalDrives = new ArrayList<Double>();
                EditText finalDrive1 = findViewById(R.id.finalDriveTuning1Input);
                finalDrives.add(Double.parseDouble(finalDrive1.getText().toString()));
                EditText finalDrive2 = findViewById(R.id.finalDriveTuning2Input);
                finalDrives.add(Double.parseDouble(finalDrive2.getText().toString()));

                generateGraph(tireWidth[0], tireAspectRatio[0], tireDiameter[0], gearRatios, finalDrives);
            }
        });

    }

    public void generateGraph(int width, int aspectRatio, int diameter, ArrayList<Double> gearRatios,
            ArrayList<Double> finalDrives) {
        Intent intent = new Intent(this, GeneratedGraphViewer.class);
        intent.putExtra("width", width);
        intent.putExtra("aspectRatio", aspectRatio);
        intent.putExtra("diameter", diameter);

        intent.putExtra("gearOne", gearRatios.get(0));
        intent.putExtra("gearTwo", gearRatios.get(1));
        intent.putExtra("gearThree", gearRatios.get(2));
        intent.putExtra("gearFour", gearRatios.get(3));
        intent.putExtra("gearFive", gearRatios.get(4));

        intent.putExtra("finalDriveOne", finalDrives.get(0));
        intent.putExtra("finalDriveTwo", finalDrives.get(1));

        startActivity(intent);
    }
}
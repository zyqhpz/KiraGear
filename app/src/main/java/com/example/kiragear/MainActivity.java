package com.example.kiragear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // force to disable DarkMode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_gear_input);

        TextView tireWidthLabel = (TextView) findViewById(R.id.tireWidthText);
        tireWidthLabel.setTextColor(Color.BLACK);

        TextView tireAspectRatioLabel = (TextView) findViewById(R.id.tireAspectRatioText);
        tireAspectRatioLabel.setTextColor(Color.BLACK);

        TextView tireDiameterLabel = (TextView) findViewById(R.id.tireDiameterText);
        tireDiameterLabel.setTextColor(Color.BLACK);

        int[] tireWidthData = { 145, 155, 160, 165, 175, 180, 185, 195, 205, 215, 225, 235, 245 };
        int[] tireAspectRatioData = { 35, 40, 45, 50, 55, 60, 65, 70, 75 };

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
        spinnerWidth.setBackgroundColor(Color.WHITE);

        Spinner spinnerAspectRatio = (Spinner) findViewById(R.id.spinnerTireAspectRatio);
        spinnerAspectRatio.setBackgroundColor(Color.WHITE);

        Spinner spinnerDiameter = (Spinner) findViewById(R.id.spinnerTireDiameter);
        spinnerDiameter.setBackgroundColor(Color.WHITE);

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
        button.setEnabled(false);

        EditText gear1 = findViewById(R.id.tuningOneGearOneRatioInput);
        // gear1.setText("Disabled");
        // gear1.setFocusable(false);
        // gear1.setEnabled(false);

        EditText gear2 = findViewById(R.id.tuningOneGearTwoRatioInput);
        EditText gear3 = findViewById(R.id.tuningOneGearThreeRatioInput);
        EditText gear4 = findViewById(R.id.tuningOneGearFourRatioInput);
        EditText gear5 = findViewById(R.id.tuningOneGearFiveRatioInput);

        List<EditText> tuningTwo = new ArrayList<EditText>();

        tuningTwo = setTuningTwo();

        EditText finalDrive1 = findViewById(R.id.finalDriveTuning1Input);
        EditText finalDrive2 = findViewById(R.id.finalDriveTuning2Input);

        ArrayList<EditText> gearsInput = new ArrayList<EditText>(Arrays.asList(gear1, gear2, gear3, gear4, gear5));

        Switch switchIsSameGearRatio = (Switch) findViewById(R.id.switchTuningOneTwoSameRatio);

        List<EditText> finalTuningTwo = tuningTwo;
        switchIsSameGearRatio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getBaseContext(), "Same Ratio On", Toast.LENGTH_SHORT).show();
                    isSameGearRatio(finalTuningTwo, true);
                } else {
                    Toast.makeText(getBaseContext(), "Same Ratio Off", Toast.LENGTH_SHORT).show();
                    isSameGearRatio(finalTuningTwo, false);
                }
            }
        });

        for (EditText gearInput : gearsInput) {
            gearInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    CharSequence s1 = gear1.getText().toString().trim();
                    CharSequence s2 = gear2.getText().toString().trim();
                    CharSequence s3 = gear3.getText().toString().trim();
                    CharSequence s4 = gear4.getText().toString().trim();
                    CharSequence s5 = gear5.getText().toString().trim();

                    if (s1.length() > 0 && s2.length() > 0 && s3.length() > 0 && s4.length() > 0 && s5.length() > 0) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Double> gearRatios = new ArrayList<Double>();
                gearRatios.add(Double.parseDouble(gear1.getText().toString()));
                gearRatios.add(Double.parseDouble(gear2.getText().toString()));
                gearRatios.add(Double.parseDouble(gear3.getText().toString()));
                gearRatios.add(Double.parseDouble(gear4.getText().toString()));
                gearRatios.add(Double.parseDouble(gear5.getText().toString()));

                ArrayList<Double> finalDrives = new ArrayList<Double>();
                finalDrives.add(Double.parseDouble(finalDrive1.getText().toString()));
                finalDrives.add(Double.parseDouble(finalDrive2.getText().toString()));

                generateGraph(tireWidth[0], tireAspectRatio[0], tireDiameter[0], gearRatios, finalDrives);
            }
        });

    }

    public ArrayList<EditText> setTuningTwo() {
        ArrayList<EditText> tuningTwo = new ArrayList<EditText>();
        EditText gear1 = findViewById(R.id.tuningTwoGearOneRatioInput);
        EditText gear2 = findViewById(R.id.tuningTwoGearTwoRatioInput);
        EditText gear3 = findViewById(R.id.tuningTwoGearThreeRatioInput);
        EditText gear4 = findViewById(R.id.tuningTwoGearFourRatioInput);
        EditText gear5 = findViewById(R.id.tuningTwoGearFiveRatioInput);

        tuningTwo.add(gear1);
        tuningTwo.add(gear2);
        tuningTwo.add(gear3);
        tuningTwo.add(gear4);
        tuningTwo.add(gear5);

        return tuningTwo;
    }

    public void isSameGearRatio(List<EditText> tuningTwo, boolean isSame) {
        if (isSame) {
            for (EditText gearInput : tuningTwo) {
                gearInput.setText("Disabled");
                gearInput.setEnabled(false);
                gearInput.setFocusable(false);
                gearInput.setTextColor(Color.parseColor("#808080"));
            }
        } else {
            for (EditText gearInput : tuningTwo) {
                gearInput.setText("");
                gearInput.setEnabled(true);
                gearInput.setFocusable(true);
                gearInput.setTextColor(Color.parseColor("#808080"));
            }
        }
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
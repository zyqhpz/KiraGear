package com.example.kiragear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import model.Tire;

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

        ArrayList<String> list = new ArrayList<String>(); // make this as field atribute

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
                // String item = list.get(position).toString();
                item = tireWidthList.get(position).intValue();
                // String item = parent.getItemAtPosition(position).toString();
                tireWidth[0] = item;
                System.out.println(tireWidth[0]);

                // tireWidth[0] = item;

                // Showing selected spinner item
                // Toast.makeText(context, "Selected: " + item, Toast.LENGTH_LONG).show();
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
                generateGraph(tireWidth[0], tireAspectRatio[0], tireDiameter[0]);
            }
        });

    }

    public void generateGraph(int width, int aspectRatio, int diameter) {
        Intent intent = new Intent(this, GeneratedGraphViewer.class);
        intent.putExtra("width", width);
        intent.putExtra("aspectRatio", aspectRatio);
        intent.putExtra("diameter", diameter);
        startActivity(intent);
    }
}
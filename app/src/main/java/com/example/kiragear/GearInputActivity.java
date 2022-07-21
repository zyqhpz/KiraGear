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

        // ArrayList<Integer> tireWidthList = []
        int[] tireWidthData = { 145, 155, 160, 165, 175, 180, 185, 195, 205, 215, 225, 235, 245 };
        int[] tireAspectRatioData = { 60, 65, 70, 75 };
        // int[] tireDiameterData = {}

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
        list.add("A");
        list.add("B");
        list.add("C");
        Spinner s = (Spinner) findViewById(R.id.spinnerTireWidth);
        s.setBackgroundColor(Color.BLACK);
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        // android.R.layout.simple_spinner_item, list);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                tireWidthList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);

        final int[] tireWidth = {0};

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            int item;

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                String item = list.get(position).toString();
                item = tireWidthList.get(position).intValue();
                // String item = parent.getItemAtPosition(position).toString();
                tireWidth[0] = item;
                System.out.println(tireWidth[0]);

//                tireWidth[0] = item;

                // Showing selected spinner item
                // Toast.makeText(context, "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        button = (Button) findViewById(R.id.btnGenerateGraph);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateGraph(tireWidth[0]);
            }
        });

    }

    public void generateGraph(int width) {
        Intent intent = new Intent(this, GeneratedGraphViewer.class);
        intent.putExtra("width", width);
        intent.putExtra("aspectRatio", 50);
        intent.putExtra("diameter", 17);
        startActivity(intent);
    }
}
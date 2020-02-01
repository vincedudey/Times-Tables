package com.example.timestables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView timesTable;

    public void generateTimesTable(final int timesTableNumber){
        final ArrayList<Integer> multiples = new ArrayList<Integer>();
        for(int num=1; num<101; ++num)multiples.add(timesTableNumber*num);

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, multiples);
        timesTable.setAdapter(arrayAdapter);

        timesTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), timesTableNumber + " X " + (i+1) + " = "+ multiples.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar seekTimesTable = findViewById(R.id.seekTimesTable);
        timesTable = findViewById(R.id.timesTable);
        int max = 20;
        int startingPosition = 10;

        seekTimesTable.setMax(max);
        seekTimesTable.setProgress(startingPosition);

        generateTimesTable(startingPosition);

        seekTimesTable.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min = 1;
                int timesTableNumber;

                if(i<min){
                    timesTableNumber = min;
                    seekTimesTable.setProgress(min);
                }
                else timesTableNumber = i;

                generateTimesTable(timesTableNumber);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}

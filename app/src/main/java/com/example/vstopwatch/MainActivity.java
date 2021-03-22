package com.example.vstopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button startBtn, resetBtn, lapbtn;
    TextView timeView;
    Handler customHandler = new Handler();
    LinearLayout ll;

    Stopwatch stopwatch = new Stopwatch();

    Runnable updateLayout = new Runnable() {
        @Override
        public void run() {
            DecimalFormat dc2 = new DecimalFormat("#00");
            DecimalFormat dc3 = new DecimalFormat("#000");
            timeView.setText(dc2.format(stopwatch.Hour) + ":" + dc2.format(stopwatch.Minute)
            + ":" + dc2.format(stopwatch.Second) + ":" + dc3.format(stopwatch.Millisecond));

            customHandler.postDelayed(this,10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        resetBtn = findViewById(R.id.resetBtn);
        lapbtn = findViewById(R.id.lapBtn);
        timeView = findViewById(R.id.timeView);
        ll = findViewById(R.id.lapRecord);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!stopwatch.isRunning){
                    stopwatch.Start();
                    startBtn.setText("Stop");
                    customHandler.postDelayed(updateLayout,0);
                }else {
                    stopwatch.Stop();
                    startBtn.setText("Start");
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.Reset();
            }
        });

        lapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = new TextView(ll.getContext());
                tv.setText(timeView.getText());
                ll.addView(tv);
            }
        });
    }
}
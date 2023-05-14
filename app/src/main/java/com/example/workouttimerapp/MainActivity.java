package com.example.workouttimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button controlBtn = findViewById(R.id.controlButton);
        TextView countdownText = findViewById(R.id.remainingTime);
        EditText timeInputText = findViewById(R.id.timeInput);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        EditText roundText = findViewById(R.id.pharse_input);
        EditText resetText = findViewById(R.id.preriod_input);

        new MainPage(controlBtn, timeInputText, countdownText, roundText, resetText, progressBar);
    }
}
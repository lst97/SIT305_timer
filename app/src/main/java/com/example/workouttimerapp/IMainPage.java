package com.example.workouttimerapp;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public interface IMainPage {

    void setControlBtn(Button controlBtn);

    void setTimeText(EditText timeText);

    TextView getCountdownText();
    void setCountdownText(TextView countdownText);

    void setProgressBar(ProgressBar progressBar);

    void start(int second);
    void end();
}


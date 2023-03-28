package com.example.workouttimerapp;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

public class PageData {
    private final MainPage mainPage;
    private CountDownTimer countDownTimer;

    private int targetedTime;

    PageData(MainPage mainPage){
        this.mainPage = mainPage;
    }

    public void start(int millisecond){
        if (countDownTimer == null){
            this.targetedTime = millisecond;
            newTimer();
        }
        this.countDownTimer.start();
    }

    public void end(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void newTimer(){
        this.countDownTimer = createTimer(targetedTime);
    }

    private CountDownTimer createTimer(int millisecond){
        return new CountDownTimer(millisecond, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                int percentage = Math.abs(100 - (int)((millisUntilFinished * 100.0f / targetedTime )));
                mainPage.setProgressbarValue(percentage);
                mainPage.getCountdownText().setText(secondsRemaining + " S");
            }

            @Override
            public void onFinish() {
                end();
                mainPage.reset();
            }
        };
    }
}

package com.example.workouttimerapp;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

public class PageData {
    private final MainPage mainPage;
    private CountDownTimer countDownTimer;

    private int targetedTime;
    private int round;
    private int resetTime;
    private boolean isReset = false;

    public void setTargetedTime(int targetedTime) {
        this.targetedTime = targetedTime;
    }
    public void setRound(int round) {
        this.round = round;
    }

    public void setResetTime(int resetTime) {
        this.resetTime = resetTime;
    }

    PageData(MainPage mainPage){
        this.mainPage = mainPage;
    }

    public void end(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void newTimer(int millisecond){
        this.countDownTimer = createTimer(millisecond);
        this.countDownTimer.start();
    }

    private CountDownTimer createTimer(int millisecond){
        return new CountDownTimer(millisecond, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                int percentage = (int) (100 - (secondsRemaining * 100 / targetedTime));
                mainPage.setProgressbarValue(percentage);
                mainPage.getCountdownText().setText(secondsRemaining + " S");
            }

            @Override
            public void onFinish() {
                // if the round is not 0, start the reset timer
                if (round != 0){
                    if (isReset){
                        isReset = false;
                        mainPage.getCountdownText().setText("Round " + round);
                        mainPage.setProgressbarValue(0);
                        countDownTimer = null;
                        newTimer(targetedTime * 1000);
                    }else{
                        round--;
                        isReset = true;
                        mainPage.getCountdownText().setText("Reset");
                        mainPage.setProgressbarValue(0);
                        countDownTimer = null;
                        newTimer(resetTime * 1000);
                    }
                    return;
                }
                end();
                mainPage.reset();
            }
        };
    }
}

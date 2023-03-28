package com.example.workouttimerapp;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainPage implements IMainPage {
    private Button controlBtn;
    private EditText timeText;
    private TextView countdownText;
    private ProgressBar progressBar;
    private PageData data;
    private Boolean clickFlag;

    public void reset(){
        clickFlag = false;
        controlBtn.setText(R.string.btn_start);
        countdownText.setText(R.string.text_timeup);
        progressBar.setProgress(0, true);
    }

    @Override
    public void setControlBtn(Button controlBtn) {
        this.controlBtn = controlBtn;
    }

    @Override
    public void setTimeText(EditText timeText) {
        this.timeText = timeText;
    }

    @Override
    public TextView getCountdownText() {
        return countdownText;
    }

    @Override
    public void setCountdownText(TextView countdownText) {
        this.countdownText = countdownText;
    }

    @Override
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void start(int second) {
        data.start(second * 1000);
    }

    @Override
    public void end() {
        data.end();
    }

    public void setProgressbarValue(int value){
        if (value >= 0 && value <= 100){
            progressBar.setProgress(value, true);
        }
    }
    private void initContent(){
        this.countdownText.setText("0 S");
        this.controlBtn.setText(R.string.btn_start);
    }

    private void initCallbacks(){
        this.controlBtn.setOnClickListener(view -> {
            int targetedTime = 0;
            try{
                targetedTime =Integer.parseInt(timeText.getText().toString());
            }catch (NumberFormatException e){
                countdownText.setText(R.string.text_invalid);
            }

            if (targetedTime > 0 && targetedTime < 1410065408){
                if(!clickFlag){
                    controlBtn.setText(R.string.btn_stop);
                    start(targetedTime);
                }else{
                    controlBtn.setText(R.string.btn_start);
                    end();
                }
                clickFlag = !clickFlag;
            }else {
                countdownText.setText(R.string.text_invalid);
            }
        });
    }

    private void initData(){
        // timer need to modify the TextView and need to use reset()
        this.data = new PageData(this);
    }

    MainPage(Button controlBtn, EditText timeText, TextView countdownText, ProgressBar progressBar){
        setControlBtn(controlBtn);
        setCountdownText(countdownText);
        setTimeText(timeText);
        setProgressBar(progressBar);
        initContent();
        initCallbacks();
        initData();
        reset();
    }
}

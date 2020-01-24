package ru.sk1ly.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import static ru.sk1ly.firstapp.Constants.*;

public class StopwatchActivity extends Activity {

    private boolean mStopwatchIsRunning = false;
    private int mStopwatchSeconds = 0;
    private TextView mStopwatchTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        mStopwatchTime = findViewById(R.id.stopwatch_time);
        if (savedInstanceState != null) {
            mStopwatchIsRunning = savedInstanceState.getBoolean(Keys.STOPWATCH_STATUS);
            mStopwatchSeconds = savedInstanceState.getInt(Keys.STOPWATCH_SECONDS);
        }
        runStopwatch();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Keys.STOPWATCH_STATUS, mStopwatchIsRunning);
        outState.putInt(Keys.STOPWATCH_SECONDS, mStopwatchSeconds);
    }

    public void onClickStartStopwatchButton(View view) {
        mStopwatchIsRunning = true;
    }

    public void onClickStopStopwatchButton(View view) {
        mStopwatchIsRunning = false;
    }

    public void onClickRefreshStopwatchButton(View view) {
        mStopwatchIsRunning = false;
        mStopwatchSeconds = 0;
        calculateAndSetTime();
    }

    private void runStopwatch() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mStopwatchIsRunning) {
                    calculateAndSetTime();
                    mStopwatchSeconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void calculateAndSetTime() {
        int hours = mStopwatchSeconds / 3600;
        int minutes = (mStopwatchSeconds % 3600) / 60;
        int seconds = mStopwatchSeconds % 60;
        String time = String.format(Locale.getDefault(), "%d:%02d:%02d",
                hours, minutes, seconds);
        mStopwatchTime.setText(time);
    }
}

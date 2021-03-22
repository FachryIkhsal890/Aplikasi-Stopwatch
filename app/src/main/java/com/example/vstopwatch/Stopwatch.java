package com.example.vstopwatch;

import android.os.Handler;
import android.os.SystemClock;

import androidx.appcompat.widget.MenuItemHoverListener;

public class Stopwatch {

    private long timeInMillisecond, timerMillisecond, pauseOffSet;
    private int _Second, _Minute, _Hour;
    public int Millisecond, Second, Minute, Hour;
    public Boolean isRunning = false;

    Handler customHandler = new Handler();

    Runnable updateTime = new Runnable() {
        @Override
        public void run() {
            timerMillisecond = SystemClock.uptimeMillis() - timeInMillisecond ;
            Millisecond = (int)(timerMillisecond % 1000);
            _Second = (int)(timerMillisecond / 1000);
            _Minute = Second / 60;
            Hour = Minute / 60;
            Second = _Second % 60;
            Minute = _Minute % 60;

            customHandler.postDelayed(this,0);
        }
    };

    void StartNew(){
        timeInMillisecond = SystemClock.uptimeMillis();
        pauseOffSet = 0;
        isRunning = true;
        customHandler.postDelayed(updateTime,0);
    }

    void Start(){
        timeInMillisecond = SystemClock.uptimeMillis() + (timeInMillisecond - pauseOffSet);
        isRunning = true;
        customHandler.postDelayed(updateTime,0);
    }

    void Stop(){
        pauseOffSet = SystemClock.uptimeMillis();
        isRunning = false;
        customHandler.removeCallbacks(updateTime);
    }

    void Reset(){
        Millisecond = Second = Minute = Hour = 0;
        timeInMillisecond = SystemClock.uptimeMillis();
        pauseOffSet = timeInMillisecond;
    }

}

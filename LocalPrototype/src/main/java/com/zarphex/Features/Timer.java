package com.zarphex.Features;

import java.time.Duration;

public class Timer {
    private final int TIME_DECREMENT = 1;
    private Duration currentTimer, setTimer;
    private String currentAlarmSound;
    private boolean isTimerPaused = false;

    public Timer() {
        this.currentTimer = Duration.ZERO;
    }

    public void startTimer() {
        if (!isTimerPaused) {
            currentTimer = currentTimer.minusSeconds(TIME_DECREMENT);
        }
    }
    public void pauseTimer() {
        isTimerPaused = !isTimerPaused;
    }
    public void resetTimer() {
        currentTimer = setTimer;
    }
    public void restartTimer() {
        currentTimer = Duration.ZERO;
    }
    public void setCurrentAlarmSound(String alarm) {}
}

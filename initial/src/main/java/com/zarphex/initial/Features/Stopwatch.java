package com.zarphex.initial.Features;
import java.time.*;
import java.util.ArrayList;

public class Stopwatch {
    private final int TIME_INCREMENT = 1;
    private Duration currentTime;
    private ArrayList<Duration> stopwatchLaps;

    public Stopwatch() {
        this.currentTime = Duration.ZERO;
    }

    public void lapTime() {
        stopwatchLaps.add(currentTime);
    }
    public void startStopwatch() {
        currentTime.plusSeconds(TIME_INCREMENT);
    }
    public void resetStopwatch() {
        currentTime = Duration.ZERO;
    }
}

package com.zarphex.Features;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class Stopwatch extends Feature {
    private final int TIME_INCREMENT = 1;
    private Duration currentTime;
    private ArrayList<Duration> stopwatchLaps;

    public Stopwatch(Properties props) {
        super(props);
        this.currentTime = Duration.ZERO;

        createGUI();
    }

    @Override
    public void createGUI() {
        super.createGUI();
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

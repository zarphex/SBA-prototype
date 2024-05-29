package com.zarphex.Features;

import java.time.Duration;
import java.util.Properties;

public class PomodoroTimer extends Feature {
    private final int DEFAULT_WORK_TIMER = 25;
    private final int DEFAULT_SHORT_BREAK = 5;
    private final int DEFAULT_LONG_BREAK = 10;
    private final int DEFAULT_CYCLE_INTERVAL = 5;
    private Duration shortBreakLength, longBreakLength, workLength, currentTimer;
    private int numberCyclesInterval;
    private String breakAlarmSound;

    public PomodoroTimer(Properties props) {
        super(props);
        this.shortBreakLength = Duration.ofMinutes(DEFAULT_SHORT_BREAK);
        this.longBreakLength = Duration.ofMinutes(DEFAULT_LONG_BREAK);
        this.workLength = Duration.ofMinutes(DEFAULT_WORK_TIMER);
        this.numberCyclesInterval = DEFAULT_CYCLE_INTERVAL;
        createGUI();
    }
    public PomodoroTimer(Properties props, int work, int shortBreak, int longBreak, int interval) {
        super(props);
        this.workLength = Duration.ofMinutes(work);
        this.shortBreakLength = Duration.ofMinutes(shortBreak);
        this.longBreakLength = Duration.ofMinutes(longBreak);
        this.numberCyclesInterval = interval;
    }

    @Override
    public void createGUI() {
        super.createGUI();
    }
}

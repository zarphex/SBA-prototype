package com.zarphex.initial.Features;
import java.time.*;

public class PomodoroTimer extends Timer {
    private final int DEFAULT_WORK_TIMER = 25;
    private final int DEFAULT_SHORT_BREAK = 5;
    private final int DEFAULT_LONG_BREAK = 10;
    private final int DEFAULT_CYCLE_INTERVAL = 5;
    private Duration shortBreakLength, longBreakLength, workLength, currentTimer;
    private int numberCyclesInterval;
    private String breakAlarmSound;

    public PomodoroTimer() {}

    public void startBreak() {}
}
